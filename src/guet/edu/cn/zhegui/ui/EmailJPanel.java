package guet.edu.cn.zhegui.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class EmailJPanel extends JPanel{
	private JTextField textField;
	public EmailJPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8F93\u5165\u90AE\u7BB1\u5730\u5740");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 43, 152, 47);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setText("1221");
		textField.setFont(new Font("ËÎÌו", Font.PLAIN, 15));
		textField.setBounds(10, 109, 233, 32);
		add(textField);
		textField.setColumns(10);
	}

}
