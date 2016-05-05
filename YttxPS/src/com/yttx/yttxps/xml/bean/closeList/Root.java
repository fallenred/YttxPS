package com.yttx.yttxps.xml.bean.closeList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.yttx.yttxps.xml.bean.Body;

@XStreamAlias("body")
public class Root {

	@XStreamAlias("body")
	private Body body;
	
	public Root() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Root(Body body) {
		super();
		this.body = body;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	
}
