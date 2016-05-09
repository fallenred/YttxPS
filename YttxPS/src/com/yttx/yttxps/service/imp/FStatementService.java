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
import com.yttx.yttxps.mapper.FStatementMapper;
import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.service.IFStatementService;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;
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
		Shop origShop = root.getBody().getIncomedetails().getShop();
		//遍历原始购物店list，替换id相同的购物店信息
		List<Reslist> reslists = origShop.getReslist();
		BigDecimal total = BigDecimal.ZERO;
		List<Reslist> list = new ArrayList<Reslist>();
		for (int i = 0; i < reslists.size(); i++) {
			Reslist reslist2 = reslists.get(i);
			//如果新增的购物店id与content中购物店id相同，则替换
			if (reslist.getResno().equals(reslist2.getResno())) {
				list.add(reslist);
			} else {
				list.add(reslist2);
			}
			//总金额
			total = total.add(new BigDecimal(list.get(i).getTotalprofit()));
		}
		origShop.setReslist(list);
		origShop.setTotal(total.toString());
		try {
			String content = ResScheduleXMLConverter.toXml("www.yttx.co", root);
			fStatement.setOrderContent(content);
			this.fStatementMapper.updateFSSelective(fStatement);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return origShop;
	}

	@Override
	public Shop delShopReslist(String orderid, String resno) {
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
		return shop;
	}
	
}
