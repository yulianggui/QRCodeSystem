package guet.edu.cn.zhegui.ui;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;

public class DecodeQRJFrame extends JFrame {
	
	private ShowQRDecodeJPanel showQRDecodeJPanel;
	private static DecodeQRJFrame decodeQRJFrame;
	
	public static DecodeQRJFrame getDecodeQRJFrameInstance(){
		if(decodeQRJFrame==null){
			decodeQRJFrame =  new DecodeQRJFrame();
			//return decodeQRJFrame;
		}
		return decodeQRJFrame;
	}
	private DecodeQRJFrame() {
		setTitle("QR\u4E8C\u7EF4\u7801\u89E3\u7801");
		//this.setSize(783, 479);
		this.setBounds(300, 200, 780, 479);
		Container c = getContentPane(); 
		getContentPane().setLayout(null);
		showQRDecodeJPanel = new ShowQRDecodeJPanel();
		showQRDecodeJPanel.setBounds(0, 10, 710, 462);
		c.add(showQRDecodeJPanel);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				decodeQRJFrame = null;
			}
		});
	}
	
	
//	public static void main(String[] args){
//		new DecodeQRJFrame();
//	}
}
