package guet.edu.cn.zhegui.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class CardJPanel extends JPanel{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextArea  textArea;
	public CardJPanel() {
		setLayout(null);
		setSize(400,500);
		JLabel lblNewLabel = new JLabel("\u8F93\u5165\u8054\u7CFB\u4EBA\u4FE1\u606F");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 25, 144, 34);
		add(lblNewLabel);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(10, 69, 62, 34);
		add(label);
		
		JLabel label_1 = new JLabel("\u8054\u7CFB\u7535\u8BDD");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		label_1.setBounds(10, 112, 62, 34);
		add(label_1);
		
		JLabel label_2 = new JLabel("\u7535\u5B50\u90AE\u4EF6");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("宋体", Font.PLAIN, 15));
		label_2.setBounds(10, 156, 62, 34);
		add(label_2);
		
		JLabel label_3 = new JLabel("\u7F51\u5740");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("宋体", Font.PLAIN, 15));
		label_3.setBounds(10, 203, 62, 34);
		add(label_3);
		
		JLabel label_4 = new JLabel("\u516C\u53F8");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("宋体", Font.PLAIN, 15));
		label_4.setBounds(10, 247, 62, 34);
		add(label_4);
		
		JLabel label_5 = new JLabel("\u5730\u5740");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("宋体", Font.PLAIN, 15));
		label_5.setBounds(10, 293, 62, 34);
		add(label_5);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 15));
		textField.setBounds(88, 76, 247, 21);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(88, 119, 247, 21);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(88, 163, 247, 21);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("宋体", Font.PLAIN, 15));
		textField_3.setColumns(10);
		textField_3.setBounds(88, 210, 247, 21);
		add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("宋体", Font.PLAIN, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(88, 254, 247, 21);
		add(textField_4);
		
		textArea = new JTextArea();
		//textArea.setBounds(88, 305, 247, 104);
		textArea.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(88, 305, 247, 104);
		//new JScrollPane(textArea));
		add(scroll);
	}
	
	public String getThisContentString(){
		String content = "";
		String name=textField.getText().trim();
		String tel = textField_1.getText().trim();
		String email=textField_2.getText().trim();
		String url=textField_3.getText().trim();
		String org = textField_4.getText().trim();
		String adr = textArea.getText().trim();
		content=String.format("MECARD:N:%s;EMAIL:%s;URL:%s;ORG:%s;ADR:%s;TEL:",name,email,url,org,adr,tel);
		return content;
	}
	
	public void reInit(){
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textArea.setText("");
	}
}
