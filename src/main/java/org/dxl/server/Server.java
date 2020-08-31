package org.dxl.server;

import lombok.extern.slf4j.Slf4j;
import org.dxl.opt.WindowMain;
import org.dxl.util.IoCloseUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器，用于启动和停止服务
 *
 * @author Administrator
 */
@Slf4j
public class Server {
    private ServerSocket server;
    /**
     * 默认没有出错
     */
    private boolean isShutDown = false;

    public boolean isShutDown() {
        return isShutDown;
    }

    public void restShutDown(){
        this.isShutDown = false;
    }

    public static void main(String[] args) {
        // 创建服务器对象
        new Server().start();
    }

    public void start() {
        log.info("启动服务器开始。。。。。。。。。");
        WindowMain.addInfo("启动服务器开始。。。。。。。。。");
        this.start(8888);
        log.info("启动服务器成功。。。。。。。。。");
        WindowMain.addInfo("启动服务器成功。。。。。。。。。");
    }

    public void start(int port) {
        try {
            log.info("启动服务器开始。。。。。。。。。");
            WindowMain.addInfo("启动服务器开始。。。。。。。。。");
            server = new ServerSocket(port);
            isShutDown = false;
            log.info("启动服务器成功。。。。。。。。。");
            WindowMain.addInfo("启动服务器成功。。。。。。。。。");
            //调用接收请求信息的方法
            this.receive();
        } catch (IOException e) {
            log.error("启动服务器出错。。。。。。。。。");
            log.error(e.getMessage(), e);
            WindowMain.addInfo("启动服务器出错：" + e.getMessage());
            isShutDown = true;
            IoCloseUtil.closeAll(server);
        }
    }

    private void receive() {
        while (!isShutDown) {
            try {
                //(1)监听
                Socket client = server.accept();
                //创建线程类的对象
                if(!client.isClosed()){
                    Dispatcher dis = new Dispatcher(client);
                    //创建线程的代理类，并启动线程
                    Thread thread = new Thread(dis);
                    thread.start();
                }
            } catch (IOException e) {
                log.error("接收来自客户端Socket出错。。。。。。。。。");
                log.error(e.getMessage(), e);
                WindowMain.addInfo("接收来自客户端Socket出错：" + e.getMessage());
//			this.stop();//关闭服务器
            }
        }
        log.info("关闭服务器结束。。。。。。。。。");
        WindowMain.addInfo("关闭服务器结束。。。。。。。。。");
    }

    public void stop() {
        log.info("关闭服务器开始。。。。。。。。。");
        WindowMain.addInfo("关闭服务器开始。。。。。。。。。");
        isShutDown = true;
        if(!server.isClosed()){
            IoCloseUtil.closeAll(server);
        }
    }
}
