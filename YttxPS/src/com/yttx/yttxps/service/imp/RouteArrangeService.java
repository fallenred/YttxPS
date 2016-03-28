package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.mapper.TResTypeDircMapper;
import com.yttx.yttxps.mapper.TRouteArrangeMapper;
import com.yttx.yttxps.mapper.TRouteCCMapper;
import com.yttx.yttxps.mapper.TRoutePropClassMapper;
import com.yttx.yttxps.model.RouteCCType;
import com.yttx.yttxps.model.TResTypeDirc;
import com.yttx.yttxps.model.TResTypeDircExample;
import com.yttx.yttxps.model.TRouteArrange;
import com.yttx.yttxps.model.TRouteArrangeExample;
import com.yttx.yttxps.model.TRouteArrangeWithBLOBs;
import com.yttx.yttxps.model.TRouteCCExample;
import com.yttx.yttxps.model.TRouteCCExample.Criteria;
import com.yttx.yttxps.model.TRouteCCKey;
import com.yttx.yttxps.model.TRoutePropClass;
import com.yttx.yttxps.model.TRoutePropClassExample;
import com.yttx.yttxps.model.VResSnapshot;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IRouteArrangeService;


@Service("routeArrangeService")
public class RouteArrangeService implements IRouteArrangeService {

	@Autowired
	private IPubService<TRouteArrange> pubService;
	
	@Autowired
	private TRouteArrangeMapper<TRouteArrange> routeArrangeMapper;
	
	@Autowired
	private TRouteCCMapper<TRouteCCKey> routeCCMapper;
	
	@Autowired
	private TResTypeDircMapper resTypeDircMapper;
	
	@Autowired
	private TRoutePropClassMapper routePropClassMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return routeArrangeMapper.selectCountSelective(map);
	}

	@Override
	public List<TRouteArrange> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, routeArrangeMapper);
	}

	@Override
	public int insert(TRouteArrangeWithBLOBs record) {
		record.setFsId(String.format("%010d", routeArrangeMapper.selectFsId()));
		for(TRouteCCKey routeCC : record.getRoutecc()) {
			routeCC.setFsRouteno(record.getFsId());
			routeCCMapper.insert(routeCC);
		}
		
		TRoutePropClass routePropClass = new TRoutePropClass();
		routePropClass.setFiClass(new BigDecimal(record.getFsProperty()));
		routePropClass.setFsId(record.getFsId());
		routePropClass.setFiClasstype(BigDecimal.ONE);
		routePropClassMapper.insert(routePropClass);
		return routeArrangeMapper.insert(record);
	}
	
	@Override
	public void insertRouteCC(TRouteArrangeWithBLOBs record) {
		for(TRouteCCKey routeCC : record.getRoutecc()) {
			routeCCMapper.insert(routeCC);
		}
		TRouteArrangeWithBLOBs routeArrange = routeArrangeMapper.selectByPrimaryKey(record.getFsId());
		creatFcRessnapshot(routeArrange);
	}

	@Override
	public int update(TRouteArrangeWithBLOBs record) {
		TResTypeDircExample reesTypeDircExample = new TResTypeDircExample();
		com.yttx.yttxps.model.TResTypeDircExample.Criteria reesTypeDircCriteria = reesTypeDircExample.createCriteria();
		reesTypeDircCriteria.andFsRespropEqualTo("comm");
		List<TResTypeDirc> reesTypeDircList = resTypeDircMapper.selectByExample(reesTypeDircExample);
		List<String> restTypeList = new ArrayList<String>();
		for(TResTypeDirc dirc : reesTypeDircList) {
			restTypeList.add(dirc.getFsRestype());
		}
		
		TRouteCCExample example = new TRouteCCExample();
		Criteria criteria = example.createCriteria();
		criteria.andFsRoutenoEqualTo(record.getFsId());
		criteria.andFsRestypeIn(restTypeList);
		routeCCMapper.deleteByExample(example);
		for(TRouteCCKey routeCC : record.getRoutecc()) {
			routeCC.setFsRouteno(record.getFsId());
			routeCCMapper.insert(routeCC);
		}
		
		//update TRoutePropClass
		TRoutePropClass routePropClass = new TRoutePropClass();
		routePropClass.setFiClass(new BigDecimal(record.getFsProperty()));
		routePropClass.setFsId(record.getFsId());
		routePropClass.setFiClasstype(BigDecimal.ONE);
		
		TRouteArrangeWithBLOBs obsoleteRouteArrange = routeArrangeMapper.selectByPrimaryKey(record.getFsId());
		TRoutePropClassExample routePropClassExample = new TRoutePropClassExample();
		com.yttx.yttxps.model.TRoutePropClassExample.Criteria routePropClassExampleCriteria = routePropClassExample.createCriteria();
		routePropClassExampleCriteria.andFsIdEqualTo(record.getFsId());
		routePropClassExampleCriteria.andFiClassEqualTo(new BigDecimal(obsoleteRouteArrange.getFsProperty()));
		routePropClassExampleCriteria.andFiClasstypeEqualTo(BigDecimal.ONE);
		routePropClassMapper.updateByExample(routePropClass, routePropClassExample);
		
		return routeArrangeMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public void updateRouteCC(TRouteArrangeWithBLOBs record) {
		TResTypeDircExample reesTypeDircExample = new TResTypeDircExample();
		com.yttx.yttxps.model.TResTypeDircExample.Criteria reesTypeDircCriteria = reesTypeDircExample.createCriteria();
		reesTypeDircCriteria.andFsRespropEqualTo("comm");
		List<TResTypeDirc> reesTypeDircList = resTypeDircMapper.selectByExample(reesTypeDircExample);
		List<String> restTypeList = new ArrayList<String>();
		for(TResTypeDirc dirc : reesTypeDircList) {
			restTypeList.add(dirc.getFsRestype());
		}
		
		TRouteCCExample example = new TRouteCCExample();
		Criteria criteria = example.createCriteria();
		criteria.andFiDayflagEqualTo(record.getFiDayflag());
		criteria.andFsRoutenoEqualTo(record.getFsId());
		criteria.andFsRestypeNotIn(restTypeList);
		routeCCMapper.deleteByExample(example);
		for(TRouteCCKey routeCC : record.getRoutecc()) {
			routeCCMapper.insert(routeCC);
		}
		
		TRouteArrangeWithBLOBs routeArrange = routeArrangeMapper.selectByPrimaryKey(record.getFsId());
		creatFcRessnapshot(routeArrange);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(String no) {
		routeArrangeMapper.deleteByPrimaryKey(no);
		//删除线路产品-资源
		TRouteCCExample example = new TRouteCCExample();
		Criteria criteria = example.createCriteria();
		criteria.andFsRoutenoEqualTo(no);
		routeCCMapper.deleteByExample(example);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteRouteCC(String no) {
		TResTypeDircExample reesTypeDircExample = new TResTypeDircExample();
		com.yttx.yttxps.model.TResTypeDircExample.Criteria reesTypeDircCriteria = reesTypeDircExample.createCriteria();
		reesTypeDircCriteria.andFsRespropEqualTo("comm");
		List<TResTypeDirc> reesTypeDircList = resTypeDircMapper.selectByExample(reesTypeDircExample);
		List<String> restTypeList = new ArrayList<String>();
		for(TResTypeDirc dirc : reesTypeDircList) {
			restTypeList.add(dirc.getFsRestype());
		}
		
		TRouteCCExample example = new TRouteCCExample();
		Criteria criteria = example.createCriteria();
		criteria.andFsRoutenoEqualTo(no);
		criteria.andFsRestypeNotIn(restTypeList);
		routeCCMapper.deleteByExample(example);
		
		//清空模糊资源
		TRouteArrangeWithBLOBs record = new TRouteArrangeWithBLOBs();
		record.setFsId(no);
		record.setFcRessnapshot("");
		routeArrangeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<TRouteArrange> selectTRouteArrange(TRouteArrangeExample example) {
		return routeArrangeMapper.selectByExample(example);
	}
	
	public List<RouteCCType> findRouteCCType(Map<String, Object> map) {
		return routeArrangeMapper.selectRouteCCType(map);
	}
	
	public List<TRouteCCKey> findTRouteCCKey(TRouteCCExample example) {
		return routeCCMapper.selectByExample(example);
	}
	
	public int findRouteCCCount(TRouteCCExample example) {
		return routeCCMapper.countByExample(example);
	}
	
	public TRouteArrangeWithBLOBs findTRouteArrange(String fsId) {
		return routeArrangeMapper.selectByPrimaryKey(fsId);
	}
	
	private void creatFcRessnapshot(TRouteArrangeWithBLOBs routeArrange) {
		Document fcRessnapshot = new Document();
		Element root = new Element("root");
		Namespace space = Namespace.getNamespace("h", "http://www.yttx.com/");
		root.setNamespace(space);
		Element body = new Element("body");
		root.addContent(body);
		fcRessnapshot.setRootElement(root);
		
		BigDecimal fiDays = routeArrange.getFiDays();
		if(fiDays != null) {
			for(int i = 0; i < fiDays.intValue(); i ++) {
				Element day = new Element("daylist");
				Element dayFlag = new Element("dayflag").setText(Integer.toString(i));
				day.addContent(dayFlag);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("fsRouteno", routeArrange.getFsId());
				map.put("fiDayflag", BigDecimal.valueOf(i));
				List<VResSnapshot> list = routeArrangeMapper.selectRessnapshot(map);
				for(VResSnapshot v : list) {
					Element resList = new Element("reslist");
					resList.addContent(new Element("restype").setText(v.getResType()));
					resList.addContent(new Element("resprop").setText(v.getResProp()));
					resList.addContent(new Element("resno").setText(v.getResNo()));
					resList.addContent(new Element("resname").setText(v.getResName()));
					day.addContent(resList);
				}
				body.addContent(day);
			}
		}
		
		XMLOutputter output = new XMLOutputter();
		Format format = Format.getCompactFormat();
		format.setEncoding("UTF-8");
		output.setFormat(format);
		String snapshotString = output.outputString(fcRessnapshot);
		TRouteArrangeWithBLOBs record = new TRouteArrangeWithBLOBs();
		record.setFsId(routeArrange.getFsId());
		record.setFcRessnapshot(snapshotString);
		routeArrangeMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 获取到价格之后车辆价格除以25再求最终单人报价
	 * @author marongcai
	 * 2016-3-25
	 */
	@Override
	public List<Map<String, Object>> selectRouteArrangeInfo(String fsRouteNo) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("fsRouteNo", fsRouteNo);
		List<Map<String,Object>> list = routeArrangeMapper.selectCost(map);
		for (Map<String, Object> resultMap : list) {
			BigDecimal price1 = (BigDecimal) resultMap.get("PRICE1");
			BigDecimal price2 = (BigDecimal) resultMap.get("PRICE2");
			if (price2 != null ){
				price2 = price2.divide(new BigDecimal(25));
			}else {
				price2 = BigDecimal.ZERO;
			}
			if(price1 == null){
				price1 = BigDecimal.ZERO;
			}
			resultMap.remove("PRICE1");
			resultMap.remove("PRICE2");
			resultMap.put("price", price1.add(price2));
		}
		return list;
	}
}
