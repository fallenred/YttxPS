package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yttx.yttxps.mapper.TCCPriceMapper;
import com.yttx.yttxps.mapper.TEntertainmentMapper;
import com.yttx.yttxps.mapper.TEntertainmentPriceMapper;
import com.yttx.yttxps.mapper.TResourceScenicMapper;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TCCPriceExample;
import com.yttx.yttxps.model.TEntertainment;
import com.yttx.yttxps.model.TEntertainmentExample;
import com.yttx.yttxps.model.TResourceScenic;
import com.yttx.yttxps.model.TResourceScenicExample;
import com.yttx.yttxps.model.TResourceScenicExample.Criteria;
import com.yttx.yttxps.service.IEntertainmentService;
import com.yttx.yttxps.service.IPubService;

@Service("entertainmentService")
public class EntertainmentService implements IEntertainmentService {
	
	@Autowired
	private TEntertainmentMapper<TEntertainment> entertainmentMapper;
	
	@Autowired
	private TEntertainmentPriceMapper<TEntertainment> entertainmentPriceMapper;
	
	@Autowired
	private IPubService<TEntertainment> pubService;
	
	@Autowired
	private TResourceScenicMapper<TResourceScenic> resourceScenicMapper;
	
	@Autowired
	private TCCPriceMapper tccPriceMapper;

	/**
	 * 分页查询总记录
	 */
	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return entertainmentMapper.selectCountSelective(map);
	}

	/**
	 * 分页查询
	 */
	@Override
	public List<TEntertainment> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, entertainmentMapper);
	}

	@Override
	public int selectCountEntertainmentPrice(Map<String, Object> map) {
		return 0;
	}

	@Override
	public List<TEntertainment> selectEntertainmentPricePage(Map<String, Object> map) {
		return pubService.doPage(map, entertainmentPriceMapper);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void insert(TEntertainment record) {
		record.setFsNo(String.format("%010d", entertainmentMapper.selectFsNo()));
		entertainmentMapper.insert(record);
		//新增景区资源对照数据
		TResourceScenic resourceScenic = new TResourceScenic();
		resourceScenic.setFiIndex(BigDecimal.valueOf(resourceScenicMapper.getSeq()));
		resourceScenic.setFsResno(record.getFsNo());
		resourceScenic.setFsRestype("yl");
		resourceScenic.setFsScenicno(record.getFsScenicno());
		resourceScenicMapper.insert(resourceScenic);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void insertEntertainmentPrice(TEntertainment record) {
		if (CollectionUtils.isEmpty(record.getTccPrices())) return;
		for (TCCPrice price : record.getTccPrices()) {
			//如果价格为空则不作处理
			if (price.getFdPrice() == null) continue;
			//票价类型为淡季时不对旺季价格和接送费用进行保存
			if("1".equals(record.getPriceType())) {
				if(record.getTccPrices().indexOf(price) > 7)
					continue;
			} 
			//票价类型为旺季时不对淡季价格和接送费用进行保存
			else if ("2".equals(record.getPriceType())) {
				if(record.getTccPrices().indexOf(price) < 8 || record.getTccPrices().indexOf(price) > 15)
					continue;
			}
			//票价类型为接送费用时不对淡季价格和旺季价格进行保存
			else if("3".equals(record.getPriceType())) {
				if(record.getTccPrices().indexOf(price) < 16)
					continue;
			}
			price.setFtStartdate(record.getFtStartdate());
			price.setFtEnddate(record.getFtEnddate());
			price.setFsRestype("yl");
			price.setFsResno(record.getFsNo());
			tccPriceMapper.insertSelective(price);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(TEntertainment record) {
		entertainmentMapper.updateByPrimaryKeySelective(record);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateEntertainmentPrice(TEntertainment record) {
		if (CollectionUtils.isEmpty(record.getTccPrices())) return;
		for (TCCPrice price : record.getTccPrices()) {
			//如果价格为空则不作处理
			if (price.getFdPrice() == null) continue;
			//票价类型为淡季时不对旺季价格和接送费用进行保存
			if("1".equals(record.getPriceType())) {
				if(record.getTccPrices().indexOf(price) > 7)
					continue;
			} 
			//票价类型为旺季时不对淡季价格和接送费用进行保存
			else if ("2".equals(record.getPriceType())) {
				if(record.getTccPrices().indexOf(price) < 8 || record.getTccPrices().indexOf(price) > 15)
					continue;
			}
			//票价类型为接送费用时不对淡季价格和旺季价格进行保存
			else if("3".equals(record.getPriceType())) {
				if(record.getTccPrices().indexOf(price) < 16)
					continue;
			}
			price.setFtStartdate(record.getFtStartdate());
			price.setFtEnddate(record.getFtEnddate());
			price.setFsRestype("yl");
			price.setFsResno(record.getFsNo());
			tccPriceMapper.updateByPrimaryKey(price);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(String no) {
		entertainmentMapper.deleteByPrimaryKey(no);
		TResourceScenicExample example = new TResourceScenicExample();
		Criteria criteria = example.createCriteria();
		criteria.andFsResnoEqualTo(no);
		resourceScenicMapper.deleteByExample(example);
		
		//还需要删除价格参数
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteEntertainmentPrice(TCCPrice price) {
		TCCPriceExample example = new TCCPriceExample();
		com.yttx.yttxps.model.TCCPriceExample.Criteria criteria = example.createCriteria();
		criteria.andFtStartdateEqualTo(price.getFtStartdate());
		criteria.andFtEnddateEqualTo(price.getFtEnddate());
		criteria.andFsRestypeEqualTo("yl");
		criteria.andFsResnoEqualTo(price.getFsResno());
		tccPriceMapper.deleteByExample(example);
	}

	@Override
	public List<TEntertainment> selectEntertainment(TEntertainmentExample example) {
		return entertainmentMapper.selectByExample(example);
	}

	@Override
	public List<TEntertainment> selectEntertainmentDynamic(Map<String, Object> map) {
		return entertainmentMapper.selectEntertainmentDynamic(map);
	}

	/**
     * 查询娱乐项目列表
     * add by huangtao
     * 2016-02-24
     * @param map
     * @return
     */
	@Override
	public List<TEntertainment> selectEntertainment(Map<String, Object> map) {
		return entertainmentMapper.selectEntertainment(map);
	}
}
