package com.partys.login;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import DomXML.DOMParser;

import com.partys.book.CalendarFrame;
import com.partys.config.Configure;
import com.partys.customer.CustomerInfor;
import com.partys.emp.EmpInfo;
import com.partys.tools.BasicUtil;
import com.partys.tools.ImagePanel;
import com.partys.tools.MyTools;
import com.partysx.tool.Calculator;

public class Window1 extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6829991336116522887L;
	// ������Ҫ�����
	private Image titleIcon, p3Icon, chart;
	private ImagePanel  ct, jp2;
	private JMenuBar jmb;
	private JMenu jm[] = new JMenu[7];
	private JMenuItem[] jmi = new JMenuItem[14];
	private JMenuItem[] tools = new JMenuItem[4];
	private ImageIcon jmi1_icon1, jmi2_icon2, jmi3_icon3, jmi4_icon4,
			jmi5_icon5, jmi6_icon6, jmi8_icon8, jmi9_icon9,
			jmi10_icon10, jmi11_icon11, jmi12_icon12,jmi13_icon13,jmi14_icon14,jmi15_icon15;
	private JToolBar jtb;
	private JButton[] jb = new JButton[10];
	private JPanel jp3;
	private String userName;
	private CardLayout myCard;
	private String uid;
	private EmpInfo ei;
	private CustomerInfor customerInfor;
	private String tableHight;
	private boolean activeCustomerInfor;
	private boolean firstIniCustomerTab=false;
	private DOMParser parser;
	private CalendarFrame frame;
	public static void main(String[] args) {
		new Window1("","���Ʒ�");
	}
	// �˵�
	private void initMenu() {
		// һ���˵�

		jmi1_icon1 = new ImageIcon("image/toolBar_image/switchuser.png");
		jmi2_icon2 = new ImageIcon("image/toolBar_image/password.png");
		jmi3_icon3 = new ImageIcon("image/toolBar_image/config.png");
		jmi4_icon4 = new ImageIcon("image/toolBar_image/exit.png");
		jmi5_icon5 = new ImageIcon("image/toolBar_image/kaoqin.png");
		jmi6_icon6 = new ImageIcon("image/toolBar_image/payment.png");
		jmi8_icon8 = new ImageIcon("image/toolBar_image/jb5.jpg");
		jmi10_icon10 = new ImageIcon("image/toolBar_image/bug.png");
		jmi11_icon11 = new ImageIcon("image/toolBar_image/updatebuild.png");
		jmi12_icon12 = new ImageIcon("image/toolBar_image/aboutus.png");
		jmi13_icon13 = new ImageIcon("image/toolBar_image/dianpingsmall.png");
		jmi14_icon14 = new ImageIcon("image/toolBar_image/nuomi.png");
		jmi15_icon15 = new ImageIcon("image/toolBar_image/meituan.png");
		jm[0] = new JMenu("ϵͳ����");
		// ����������˵�
		jmi[0] = new JMenuItem("�л��û�", jmi1_icon1);				
		jmi[1] = new JMenuItem("�޸�����", jmi2_icon2);
		jmi[2] = new JMenuItem("����", jmi3_icon3);
		jmi[3] = new JMenuItem("�˳�", jmi4_icon4);

		jm[1] = new JMenu("���¹���");
		jmi[4] = new JMenuItem("���ڼ�¼", jmi5_icon5);
		jmi[5] = new JMenuItem("���ʽ���", jmi6_icon6);
		jm[1].add(jmi[4]);
		jm[1].add(jmi[5]);
		jm[2] = new JMenu("�Ź���̨");

		jmi[6] = new JMenuItem("���ֵ���", jmi13_icon13);
		jmi[12] = new JMenuItem("Ŵ��", jmi14_icon14);
		jmi[13] = new JMenuItem("����", jmi15_icon15);
		jm[2].add(jmi[6]);
		jm[2].add(jmi[12]);
		jm[2].add(jmi[13]);
		
		jm[3] = new JMenu("����ͳ��");

		jmi[7] = new JMenuItem("���񱨱�", jmi8_icon8);
		jm[3].add(jmi[7]);

		jmi9_icon9 = new ImageIcon("image/toolBar_image/jb7.jpg");
		jm[4] = new JMenu("�ɱ�����");

		jmi[8] = new JMenuItem("�ɱ�����", jmi9_icon9);
		jm[4].add(jmi[8]);
	
		ImageIcon tools_icon1=new ImageIcon("image/toolBar_image/Caculator.png");
		jm[5] = new JMenu("ʵ�ù���");
		tools[0]=new JMenuItem("������", tools_icon1);
		tools[1]=new JMenuItem("����1", jmi9_icon9);
		tools[2]=new JMenuItem("����2", jmi9_icon9);
		tools[3]=new JMenuItem("����3", jmi9_icon9);
		
		for(int x=0;x<4;x++){
			jm[5].add(tools[x]);
			tools[x].setFont(MyTools.f2);
			tools[x].addActionListener(this);
		}
		
		jm[6] = new JMenu("��������");
		jmi[9] = new JMenuItem("�ύbug", jmi10_icon10);
		jmi[10] = new JMenuItem("�汾����", jmi11_icon11);
		jmi[11] = new JMenuItem("��������", jmi12_icon12);
		jm[6].add(jmi[9]);
		jm[6].add(jmi[10]);
		jm[6].add(jmi[11]);
		for (int x = 0; x < 4; x++) {
			jm[0].add(jmi[x]);
		}

		for (int x = 0; x < 14; x++) {
			jmi[x].setFont(MyTools.f2);
			jmi[x].addActionListener(this);
		}
		jmb = new JMenuBar();
		
		for (int x = 0; x < 7; x++) {
			jm[x].setFont(MyTools.f3);
			jmb.add(jm[x]);
		}
		JPanel showName=new JPanel(new FlowLayout(FlowLayout.RIGHT,20,5));
		showName.setOpaque(false);
		JLabel name=new JLabel(userName);
		name.setOpaque(false);
		showName.add(name);
		jmb.add(showName);

		this.setJMenuBar(jmb);
	}

	// ������
	private void initToolBar() {
		jtb = new JToolBar();
		jtb.setFloatable(false);
		jtb.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
		jb[0] = new JButton(new ImageIcon("image/toolBar_image/people.png"));		
		jb[1] = new JButton(new ImageIcon("image/toolBar_image/customerInfor.png"));
		jb[2] = new JButton(new ImageIcon("image/toolBar_image/phone.png"));
		jb[3] = new JButton(new ImageIcon("image/toolBar_image/chart.png"));
		jb[4] = new JButton(new ImageIcon("image/toolBar_image/stats.png"));
		jb[5] = new JButton(new ImageIcon("image/toolBar_image/zhangben.png"));
		jb[6] = new JButton(new ImageIcon("image/toolBar_image/addplace.png"));
		jb[7] = new JButton(new ImageIcon("image/toolBar_image/dianping.png"));
		jb[8] = new JButton(new ImageIcon("image/toolBar_image/wechatweb.png"));		
		jb[9] = new JButton(new ImageIcon("image/toolBar_image/wechatpublic.png"));
		Cursor myCursor = new Cursor(Cursor.HAND_CURSOR);
		String[] toolTip={"���¹���","������Ϣ","Ԥ������","���񱨱�","��ˮͳ��","�ɱ�����","��ӵ���","������̨","΢����ҳ��","΢�Ź��ں�"};
		for (int x = 0; x < 10; x++) {
			jb[x].setBorderPainted(false);
			jb[x].setCursor(myCursor);
			jb[x].setOpaque(false);
			jb[x].setFocusable(false);
			jb[x].addMouseListener(this);
			jb[x].setToolTipText(toolTip[x]);
			jtb.add(jb[x]);
		}

	}

	private void initCenter() {
	
		myCard = new CardLayout();

		jp3 = new JPanel(myCard);
		// �ȸ�jp3���������濨Ƭ
		try {
			p3Icon = ImageIO.read(new File("image/center_image/background.jpg"));
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		jp2 = new ImagePanel(p3Icon);
		jp2.setLayout(new BorderLayout());
		// ���¹���
		ei = new EmpInfo();
		jp3.add(ei, "1");
		
		// ��¼����
		

		

		// ����ͳ��
		try {
			chart = ImageIO.read(new File("image/chart.jpg"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		ct = new ImagePanel(chart);

		jp3.add(ct, "4");
		// �ɱ����ⷿ

	}

	public Window1(String uid,String userName) {
		this.userName=userName;
		this.uid=uid;
		
		try {
			titleIcon = ImageIO.read(new File("image/title.gif"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		// �˵�
		this.initMenu();
		// ������
		this.initToolBar();
		// �м�
		this.initCenter();
		// ״̬��
		Container container = this.getContentPane();
		jp3.setOpaque(false);
		jp2.add(jtb, "North");
		jp2.add(jp3, "Center");
		container.add(jp2);
		int width = BasicUtil.getSreenWidthAndHeight()[0];
		int height = BasicUtil.getSreenWidthAndHeight()[1];
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height-40);
		this.setIconImage(titleIcon);
		this.setTitle("Partys ����ϵͳ");
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==jmi[0]){
			new UserLogin();
			this.dispose();
		}
		
		else if(e.getSource()==jmi[1]){
			new ModifyPassword(this, "�޸��ܴa", true,uid);
		}
		
		else if(e.getSource()==jmi[2]){
			new Configure();
		}
		
		else if(e.getSource()==tools[0]){
			new Calculator();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		if (arg0.getSource() == jb[0]) {
			if(ei==null){
				ei = new EmpInfo();
				jp3.add(ei, "1");
			}
			
			this.myCard.show(jp3, "1");
			
			
		} else if (arg0.getSource() == jb[1]) {
			
			if(parser==null){
				parser=new DOMParser("settings.xml");
			}
			
			if(customerInfor==null){				
				firstIniCustomerTab=Boolean.parseBoolean(parser.getAttributeByTagName("table", "first-ini")[0]);
				activeCustomerInfor=Boolean.parseBoolean(parser.getAttributeByTagName("table", "activeCustomerInfor")[0]);
				if(firstIniCustomerTab){
					tableHight=10+"";
				}
				else{
					tableHight=parser.getAttributeByTagName("table", "height")[0];
				}
				customerInfor=new CustomerInfor(tableHight);
				jp3.add(customerInfor, "2");
			}
			else{
				if(!activeCustomerInfor){
					tableHight=parser.getAttributeByTagName("table", "height")[0];
					customerInfor=new CustomerInfor(tableHight);
					jp3.add(customerInfor, "2");
					parser.updateNodeAttributeByTagName("table", "activeCustomerInfor", "false", "true");
					activeCustomerInfor=true;
				}
			}
			

			this.myCard.show(jp3, "2");
			if(firstIniCustomerTab){
				String row=BasicUtil.caculateRow(customerInfor.getPanelHight());
				parser.updateNodeAttributeByTagName("table", "first-ini", "true", "false");
				parser.updateNodeAttributeByTagName("table", "height", "", row);
				firstIniCustomerTab=false;
			}				
		} 
		
		
		else if (arg0.getSource() == jb[2]){
			if(frame==null){
				frame=new CalendarFrame(); 
				jp3.add(frame, "3");
			}
			
			myCard.show(jp3, "3");

		}
	}
		
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO �Զ����ɵķ������

	}
	
}
