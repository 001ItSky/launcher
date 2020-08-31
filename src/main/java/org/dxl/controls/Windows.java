package org.dxl.controls;

import com.sun.java.swing.plaf.windows.resources.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


/**
 * * @projectName launcher
 * * @title Windows
 * * @package org.dxl
 * * @description  该类的作用描述，必填  
 * * @author IT_CREAT     
 * * @date  2020 2020/5/26/026 0:14  
 * * @version 1.01.0
 */
public class Windows extends JFrame {

    public Windows(String title) throws HeadlessException {
        // 加上这一句
        this.setLayout(new BorderLayout());
        //设置显示窗口标题
        setTitle(title);
        //设置窗口显示尺寸
        setSize(800, 400);
        //在屏幕中居中显示
        setLocationRelativeTo(getOwner());
        //置窗口是否可以关闭
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置图标
        setIconImage(getWindowTitleIcon(this));
//        Container contentPane = getContentPane();
//        contentPane.setBackground(new Color(38, 51, 49));
    }

    public static Image getWindowTitleIcon(JFrame jFrame) {
        return getImage(jFrame);
    }

    public static Image getDialogTitleIcon(JDialog jDialog) {
        return getImage(jDialog);
    }

    public static Image getImage(Window window) {
        String path = Windows.class.getResource("/img/head.png").getPath();
        //得到一个Toolkit对象
        Toolkit tool = window.getToolkit();
        //由tool获取图像
        return tool.getImage(path);
    }


}
