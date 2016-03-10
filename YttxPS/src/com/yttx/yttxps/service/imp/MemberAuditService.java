package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.CustomInfoMapper;
import com.yttx.yttxps.mapper.CustomerAuditMapper;
import com.yttx.yttxps.model.CustomInfo;
import com.yttx.yttxps.service.IMemberAuditService;
import com.yttx.yttxps.service.IPubService;

/**
 * 会员模块--会员审核Service接口的实现类
 */
@Service("memberAuditService")
public class MemberAuditService implements IMemberAuditService {

	@Autowired
	private IPubService<CustomInfo> pubService;
	
	@Autowired
	private CustomerAuditMapper customerAuditMapper;
	
	@Autowired 
	private CustomInfoMapper customInfoMapper;
	
	
	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return customerAuditMapper.selectCountSelective(map);
	}

	@Override
	public List<CustomInfo> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, customerAuditMapper);
	}

	@Override
	public CustomInfo selectCusByAuditNo(String auditNo) {
		return customerAuditMapper.selectByAuditNo(auditNo);
	}

	//会员审核
	@Override
	public boolean auditCustomer(CustomInfo auditRec) {
		//修改客户信息调整审核表：审核人，审核时间，审核结果，审核意见
		customerAuditMapper.updateByPrimaryKeySelective(auditRec);
		//修改客户信息表
		int auditResult = auditRec.getAuditRet().intValue();//审核结果
		int auditType = auditRec.getAuditType().intValue();//审核类型
		
		//修改会员表
		auditRec.setTimestamp(auditRec.getAuditTime());
		if(auditResult==1){//如果是审核成功
			auditRec.setStat(new BigDecimal(1));//设置客户状态为1
			customInfoMapper.updateByPassAudit(auditRec);
		}else{
			if(auditType==1){//新增操作
				auditRec.setStat(new BigDecimal(0));//新增操作设置为未激活状态
			}else{
				auditRec.setStat(new BigDecimal(1));//变更操作设置为正常状态
			}
			customInfoMapper.updateByNotPassAudit(auditRec);
			
		}
		return true;
	}
}
