package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TOrderCustomMapper;
import com.yttx.yttxps.mapper.TOrderlistMapper;
import com.yttx.yttxps.model.TOrderCustomExample;
import com.yttx.yttxps.model.TOrderCustomExample.Criteria;
import com.yttx.yttxps.model.TOrderCustomWithBLOBs;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;
import com.yttx.yttxps.service.IOrderCustomService;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;
import com.yttx.yttxps.xml.bean.Body;
import com.yttx.yttxps.xml.bean.Cclist;
import com.yttx.yttxps.xml.bean.Daylist;
import com.yttx.yttxps.xml.bean.Reslist;
import com.yttx.yttxps.xml.bean.Root;


@Service("orderCustomService")
public class OrderCustomService implements IOrderCustomService {

	@Autowired
	private IPubService<TOrderCustomWithBLOBs> pubService;
	
	@Autowired
	private TOrderCustomMapper<TOrderCustomWithBLOBs> orderCustomMapper;
	
	@Autowired
	private TOrderlistMapper<TOrderlistWithBLOBs> orderlistMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return orderCustomMapper.selectCountSelective(map);
	}

	@Override
	public List<TOrderCustomWithBLOBs> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, orderCustomMapper);
	}

	@Override
	public int insert(TOrderCustomWithBLOBs record) {
		return orderCustomMapper.insert(record);
	}

	@Override
	public int update(TOrderCustomWithBLOBs record) throws Exception {
		Body body = record.getBody();
		String fcResSnapshot = "";
		//设置餐厅数量
		try{
			if (body != null) {
				for (Daylist daylist : body.getDaylist()) {
					if(daylist.getReslist() == null) continue;
					List<Reslist> resList = new ArrayList<Reslist>();
					for(Reslist reslist : daylist.getReslist()){
						if (reslist == null || reslist.getCclist() == null) continue;
						resList.add(reslist);
						String usernum = reslist.getCclist().get(0).getUsernum();
						List<Cclist> ccList = new ArrayList<Cclist>();
						for (Cclist cclist : reslist.getCclist()) {
							cclist.setUsernum(usernum);
							if (cclist.getPrice() == null){
								continue;
							}
							ccList.add(cclist);
						}
						reslist.setCclist(ccList);
					}
					daylist.setReslist(resList);
				}
				Root root = new Root(record.getBody());
				fcResSnapshot = ResScheduleXMLConverter.toXml("http://www.yttx.com/", root);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		record.setFcRessnapshot(fcResSnapshot);
		//更新批次成功后修改订单预估金额，以及将其余批次精确资源为空的设置为本批次资源
		int num = 0;
		if ((num = orderCustomMapper.updateByPrimaryKeySelective(record)) == 1){
			TOrderCustomExample example = new TOrderCustomExample();
			Criteria criteria = example.createCriteria();
			criteria.andFsOrderIdEqualTo(record.getFsOrderId());
			List<TOrderCustomWithBLOBs> list = orderCustomMapper.selectByExample(example);
			BigDecimal totalfee = BigDecimal.ZERO;
			for (TOrderCustomWithBLOBs orderCustom : list) {
				if(StringUtils.isEmpty(orderCustom.getFcRessnapshot())){
					orderCustom.setFcRessnapshot(record.getFcRessnapshot());
					totalfee.add(record.getFdAmt());
					orderCustomMapper.updateByPrimaryKey(orderCustom);
				}
				totalfee = totalfee.add(orderCustom.getFdAmt());
			}
			TOrderlistWithBLOBs orderlist = new TOrderlistWithBLOBs();
			orderlist.setFsNo(record.getFsOrderId());
			orderlist.setFdTotalfee(totalfee);
			orderlistMapper.updateByPrimaryKeySelective(orderlist);
		}
		return num;
	}

	@Override
	public int delete(String no) {
		return orderCustomMapper.deleteByPrimaryKey(new BigDecimal(no));
	}

	@Override
	public List<TOrderCustomWithBLOBs> selectTOrderCustom(TOrderCustomExample example) {
		// TODO Auto-RouteArrangeerated method stub
		return orderCustomMapper.selectByExampleWithBLOBs(example);
	}
	
}
