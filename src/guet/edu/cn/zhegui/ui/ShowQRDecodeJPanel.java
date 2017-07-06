package guet.edu.cn.zhegui.ui;

import guet.edu.cn.zhegui.util.ImageUtil;
import guet.edu.cn.zhegui.util.ZxingHandler;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class ShowQRDecodeJPanel extends JPanel {
	
	//private Image image=null;
	private BufferedImage image=null;
	private boolean isDecode = false;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextArea textArea;
	public ShowQRDecodeJPanel() {
		setLayout(null);
		this.setSize(750, 460);
		btnNewButton = new JButton("\u6253\u5F00\u56FE\u7247");
		
		//打开图片
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setMultiSelectionEnabled(false);
				FileNameExtensionFilter fileter1 = new FileNameExtensionFilter(".jpg", "jpg");
				FileNameExtensionFilter fileter2 = new FileNameExtensionFilter(".png", "png");
				FileNameExtensionFilter fileter3 = new FileNameExtensionFilter(".bmp", "bmp");
				fileChooser.setFileFilter(fileter3);
				fileChooser.setFileFilter(fileter2);
				fileChooser.setFileFilter(fileter1);
				fileChooser.showOpenDialog(null);
				
				File file=fileChooser.getSelectedFile();
				if(file!=null){
					//System.out.println(file.getAbsolutePath());
					textArea.setText("");
					if(updateImage(file)){
						btnNewButton_1.setEnabled(true);
						repaint();
					}
				}else{
					if(image==null){
						JOptionPane.showMessageDialog(null, "请选择要打开的图片", "打开文件失败", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
		btnNewButton.setBounds(85, 380, 100, 30);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("\u5F00\u59CB\u89E3\u7801");
		btnNewButton_1.setEnabled(false);
		
		
		//开始解码
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(image==null){
					return ;
				}
				//先进行灰度化
				image=ImageUtil.grayImage(image);
				//图像中值滤波
				//image=ImageUtil.medianFiltering(image,ImageUtil.TEMPLATE_FIVE);
				image=ImageUtil.medianFiltering(image,ImageUtil.TEMPLATE_THREE);
				//System.out.println("中值滤波");
				//二值化
				int threshold=ImageUtil.otsu(image);
				image=ImageUtil.BinarizationIamge(image, threshold);
				String result = ZxingHandler.decode(image);
				if(result==null || result.equals("$$erorr$$")){
					textArea.setText("错误：此系统无法解析该二维码！！！");
				}else{
					textArea.setText(result);
				}
				btnNewButton_1.setEnabled(false);
				repaint();
			}
		});
		
		
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 15));
		btnNewButton_1.setBounds(225, 380, 100, 30);
		add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("\u89E3\u7801\u5185\u5BB9\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(416, 0, 118, 30);
		add(lblNewLabel);
		

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(416, 34, 324, 323);
		
		add(scroll);
		
		
		try {
			 image = ImageIO.read(new FileInputStream("./image/initPictrue.png"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public boolean updateImage(File file){
		try {
			this.image = ImageIO.read(new FileInputStream(file));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // paints the component's background, since
									// this component is opaque
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, 50, 35, 320, 320, null);
	}
}
