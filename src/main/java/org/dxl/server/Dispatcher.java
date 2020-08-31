package org.dxl.server;

import lombok.extern.slf4j.Slf4j;
import org.dxl.opt.WindowMain;
import org.dxl.servlet.BaseServlet;
import org.dxl.util.IoCloseUtil;

import java.io.IOException;
import java.net.Socket;

/**
 * 一个请求与响应就是一个Dispatcher
 *
 * @author Administrator
 */
@Slf4j
public class Dispatcher implements Runnable {
    private Request req;
    private Response rep;
    private Socket client;
    /**
     * 状态码
     */
    private int code = 200;

    /**
     * 构造方法初始化属性
     *
     * @param client Socket
     */
    public Dispatcher(Socket client) {
        //将局部变量的值赋给成员变量
        this.client = client;
        try {
            req = new Request(this.client.getInputStream());
            rep = new Response(this.client.getOutputStream());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            WindowMain.addInfo("构建请求响应的Dispatcher失败，失败原因：" + e.getMessage());
            code = 500;
        }
    }

    @Override
    public void run() {
        // 根据不同的url创建指定的Servlet对象
        /* System.out.println(req.getUrl()); */
        BaseServlet servlet = WebApp.getServlet(req.getUrl());
        if (servlet == null) {
            this.code = 404;
            log.error("配置映射接口不存在");
            WindowMain.addInfo("配置映射接口不存在");
        } else {
            //调用相应的Servlet中的service方法
            try {
                servlet.service(req, rep);
            } catch (Exception e) {
                this.code = 500;
                log.error("后台服务器处理异常：500");
                log.error(e.getMessage(), e);
                WindowMain.addInfo("后台服务器处理异常:500");
            }
        }
        //将响应结果推送到客户机的浏览器
        rep.pushToClient(code);
        IoCloseUtil.closeAll(client);
    }

}
