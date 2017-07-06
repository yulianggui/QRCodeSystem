package guet.edu.cn.zhegui.ui;

import guet.edu.cn.zhegui.util.ZxingHandler;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class createJFram extends JFrame{
	
	private QRImageShowJpanel rightJpanel;
    private JTabbedPane tabbedPane;   //创建选项卡 
    private TextJPanel textPanel = new TextJPanel();      //文本TextJpane
    private WebURLJPanel webURLPanel = new WebURLJPanel(); 
    private CardJPanel cardJpanel = new CardJPanel();
    private WiFiJPanel wifiJpanel = new WiFiJPanel();
    ButtonGroup group=new ButtonGroup();
    private JRadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    
    private static createJFram cFram;
    
    public static createJFram getInstance(){
    	if(cFram==null){
    		cFram = new createJFram();
    	}
    	return cFram;
    }
    
	private createJFram(){
		super("生成二维码"); 
		//setSize(800,500);  
		this.setResizable(false);
		this.setBounds(300,200,800,500);
		
		//窗口关闭监听
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				cFram=null;
			}
		});
		
		
		rightJpanel = new QRImageShowJpanel();
		setLayout(new GridLayout(1,2));   
        Container c = getContentPane();  
        tabbedPane=new JTabbedPane();   //创建选项卡面板对象  
        
        
        JButton createJbuton = new JButton("生成二维码");
        createJbuton.setBounds(100, 400, 100, 30);
        rightJpanel.add(createJbuton);
        
        createJbuton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String stContent="";
				int index = tabbedPane.getSelectedIndex();
				
				//纠错等级
				ErrorCorrectionLevel errorCorrectionLevel=null;
				if(radioButton1.isSelected()){
					errorCorrectionLevel=ErrorCorrectionLevel.L;
				}else if(radioButton2.isSelected()){
					errorCorrectionLevel=ErrorCorrectionLevel.M;
				}else if(radioButton3.isSelected()){
					errorCorrectionLevel=ErrorCorrectionLevel.Q;
				}else if(radioButton4.isSelected()){
					errorCorrectionLevel = ErrorCorrectionLevel.H;
				}
				
				//选中的选项
				if(index==0){
					stContent = getQRStringBytextPanel(textPanel);
				}else if(index==1){
					stContent = getQRStrngByWebURLJPanel(webURLPanel);
				}else if(index==2){
					stContent = getQRStringByCardJPanel(cardJpanel);
				}else if(index==3){
					stContent = getQRStringByWiFiJPanel(wifiJpanel);
				}
				
				rightJpanel.setImage(ZxingHandler.encode(stContent, 350, 350,errorCorrectionLevel));
				rightJpanel.repaint();
			}
		});
         
        radioButton1 = new JRadioButton("7%");// 创建单选按钮
        radioButton1.setBounds(70, 360, 60, 30);
        radioButton1.setSelected(true);
        rightJpanel.add(radioButton1);// 应用单选按钮
        
        radioButton2 = new JRadioButton("15%");// 创建单选按钮
        radioButton2.setBounds(140, 360, 60, 30);
        rightJpanel.add(radioButton2);// 应用单选按钮
        
        radioButton3 = new JRadioButton("25%");// 创建单选按钮
        radioButton3.setBounds(210, 360, 60, 30);
        rightJpanel.add(radioButton3);// 应用单选按钮
        
        radioButton4 = new JRadioButton("30%");// 创建单选按钮
        radioButton4.setBounds(280, 360, 60, 30);
        rightJpanel.add(radioButton4);// 应用单选按钮
        
        
        //group = // 创建单选按钮组
        group.add(radioButton1);// 将radioButton1增加到单选按钮组中
        group.add(radioButton2);// 将radioButton2增加到单选按钮组中
        group.add(radioButton3);// 将radioButton3增加到单选按钮组中
        group.add(radioButton4);
        
        
        //将标签面板加入到选项卡面板对象上   
        tabbedPane.addTab("文本", null, textPanel, "text panel");
        tabbedPane.addTab("URL",null,webURLPanel,"URL panel"); 
        tabbedPane.addTab("名片",null,cardJpanel,"Card panel");
        tabbedPane.addTab("WIFI",null,wifiJpanel,"WIFI panel");
        
        c.add(tabbedPane);  
        c.add(rightJpanel);
        c.setBackground(Color.white);  
  
        //选项卡改变重置
        tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				textPanel.reInit();
				webURLPanel.reInit();
				cardJpanel.reInit();
				wifiJpanel.reInit();
			}
		});
        
        setVisible(true);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        //System.out.println(tabbedPane.getSelectedIndex());
        
    }  
	
	//返回当前的tabbedPane
	public JTabbedPane getTabbedPane(){
		return this.tabbedPane;
	}
	
	public String getQRStringBytextPanel(TextJPanel tp){
		return tp.getTextArea().getText();
	}
	
	public String getQRStringByCardJPanel(CardJPanel cp){
		return cp.getThisContentString();
	}
	
	public String getQRStringByWiFiJPanel(WiFiJPanel wp){
		return wp.getWIFIContent();
	}
	
	public String getQRStrngByWebURLJPanel(WebURLJPanel wp){
		return wp.getTextArea().getText().trim();
	}


	public static void main(String[] args) {
		
		new createJFram();
	}

}
