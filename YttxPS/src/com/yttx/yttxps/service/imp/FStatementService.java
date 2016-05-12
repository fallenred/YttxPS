package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.except.BusinessException;
import com.yttx.yttxps.mapper.CustomOperMapper;
import com.yttx.yttxps.mapper.FStatementMapper;
import com.yttx.yttxps.model.CustomOper;
import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.service.IFStatementService;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;
import com.yttx.yttxps.xml.bean.closeList.Body;
import com.yttx.yttxps.xml.bean.closeList.CostDetails;
import com.yttx.yttxps.xml.bean.closeList.IncomeDetails;
import com.yttx.yttxps.xml.bean.closeList.Reslist;
import com.yttx.yttxps.xml.bean.closeList.Root;
import com.yttx.yttxps.xml.bean.closeList.Shop;
import com.yttx.yttxps.xml.bean.closeList.Stuff;

/**
 * 类描述：结算单相关service
 * @author sunchao
 * @date 2016年2月26日 上午11:13:12
 */
@Service("fStatementService")
public class FStatementService implements IFStatementService{
	
	@Autowired
	private IPubService<FStatement> pubService;
	
	@Autowired
	private FStatementMapper<FStatement>  fStatementMapper;

	@Override
	public List<FStatement> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, fStatementMapper);
	}
	
	/**
	 * 通过订单号找到结算单
	 */
	@Override
	public FStatement findFStatByOrderid(String orderid) {
		return fStatementMapper.selectFSByOrderId(orderid);
	}
	
	/**
	 * 通过结算单号找到结算单
	 */
	@Override
	public FStatement findFStatByFSId(String fsId) {
		return fStatementMapper.selectFSById(fsId);
	}

	@Override
	public boolean updateSelectiveFs(FStatement fStatement) {
		fStatementMapper.updateFSSelective(fStatement);
		return true;
	}

	/**
	 * 结算完毕更新
	 */
	@Override
	public boolean confrimFs(FStatement fStatement) {
		String fsId = fStatement.getStatmentId();
		FStatement fS =fStatementMapper.selectFSById(fsId);
		if(fS.getStat()==0){
			throw new RuntimeException("该结算单正等待客户确认！");
		}else if(fS.getStat()==2){
			throw new RuntimeException("该结算单已结算完毕！");
		}else{
			fStatementMapper.updateFSSelective(fStatement);
		}
		return true;
	}

	@Override
	public boolean editFStatement(FStatement fStatement) {
		FStatement fsFromDB = fStatementMapper.selectFSById(fStatement.getStatmentId());
		if(fsFromDB.getStat()!=0){
			throw new RuntimeException("该结算单已被客户确认或已结算完毕，不能修改");
		}
		fStatement.setCreatDate(fStatementMapper.getCurrentSysdate());
		fStatementMapper.updateFSSelective(fStatement);
		return true;
	}

	@Override
	public void saveCloselist(Root root, String orderid, String stat) throws Exception{
		FStatement fStatement = this.fStatementMapper.selectFSByOrderId(orderid);
		if (fStatement == null) {
			throw new BusinessException("结算单信息不存在");
		}
		Root origRoot = ResScheduleXMLConverter.fromXml("www.yttx.co", fStatement.getOrderContent(), Root.class);
		Shop shop = origRoot.getBody().getIncomedetails().getShop();
		Body body = root.getBody();
		CostDetails costDetails = body.getCostdetails();
		IncomeDetails incomeDetails = body.getIncomedetails();
		BigDecimal income = BigDecimal.ZERO;		//总收入
		BigDecimal shopTotal = new BigDecimal(shop.getTotal());	//购物收入
		BigDecimal entertainmentTotal = new BigDecimal(incomeDetails.getEntertainment().getTotal());	//娱乐收入
		BigDecimal otherTotal = new BigDecimal(incomeDetails.getOther().getTotal());	//其他收入
		income = shopTotal.add(entertainmentTotal).add(otherTotal);
		body.setIncome(income.toString());
		BigDecimal expenditure = BigDecimal.ZERO;	//总支出
		BigDecimal carTotal = new BigDecimal(costDetails.getCar().getTotal());	//车辆支出
		BigDecimal accomadationTotal = new BigDecimal(costDetails.getAccomadation().getTotal());//酒店支出
		BigDecimal restaurant = new BigDecimal(costDetails.getRestaurant().getTotal());	//餐饮支出
		BigDecimal ticketTotal = new BigDecimal(costDetails.getTicket().getTotal());	//门票支出
		BigDecimal otherTotal1 = new BigDecimal(costDetails.getOther().getTotal());	//其他支出
		expenditure = carTotal.add(accomadationTotal).add(restaurant).add(ticketTotal).add(otherTotal1);
		body.setExpenditure(expenditure.toString());
		BigDecimal profit = BigDecimal.ZERO;		//总利润
		profit = income.subtract(profit);
		body.setProfit(profit.toString());
		root.getBody().getIncomedetails().setShop(shop);
		String content = ResScheduleXMLConverter.toXml("www.yttx.co", root);
		//清楚空节点
		content = content.replace("<reslist/>", "");
		fStatement.setOrderContent(content);
		//设置结算单状态
		if (StringUtils.isNotBlank(stat)){
			fStatement.setStat(Long.parseLong(stat));
		}
		this.fStatementMapper.updateFSSelective(fStatement);
		
	}
	
	@Override
	public Shop addShopReslist(Shop shop, String orderid) {
		// TODO Auto-generated method stub
		FStatement fStatement = fStatementMapper.selectFSByOrderId(orderid);
		if (fStatement == null) {
			throw new BusinessException("购物信息保存失败");
		}
		Root root = ResScheduleXMLConverter.fromXml("www.yttx.co", fStatement.getOrderContent(), Root.class);
		//cclist不为空，清理cclist下标为空null的节点
		Reslist reslist = shop.getReslist().get(0);
		if (CollectionUtils.isNotEmpty(reslist.getCclist())){
			List<Stuff> list = reslist.getCclist();
			Iterator<Stuff> it = list.iterator();
			List<Stuff> cclist = new ArrayList<Stuff>();
			while (it.hasNext()) {
				Stuff stuff = it.next();
				if (StringUtils.isNotBlank(stuff.getTypeno())) {
					cclist.add(stuff);
				}
			}
			reslist.setCclist(cclist);
		}
		IncomeDetails incomeDetails = root.getBody().getIncomedetails();
		if (incomeDetails == null || incomeDetails.getShop() == null) {
			incomeDetails = new IncomeDetails();
			incomeDetails.setShop(shop);
			shop.setTotal(reslist.getTotalprofit());
			root.getBody().setIncomedetails(incomeDetails);
		} else {
			Shop origShop = incomeDetails.getShop();
			//遍历原始购物店list，替换id相同的购物店信息
			List<Reslist> reslists = origShop.getReslist();
			BigDecimal total = BigDecimal.ZERO;
			List<Reslist> list = new ArrayList<Reslist>();
			boolean isAdd = true;
			if (CollectionUtils.isNotEmpty(reslists)) {
				for (int i = 0; i < reslists.size(); i++) {
					Reslist reslist2 = reslists.get(i);
					//如果新增的购物店id与content中购物店id相同，则为修改
					if (reslist.getResno().equals(reslist2.getResno())) {
						list.add(reslist);
						isAdd = false;	//设置状态为修改
					} else {
						list.add(reslist2);
					}
					BigDecimal totalprofit = new BigDecimal(list.get(i).getTotalprofit());//打单总利润
					BigDecimal peopleprofit = new BigDecimal(list.get(i).getPeopleprofit());//人头费
					//总利润
					total = total.add(totalprofit).add(peopleprofit);
				}
			}
			//新增
			if (isAdd) {
				list.add(reslist);
				BigDecimal totalprofit = new BigDecimal(reslist.getTotalprofit());//打单总利润
				BigDecimal peopleprofit = new BigDecimal(reslist.getPeopleprofit());//人头费
				total = total.add(totalprofit).add(peopleprofit);
			}
			origShop.setReslist(list);
			origShop.setTotal(total.toString());
			shop = origShop;
		}
		try {
			String content = ResScheduleXMLConverter.toXml("www.yttx.co", root).replace("<cclist/>", "");
			fStatement.setOrderContent(content);
			this.fStatementMapper.updateFSSelective(fStatement);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shop;
	}

	@Override
	public Shop delShopReslist(String orderid, String resno) throws Exception {
		// TODO Auto-generated method stub
		FStatement fStatement = fStatementMapper.selectFSByOrderId(orderid);
		if (fStatement == null) {
			throw new BusinessException("购物信息删除失败");
		}
		Root root = ResScheduleXMLConverter.fromXml("www.yttx.co", fStatement.getOrderContent(), Root.class);
		Shop shop = root.getBody().getIncomedetails().getShop();
		List<Reslist> list = shop.getReslist();
		if (CollectionUtils.isNotEmpty(list)) {
			//遍历购物店reslist
			Iterator<Reslist> it = list.iterator();
			List<Reslist> reslists = new ArrayList<Reslist>();
			BigDecimal total = BigDecimal.ZERO;
			while(it.hasNext()){
				Reslist reslist = it.next();
				//如果resno不同，则添加到reslists中
				if (!resno.equals(reslist.getResno())) {
					reslists.add(reslist);
					//计算总利润
					total = total.add(new BigDecimal(reslist.getTotalprofit()).add(new BigDecimal(reslist.getPeopleprofit())));
				}
			}
			shop.setTotal(total.toString());
			shop.setReslist(reslists);
		}
		String xml = ResScheduleXMLConverter.toXml("www.yttx.co", root);
		fStatement.setOrderContent(xml);
		this.fStatementMapper.updateFSSelective(fStatement);
		return shop;
	}
	
}
