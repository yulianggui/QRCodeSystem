package guet.edu.cn.zhegui.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class WiFiJPanel extends JPanel{
	private JTextField textField;
	private JPasswordField passwordField;
//	private JTextField textField2;
	private JComboBox comboBox;
	public WiFiJPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8F93\u5165WiFi\u8D26\u53F7\u4FE1\u606F");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 44, 152, 38);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7F51\u7EDCSSID");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 105, 81, 43);
		add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u52A0\u5BC6\u7C7B\u578B");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 158, 81, 43);
		add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(10, 211, 81, 43);
		add(label_1);
		
		textField= new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 15));
		textField.setBounds(101, 116, 206, 21);
		add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField("");
		passwordField.setFont(new Font("宋体", Font.PLAIN, 15));
		passwordField.setBounds(101, 222, 206, 21);
		add(passwordField);
		
		String labels[] = { "无加密", "WPA/WPA2","WPE"};
		comboBox = new JComboBox(labels);
		comboBox.setFont(new Font("宋体", Font.PLAIN, 15));
		comboBox.setBounds(101, 169, 102, 21);
		
		//comboBox.addItemListener(new)
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selectString = comboBox.getSelectedItem().toString().trim();
				if(selectString.equals("无加密")){
					passwordField.setEnabled(false);
					passwordField.setText("");
				}else{
					passwordField.setEnabled(true);
				}
			}
		});
		
		passwordField.setEnabled(false);
		add(comboBox);
	}
	
	public String getWIFIContent(){
		String content="";
		String SSID=textField.getText().trim();
		String comboBoxSelect = comboBox.getSelectedItem().toString();
		String password = passwordField.getText();
		content=content.format("WIFI:T:%s;S:%s;P:%s;", comboBoxSelect,SSID,password);
		return content;
	}
	
	public void reInit(){
		textField.setText("");
		passwordField.setText("");
		comboBox.setSelectedIndex(0);
	}

}
