package guet.edu.cn.zhegui.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.JTextArea;

public class WebURLJPanel extends JPanel{
	private JTextArea txtrHttp;
	public WebURLJPanel() {
		setLayout(null);
		setSize(400,500);
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165URL\u7F51\u5740");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 15));
		lblNewLabel.setBounds(20, 30, 135, 41);
		add(lblNewLabel);
		
		txtrHttp = new JTextArea();
		txtrHttp.setFont(new Font("ËÎÌו", Font.PLAIN, 15));
		txtrHttp.setText("http://");
		//txtrHttp.setBounds(20, 82, 350, 300);
		JScrollPane scroll = new JScrollPane(txtrHttp);
		scroll.setBounds(20, 82, 350, 300);
		add(scroll);
	}
	public JTextArea getTextArea(){
		return this.txtrHttp;
	}
	public void reInit(){
		txtrHttp.setText("http://");
	}

}
