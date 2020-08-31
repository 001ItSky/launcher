package org.dxl;

import lombok.extern.slf4j.Slf4j;
import org.dxl.opt.WindowMain;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
@Slf4j
public class App {

    public static void main(String[] args) throws IOException {
        log.info("启动器正在启动。。。。。。");
        start();
        log.info("启动器启动成功。。。。。。");
    }

    /**
     * 线程池启动程序
     */
    public static void start(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MINUTES, new LinkedBlockingDeque<>(1));
        Runnable startRunnable = new Runnable() {
            @Override
            public void run() {
                WindowMain.editWindow("启动器", "http://localhost:8080");
            }
        };
        threadPoolExecutor.execute(startRunnable);
    }

}
