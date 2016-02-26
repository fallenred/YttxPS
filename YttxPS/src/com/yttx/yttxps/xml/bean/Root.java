package com.yttx.yttxps.xml.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("body")
public class Root {
	public Root() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Root(Body body) {
		super();
		this.body = body;
	}

	@XStreamAlias("body")
	private Body body;

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	
}
