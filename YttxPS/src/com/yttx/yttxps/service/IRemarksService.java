package com.yttx.yttxps.service;


import java.util.List;

import com.yttx.yttxps.model.TRemarks;
import com.yttx.yttxps.model.TRemarksExample;


public interface IRemarksService {
	
	List<TRemarks> selectRemarks(TRemarksExample example);
	
}
