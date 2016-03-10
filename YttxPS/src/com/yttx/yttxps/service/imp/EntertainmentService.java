package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yttx.yttxps.mapper.TCCPriceMapper;
import com.yttx.yttxps.mapper.TEntertainmentMapper;
import com.yttx.yttxps.mapper.TEntertainmentPriceMapper;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TCCPriceExample;
import com.yttx.yttxps.model.TEntertainment;
import com.yttx.yttxps.model.TEntertainmentExample;
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
		record.setFsNo(String.format("yl%08d", entertainmentMapper.selectFsNo()));
		entertainmentMapper.insert(record);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void insertEntertainmentPrice(TEntertainment record) {
		if (CollectionUtils.isEmpty(record.getTccPrices())) return;
		for (TCCPrice price : record.getTccPrices()) {
			price.setFtStartdate(record.getFtStartdate());
			price.setFtEnddate(record.getFtEnddate());
			price.setFsRestype("yl");
			price.setFsResno(record.getFsNo());
			tccPriceMapper.insertPrice(price);
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
			price.setFtStartdate(record.getFtStartdate());
			price.setFtEnddate(record.getFtEnddate());
			price.setFsRestype("yl");
			price.setFsResno(record.getFsNo());
			tccPriceMapper.insertPrice(price);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(String no) {
		entertainmentMapper.deleteByPrimaryKey(no);
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
