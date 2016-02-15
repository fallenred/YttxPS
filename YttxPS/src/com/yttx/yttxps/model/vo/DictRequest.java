package com.yttx.yttxps.model.vo;

import com.yttx.yttxps.model.Dict;
import com.yttx.yttxps.model.DictExample;
import com.yttx.yttxps.model.DictExample.Criteria;

public class DictRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

	private Dict dict;

	public void copyExample(DictExample example) {
		Criteria criteria = example.createCriteria();
		if (dict != null) {
			if (dict.getFsParentno() != null)
				criteria.andFsParentnoEqualTo(dict.getFsParentno());
			if (dict.getFsDictno() != null)
				criteria.andFsDictnoEqualTo(dict.getFsDictno());
		}
		example.setOrderByClause("FS_DICTNO");
	}
}