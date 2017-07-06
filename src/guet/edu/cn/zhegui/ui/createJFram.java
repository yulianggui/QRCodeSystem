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
    private JTabbedPane tabbedPane;   //����ѡ� 
    private TextJPanel textPanel = new TextJPanel();      //�ı�TextJpane
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
		super("���ɶ�ά��"); 
		//setSize(800,500);  
		this.setResizable(false);
		this.setBounds(300,200,800,500);
		
		//���ڹرռ���
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
        tabbedPane=new JTabbedPane();   //����ѡ�������  
        
        
        JButton createJbuton = new JButton("���ɶ�ά��");
        createJbuton.setBounds(100, 400, 100, 30);
        rightJpanel.add(createJbuton);
        
        createJbuton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String stContent="";
				int index = tabbedPane.getSelectedIndex();
				
				//����ȼ�
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
				
				//ѡ�е�ѡ��
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
         
        radioButton1 = new JRadioButton("7%");// ������ѡ��ť
        radioButton1.setBounds(70, 360, 60, 30);
        radioButton1.setSelected(true);
        rightJpanel.add(radioButton1);// Ӧ�õ�ѡ��ť
        
        radioButton2 = new JRadioButton("15%");// ������ѡ��ť
        radioButton2.setBounds(140, 360, 60, 30);
        rightJpanel.add(radioButton2);// Ӧ�õ�ѡ��ť
        
        radioButton3 = new JRadioButton("25%");// ������ѡ��ť
        radioButton3.setBounds(210, 360, 60, 30);
        rightJpanel.add(radioButton3);// Ӧ�õ�ѡ��ť
        
        radioButton4 = new JRadioButton("30%");// ������ѡ��ť
        radioButton4.setBounds(280, 360, 60, 30);
        rightJpanel.add(radioButton4);// Ӧ�õ�ѡ��ť
        
        
        //group = // ������ѡ��ť��
        group.add(radioButton1);// ��radioButton1���ӵ���ѡ��ť����
        group.add(radioButton2);// ��radioButton2���ӵ���ѡ��ť����
        group.add(radioButton3);// ��radioButton3���ӵ���ѡ��ť����
        group.add(radioButton4);
        
        
        //����ǩ�����뵽ѡ���������   
        tabbedPane.addTab("�ı�", null, textPanel, "text panel");
        tabbedPane.addTab("URL",null,webURLPanel,"URL panel"); 
        tabbedPane.addTab("��Ƭ",null,cardJpanel,"Card panel");
        tabbedPane.addTab("WIFI",null,wifiJpanel,"WIFI panel");
        
        c.add(tabbedPane);  
        c.add(rightJpanel);
        c.setBackground(Color.white);  
  
        //ѡ��ı�����
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
	
	//���ص�ǰ��tabbedPane
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
