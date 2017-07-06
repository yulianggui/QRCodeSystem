package guet.edu.cn.zhegui.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class TelePhoneJPanel extends JPanel{
	private JTextField textField;
	public TelePhoneJPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7535\u8BDD\u53F7\u7801");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 50, 139, 41);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("ËÎÌו", Font.PLAIN, 15));
		textField.setBounds(10, 111, 220, 31);
		add(textField);
		textField.setColumns(10);
	}

}
