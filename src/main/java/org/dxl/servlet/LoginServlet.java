package org.dxl.servlet;

import org.dxl.server.Request;
import org.dxl.server.Response;

/**
 * 登录请求
 *
 * @author Administrator
 */
public class LoginServlet extends BaseServlet {

    @Override
    public void doGet(Request req, Response rep) throws Exception {
        //获取请求参数
        String name = req.getParameter("username");
        String pwd = req.getParameter("pwd");

        if (this.login(name, pwd)) {
            //调用响应中的构建内容的方
            rep.println(name + "登录成功");
        } else {
            rep.println(name + "登录失败，对不起，账号或密码不正确");
        }
    }

    private boolean login(String name, String pwd) {
        if ("bjsxt".equals(name) && "123".equals(pwd)) {
            return true;
        }
        return false;
    }

    @Override
    public void doPost(Request req, Response rep) throws Exception {
        // TODO Auto-generated method stub
    }
}
