package com.yttx.yttxps.service.imp;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.except.BusinessException;
import com.yttx.yttxps.comm.Constants.MsgTemp;
import com.yttx.yttxps.comm.Constants.RecRole;
import com.yttx.yttxps.mapper.CustomOperMapper;
import com.yttx.yttxps.mapper.MessageAuthMapper;
import com.yttx.yttxps.mapper.MessageMapper;
import com.yttx.yttxps.model.CustomInfo;
import com.yttx.yttxps.model.CustomOper;
import com.yttx.yttxps.model.CustomOperExample;
import com.yttx.yttxps.model.Message;
import com.yttx.yttxps.model.MessageAuth;
import com.yttx.yttxps.model.TOrderlist;
import com.yttx.yttxps.model.TRemarks;
import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.service.IMsgService;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * 消息提醒service
 * @author huangtao
 *
 */
@Service("msgService")
public class MsgService implements IMsgService {
	static Logger logger = LoggerFactory.getLogger(MsgService.class);
	
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private MessageAuthMapper authMapper;
	@Autowired
	private CustomOperMapper operMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveMsg(Object obj) {
		// TODO Auto-generated method stub
		List<Message> list = this.getMessage(obj);
		if (CollectionUtils.isEmpty(list)) return;
		for(Message message : list){
			messageMapper.insert(message);
		}
	}
	
	/**
	 * 根据业务类型获取消息集合
	 * @param obj
	 * @return
	 */
	private List<Message> getMessage(Object obj) {
		String tempid = "";	//消息模板id
		String custid = "";	//客户id
		String subid = "";	//子客户id
		Map<String, Object> tempParam = new HashMap<String, Object>();
		List<Message> msgList = null;
		//会员审核
		if (obj instanceof CustomInfo) {
			CustomInfo customInfo = (CustomInfo)obj;
			int stat = customInfo.getStat();
			//审核通过
			if (stat == 1) {
				tempid = MsgTemp.AUDIT.getVal();
				custid = customInfo.getId();
				tempParam.put("", "");
			}
		} else if (obj instanceof TOrderlist) {//订单
			TOrderlist orderlist = (TOrderlist) obj;
			custid = orderlist.getFsUserId();
			subid = orderlist.getFsUserSubid();
			int stat = Integer.valueOf(orderlist.getFiStat());
			switch (stat) {
				case -5://报价
					tempid = MsgTemp.DIRECTOR.getVal();
					break;
				case 1: //审核
					if ("02".equals(orderlist.getFsType())) {//专家线路
						tempid = MsgTemp.EXP_ORDER_AUDIT.getVal();
					} else if("03".equals(orderlist.getFsType())) {//订制线路
						tempid = MsgTemp.CUS_ORDER_AUDIT.getVal();
					}
					break;
				default:
					break;
			}
		} else if (obj instanceof TRemarks) {//订单备注
			
		} else if (obj instanceof FStatement) {//结算单
			FStatement statement = (FStatement) obj;
			custid = statement.getUserID();
			subid = statement.getUserSubID();
			int stat = Integer.valueOf(statement.getStat().toString());
			switch (stat) {
				case 0:	//待确认
					tempid = MsgTemp.STATEMENT.getVal();
					break;
				case 2: //结算完毕
					tempid = MsgTemp.STATEMENT_DONE.getVal();
					break;
				default:
					break;
			}
		}
		msgList = this.getMessage4Auth(tempid, custid, subid, tempParam);
		return msgList;
	}
	
	/**
	 * 根据消息模板权限获取需要发送的消息提醒集合
	 * @param tempid 模板id
	 * @param custid 客户id
	 * @param subid 客户子id
	 * @param tempParam 模板参数
	 * @return
	 */
	private List<Message> getMessage4Auth(String tempid, String custid, String subid, Map<String, Object> tempParam){
		List<CustomOper> list = null;
		//查询消息模板权限表
		MessageAuth messageAuth = authMapper.selectByPrimaryKey(tempid);
		if (messageAuth != null && BigDecimal.ONE.compareTo(messageAuth.getStat()) == 0) {
			String temp = messageAuth.getMsgTemp();//消息模板
			if (StringUtils.isBlank(temp)) {
				String resMsg = "消息模板内容为空";
				logger.error(String.format("[errMsg] retMsg-%1$s，tempid-%2$s", resMsg, messageAuth.getId()));
				throw new BusinessException(resMsg);
			}
			String receive = messageAuth.getReceiveConf();//消息接收者
			if(StringUtils.isEmpty(receive)) return null;
			JSONArray jsonArr = JSONArray.fromObject(receive);
			JSONObject json = jsonArr.getJSONObject(0);
			int recRole = Integer.valueOf(json.getString("recRole"));
			CustomOperExample operExample = new CustomOperExample();
			com.yttx.yttxps.model.CustomOperExample.Criteria criteria = operExample.createCriteria();
			//不发送消息提醒
			if (RecRole.CANCEL.getVal() == recRole) {
				return null;
			} else if (RecRole.ALL.getVal() == recRole){//发送给所有人
				if (StringUtils.isBlank(custid)) {
					String resMsg = "消息提醒-查询用户失败，客户id为空";
					logger.error(String.format("[errMsg] retMsg-%1$s，recRole-%2$s, custid-%3$s", resMsg, recRole, custid));
					throw new BusinessException(resMsg);
				}
				criteria.andIdEqualTo(custid);
				list = this.operMapper.selectByExample(operExample);
			} else if (RecRole.DIRECTOR.getVal() == recRole){//发送给主管
				if (StringUtils.isBlank(custid)) {
					String resMsg = "消息提醒-查询用户主管失败，客户id为空";
					logger.error(String.format("[errMsg] retMsg-%1$s，recRole-%2$s, custid-%3$s", resMsg, recRole, custid));
					throw new BusinessException(resMsg);
				}
				criteria.andIdEqualTo(custid);
				criteria.andTypeEqualTo("00");
				list = this.operMapper.selectByExample(operExample);
			} else if (RecRole.OPERATOR.getVal() == recRole){//发送给下单员
				if (StringUtils.isBlank(custid) || StringUtils.isBlank(subid)) {
					String resMsg = "消息提醒-查询下单用户失败，客户id为空";
					logger.error(String.format("[errMsg] retMsg-%1$s，recRole-%2$s, custid-%3$s", resMsg, recRole, custid));
					throw new BusinessException(resMsg);
				}
				criteria.andIdEqualTo(custid);
				criteria.andSubidEqualTo(subid);
				list = this.operMapper.selectByExample(operExample);
			}
			if (CollectionUtils.isNotEmpty(list)) {
				List<Message> msgList = new ArrayList<Message>();
				//将消息模板转为具体消息内容
				Configuration cfg = new Configuration();
		        StringTemplateLoader stringLoader = new StringTemplateLoader();
		        String templateContent = temp;
		        stringLoader.putTemplate("template",templateContent);
		        StringWriter writer = new StringWriter();
		        try {
					Template template = cfg.getTemplate("template","utf-8");
					template.process(tempParam, writer);
				} catch (Exception e) {
					String resMsg = "消息模板格式化错误";
					logger.error(String.format("[errMsg] retMsg-%1$s，temp-%2$s", resMsg, temp));
					throw new BusinessException(resMsg, e);
				}
				for (CustomOper oper : list) {
					Message msg = new Message();
					msg.setSendType("1");
					msg.setMsgText(writer.toString());
					msg.setRecvId(oper.getId());
					msg.setSubId(oper.getSubid());
					msg.setMsgDate(new Date());
					msg.setMsgStat(BigDecimal.ZERO);
				}
				return msgList;
			}
		}
		return null;
	}

}
