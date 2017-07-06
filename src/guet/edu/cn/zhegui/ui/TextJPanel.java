package guet.edu.cn.zhegui.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class TextJPanel extends JPanel{
	private JTextArea textArea;
	public TextJPanel() {
		setLayout(null);
		setSize(400,500);
		JLabel lblNewLabel = new JLabel("\u8F93\u5165\u6587\u672C");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 15));
		lblNewLabel.setBounds(20, 30, 82, 41);
		add(lblNewLabel);
		
		textArea = new JTextArea();
		textArea.setText("\u8F93\u5165\u6587\u672C....");
		//textArea.setBounds(20, 82, 350, 300);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(20, 82, 350, 300);
		add(scroll);
	}
	public JTextArea getTextArea(){
		return this.textArea;
	}
	public void reInit(){
		this.textArea.setText("\u8F93\u5165\u6587\u672C....");
	}


}
