package guet.edu.cn.zhegui.ui;

import guet.edu.cn.zhegui.util.ImageUtil;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class QRImageShowJpanel extends JPanel{

	private JButton saveQRPicture = new JButton("保存图片");
	private BufferedImage bfimage=null;
	private Image image=null;

	
	 protected void paintComponent(Graphics g) {  
		super.paintComponent(g); // paints the component's background, since
									// this component is opaque
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, 50, 30, 320, 320, null);        
     }  
    
	public void setImage(BufferedImage bufferedImage){
		// this.image=ImageIO.read(new File("E:\\QRcodeTest\\222OK121212.jpg"));
		// ImageIO.createImageOutputStream(os);
		this.image = bufferedImage;
		this.bfimage = bufferedImage;
	} 
	
	public QRImageShowJpanel() {
		setLayout(null);
		saveQRPicture.setBounds(230, 400, 100, 30);
		this.add(saveQRPicture);
		
		
		try {
			 image = ImageIO.read(new FileInputStream("./image/initPictrue.png"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		saveQRPicture.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(bfimage==null){
					System.out.println("图像为空!");
					return ;
				}
				
				int option=-1;
				String fileName=null;
				String newFile=null;
				//在弹出对话框之前，创建文件类型的过滤器
				FileNameExtensionFilter filter1,filter2;
				
				JFileChooser fileChoose = new JFileChooser();
				//fileChoose.setDialogType(JFileChooser.FILES_ONLY);
				filter1=new FileNameExtensionFilter(".jpg", "jpg");
				filter2=new FileNameExtensionFilter(".png", "png");
				fileChoose.setDialogTitle("选择文件");
				fileChoose.setFileFilter(filter1);
				fileChoose.setFileFilter(filter2);
				fileChoose.setMultiSelectionEnabled(false);
				option=fileChoose.showSaveDialog(fileChoose);
				
//				if (fileChoose.getSelectedFile()==null) {
//					System.out.println("失败");
//				}
				if(option==JFileChooser.APPROVE_OPTION){
					System.out.println("option==="+option);
					fileName = fileChoose.getSelectedFile().getPath();
					FileFilter filterx=fileChoose.getFileFilter();
					String ext=filterx.getDescription();
					//如果没有扩展名就自动加上，如果有就不加
					File filex=fileChoose.getSelectedFile();
					if (filex.getAbsolutePath().toUpperCase().endsWith(ext.toUpperCase())) {
						newFile=filex.getAbsolutePath();
					}else{
						//int index =filex.getAbsolutePath().lastIndexOf(".");
						newFile=filex.getAbsolutePath()+ext;
					}

					System.out.println(newFile+"       =="+ext.substring(1, ext.length()));
					int flag=ImageUtil.saveImage(bfimage,newFile,ext.substring(1, ext.length()));
					if(flag==-1){
						JOptionPane.showMessageDialog(null, "该文件已存在", "文件已存在", JOptionPane.ERROR_MESSAGE);
					}else if(flag==0){
						JOptionPane.showMessageDialog(null, "保存失败", "保存提示", JOptionPane.ERROR_MESSAGE);
					}else if(flag==1){
						JOptionPane.showMessageDialog(null, "保存成功", "保存提示",JOptionPane.PLAIN_MESSAGE);
						//JOptionPane.showMessageDialog(null, "保存成功", "保存提示", JOptionPane.OK_OPTION);
					}
				}
				
			}
		});

	}

}
