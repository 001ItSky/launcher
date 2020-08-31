package org.dxl.servlet;

import org.dxl.server.Request;
import org.dxl.server.Response;

/**
 * 是所有的请求的Servlet的父类
 * @author Administrator
 */
public abstract class BaseServlet {

	public void service(Request req, Response rep) throws Exception{
		this.doGet( req, rep);
		this.doPost( req, rep);
	}

	/**
	 * get请求
	 * @param req Request
	 * @param rep rep
	 * @throws Exception 异常
	 */
	public abstract void doGet(Request req,Response rep) throws Exception;

	/**
	 * post请求
	 * @param req Request
	 * @param rep Response
	 * @throws Exception 异常
	 */
	public abstract void doPost(Request req,Response rep) throws Exception;
}
