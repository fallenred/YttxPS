package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.FStatementMapper;
import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.service.IFStatementService;
import com.yttx.yttxps.service.IPubService;

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

}
