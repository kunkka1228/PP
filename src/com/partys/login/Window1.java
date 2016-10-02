package com.partys.login;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.Timer;

import com.partys.tools.BasicUtil;
import com.partys.tools.ImagePanel;
import com.partys.tools.MediaHelp;
import com.partys.tools.MyTools;
public class Window1 extends JFrame implements ActionListener,MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6829991336116522887L;
	//������Ҫ�����
	private Image titleIcon,timeBg,p1_bg,p3Icon,chart;
	private ImagePanel p1_bgImage,jp3Image,ct;
	private JMenuBar jmb;
	private JSplitPane jsp;
	private JMenu jm1,jm2,jm3,jm4,jm5,jm6;
	private JMenuItem jmi1,jmi2,jmi3,jmi4,jmi5,jmi6,jmi7,jmi8,jmi9,jmi10,jmi11,jmi12;
	private ImageIcon jmi1_icon1,jmi2_icon2,jmi3_icon3,jmi4_icon4,jmi5_icon5,jmi6_icon6,jmi7_icon7,jmi8_icon8,jmi9_icon9,jmi10_icon10,jmi11_icon11,jmi12_icon12;
	private JToolBar jtb;
	private JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	private JPanel jp1,jp2,jp3,jp4,jp5;
	private JLabel showTime;//��ʾʱ��
	private JLabel p2_jl1,p2_jl2;
	private JLabel p1_jl1,p1_jl2,p1_jl3,p1_jl4,p1_jl5,p1_jl6,p1_jl7,p1_jl8;
	private CardLayout myCard;
	private Timer t;//�ɶ�ʱ����Action�¼�
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		new Window1();
	}
	//�˵�
	private void initMenu()
	{
		//һ���˵�

				jmi1_icon1=new ImageIcon("image/jm1_icon1.jpg");
				jmi2_icon2=new ImageIcon("image/jm1_icon2.jpg");
				jmi3_icon3=new ImageIcon("image/jm1_icon3.jpg");
				jmi4_icon4=new ImageIcon("image/jm1_icon4.jpg");
				jmi5_icon5=new ImageIcon("image/jm1_icon5.jpg");
				jm1=new JMenu("ϵͳ����");
				jm1.setFont(MyTools.f3);
				//����������˵�
				jmi1=new JMenuItem("�л��û�",jmi1_icon1);
				jmi1.setFont(MyTools.f2);
				jmi2=new JMenuItem("�л����տ����",jmi2_icon2);
				jmi2.setFont(MyTools.f2);
				jmi3=new JMenuItem("��½����",jmi3_icon3);
				jmi3.setFont(MyTools.f2);
				jmi4=new JMenuItem("������",jmi4_icon4);
				jmi4.setFont(MyTools.f2);
				jmi5=new JMenuItem("�˳�",jmi5_icon5);
				jmi5.setFont(MyTools.f2);
				
				jm1.add(jmi1);
				jm1.add(jmi2);
				jm1.add(jmi3);
				jm1.add(jmi4);
				jm1.add(jmi5);
				
				jmi6_icon6=new ImageIcon("image/toolBar_image/jb4.jpg");
				
				jm2=new JMenu("���¹���");
				jm2.setFont(MyTools.f3);
				jmi6=new JMenuItem("���ڼ�¼",jmi6_icon6);
				jmi6.setFont(MyTools.f2);
				jm2.add(jmi6);
				
				jmi7_icon7=new ImageIcon("image/toolBar_image/jb6.jpg");
				jm3=new JMenu("�˵�����");
				jm3.setFont(MyTools.f3);
				jmi7=new JMenuItem("�˵����۸�¼��",jmi7_icon7);
				jmi7.setFont(MyTools.f2);
				jm3.add(jmi7);
				
				jmi8_icon8=new ImageIcon("image/toolBar_image/jb5.jpg");
				jm4=new JMenu("����ͳ��");
				jm4.setFont(MyTools.f3);
				jmi8=new JMenuItem("���񱨱�",jmi8_icon8);
				jmi8.setFont(MyTools.f2);
				jm4.add(jmi8);
				
				
				
				jmi9_icon9=new ImageIcon("image/toolBar_image/jb7.jpg");
				jm5=new JMenu("�ɱ�����");
				jm5.setFont(MyTools.f3);
				jmi9=new JMenuItem("�ɱ�����",jmi9_icon9);
				jmi9.setFont(MyTools.f2);
				jm5.add(jmi9);
				
				jmi10_icon10=new ImageIcon("image/toolBar_image/jb9.jpg");
				jmi11_icon11=new ImageIcon("image/toolBar_image/jb10.jpg");
				jmi12_icon12=new ImageIcon("image/toolBar_image/jb8.jpg");
				jm6=new JMenu("��������");
				jm6.setFont(MyTools.f3);
				jmi10=new JMenuItem("��������",jmi10_icon10);
				jmi10.setFont(MyTools.f2);
				jmi11=new JMenuItem("�ı�����",jmi11_icon11);
				jmi11.setFont(MyTools.f2);
				jmi12=new JMenuItem("��������",jmi12_icon12);
				jmi12.setFont(MyTools.f2);
				jmi10.setFont(MyTools.f2);
				jm6.add(jmi10);
				jm6.add(jmi11);
				jm6.add(jmi12);
				
				jm6.add(jmi10);
				
				
				jmb=new JMenuBar();
				jmb.add(jm1);
				jmb.add(jm2);
				jmb.add(jm3);
				jmb.add(jm4);
				jmb.add(jm5);
				jmb.add(jm6);
				this.setJMenuBar(jmb);
	}
	//������
	private void initToolBar()
	{
		jtb=new JToolBar();
		jtb.setFloatable(false);
		jb1=new JButton(new ImageIcon("image/jm1_icon1.jpg"));
		jb2=new JButton(new ImageIcon("image/jm1_icon2.jpg"));
		jb3=new JButton(new ImageIcon("image/jm1_icon3.jpg"));
		jb4=new JButton(new ImageIcon("image/jm1_icon4.jpg"));
		jb5=new JButton(new ImageIcon("image/toolBar_image/jb5.jpg"));
		jb6=new JButton(new ImageIcon("image/toolBar_image/jb6.jpg"));
		jb7=new JButton(new ImageIcon("image/toolBar_image/jb7.jpg"));
		jb8=new JButton(new ImageIcon("image/toolBar_image/jb8.jpg"));
		jb9=new JButton(new ImageIcon("image/toolBar_image/jb9.jpg"));
		jb10=new JButton(new ImageIcon("image/toolBar_image/jb10.jpg"));
		jtb.add(jb1);
		jtb.add(jb2);
		jtb.add(jb3);
		jtb.add(jb4);
		jtb.add(jb5);
		jtb.add(jb6);
		jtb.add(jb7);
		jtb.add(jb8);
		jtb.add(jb9);
		jtb.add(jb10);
	
	}
	private void initCenter()
	{

		//jp1
		jp1=new JPanel(new BorderLayout());
		try {
			p1_bg=ImageIO.read(new File("image/center_image/jp1_bg.jpg"));
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		Cursor myCursor=new Cursor(HAND_CURSOR);
		p1_bgImage=new ImagePanel(p1_bg);
		p1_bgImage.setLayout(new GridLayout(8,1));
		p1_jl1=new JLabel(new ImageIcon("image/center_image/label_1.gif"));
		p1_bgImage.add(p1_jl1);
		p1_jl2=new JLabel("��  ��  ��  ��",new ImageIcon("image/center_image/label_2.jpg"),0);
		p1_jl2.setFont(MyTools.f4);
		//��label ��ʼ������
		p1_jl2.setCursor(myCursor);
		p1_jl2.setEnabled(false);
		p1_jl2.addMouseListener(this);
		p1_bgImage.add(p1_jl2);
		p1_jl3=new JLabel("��  ½  ��  ��",new ImageIcon("image/center_image/label_3.jpg"),0);
		p1_jl3.setFont(MyTools.f4);
		p1_jl3.setCursor(myCursor);
		p1_jl3.setEnabled(false);
		p1_jl3.addMouseListener(this);
		p1_bgImage.add(p1_jl3);
		p1_jl4=new JLabel("Ԥ  ��  ��  ��",new ImageIcon("image/center_image/label_4.jpg"),0);
		p1_jl4.setFont(MyTools.f4);
		p1_jl4.setCursor(myCursor);
		p1_jl4.setEnabled(false);
		p1_jl4.addMouseListener(this);
		p1_bgImage.add(p1_jl4);
		p1_jl5=new JLabel("��  ��  ͳ  ��",new ImageIcon("image/center_image/label_5.jpg"),0);
		p1_jl5.setFont(MyTools.f4);
		p1_jl5.setCursor(myCursor);
		p1_jl5.setEnabled(false);
		p1_jl5.addMouseListener(this);
		p1_bgImage.add(p1_jl5);
		p1_jl6=new JLabel("��  ��  ��  Ϣ",new ImageIcon("image/center_image/label_6.jpg"),0);
		p1_jl6.setFont(MyTools.f4);
		p1_jl6.setCursor(myCursor);
		p1_jl6.setEnabled(false);
		p1_jl6.addMouseListener(this);
		p1_bgImage.add(p1_jl6);
		p1_jl7=new JLabel("��  ˮ  ͳ  ��",new ImageIcon("image/center_image/label_7.jpg"),0);
		p1_jl7.setFont(MyTools.f4);
		p1_jl7.setCursor(myCursor);
		p1_jl7.setEnabled(false);
		p1_jl7.addMouseListener(this);
		p1_bgImage.add(p1_jl7);
		p1_jl8=new JLabel("ý  ��  ��  ��",new ImageIcon("image/center_image/label_8.jpg"),0);
		p1_jl8.setFont(MyTools.f4);
		p1_jl8.setCursor(myCursor);
		p1_jl8.setEnabled(false);
		p1_jl8.addMouseListener(this);
		p1_bgImage.add(p1_jl8);
		jp1.add(p1_bgImage);
		
		//jp4,jp2,jp3
		myCard=new CardLayout();
		jp4=new JPanel(new BorderLayout());
		jp2=new JPanel(new CardLayout());
		p2_jl1=new JLabel(new ImageIcon("image/center_image/jp2_left.jpg"));
		//ͼ���л�
		p2_jl1.addMouseListener(this);
		p2_jl2=new JLabel(new ImageIcon("image/center_image/jp2_right.jpg"));
		p2_jl2.addMouseListener(this);
		jp2.add(p2_jl1,"0");
		jp2.add(p2_jl2,"1");
		
		jp3=new JPanel(myCard);
		//�ȸ�jp3���������濨Ƭ
		try {
			p3Icon=ImageIO.read(new File("image/center_image/jp1_bg.jpg"));
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		jp3Image=new ImagePanel(p3Icon);
		
		jp3.add(jp3Image,"0");
		//���¹���
		 EmpInfo ei=new EmpInfo();
		 jp3.add(ei,"1");
		
		 //��¼����
		 EmpLogin el=new EmpLogin();
		 jp3.add(el,"2");
		
		 //�˵��۸�
		 MenuInfo mi=new MenuInfo();
		 jp3.add(mi,"3");
		 
		 //����ͳ��
		 try {
			 chart=ImageIO.read(new File("image/chart.jpg"));
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			ct=new ImagePanel(chart);
			//ct.setBounds(0, 0, this.getWidth()-50, this.getHeight()-10);
			jp3.add(ct,"4");
		 //�ɱ����ⷿ
		 CostNum cn=new CostNum();
		 jp3.add(cn,"5");
		 
		 
		 //ϵͳ����
		 OperatChoose oc=new OperatChoose();
		 jp3.add(oc,"6");
		 
		 //��������
		
		 
		 
		
		 
		jp4.add(jp2,"West");
		jp4.add(jp3,"Center");
		jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jp1,jp4);
		jsp.setDividerLocation(150);
		jsp.setDividerSize(0);
	}
	//״̬��
	private void initEnd()
	{
		jp5=new JPanel(new BorderLayout());
		t=new Timer(1000, this);//ÿ��һ�봥��ActonEvent
		showTime=new JLabel("��ǰʱ�䣺"+Calendar.getInstance().getTime().toLocaleString()+"   ");
		showTime.setFont(MyTools.f1);
		
		t.start();
		try {
			timeBg=ImageIO.read(new File("image/time_bg.jpg"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		ImagePanel ip1=new ImagePanel(timeBg);
		ip1.setLayout(new BorderLayout());
		ip1.add(showTime,"East");
		jp5.add(ip1);
	}
	public Window1()
	{
		try {
			titleIcon=ImageIO.read(new File("image/title.gif"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		
		//�˵�
		this.initMenu();
		//������
		this.initToolBar();
		
		//�м�
		this.initCenter();
		
		//״̬��
		this.initEnd();
		
		
		Container ct=this.getContentPane();
		ct.add(jtb,"North");
		ct.add(jp5,"South");
		ct.add(jsp,"Center");
		
		
		int width=BasicUtil.getSreenWidthAndHeight()[0];
		int height=BasicUtil.getSreenWidthAndHeight()[1];
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width,height-40);
		this.setIconImage(titleIcon);
		this.setTitle("Partys ����ϵͳ");
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		this.showTime.setText("��ǰʱ�䣺"+Calendar.getInstance().getTime().toLocaleString()+"   ");
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		if(arg0.getSource()==p1_jl2)
		{
			this.myCard.show(jp3,"1");
		}
		else if(arg0.getSource()==p1_jl3)
		{
			this.myCard.show(jp3,"2");
		}
		else if(arg0.getSource()==p1_jl4)
		{
			this.myCard.show(jp3,"3");
		}
		else if(arg0.getSource()==p1_jl5)
		{
			this.myCard.show(jp3,"4");
		}
		else if(arg0.getSource()==p1_jl6)
		{
			this.myCard.show(jp3,"5");
		}
		else if(arg0.getSource()==p1_jl7)
		{
			this.myCard.show(jp3,"6");
		}
		else if(arg0.getSource()==p1_jl8)
		{
			MediaHelp mh=new MediaHelp();
		}
		else if(arg0.getSource()==p2_jl1)
		{
			this.jsp.setDividerLocation(0);
		}
		else if(arg0.getSource()==p2_jl2)
		{
			this.jsp.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().width);
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		if(arg0.getSource()==p1_jl2)
		{
			this.p1_jl2.setEnabled(true);
		}else if(arg0.getSource()==p1_jl3)
		{
			this.p1_jl3.setEnabled(true);
		}
		else if(arg0.getSource()==p1_jl4)
		{
			this.p1_jl4.setEnabled(true);
		}
		else if(arg0.getSource()==p1_jl5)
		{
			this.p1_jl5.setEnabled(true);
		}
		else if(arg0.getSource()==p1_jl6)
		{
			this.p1_jl6.setEnabled(true);
		}
		else if(arg0.getSource()==p1_jl7)
		{
			this.p1_jl7.setEnabled(true);
		}
		else if(arg0.getSource()==p1_jl8)
		{
			this.p1_jl8.setEnabled(true);
		}
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		if(arg0.getSource()==p1_jl2)
		{
		this.p1_jl2.setEnabled(false);
		}else if(arg0.getSource()==p1_jl3)
		{
			this.p1_jl3.setEnabled(false);
		}else if(arg0.getSource()==p1_jl4)
		{
			this.p1_jl4.setEnabled(false);
		}
		else if(arg0.getSource()==p1_jl5)
		{
			this.p1_jl5.setEnabled(false);
		}
		else if(arg0.getSource()==p1_jl6)
		{
			this.p1_jl6.setEnabled(false);
		}
		else if(arg0.getSource()==p1_jl7)
		{
			this.p1_jl7.setEnabled(false);
		}
		else if(arg0.getSource()==p1_jl8)
		{
			this.p1_jl8.setEnabled(false);
		}
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

}
