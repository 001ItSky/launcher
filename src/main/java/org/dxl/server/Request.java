package org.dxl.server;

import lombok.extern.slf4j.Slf4j;
import org.dxl.opt.WindowMain;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * 请求
 *
 * @author Administrator
 */
@Slf4j
public class Request {
    /**
     * 输入流
     */
    private InputStream is;
    /**
     * 请求字符串,请求方式，请求的路径，参数，协议，协议版本，请求的正文。。。
     */
    private String requestInfo;
    /**
     * 请求的方式
     */
    private String method;
    /**
     * 请求的url
     */
    private String url;

    /**
     * 参数
     * 输入框的name为key,值为value
     * key: username    value  :bjsxt
     * key:pwd          value:123
     * key:hobby        value   :read,ball
     */
    private Map<String, List<String>> parametermapValues;

    /**
     * 换行
     */
    private static final String CRLF = "\r\n";
    /**
     * 空格
     */
    private static final String BLANK = " ";

    /**
     * 构造方法，初始化属性
     */
    public Request() {
        parametermapValues = new HashMap<>();
        method = "";
        url = "";
        requestInfo = "";
    }

    public Request(InputStream is) {
        //调用本类无参的构造方法
        this();
        this.is = is;
        try {
            byte[] buf = new byte[20480];
            int len = this.is.read(buf);
            if (len != -1) {
                requestInfo = new String(buf, 0, len);
            }
            log.info("接收到客户端发送信息：" + CRLF + requestInfo);
            WindowMain.addInfo("接收到客户端发送信息：" + CRLF + requestInfo);
        } catch (Exception e) {
            log.error("读取客户端发送的信息出错");
            log.error(e.getMessage(), e);
            WindowMain.addInfo("读取客户端发送的信息出错：" + e.getMessage());
            return;
        }
        //调用本类中的分解请求信息的方法
        log.info("开始解析请求参数。。。。");
        WindowMain.addInfo("开始解析请求参数。。。。");
        this.parseRequestInfo();
        log.info("结束解析请求参数。。。。");
        WindowMain.addInfo("结束解析请求参数。。。。");
    }

    public String getUrl() {
        return url;
    }

    /**
     * 分解请求信息的方法
     * 请求方式
     * 请求路径
     * 请求的参数
     */
    private void parseRequestInfo() {
        if (requestInfo == null || "".equals(requestInfo.trim())) {
            return;
        }
        //用于存储请求参数
        String paraString = "";
        //获取请求参数的第一行
        //从0开始，到第一个换行的位置
        String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF)).trim();
        //分解出请求方式
        int index = firstLine.indexOf("/");
        this.method = firstLine.substring(0, index).trim();
        //分解url  ,get可能包含参数，也可能不包含参数post
        String urlString = firstLine.substring(index, firstLine.indexOf("HTTP/")).trim();
        //判断请求方式是GET还 是POST
        //包含请求参数
        if ("get".equalsIgnoreCase(this.method)) {
            if (urlString.contains("?")) {
                String[] urlArray = urlString.split("\\?");
                this.url = urlArray[0];
                paraString = urlArray[1];
            } else {
                this.url = urlString;
            }
        } else { // post不包含请求参数
            this.url = urlString;
            paraString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
        }
        if ("".equals(paraString)) {
            return;
        }
        // 请求参数
        /* System.out.println(paraString); */

        // 调用本类的中的分析请求参数的方法
        this.parseParam(paraString);
    }

    /**
     * 解析get传输的参数
     * username=bjsxt
     * pwd=123
     * hobby=ball
     * hobby=paint
     * <p>
     * username=
     *
     * @param paramString 参数
     */
    private void parseParam(String paramString) {
        if (paramString == null || "".equals(paramString.trim())) {
            return;
        }
        String[] token = paramString.split("&");
        for (int i = 0; i < token.length; i++) {
            // username= fasaf
            String keyValues = token[i];

            //继续分割
            //username=
            String[] keyValue = keyValues.split("=");
            if (keyValue.length == 1) {
                keyValue = Arrays.copyOf(keyValue, 2);
                keyValue[1] = null;
            }
            //转成Map集合
            String key = keyValue[0].trim();
            String value = keyValue[1] == null ? null : decode(keyValue[1].trim(), "utf-8");
            //放到参数的集合中存储
            if (!parametermapValues.containsKey(key)) {
                parametermapValues.put(key, new ArrayList<String>());
            }
            List<String> values = parametermapValues.get(key);
            values.add(value);
        }
    }

    /**
     * 编写根据表单元素的name获取多个对应的值
     */
    public String[] getParamterValues(String name) {
        List<String> values = parametermapValues.get(name);
        if (values == null) {
            return null;
        } else {
            return values.toArray(new String[0]);
        }
    }

    /**
     * 根据表单元素的name获取单个值
     */
    public String getParameter(String name) {
        //调用根据名称获取多个值的方法
        String[] values = getParamterValues(name);
        if (values == null) {
            return null;
        } else {
            return values[0];
        }
    }

    /**
     * 处理中文，因为浏览器对中文进行了编码，进行解码
     *
     * @param value   等待解码的值
     * @param charset 编码格式，字符集
     * @return 解码后的值
     */
    private String decode(String value, String charset) {
        try {
            return URLDecoder.decode(value, charset);
        } catch (UnsupportedEncodingException e) {
            log.error("解码客户端发送的信息出错");
            log.error(e.getMessage(), e);
            WindowMain.addInfo("解码客户端发送的信息出错：" + e.getMessage());
        }
        return null;
    }

    /**
     * 用于测试
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        Request req = new Request();
        //调用分解参数的方法
        req.parseParam("username=%E5%8C%97%E4%BA%AC%E5%B0%9A%E5%AD%A6%E5%A0%82&pwd=123&hobby=ball&hobby=paint");
        System.out.println(req.parametermapValues);

        //调用获取多个值的方法
        String[] str = req.getParamterValues("hobby");
        for (String string : str) {
            System.out.println(string);
        }
        //调用获取单个值的方法
        System.out.println(req.getParameter("pwd"));
    }
}
