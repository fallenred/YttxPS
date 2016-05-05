package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.comm.Constants.OrderStat;
import com.yttx.yttxps.mapper.TOrderCustomMapper;
import com.yttx.yttxps.mapper.TOrderlistMapper;
import com.yttx.yttxps.mapper.TRemarksMapper;
import com.yttx.yttxps.model.TOrderCustomWithBLOBs;
import com.yttx.yttxps.model.TOrderlist;
import com.yttx.yttxps.model.TOrderlistExample;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;
import com.yttx.yttxps.model.TRemarks;
import com.yttx.yttxps.model.TRemarksExample;
import com.yttx.yttxps.model.TRemarksExample.Criteria;
import com.yttx.yttxps.service.IMsgService;
import com.yttx.yttxps.service.IOrderlistService;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;
import com.yttx.yttxps.xml.bean.Body;
import com.yttx.yttxps.xml.bean.Daylist;
import com.yttx.yttxps.xml.bean.Reslist;
import com.yttx.yttxps.xml.bean.Root;


@Service("orderlistService")
public class OrderlistService implements IOrderlistService {

	@Autowired
	private IPubService<TOrderlistWithBLOBs> pubService;
	
	@Autowired
	private IMsgService msgService;
	
	@Autowired
	private TOrderlistMapper<TOrderlistWithBLOBs> orderlistMapper;
	
	@Autowired
	private TOrderCustomMapper<TOrderCustomWithBLOBs> orderCustomMapper;

	@Autowired
	private TRemarksMapper<TRemarks> remarksMapper;
	
	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return orderlistMapper.selectCountSelective(map);
	}

	@Override
	public List<TOrderlistWithBLOBs> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, orderlistMapper);
	}

	@Override
	public int insert(TOrderlistWithBLOBs record) {
		return orderlistMapper.insert(record);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int update(TOrderlistWithBLOBs record) throws Exception {
		//处理状态
		handleStat(record);
		//公共精确资源:orderlist-fc_Schedule
		Body commBody = new Body();
		//处理body-reslist
		commBody.setReslist(handleResListIndex(record.getCommBody().getReslist()));
		if (CollectionUtils.isNotEmpty(record.getCommBody().getDaylist())) {
			for (Daylist daylist : record.getCommBody().getDaylist()){
				//处理body-daylist-reslist
				daylist.setReslist(handleResListIndex(daylist.getReslist()));
			}
			commBody.setDaylist(record.getCommBody().getDaylist());
			commBody.setCommprice(record.getCommBody().getCommprice());
		}
		//将公共精确资源转换为xml
		ResScheduleXMLConverter.toXml("http://www.cnacex.com/", commBody);
		String fcCommressnapshot = ResScheduleXMLConverter.toXml("http://www.cnacex.com/", new Root(commBody));
		record.setFcCommressnapshot(fcCommressnapshot);
		
		//批次精确资源:ordercustom-fc_ResSnapshot
		if (CollectionUtils.isNotEmpty(record.getBatchBody())){
			for (int i = 0; i < record.getBatchBody().size(); i++) {
				Body body = record.getBatchBody().get(i);
				for(Daylist daylist : body.getDaylist()){
					daylist.setReslist(handleResListIndex(daylist.getReslist()));
				}
				TOrderCustomWithBLOBs customWithBLOBs = new TOrderCustomWithBLOBs();
				ResScheduleXMLConverter.toXml("http://www.cnacex.com/", body);
				String fcRessnapshot = ResScheduleXMLConverter.toXml("http://www.cnacex.com/", new Root(body));
				customWithBLOBs.setFcRessnapshot(fcRessnapshot);
				customWithBLOBs.setFiId(new BigDecimal(body.getFiId()));
				customWithBLOBs.setFdAmt(record.getBatchAmt().get(i));
				orderCustomMapper.updateByPrimaryKeySelective(customWithBLOBs);
			}
		}
		
		//先删除订单备注
		//更新订单备注
		if (CollectionUtils.isNotEmpty(record.getRemarks())) {
			TRemarksExample example = new TRemarksExample();
			Criteria criteria = example.createCriteria();
			criteria.andFsOrderIdEqualTo(record.getFsNo());
			remarksMapper.deleteByExample(example);
			for (int i = 0; i < record.getRemarks().size(); i++) {
				TRemarks remarks = record.getRemarks().get(i);
				if (remarks.getFdAmt() == null) continue;
				remarks.setFsOrderId(record.getFsNo());
				remarks.setFsOperId(record.getFsOperId());
				remarks.setFiSeq(new BigDecimal(i));
				remarks.setFiClosestat(BigDecimal.ZERO);
				if (remarks.getFiStat().compareTo(BigDecimal.ONE) == 0) {
					remarks.setFdPaidamt(remarks.getFdAmt());
				} else {
					remarks.setFdPaidamt(BigDecimal.ZERO);
				}
				remarksMapper.insertSelective(remarks);
//				msgService.saveMsg(remarks, record.getFsOperId());
			}
		}
		return orderlistMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int update4custom(TOrderlistWithBLOBs record) throws Exception {
		//处理订单状态
		handleStat(record);
		//询价快照
		Body scheduleBody = record.getScheduleBody();
		//设置早餐、午餐、晚餐默认值
		for(Daylist daylist : scheduleBody.getDaylist()){
			if (StringUtils.isEmpty(daylist.getBreakfast())) daylist.setBreakfast("0");
			if (StringUtils.isEmpty(daylist.getLunch())) daylist.setLunch("0");
			if (StringUtils.isEmpty(daylist.getDinner())) daylist.setDinner("0");
		}
		ResScheduleXMLConverter.toXml("http://www.cnacex.com/", scheduleBody);
		String fcSchedule = ResScheduleXMLConverter.toXml("http://www.cnacex.com/", new Root(scheduleBody));
		record.setFcSchedule(fcSchedule);
		
		//公共精确资源:orderlist-fc_Schedule
		Body commBody = new Body();
		//处理body-reslist
		commBody.setReslist(handleResListIndex(record.getCommBody().getReslist()));
		if (CollectionUtils.isNotEmpty(record.getCommBody().getDaylist())) {
			for (Daylist daylist : record.getCommBody().getDaylist()){
				//处理body-daylist-reslist
				daylist.setReslist(handleResListIndex(daylist.getReslist()));
			}
			commBody.setDaylist(record.getCommBody().getDaylist());
			commBody.setCommprice(record.getCommBody().getCommprice());
		}
		//将公共精确资源转换为xml
		ResScheduleXMLConverter.toXml("http://www.cnacex.com/", commBody);
		String fcCommressnapshot = ResScheduleXMLConverter.toXml("http://www.cnacex.com/", new Root(commBody));
		record.setFcCommressnapshot(fcCommressnapshot);
		
		//批次精确资源:ordercustom-fc_ResSnapshot
		if (CollectionUtils.isNotEmpty(record.getBatchBody())){
			for (int i = 0; i < record.getBatchBody().size(); i++) {
				Body body = record.getBatchBody().get(i);
				for(Daylist daylist : body.getDaylist()){
					daylist.setReslist(handleResListIndex(daylist.getReslist()));
				}
				TOrderCustomWithBLOBs customWithBLOBs = new TOrderCustomWithBLOBs();
				ResScheduleXMLConverter.toXml("http://www.cnacex.com/", body);
				String fcRessnapshot = ResScheduleXMLConverter.toXml("http://www.cnacex.com/", new Root(body));
				customWithBLOBs.setFcRessnapshot(fcRessnapshot);
				customWithBLOBs.setFiId(new BigDecimal(body.getFiId()));
				customWithBLOBs.setFdAmt(record.getBatchAmt().get(i));
				orderCustomMapper.updateByPrimaryKeySelective(customWithBLOBs);
			}
		}
		//先删除订单备注
		//更新订单备注
		if (CollectionUtils.isNotEmpty(record.getRemarks())) {
			TRemarksExample example = new TRemarksExample();
			Criteria criteria = example.createCriteria();
			criteria.andFsOrderIdEqualTo(record.getFsNo());
			remarksMapper.deleteByExample(example);
			for (int i = 0; i < record.getRemarks().size(); i++) {
				TRemarks remarks = record.getRemarks().get(i);
				if (remarks.getFdAmt() == null) continue;
				remarks.setFsOrderId(record.getFsNo());
				remarks.setFsOperId(record.getFsOperId());
				remarks.setFiSeq(new BigDecimal(i));
				if (remarks.getFiStat().compareTo(BigDecimal.ONE) == 0) {
					remarks.setFdPaidamt(remarks.getFdAmt());
				} else {
					remarks.setFdPaidamt(BigDecimal.ZERO);
				}
				remarks.setFiClosestat(BigDecimal.ZERO);
				remarksMapper.insertSelective(remarks);
//				msgService.saveMsg(remarks,record.getFsOperId());
				
			}
		}
		//预估费用
		if (StringUtils.isNotBlank(scheduleBody.getPrice())){
			record.setFdPrice(new BigDecimal(scheduleBody.getPrice()));
		}
		if("-5".equals(record.getFiStat()))
			record.setFdTotalfee(record.getFdPrice());
		return orderlistMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String fsNo) {
		TOrderlistWithBLOBs record = new TOrderlistWithBLOBs();
		record.setFsNo(fsNo);
		record.setFiStat("-100");//设置为已删除状态
		return orderlistMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<TOrderlistWithBLOBs> selectTOrderlist(TOrderlistExample example) {
		// TODO Auto-RouteArrangeerated method stub
		return orderlistMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public TOrderlistWithBLOBs selectByPrimaryKey(String fsNo) {
		// TODO Auto-generated method stub
		return orderlistMapper.selectByPrimaryKey(fsNo);
	}
	
	//处理reslist中跳跃的节点（如list含三个元素，下标为0、2、3）
	private List<Reslist> handleResListIndex(List<Reslist> obj){
		List<Reslist> list = new ArrayList<Reslist>();
		if (CollectionUtils.isNotEmpty(obj)) {
			for (Reslist r : obj) {
				if (CollectionUtils.isNotEmpty(r.getCclist())){
					list.add(r);
				}
			}
		}
		return list;
	}
	//处理daylist中跳跃的节点（如list含三个元素，下标为0、2、3）
	@SuppressWarnings("unused")
	private List<Daylist> handleDayListIndex(List<Daylist> obj){
		List<Daylist> list = new ArrayList<Daylist>();
		if (CollectionUtils.isNotEmpty(obj)) {
			for (Daylist d : obj) {
				if (CollectionUtils.isNotEmpty(d.getReslist())){
					list.add(d);
				}
			}
		}
		return list;
	}
	
	/**
	 * 处理订单状态
	 * @param orderlist
	 */
	private void handleStat(TOrderlist orderlist) {
		if (StringUtils.isBlank(orderlist.getFiStat())) {
			orderlist.setFiStat(null);
			return;
		}
		BigDecimal stat = new BigDecimal(orderlist.getFiStat());
		//询价状态时
		if (OrderStat.INQUIRY.getVal().compareTo(stat) == 0) {
			//修改状态为报价
			orderlist.setFiStat(OrderStat.OFFER.getVal().toString());
		}
		//待审核状态时
		if (OrderStat.WAITCONFIRM.getVal().compareTo(stat) == 0) {
			//修改状态为已审核
			orderlist.setFiStat(OrderStat.AUDITED.getVal().toString());
		}
	}
		
}
