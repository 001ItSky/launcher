package org.dxl.server;

import org.dxl.servlet.BaseServlet;

import java.util.List;
import java.util.Map;

/**
 * App的意思是应用程序
 *
 * @author Administrator
 */
public class WebApp {
    /**
     * 上下文
     */
    private static ServletContext context;

    static {
        context = new ServletContext();
        //分别获取对应关系的Map集合
        Map<String, String> servlet = context.getServlet();
        Map<String, String> mapping = context.getMapping();
        //创建解析XML文件对象
        WebDom4j web = new WebDom4j();
        //解析xml
        web.parse(web.getDocument());
        //获取解析XML之后的List集合
        List<Entity> entityList = web.getEntityList();
        List<Mapping> mappingList = web.getMappingList();

        //将List集合中的数据存储到Map集合
        for (Entity entity : entityList) {
            servlet.put(entity.getName(), entity.getClazz());
        }
        /* System.out.println(servlet); */
        for (Mapping map : mappingList) {
            //遍历url-pattern的集合
            List<String> urlPattern = map.getUrlPattern();
            for (String s : urlPattern) {
                mapping.put(s, map.getName());
            }
        }
        //System.out.println(mapping);
    }

    /**
     * 根据url创建不同的Servlet对象
     *
     * @param url 地址
     * @return Servlet
     */
    public static BaseServlet getServlet(String url) {
        if (url == null || url.trim().equals("")) {
            return null;
        }

        //调用无参构造方法创建Servlet对象
        try {
            //如果url正确
            //根据url的key获取servlet-name的值 /log=login, /reg=register
            String servletName = context.getMapping().get(url);
            //根据servletName得到对应的servlet-class
            //得到的是一个完整个的包名+类的字符串
            String servletClass = context.getServlet().get(servletName);
            //使用反射创建 Servlet对象
            Class<?> clazz = Class.forName(servletClass);
            return (BaseServlet) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 测试
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        System.out.println(getServlet("/log"));
        System.out.println(getServlet("/login"));
    }
}
