package com.yttx.yttxps.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TRemarksMapper;
import com.yttx.yttxps.model.TRemarks;
import com.yttx.yttxps.model.TRemarksExample;
import com.yttx.yttxps.service.IRemarksService;


@Service("remarksService")
public class RemarksService implements IRemarksService {

	@Autowired
	private TRemarksMapper<TRemarks> remarksMapper;
	
	@Override
	public List<TRemarks> selectRemarks(TRemarksExample example) {
		// TODO Auto-generated method stub
		return remarksMapper.selectByExample(example);
	}

}
