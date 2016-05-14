package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.mapper.CustomInfoMapper;
import com.yttx.yttxps.mapper.CustomOperMapper;
import com.yttx.yttxps.mapper.DictMapper;
import com.yttx.yttxps.mapper.RoomMapper;
import com.yttx.yttxps.mapper.SysOperMapper;
import com.yttx.yttxps.mapper.TCloselistMapper;
import com.yttx.yttxps.mapper.TRestaurantMapper;
import com.yttx.yttxps.xml.bean.closeList.Car;
import com.yttx.yttxps.xml.bean.closeList.CostDetails;
import com.yttx.yttxps.xml.bean.closeList.Entertainment;
import com.yttx.yttxps.xml.bean.closeList.IncomeDetails;
import com.yttx.yttxps.xml.bean.closeList.Other;
import com.yttx.yttxps.model.CustomInfo;
import com.yttx.yttxps.model.CustomOper;
import com.yttx.yttxps.model.CustomOperKey;
import com.yttx.yttxps.model.Dict;
import com.yttx.yttxps.model.DictKey;
import com.yttx.yttxps.model.Room;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.TCloselist;
import com.yttx.yttxps.model.TCloselistExample;
import com.yttx.yttxps.model.TOrderCustomWithBLOBs;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;
import com.yttx.yttxps.model.TRestaurant;
import com.yttx.yttxps.service.ICloselistService;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;
import com.yttx.yttxps.xml.bean.Body;
import com.yttx.yttxps.xml.bean.Cclist;
import com.yttx.yttxps.xml.bean.Daylist;
import com.yttx.yttxps.xml.bean.Reslist;
import com.yttx.yttxps.xml.bean.Root;
import com.yttx.yttxps.xml.bean.closeList.Accomadation;
import com.yttx.yttxps.xml.bean.closeList.Restaurant;
import com.yttx.yttxps.xml.bean.closeList.Shop;
import com.yttx.yttxps.xml.bean.closeList.Ticket;


@Service("closelistService")
public class CloselistService implements ICloselistService {

	@Autowired
	private IPubService<TCloselist> pubService;
	
	@Autowired
	private RoomMapper<Room> roomMapper;
	
	@Autowired
	private TRestaurantMapper<Restaurant> restaurantMapper;
	
	@Autowired
	private CustomInfoMapper customInfoMapper;
	
	@Autowired
	private CustomOperMapper customOperMapper;
	
	@Autowired
	private SysOperMapper<SysOper> sysOperMapper;
	
	@Autowired
	private DictMapper dictMapper;
	
	@Autowired
	private TCloselistMapper<TCloselist> closelistMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return closelistMapper.selectCountSelective(map);
	}

	@Override
	public List<TCloselist> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, closelistMapper);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Boolean creatCloseList(TOrderlistWithBLOBs orderList, List<TOrderCustomWithBLOBs> orderCustomList) throws Exception {
		TCloselist closeList = new TCloselist();
		closeList.setFsNo(orderList.getFsNo());
		closeList.setFsOrderId(orderList.getFsNo());
		closeList.setFsName(orderList.getFsName());
		closeList.setFsUserId(orderList.getFsUserId());
		closeList.setFsUserSubid(orderList.getFsUserSubid());
		closeList.setFsOperId(orderList.getFsOperId());
		closeList.setFtCreatdate(new Date());
		closeList.setFdTotalfee(orderList.getFdTotalfee());
		if(orderList.getFdPaidamt() == null) {
			orderList.setFdPaidamt(new BigDecimal(0));
		}
		closeList.setFdPaidamt(orderList.getFdPaidamt());
		closeList.setFdAmt(BigDecimal.ZERO);
		closeList.setFsRemark(orderList.getFsRemark());
		closeList.setFiStat(new BigDecimal(-10));
		
		com.yttx.yttxps.xml.bean.closeList.Body closeBody = new com.yttx.yttxps.xml.bean.closeList.Body();
		
		CustomOperKey customOperKey = new CustomOperKey();
		customOperKey.setId(orderList.getFsUserId());
		customOperKey.setSubid(orderList.getFsUserSubid());
		CustomOper customOper = customOperMapper.selectByPrimaryKey(customOperKey);
		CustomInfo customInfo = customInfoMapper.selectByPrimaryKey(orderList.getFsUserId());
		SysOper sysOper = sysOperMapper.selectByPrimaryKey(orderList.getFsOperId() + "    ");
		
		closeBody.setTname(customInfo.getTaname());
		closeBody.setCustopername(customOper.getName());
		closeBody.setOpername(sysOper.getSysOperName().trim());
		closeBody.setVisitornum(orderList.getFiVisitornum().toString());
		closeBody.setIncome(BigDecimal.ZERO);
		closeBody.setProfit(BigDecimal.ZERO);
		closeBody.setMinproceeds(BigDecimal.ZERO);
		closeBody.setCustexpend(BigDecimal.ZERO);
		closeBody.setCustprofit(BigDecimal.ZERO);
		
		List<Body> bodyList = new ArrayList<Body>();
		
		Root orderRoot = ResScheduleXMLConverter.fromXml("http://www.cnacex.com/", orderList.getFcCommressnapshot(), Root.class);
		bodyList.add(orderRoot.getBody());
		
		for (TOrderCustomWithBLOBs orderCustom : orderCustomList) {
			Root customRoot = ResScheduleXMLConverter.fromXml("http://www.cnacex.com/", orderCustom.getFcRessnapshot(), Root.class);
			bodyList.add(customRoot.getBody());
		}
		
		dataTransfer(orderList, bodyList, closeBody);
		calculateTotalPrice(closeBody);
		
		com.yttx.yttxps.xml.bean.closeList.Root closeRoot = new com.yttx.yttxps.xml.bean.closeList.Root(closeBody);
		ResScheduleXMLConverter.toXml("http://www.yttx.co/", closeRoot);
		closeList.setFcContent(ResScheduleXMLConverter.toXml("http://www.yttx.co/", closeRoot));
		closelistMapper.insert(closeList);
		return null;
	}
	
	protected void dataTransfer(TOrderlistWithBLOBs orderList, List<Body> bodyList, com.yttx.yttxps.xml.bean.closeList.Body closeBody) {
		Restaurant restaurant = new Restaurant();
		List<com.yttx.yttxps.xml.bean.closeList.Reslist> restReslist = new ArrayList<com.yttx.yttxps.xml.bean.closeList.Reslist>();
		restaurant.setReslist(restReslist);
		
		Ticket ticket = new Ticket();
		List<com.yttx.yttxps.xml.bean.closeList.Reslist> ticketReslist = new ArrayList<com.yttx.yttxps.xml.bean.closeList.Reslist>();
		ticket.setReslist(ticketReslist);
		
		Car car = new Car();
		List<com.yttx.yttxps.xml.bean.closeList.Reslist> carReslist = new ArrayList<com.yttx.yttxps.xml.bean.closeList.Reslist>();
		car.setReslist(carReslist);
		
		Accomadation accomadation = new Accomadation();
		List<com.yttx.yttxps.xml.bean.closeList.Reslist> accoReslist = new ArrayList<com.yttx.yttxps.xml.bean.closeList.Reslist>();
		accomadation.setReslist(accoReslist);
		
		Other otherCost = new Other();
		List<com.yttx.yttxps.xml.bean.closeList.Reslist> otherReslist = new ArrayList<com.yttx.yttxps.xml.bean.closeList.Reslist>();
		otherCost.setReslist(otherReslist);
		
		com.yttx.yttxps.xml.bean.closeList.Reslist insuranceResList = new com.yttx.yttxps.xml.bean.closeList.Reslist();
		insuranceResList.setTypeno("01");
		insuranceResList.setName("保险");
		insuranceResList.setNumber(orderList.getFiVisitornum() == null ? "0" : orderList.getFiVisitornum().toString());//待订单修改
		insuranceResList.setTotalprice(orderList.getFdInsuerprice() == null ? "0" : orderList.getFdInsuerprice().toString());//待订单修改
		otherReslist.add(insuranceResList);
		
		for (Body body : bodyList) {
			if(body.getReslist() != null && body.getReslist().size() > 0) {
				for (Reslist reslist : body.getReslist()) {
					if(reslist.getRestype().equals("cx")) {   //车
						com.yttx.yttxps.xml.bean.closeList.Reslist cxResList = new com.yttx.yttxps.xml.bean.closeList.Reslist();
						Cclist cxlist = reslist.getCclist().get(0);
						cxResList.setResno(reslist.getResno());
						cxResList.setName(reslist.getResname());
						cxResList.setUnitprice(cxlist.getPrice().toString());
						cxResList.setNumber(cxlist.getUsernum());
						cxResList.setTotalprice((cxlist.getPrice().multiply(new BigDecimal(cxlist.getUsernum()))).toString());
						cxResList.setRemark(reslist.getRemark());
						carReslist.add(cxResList);
					} else if(reslist.getRestype().equals("dy")) {   //导游
						com.yttx.yttxps.xml.bean.closeList.Reslist dyResList = new com.yttx.yttxps.xml.bean.closeList.Reslist();
						Cclist dylist = reslist.getCclist().get(0);
						dyResList.setTypeno("02");
						dyResList.setName(reslist.getResname());
						dyResList.setUnitprice(dylist.getPrice().toString());
						dyResList.setNumber(dylist.getUsernum());
						dyResList.setTotalprice((dylist.getPrice().multiply(new BigDecimal(dylist.getUsernum()))).toString());
						dyResList.setRemark(reslist.getRemark());
						otherReslist.add(dyResList);
					}
				}
			}
			for (Daylist daylist : body.getDaylist()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(orderList.getFtStartdate());
				cal.add(Calendar.DATE,Integer.parseInt(daylist.getDayflag()));
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = formatter.format(cal.getTime());
				if(daylist.getReslist() != null) {
					for (Reslist reslist : daylist.getReslist()) {
						if(reslist.getRestype().equals("mp")) {   //门票
							com.yttx.yttxps.xml.bean.closeList.Reslist mpResList = new com.yttx.yttxps.xml.bean.closeList.Reslist();
							Cclist mplist = reslist.getCclist().get(0);
							mpResList.setResno(reslist.getResno());
							mpResList.setName(reslist.getResname());
							mpResList.setType(mplist.getCcno());
							mpResList.setTypename(mplist.getCcname());
							mpResList.setUnitprice(mplist.getPrice().toString());
							mpResList.setNumber(mplist.getUsernum());
							mpResList.setTotalprice((mplist.getPrice().multiply(new BigDecimal(mplist.getUsernum()))).toString());
							mpResList.setRemark(reslist.getRemark());
							ticketReslist.add(mpResList);
						} else if(reslist.getRestype().equals("ct")) {   //餐
							com.yttx.yttxps.xml.bean.closeList.Reslist ctResList = new com.yttx.yttxps.xml.bean.closeList.Reslist();
							Cclist ctlist = reslist.getCclist().get(0);
							ctResList.setResno(reslist.getResno());
							ctResList.setName(reslist.getResname());
							TRestaurant tmp = restaurantMapper.selectByPrimaryKey(reslist.getResno());
							ctResList.setType(tmp == null ? "" : tmp.getSpecial());
							ctResList.setTime(startDate);
							ctResList.setUnitprice(ctlist.getPrice().toString());
							ctResList.setNumber(ctlist.getUsernum());
							ctResList.setTotalprice((ctlist.getPrice().multiply(new BigDecimal(ctlist.getUsernum()))).toString());
							ctResList.setRemark(reslist.getRemark());
							restReslist.add(ctResList);
						} else if(reslist.getRestype().equals("bg")) {   //房
							com.yttx.yttxps.xml.bean.closeList.Reslist bgResList = new com.yttx.yttxps.xml.bean.closeList.Reslist();
							Cclist bglist = reslist.getCclist().get(0);
							Room tmp = roomMapper.selectByPrimaryKey(reslist.getResno());
							bgResList.setAccomno(tmp == null ? "" : tmp.getFsAccomno());
							bgResList.setName(reslist.getResname());
							Dict dict = null;
							if(tmp != null) {
								DictKey dictKey = new DictKey();
								dictKey.setFsDictno(tmp.getFsType());
								dictKey.setFsParentno("fx");
								dict = dictMapper.selectByPrimaryKey(dictKey);
							}
							bgResList.setRoomtypeno(dict == null ? "" : dict.getFsDictno());
							bgResList.setRoomtype(dict == null ? "" : dict.getFsDictname());
							bgResList.setTime(startDate);
							bgResList.setUnitprice(bglist.getPrice().toString());
							bgResList.setNumber(bglist.getUsernum());
							bgResList.setTotalprice((bglist.getPrice().multiply(new BigDecimal(bglist.getUsernum()))).toString());
							bgResList.setRemark(reslist.getRemark());
							accoReslist.add(bgResList);
						}
					}
				}
			}
		}
		
		CostDetails costDetails = new CostDetails();
		costDetails.setAccomadation(accomadation);
		costDetails.setCar(car);
		costDetails.setOther(otherCost);
		costDetails.setRestaurant(restaurant);
		costDetails.setTicket(ticket);
		closeBody.setCostdetails(costDetails);
		
		IncomeDetails incomeDetails = new IncomeDetails();
		Shop shop = new Shop();
		shop.setTotal("0");
		Entertainment entertainment = new Entertainment();
		entertainment.setTotal("0");
		Other otherIncome = new Other();
		otherIncome.setTotal("0");
		incomeDetails.setShop(shop);
		incomeDetails.setEntertainment(entertainment);
		incomeDetails.setOther(otherIncome);
		closeBody.setIncomedetails(incomeDetails);
	}
	
	protected void calculateTotalPrice(com.yttx.yttxps.xml.bean.closeList.Body closeBody) {
		CostDetails costDetails = closeBody.getCostdetails();
		
		Accomadation accomadation = costDetails.getAccomadation();
		Car car = costDetails.getCar();
		Restaurant restaurant = costDetails.getRestaurant();
		Ticket ticket = costDetails.getTicket();
		Other other = costDetails.getOther();
		
		BigDecimal accoTotalPrice = new BigDecimal(0);
		for (com.yttx.yttxps.xml.bean.closeList.Reslist reslist : accomadation.getReslist()) {
			accoTotalPrice = accoTotalPrice.add(new BigDecimal(reslist.getTotalprice()));
		}
		BigDecimal carTotalPrice = new BigDecimal(0);
		for (com.yttx.yttxps.xml.bean.closeList.Reslist reslist : car.getReslist()) {
			carTotalPrice = carTotalPrice.add(new BigDecimal(reslist.getTotalprice()));
		}
		BigDecimal restTotalPrice = new BigDecimal(0);
		for (com.yttx.yttxps.xml.bean.closeList.Reslist reslist : restaurant.getReslist()) {
			restTotalPrice = restTotalPrice.add(new BigDecimal(reslist.getTotalprice()));
		}
		BigDecimal ticketTotalPrice = new BigDecimal(0);
		for (com.yttx.yttxps.xml.bean.closeList.Reslist reslist : ticket.getReslist()) {
			ticketTotalPrice = ticketTotalPrice.add(new BigDecimal(reslist.getTotalprice()));
		}
		BigDecimal otherTotalPrice = new BigDecimal(0);
		for (com.yttx.yttxps.xml.bean.closeList.Reslist reslist : other.getReslist()) {
			otherTotalPrice = otherTotalPrice.add(new BigDecimal(reslist.getTotalprice()));
		}
		accomadation.setTotal(accoTotalPrice.toString());
		car.setTotal(carTotalPrice.toString());
		restaurant.setTotal(restTotalPrice.toString());
		ticket.setTotal(ticketTotalPrice.toString());
		other.setTotal(otherTotalPrice.toString());
		
		BigDecimal totalPrice = otherTotalPrice.add(ticketTotalPrice).add(restTotalPrice).add(carTotalPrice).add(accoTotalPrice);
		closeBody.setExpenditure(totalPrice);
	}
	
	@Override
	public TCloselist selectByOrderId(String orderId) {
		return closelistMapper.selectByPrimaryKey(orderId);
	}
	
	@Override
	public int insert(TCloselist record) {
		return closelistMapper.insert(record);
	}

	@Override
	public int update(TCloselist record) {
		return closelistMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return closelistMapper.deleteByPrimaryKey(no);
	}

	@Override
	public List<TCloselist> selectTOrderlist(TCloselistExample example) {
		// TODO Auto-RouteArrangeerated method stub
		return closelistMapper.selectByExampleWithBLOBs(example);
	}
	
}
