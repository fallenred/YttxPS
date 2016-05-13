package com.yttx.yttxps.service.imp;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.yttx.except.BusinessException;
import com.yttx.yttxps.comm.Constants.MsgTemp;
import com.yttx.yttxps.comm.Constants.RecRole;
import com.yttx.yttxps.mapper.CustomInfoMapper;
import com.yttx.yttxps.mapper.CustomOperMapper;
import com.yttx.yttxps.mapper.FStatementMapper;
import com.yttx.yttxps.mapper.MessageAuthMapper;
import com.yttx.yttxps.mapper.MessageMapper;
import com.yttx.yttxps.mapper.SysOperMapper;
import com.yttx.yttxps.mapper.TOrderlistMapper;
import com.yttx.yttxps.model.CustomInfo;
import com.yttx.yttxps.model.CustomOper;
import com.yttx.yttxps.model.CustomOperExample;
import com.yttx.yttxps.model.Message;
import com.yttx.yttxps.model.MessageAuth;
import com.yttx.yttxps.model.MessageExample;
import com.yttx.yttxps.model.MessageExample.Criteria;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.SysOperExample;
import com.yttx.yttxps.model.TOrderlist;
import com.yttx.yttxps.model.TRemarks;
import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.service.IMsgService;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;

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
	private IPubService<Message> pubService;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private MessageAuthMapper authMapper;
	@Autowired
	private CustomOperMapper operMapper;
	@Autowired
	private SysOperMapper sysOperMapper;
	@Autowired
	private CustomInfoMapper infoMapper;
	@Autowired
	private TOrderlistMapper<TOrderlist> orderlistMapper;
	@Autowired
	private FStatementMapper<FStatement> statementMapper;
	
	@Override
	//@Transactional(rollbackFor=Exception.class)
	public void saveMsg(Object obj, String sendid, String oldStat) {
		// TODO Auto-generated method stub
		List<Message> list = this.getMessage(obj, sendid, oldStat);
		if (CollectionUtils.isEmpty(list)) return;
		for(Message message : list){
			messageMapper.insert(message);
		}
	}
	
	/**
	 * 根据业务类型获取消息集合
	 * @param obj
	 * @param oldStat 
	 * @param operid 消息发送者id
	 * @return
	 */
	private List<Message> getMessage(Object obj, String sendid, String oldStat) {
		String tempid = "";	//消息模板id
		String custid = "";	//客户id
		String subid = "";	//子客户id
		Map<String, Object> tempParam = new HashMap<String, Object>();
		List<Message> msgList = null;
		List<Message> list = null;
		//会员审核
		if (obj instanceof CustomInfo) {
			CustomInfo customInfo = (CustomInfo)obj;
			int auditRet = customInfo.getAuditRet();//审核结果
			//审核通过
			if (auditRet == 1) {
				tempid = MsgTemp.AUDIT_SUCCESS.getVal();
				custid = customInfo.getId();
				tempParam.put("cusID", custid);
				tempParam.put("taName", customInfo.getTaname());
			} else if (auditRet == -1){
				tempid = MsgTemp.AUDIT_FAILD.getVal();
				custid = customInfo.getId();
				tempParam.put("cusID", custid);
				tempParam.put("comment", customInfo.getComment());
			}
		} else if (obj instanceof TOrderlist) {//订单
			TOrderlist orderlist = (TOrderlist) obj;
			orderlist = orderlistMapper.selectByPrimaryKey(orderlist.getFsNo());
			custid = orderlist.getFsUserId();
			subid = orderlist.getFsUserSubid();
			int stat = Integer.valueOf(orderlist.getFiStat());
			if (stat == Integer.parseInt(oldStat)) return null;
			switch (Integer.parseInt(oldStat)) {
				case -10://询价
					tempid = MsgTemp.DIRECTOR.getVal();
					tempParam.put("orderID", orderlist.getFsNo());
					break;
				case 0://待审核
					tempid = MsgTemp.ORDER_AUDIT.getVal();
					tempParam.put("orderID", orderlist.getFsNo());
					tempParam.put("orderAmt", orderlist.getFdTotalfee());
					break;
				case 6: //线下支付
					tempid = MsgTemp.TOURS.getVal();
					tempParam.put("orderID", orderlist.getFsNo());
					tempParam.put("orderAmt", orderlist.getFdTotalfee());
					tempParam.put("startDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderlist.getFtStartdate()));
					tempParam.put("visitorNum", orderlist.getFiVisitornum());
					if("02".equals(orderlist.getFsType())){
						tempParam.put("type", "experts");
					} else if ("03".equals(orderlist.getFsType())){
						tempParam.put("type", "customization");
					}
					String fsOperId = orderlist.getFsOperId();
					System.out.println(String.format("%-16s",fsOperId).length());
					System.out.println("-"+String.format("%-16s",fsOperId)+"-");
					SysOper selectByPrimaryKey = this.sysOperMapper.selectByPrimaryKey(String.format("%-16s",fsOperId));
					System.out.println(selectByPrimaryKey == null);
					Long depNo = selectByPrimaryKey.getDepNo();
					list = this.getMessage4Auth2(MsgTemp.ORDER_CONFIRM.getVal(), sendid, depNo.toString(), orderlist.getFsOperId(), tempParam);
					System.out.println(list == null);
					break;
					
				case -5://报价
				case 1: //已审核
				case 8: //已付全款
//					tempid = MsgTemp.ORDER_MODIFY.getVal();
//					tempParam.put("orderID", orderlist.getFsNo());
				default:
					break;
			}
		} else if (obj instanceof TRemarks) {//订单备注
			TRemarks remarks = (TRemarks) obj;
			TOrderlist orderlist = orderlistMapper.selectByPrimaryKey(remarks.getFsOrderId());
			custid = orderlist.getFsUserId();
			subid = orderlist.getFsUserSubid();
			int stat = Integer.valueOf(remarks.getFiStat().toString());
			if (stat == 1){//未结算
				
			}
		} else if (obj instanceof FStatement) {//结算单
			FStatement statement = (FStatement) obj;
			tempParam.put("closeAmt", statement.getAmt_());
			statement = statementMapper.selectFSById(statement.getStatmentId());
			custid = statement.getUserID();
			subid = statement.getUserSubID();
//			int stat = Integer.valueOf(statement.getStat().toString());
			switch (oldStat) {
				case "-10": //新生成结算单
					tempid = MsgTemp.STATEMENT.getVal();
					tempParam.put("orderID", statement.getOrderId());
					tempParam.put("closeID", statement.getStatmentId());
					break;
				case "-1": //线下支付确认
					tempid = MsgTemp.STATEMENT_CONFIRM.getVal();
					tempParam.put("orderID", statement.getOrderId());
					tempParam.put("closeID", statement.getStatmentId());
					
				default:
					break;
			}
		}
		msgList = this.getMessage4Auth(tempid, sendid, custid, subid, tempParam);
		if(list != null){
			msgList.addAll(list);
		}
		return msgList;
	}
	
	/**
	 * 根据消息模板权限获取需要发送的消息提醒集合
	 * @param tempid 模板id
	 * @param operid 操作员id
	 * @param recDepNo 接收部门id
	 * @param recOprId 接收子id
	 * @param tempParam 模板参数
	 * @return
	 */
	private List<Message> getMessage4Auth2(String tempid, String operid, String recDepNo, String recOperId, Map<String, Object> tempParam){
		List<SysOper> list = null;
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
			int recRole = Integer.valueOf(json.getString("recRole"));//消息接收角色
			SysOperExample operExample = new SysOperExample();
			com.yttx.yttxps.model.SysOperExample.Criteria criteria = operExample.createCriteria();
			if (RecRole.CANCEL.getVal() == recRole) {//不发送消息提醒
				return null;
			} else if (RecRole.ALL.getVal() == recRole){//发送给所有人
				if (StringUtils.isBlank(recDepNo)) {
					String resMsg = "消息提醒-查询消息接收部门失败";
					logger.error(String.format("[errMsg] retMsg-%1$s，recRole-%2$s, recDepNo-%3$s", resMsg, recRole, recDepNo));
					throw new BusinessException(resMsg);
				}
				criteria.andDepNoEqualTo(new BigDecimal(recDepNo));
				list = this.sysOperMapper.selectByExample(operExample);
			} else if (RecRole.DIRECTOR.getVal() == recRole){//发送给主管
				if (StringUtils.isBlank(recDepNo)) {
					String resMsg = "消息提醒-查询消息接收部门失败";
					logger.error(String.format("[errMsg] retMsg-%1$s，recRole-%2$s, recDepNo-%3$s", resMsg, recRole, recDepNo));
					throw new BusinessException(resMsg);
				}
				criteria.andDepNoEqualTo(new BigDecimal(recDepNo));
				criteria.andAdminTypeEqualTo(new BigDecimal(2));
				list = this.sysOperMapper.selectByExample(operExample);
			} else if (RecRole.OPERATOR.getVal() == recRole){//发送给下单员
				if (StringUtils.isBlank(recDepNo) || StringUtils.isBlank(recOperId)) {
					String resMsg = "消息提醒-查询接单计调人员失败，部门号为空";
					logger.error(String.format("[errMsg] retMsg-%1$s，recRole-%2$s, recDepNo-%3$s, recOperId-%4$s", resMsg, recRole, recDepNo, recOperId));
					throw new BusinessException(resMsg);
				}
				criteria.andSysOperIdEqualTo(recOperId);
				list = new  ArrayList<SysOper>();
				list.add(this.sysOperMapper.selectByPrimaryKey(String.format("%-16s", recOperId)));
			} else if (RecRole.MIXED.getVal() == recRole){//发送给主管及接单人员(后台)或用户管理员及下单人员(前台)
				if(StringUtils.isBlank(recDepNo) || StringUtils.isBlank(recOperId)) {
					String resMsg = "消息提醒-查询接单计调人员失败，部门号为空";
					logger.error(String.format("[errMsg] retMsg-%1$s，recRole-%2$s,recDepNo-%3$s, recOperId-%4$s", resMsg, recRole, recDepNo, recOperId));
					throw new BusinessException(resMsg);
				}
				criteria.andDepNoEqualTo(new BigDecimal(recDepNo));
				criteria.andAdminTypeEqualTo(new BigDecimal(2));
				list = this.sysOperMapper.selectByExample(operExample);
				if(!"2".equals(this.sysOperMapper.selectByPrimaryKey(recOperId).getAdminType().toString())){
					SysOperExample sysOperExample1 = new SysOperExample();
					sysOperExample1.createCriteria().andSysOperIdEqualTo(recOperId);
					list.addAll(this.sysOperMapper.selectByExample(sysOperExample1));
				}
			}
			if (CollectionUtils.isNotEmpty(list)) {
				List<Message> msgList = new ArrayList<Message>();
				//将消息模板转为具体消息内容
		        try {
			        for (SysOper oper : list) {
			        	StringWriter writer = new StringWriter();
			        	Configuration cfg = new Configuration();
			        	StringTemplateLoader stringLoader = new StringTemplateLoader();
			        	String templateContent = temp;
			        	stringLoader.putTemplate("mytemplate",templateContent);
			        	cfg.setTemplateLoader(stringLoader);
			            cfg.setDefaultEncoding("UTF-8");
			            Template template = cfg.getTemplate("mytemplate");
			            BigDecimal id = messageMapper.selectFsNo();
			            tempParam.put("msgID", id);
						template.process(tempParam, writer);
						//将消息模板转为msg对象
						String msgText = writer.toString().replace("\\n", "");
						Msg msg = ResScheduleXMLConverter.fromXml(null, msgText, Msg.class);
						//根据推送客户创建消息
						Message message = new Message();
						message.setId(id);
						message.setSendType("0");
						message.setMsgHead(msg.getTitle());
						message.setMsgText(msg.getContent());
						message.setRecvid(oper.getSysOperId().trim());
						message.setMsgDate(new Timestamp(new Date().getTime()));
						message.setSendid(operid);
						message.setMsgStat("0");
						msgList.add(message);
					}
				} catch (Exception e) {
					String resMsg = "消息模板格式化错误";
					logger.error(String.format("[errMsg] retMsg-%1$s，temp-%2$s", resMsg, temp), e);
					throw new BusinessException(resMsg, e);
				}
				return msgList;
			}
		}
		return null;
	}
	
	/**
	 * 根据消息模板权限获取需要发送的消息提醒集合
	 * @param tempid 模板id
	 * @param operid 操作员id
	 * @param custid 客户id
	 * @param subid 客户子id
	 * @param tempParam 模板参数
	 * @return
	 */
	private List<Message> getMessage4Auth(String tempid, String operid, String custid, String subid, Map<String, Object> tempParam){
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
			int recRole = Integer.valueOf(json.getString("recRole"));//消息接收角色
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
			} else if (RecRole.MIXED.getVal() == recRole){//发送给主管及接单人员(后台)或用户管理员及下单人员(前台)
				if(StringUtils.isBlank(custid) || StringUtils.isBlank(subid)) {
					String resMsg = "消息提醒-查询下单用户失败，客户id为空";
					logger.error(String.format("[errMsg] retMsg-%1$s，recRole-%2$s,custid-%3$s", resMsg, recRole, custid));
					throw new BusinessException(resMsg);
				}
				criteria.andIdEqualTo(custid);
				criteria.andTypeEqualTo("00");
				list = this.operMapper.selectByExample(operExample);
				if(!"0000".equals(subid)){
					CustomOperExample operExample1 = new CustomOperExample();
					operExample1.createCriteria().andIdEqualTo(custid).andSubidEqualTo(subid);
					list.addAll(this.operMapper.selectByExample(operExample1));
				}
			}
			if (CollectionUtils.isNotEmpty(list)) {
				List<Message> msgList = new ArrayList<Message>();
				//将消息模板转为具体消息内容
		        try {
			        for (CustomOper oper : list) {
			        	StringWriter writer = new StringWriter();
			        	Configuration cfg = new Configuration();
			        	StringTemplateLoader stringLoader = new StringTemplateLoader();
			        	String templateContent = temp;
			        	stringLoader.putTemplate("mytemplate",templateContent);
			        	cfg.setTemplateLoader(stringLoader);
			            cfg.setDefaultEncoding("UTF-8");
			            Template template = cfg.getTemplate("mytemplate");
			            BigDecimal id = messageMapper.selectFsNo();
			            tempParam.put("msgID", id);
						template.process(tempParam, writer);
						//将消息模板转为msg对象
						String msgText = writer.toString().replace("\\n", "");
						Msg msg = ResScheduleXMLConverter.fromXml(null, msgText, Msg.class);
						//根据推送客户创建消息
						Message message = new Message();
						message.setId(id);
						message.setSendType("1");
						message.setMsgHead(msg.getTitle());
						message.setMsgText(msg.getContent());
						message.setRecvid(oper.getId());
						message.setSubid(oper.getSubid());
						message.setMsgDate(new Timestamp(new Date().getTime()));
						message.setSendid(operid);
						message.setMsgStat("0");
						msgList.add(message);
					}
				} catch (Exception e) {
					String resMsg = "消息模板格式化错误";
					logger.error(String.format("[errMsg] retMsg-%1$s，temp-%2$s", resMsg, temp), e);
					throw new BusinessException(resMsg, e);
				}
				return msgList;
			}
		}
		return null;
	}
	
	
	/**
	 * 根据id获取旅行社名称
	 * @return
	 */
	private String getTaName(String cusid){
		CustomInfo info = this.infoMapper.selectByPrimaryKey(cusid);
		if (info != null)
			return info.getTaname();
		return null;
	}
	
	/**
	 * 消息模板xml映射实体
	 */
	@XStreamAlias("msg")
	private class Msg {
		@XStreamAlias("title")
		private String title;
		@XStreamAlias("content")
		private String content;
		public String getTitle() {
			return title;
		}
		@SuppressWarnings("unused")
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		@SuppressWarnings("unused")
		public void setContent(String content) {
			this.content = content;
		}
	}
	
	@Override
	public List<Message> selectNewMsg(Map<String, Object> map) {
		return messageMapper.selectUnreadMsg(map);
	}

	@Override
	public List<Message> selectSelectivePage(Map<String, Object> map) {
		List<Message> list = pubService.doPage(map, messageMapper);
		for (Message  message: list) {
			message.setMsgText(message.getMsgText().trim());
		}
		return list;
	}

	/**
	 * @return 返回是否成功设置msg状态为已读（之前已是已读也算） 
	 */
	@Override
	public boolean readMsg(String id) {
		if(!org.apache.commons.lang3.StringUtils.isNoneEmpty(id)){
			return false;
		}
		Message message = new Message();
		message.setId(new BigDecimal(id));
		message.setMsgStat("1");
		messageMapper.updateByPrimaryKeySelective(message);
		return true;
	}

	@Override
	public boolean delete(String id) {
		return messageMapper.deleteByPrimaryKey(new BigDecimal(id)) > 0 ? true : false;
	}

	@Override
	public int deleteGroup(String operid, String id,String... msgTemp) {
		if(!org.apache.commons.lang3.StringUtils.isNoneEmpty(id)){
			return 0;
		}
		int count = 0;
		for (String temp : msgTemp) {
			MessageExample example = new MessageExample();
			Criteria criteria = example.createCriteria();
			criteria.andAuthidEqualTo(temp);
			criteria.andFsIdEqualTo(id);
			criteria.andRecvidNotEqualTo(operid);
			count += messageMapper.deleteByExample(example);
		}
		return count;
	}
	
}
