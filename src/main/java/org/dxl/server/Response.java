package org.dxl.server;

import lombok.extern.slf4j.Slf4j;
import org.dxl.opt.WindowMain;
import org.dxl.util.IoCloseUtil;

import java.io.*;

/**
 * 响应
 *
 * @author Administrator
 */
@Slf4j
public class Response {
    /**
     * 响应头
     */
    private StringBuilder headInfo;
    /**
     * 响应内容
     */
    private StringBuilder content;
    /**
     * 响应内容的长度
     */
    private int length;
    /**
     * 流
     */
    private BufferedWriter bw;

    /**
     * 换行
     */
    private static final String CRLF = "\r\n";
    /**
     * 空格
     */
    private static final String BLANK = " ";

    /**
     * 构造方法
     */
    public Response() {
        headInfo = new StringBuilder();
        content = new StringBuilder();
    }

    /**
     * 带参构造方法
     *
     * @param os OutputStream
     */
    public Response(OutputStream os) {
        this();//调用本类的无参构造方法
        try {
            bw = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("构造response输出流出错");
            log.error(e.getMessage(), e);
            WindowMain.addInfo("构造response输出流出错：" + e.getMessage());
            headInfo = null;
        }
    }

    /**
     * 构造正文部分
     *
     * @param info 信息
     * @return Response
     */
    public Response print(String info) {
        content.append(info);
        try {
            length += info.getBytes("utf-8").length;
        } catch (UnsupportedEncodingException e) {
            log.error("构造response输出信息出错");
            log.error(e.getMessage(), e);
            WindowMain.addInfo("构造response输出信息出错：" + e.getMessage());
        }
        return this;
    }

    /**
     * Response
     *
     * @param info 信息
     * @return Response
     */
    public Response println(String info) {
        content.append(info).append(CRLF);
        try {
            length += (info + CRLF).getBytes("utf-8").length;
        } catch (UnsupportedEncodingException e) {
            log.error("构造response输出信息出错");
            log.error(e.getMessage(), e);
            WindowMain.addInfo("构造response输出信息出错：" + e.getMessage());
        }
        return this;
    }

    /**
     * 构造响应头
     *
     * @param code 错误码
     */
    private void createHeadInfo(int code) {
        log.info("开始创建响应头信息。。。。");
        WindowMain.addInfo("开始创建响应头信息。。。。");
        headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
        switch (code) {
            case 200:
                headInfo.append("OK");
                break;
            case 500:
                headInfo.append("SERVER ERROR");
                break;
            default:
                headInfo.append("NOT FOUND");
                break;
        }
        headInfo.append(CRLF);
        headInfo.append("Content-Type:text/html;charset=utf-8").append(CRLF);
        headInfo.append("Content-Length:").append(length).append(CRLF);
        headInfo.append(CRLF);
        log.info("响应头信息：" + CRLF + headInfo.toString());
        WindowMain.addInfo("响应头信息：" + CRLF + headInfo.toString());
        log.info("结束创建响应头信息。。。。");
        WindowMain.addInfo("结束创建响应头信息。。。。");
    }

    /**
     * 推送到客户机的浏览器
     *
     * @param code 错误码
     */
    public void pushToClient(int code) {
        if (headInfo == null) {
            code = 500;
        }
        try {
            //调用本类中的构造响应头
            this.createHeadInfo(code);
            bw.write(headInfo.toString());
            bw.write(content.toString());
            bw.flush();
        } catch (IOException e) {
            log.error("向客户端回写信息出错");
            log.error(e.getMessage(), e);
            WindowMain.addInfo("向客户端回写信息出错：" + e.getMessage());
        } finally {
            this.close();
        }
    }

    public void close() {
        IoCloseUtil.closeAll(bw);
    }
}
