package com.yttx.yttxps.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.yttx.yttxps.model.Pic;

/**
 * 更新图片顺序，SpringＭＶＣ接收图片的请求类
 */
public class PicListWebRequest {
	private List<Pic> piclist=new ArrayList<Pic>();

	public List<Pic> getPiclist() {
		return piclist;
	}

	public void setPiclist(List<Pic> piclist) {
		this.piclist = piclist;
	}	
}
