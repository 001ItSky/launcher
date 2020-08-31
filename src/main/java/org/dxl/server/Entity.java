package org.dxl.server;

/**
 * <servlet>
		<servlet-name>login</servlet-name>
		<serlvet-class>com.bjsxt.servlet.LoginServlet</serlvet-class>
	</servlet>
 * servlet-name和一个servlet-name所对应的一个实体类
 * @author Administrator
 *
 */
public class Entity {
	/**
	 * servlet-name
	 */
	private String name;
	/**
	 * servlet-class
	 */
	private String clazz;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public Entity(String name, String clazz) {
		super();
		this.name = name;
		this.clazz = clazz;
	}
	public Entity() {
		super();
	}
	
}
