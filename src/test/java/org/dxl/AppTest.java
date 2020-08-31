package org.dxl;

import org.dxl.controls.Windows;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.Charset;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

    }

    public static void main(String[] args) {
//        //总窗体
//        Frame frame = new Frame();
//        frame.setSize(400,200);//设置窗体大小
//        frame.setLocation(500,500);//设置窗体位置
//        frame.setBackground(Color.white);//设置窗体背景颜色
//        frame.setVisible(true);//设置窗体可见性为可见
//        frame.setLayout(new GridLayout(2,1));//主窗体设置为上下两行栅格布局
//        //四个面板
//        Panel panel1 = new Panel(new BorderLayout());//设置面板1为边界模式
//        Panel panel2 = new Panel(new GridLayout(2,1));//设置面板2为上下两行栅格布局
//        Panel panel3 = new Panel(new BorderLayout());//设置面板3为边界模式
//        Panel panel4 = new Panel(new GridLayout(2,2));//设置面板3上下两行两列栅格布局
//        //两个按钮填充panel1面板左右
//        panel1.add(new Button("button1-EAST"),BorderLayout.EAST);//左，也就是西
//        panel1.add(new Button("button1-WEST"),BorderLayout.WEST);//右，也就是东
//        //两个按钮依次填充panel2面板上下两行
//        panel2.add(new Button("button-p2-1"));
//        panel2.add(new Button("button-p2-2"));
//        //panel2面板填充panel1面板中心
//        panel1.add(panel2,BorderLayout.CENTER);
//        //两个按钮填充panel3面板左右
//        panel3.add(new Button("button2-EAST"),BorderLayout.EAST);//左，也就是西
//        panel3.add(new Button("button2-WEST"),BorderLayout.WEST);//右，也就是东
//        for(int i= 0;i<4;i++){
//            //循环四次依次添加按钮填充panel4面板的两行两列四个部分
//            panel4.add(new Button("button-p4"+i));
//        }
//        //panel4面板填充panel3面板中心
//        panel3.add(panel4,BorderLayout.CENTER);
//        //panel1和panel3面板填充窗体上下两行布局
//        frame.add(panel1);
//        frame.add(panel3);
//        //设置监听点击窗口关闭事件，然后结束程序
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
//
//        Button button = new Button();
//        button.setActionCommand("test");
//        //监听一个动作事件（不同的组件针对的动作不同，这里按钮是点击事件）
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //e.getActionCommand()的值也就是我们button.setActionCommand("test")设置的值，这样我们可以根据这个值来对不同的按钮的点击事件进行监听区分处理
//                System.out.println(e.getActionCommand());
//            }
//        });
//
//        TextField textField = new TextField();
//        //监听一个动作事件（不同的组件针对的动作不同，这里输入后回车事件）
//        textField.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                TextField source = (TextField) e.getSource();//获得一些资源，返回一个对象
//                System.out.println(source.getText());//获得输入文本框的内容
//            }
//        });
//
//        frame.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//
//        frame.addKeyListener(new KeyAdapter() {
//            //键盘按下事件
//            @Override
//            public void keyPressed(KeyEvent e) {
//                int keyCode = e.getKeyCode();//不需要记录这个数值，直接使用静态属性VK_xxx
//                System.out.println(keyCode);
//                if(keyCode == KeyEvent.VK_UP){
//                    System.out.println("你按下了上键");
//                }
//            }
//        });

        JFrame jFrame = new JFrame("这是一个jFrame窗体");
        jFrame.setVisible(true);//设置显性显示窗体
        jFrame.setBounds(200,200,400,300);//设置窗体位置和大小
        jFrame.setLayout(new FlowLayout());
        Container contentPane = jFrame.getContentPane();//获得一个容器，jFrame放的东西在容器中
        contentPane.setBackground(Color.orange);//必须获得容器给容器设置背景色才行
//        jFrame.setBackground(Color.blue); //直接设置是不行的，这个地方是swing和awt不同的地方
        JLabel jLabel = new JLabel("欢迎来到swing的世界");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置文本居中
        jFrame.getContentPane().add(jLabel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton button1 = new JButton("点击");
        button1.setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //创建一个弹窗，因为jFrame已经存在了setDefaultCloseOperation，所以jDialog弹窗不需要再进行设置了
                JDialog jDialog = new JDialog(jFrame,"这是一个弹窗");
                jDialog.setBounds(50, 50, 250, 150);//设置位置大小
                jDialog.setVisible(true);//设置显示
                jDialog.add(new JLabel("这是一个打开的弹窗"));//添加label
            }
        });
        jFrame.getContentPane().add(button1);

    }
}
