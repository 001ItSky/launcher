//package org.dxl;
//
//import chrriis.common.UIUtils;
//import chrriis.dj.nativeswing.swtimpl.NativeInterface;
//import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
//import lombok.Data;
//
//import javax.swing.*;
//import javax.swing.plaf.ComponentUI;
//import javax.swing.plaf.PanelUI;
//import java.awt.*;
//
///**
// * * @projectName launcher
// * * @title MainTest
// * * @package org.dxl
// * * @description  该类的作用描述，必填  
// * * @author IT_CREAT     
// * * @date  2020 2020/5/28/028 22:29  
// * * @version 1.0.0
// */
//@Data
//public class MainTest extends JPanel {
//
//    private JPanel webBrowserPanel;
//
//    private JWebBrowser webBrowser;
//
//    public MainTest(String url) {
//        super(new BorderLayout());
//        webBrowserPanel = new JPanel(new BorderLayout());
//        webBrowser = new JWebBrowser();
//        webBrowser.navigate(url);
//        webBrowser.setButtonBarVisible(false);
//        webBrowser.setMenuBarVisible(false);
//        webBrowser.setBarsVisible(false);
//        webBrowser.setStatusBarVisible(false);
//        webBrowser.setLocationBarVisible(false);
//        webBrowser.setToolTipText("炫彩浏览器");
//        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
//        add(webBrowserPanel, BorderLayout.CENTER);
//        //执行Js代码
////        webBrowser.executeJavascript("alert('hello swing')");
//    }
//
//    /**
//     * 在swing里内嵌浏览器
//     *
//     * @param url   要访问的url
//     * @param title 窗体的标题
//     */
//    public static void openForm(String url, String title) {
//        UIUtils.setPreferredLookAndFeel();
//        NativeInterface.open();
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                JFrame frame = new JFrame(title);
//                //设置窗体关闭的时候不关闭应用程序
//                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                frame.getContentPane().add(new MainTest(url), BorderLayout.CENTER);
//                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//                frame.setLocationByPlatform(true);
//                //让窗体可见
//                frame.setVisible(true);
//                //重置窗体大小
//                frame.setResizable(true);
//                // 设置窗体的宽度、高度
//                frame.setSize(1400, 700);
//                // 设置窗体居中显示
//                frame.setLocationRelativeTo(frame.getOwner());
//            }
//        });
//        NativeInterface.runEventPump();
//    }
//
//    public static void main(String[] args) {
////        openForm("http://132.232.39.242:8080", "hello swing");
//        openForm("http://www.youku.com", "炫彩浏览器");
//    }
//
//
//}
