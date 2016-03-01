package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
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
		if (body != null) {
			for (Daylist daylist : body.getDaylist()) {
				for(Reslist reslist : daylist.getReslist()){
					for (int i = 0; i < reslist.getCclist().size(); i++) {
						if ("ct".equals(reslist.getRestype())) {
							reslist.getCclist().get(i).setUsernum(reslist.getCclist().get(0).getUsernum());
						}
					}
				}
			}
			Root root = new Root(record.getBody());
			fcResSnapshot = ResScheduleXMLConverter.toXml("http://www.yttx.com/", root);
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
				totalfee.add(orderCustom.getFdAmt());
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
