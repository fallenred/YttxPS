package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.CustomInfoMapper;
import com.yttx.yttxps.model.CustomInfo;
import com.yttx.yttxps.service.IMemberService;
import com.yttx.yttxps.service.IPubService;

/**
 * 会员管理模块--会员管理Service的实现类
 */
@Service("memberService")
public class MemberService implements IMemberService {

	@Autowired
	private IPubService<CustomInfo> pubService;
	
	@Autowired
	private CustomInfoMapper customInfoMapper;
	
	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return customInfoMapper.selectCountSelective(map);
	}

	@Override
	public List<CustomInfo> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, customInfoMapper);
	}

	@Override
	public CustomInfo selectCusById(String id) {
		return customInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateCusSelective(CustomInfo customInfo) {
		customInfoMapper.updateByPrimaryKeySelective(customInfo);
		return true;
	}
}
