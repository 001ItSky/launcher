package org.dxl.opt;

import lombok.extern.slf4j.Slf4j;
import org.dxl.controls.Windows;
import org.dxl.server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * * @projectName launcher
 * * @title WindowMain
 * * @package org.dxl.opt
 * * @description  整个window窗体的设置
 * * @author IT_CREAT     
 * * @date  2020 2020/5/26/026 23:10  
 * * @version 1.0.0
 */
@Slf4j
public class WindowMain {
    public static boolean start = false;

    /**
     * 创建服务器对象
     */
    static Server server = new Server();

    public static JTextArea infoTextArea = new JTextArea();

    public static JButton startAndStopButton = new JButton("启动");

    /**
     * 换行
     */
    private static final String CRLF = "\r\n";

    /**
     * 创建一个事件监听器
     * 注意： actionListener是类的属性
     */
    private static final ActionListener ACTION_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            String action = e.getActionCommand();
            if ("start".equals(action)) {

            } else if ("stop".equals(action)) {

            }
        }
    };

    public static void createHead(Container root) {
        // 创建工具栏
        JToolBar toolBar = new JToolBar();
        // 让工具栏固定、不允许浮动
        toolBar.setFloatable(false);
        root.add(toolBar, BorderLayout.PAGE_START);
        // 向工具栏上添加按钮
        toolBar.add(toolButton("/img/start3.png", "startAndStop", "启动"));
//        toolBar.add(toolButton("/img/ic_open.png", "fileOpen", "打开"));
//        toolBar.add(toolButton("/img/ic_save.png", "fileSave", "保存"));
//        toolBar.add(toolButton("/img/ic_saveas.png", "fileSaveAs", "另存为"));
//        toolBar.addSeparator();
//        toolBar.add(toolButton("/img/ic_help.png", "fileHelp", "帮助"));
    }

    /**
     * 修改工具栏上的按钮
     *
     * @param button    工具按钮组件
     * @param imagePath 图片地址
     * @param tooltip   设置提示信息
     * @return 按钮组件
     */
    private static JButton updateToolButton(JButton button, String imagePath, String tooltip) {
        // 图标
        String path = WindowMain.class.getResource(imagePath).getPath();

        // 创建按钮
        button.setToolTipText(tooltip);
        button.setIcon(new ImageIcon(path));
        return button;
    }

    /**
     * 添加工具栏上的按钮
     *
     * @param imagePath 图片地址
     * @param action    标志信息
     * @param tooltip   设置提示信息
     * @return 按钮组件
     */
    private static JButton toolButton(String imagePath, String action, String tooltip) {
        // 图标
        String path = WindowMain.class.getResource(imagePath).getPath();

        // 创建按钮
        JButton button = new JButton();
        button.setActionCommand(action);
        button.setToolTipText(tooltip);
        button.setIcon(new ImageIcon(path));
        button.setFocusPainted(false);
        button.addActionListener(ACTION_LISTENER);
        return button;
    }

    private static JButton getButton(String imagePath, String action, String tooltip) {
        // 图标
        String path = WindowMain.class.getResource(imagePath).getPath();

        // 创建按钮
        JButton button = new JButton();
        button.setActionCommand(action);
        button.setToolTipText(tooltip);
        button.setIcon(new ImageIcon(path));
        button.setFocusPainted(false);
        button.addActionListener(ACTION_LISTENER);
        return button;
    }

    private static JButton updateButton(JButton button, String imagePath, String tooltip) {
        // 图标
        String path = WindowMain.class.getResource(imagePath).getPath();

        // 创建按钮
        button.setToolTipText(tooltip);
        button.setIcon(new ImageIcon(path));
        return button;
    }

    public static void editWindow(String title, String url) {
        Font font = new Font("宋体", Font.ITALIC, 12);
        Windows windows = new Windows(title);
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setFont(font);
        windows.add(contentPane);
        //创建工具栏
//        createHead(contentPane);
        //设置窗体关闭的时候不关闭应用程序
        JPanel centerPanel = new JPanel(new BorderLayout());
        contentPane.add(centerPanel, BorderLayout.CENTER);

        JPanel editPortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(editPortPanel, BorderLayout.NORTH);
        JLabel label = new JLabel("端口");
        label.setFont(font);
        //创建JTextField，16表示16列，用于JTextField的宽度显示而不是限制字符个数
        JTextField textPortField = new JTextField(20);
        textPortField.setFont(font);

        startAndStopButton.setFont(font);
        startAndStopButton.setBackground(new Color(78, 193, 245));
        startAndStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (!start) {
                            try {
                                String portStr = textPortField.getText();
                                int port = Integer.parseInt(portStr);
                                // 重置服务状态
                                server.restShutDown();
                                //启动服务器
                                new Thread(() -> {
                                    server.start(port);
                                }).start();
                            } catch (RuntimeException ex) {
                                JOptionPane.showMessageDialog(contentPane, "输入端口出现问题，启动失败！", "警告", JOptionPane.WARNING_MESSAGE);
                                return;
                            }

                            if (!server.isShutDown()) {
                                startAndStopButton.setText("关闭");
                                startAndStopButton.setBackground(new Color(253, 82, 111));
                                start = true;
                            }
                        } else {
                            startAndStopButton.setText("启动");
                            startAndStopButton.setBackground(new Color(78, 193, 245));

                            server.stop();
                            start = false;
                        }
                    }
                }).start();
            }
        });
        startAndStopButton.setVisible(true);

        editPortPanel.add(label);
        editPortPanel.add(textPortField);
        editPortPanel.add(startAndStopButton);

        JPanel infoPortPanel = new JPanel(new BorderLayout());
        centerPanel.add(infoPortPanel, BorderLayout.CENTER);

        infoTextArea.setBackground(new Color(38, 43, 51, 255));
        infoTextArea.setFont(font);
        infoTextArea.setVisible(true);
        infoTextArea.setEnabled(false);
        //把定义的JTextArea放到JScrollPane里面去
        JScrollPane scroll = new JScrollPane(infoTextArea);
        //分别设置水平和垂直滚动条自动出现
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        infoPortPanel.add(scroll, BorderLayout.CENTER);

        //重置窗体大小
        windows.setResizable(true);
        //设置窗口是否可见
        windows.setVisible(true);

        // 设置心跳检测
        headTime();
    }

    /**
     * 心跳检测
     */
    private static void headTime() {
        log.info("启动心跳检测开始。。。。。。");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (server.isShutDown()) {
                    startAndStopButton.setText("启动");
                    startAndStopButton.setBackground(new Color(78, 193, 245));
                    start = false;
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 6000);
        log.info("启动心跳检测成功。。。。。。");
    }

    public static void addInfo(String info) {
        infoTextArea.append(info + CRLF);
        infoTextArea.setSelectionStart(infoTextArea.getText().length());
    }

}
