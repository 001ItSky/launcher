package org.dxl.server;

import java.util.ArrayList;
import java.util.List;

/**
 * <servlet-mapping>
		<serlvet-name>login</serlvet-name>
		<url-pattern>/login</url-pattern>
		<url-pattern>/log</url-pattern>
	</servlet-mapping>
 * 映射关系,多个路径访问共享资源
 * @author Administrator
 *
 */
public class Mapping {
	/**
	 * servlet-name
	 */
	private String name;
	/**
	 * url-pattern
	 */
	private List<String> urlPattern;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getUrlPattern() {
		return urlPattern;
	}
	public void setUrlPattern(List<String> urlPattern) {
		this.urlPattern = urlPattern;
	}
	public Mapping(){
		urlPattern=new ArrayList<String>();
	}
	public Mapping(String name, List<String> urlPattern) {
		super();
		this.name = name;
		this.urlPattern = urlPattern;
	}
}
