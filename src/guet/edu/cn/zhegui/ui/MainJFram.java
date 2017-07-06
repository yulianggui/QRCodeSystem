package guet.edu.cn.zhegui.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainJFram extends JFrame{
	public MainJFram() {
		setFont(new Font("Dialog", Font.PLAIN, 10));
		setBackground(new Color(0, 255, 255));
		getContentPane().setBackground(Color.RED);
		setTitle("二维码生成与识别系统");
		getContentPane().setLayout(null);
		
		JButton button = new JButton("\u89E3\u6790\u4E8C\u7EF4\u7801");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DecodeQRJFrame djf = DecodeQRJFrame.getDecodeQRJFrameInstance();
				System.out.println("解析二维码");
			}
		});
		button.setToolTipText("d");
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(80, 60, 120, 44);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u751F\u6210\u4E8C\u7EF4\u7801");
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new createJFram();
				createJFram cjf = createJFram.getInstance();
				//System.out.println("生成二维码");
			}
		});
		button_1.setBounds(80, 146, 120, 44);
		getContentPane().add(button_1);
		this.setBounds(500,200,287,350);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	public static void main(String[] args){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new MainJFram();
			}
		}).start();
		
	}
}
