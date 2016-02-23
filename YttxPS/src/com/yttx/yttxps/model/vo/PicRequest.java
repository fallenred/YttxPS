package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.Pic;

public class PicRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pic getPic() {
		return pic;
	}

	public void setPic(Pic pic) {
		this.pic = pic;
	}

	private Pic pic;

	public void copyPic(Map<String, Object> map) {
		if (pic != null) {
			map.put("index", pic.getIndex() == null ? 0:pic.getIndex());
			map.put("resType", pic.getResType() == null ? "" : pic.getResType());
			map.put("resNo", pic.getResNo() == null ? "" : pic.getResNo());
			map.put("seq", pic.getSeq() == null ? 0: pic.getSeq());
			map.put("main", pic.getMain() == null ? "" : pic.getMain());
			map.put("srcfile",pic.getSrcFile() == null ? "" : pic.getSrcFile());
			map.put("desc",pic.getDesc() == null ? "" : pic.getDesc());
		}
	}
}