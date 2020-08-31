package org.dxl.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Servlet上下用，就是一个容器，
 * Entity与Mapping的映射关系
 *
 * @author Administrator
 */
public class ServletContext {
	/**
	 * key是servlet-name  (Entity中的name),值serlvet-class Entity中的clazz
	 */
	private Map<String,String> servlet;
	/**
	 * key是url-pattern (Mapping中的List集合中的每一个元素),value是serlvet-name,是Mapping中的name
	 */
	private Map<String,String> mapping;

	public ServletContext() {
		servlet=new HashMap<String,String>();
		mapping=new HashMap<String,String>();
	}

	public Map<String, String> getServlet() {
		return servlet;
	}

	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}

	public Map<String, String> getMapping() {
		return mapping;
	}

	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
}
