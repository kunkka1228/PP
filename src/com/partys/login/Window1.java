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

public class Window1 extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6829991336116522887L;
	// 定义需要的组件
	private Image titleIcon, timeBg, p1_bg, p3Icon, chart;
	private ImagePanel p1_bgImage, jp3Image, ct;
	private JMenuBar jmb;
	private JSplitPane jsp;
	private JMenu jm[] = new JMenu[6];
	private JMenuItem[] jmi = new JMenuItem[12];
	private ImageIcon jmi1_icon1, jmi2_icon2, jmi3_icon3, jmi4_icon4,
			jmi5_icon5, jmi6_icon6, jmi7_icon7, jmi8_icon8, jmi9_icon9,
			jmi10_icon10, jmi11_icon11, jmi12_icon12;
	private JToolBar jtb;
	private JButton[] jb = new JButton[10];
	private JPanel jp1, jp2, jp3, jp4, jp5;
	private JLabel showTime;// 显示时间
	private JLabel p2_jl1, p2_jl2;
	private JLabel p1_jl[] = new JLabel[8];
	private CardLayout myCard;
	private Timer t;// 可定时触发Action事件

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new Window1();
	}

	// 菜单
	private void initMenu() {
		// 一级菜单

		jmi1_icon1 = new ImageIcon("image/jm1_icon1.jpg");
		jmi2_icon2 = new ImageIcon("image/jm1_icon2.jpg");
		jmi3_icon3 = new ImageIcon("image/jm1_icon3.jpg");
		jmi4_icon4 = new ImageIcon("image/jm1_icon4.jpg");
		jmi5_icon5 = new ImageIcon("image/jm1_icon5.jpg");
		jm[0] = new JMenu("系统管理");
		// 创建其二级菜单
		jmi[0] = new JMenuItem("切换用户", jmi1_icon1);
		jmi[1] = new JMenuItem("修改密码", jmi2_icon2);
		jmi[2] = new JMenuItem("登陆管理", jmi3_icon3);
		jmi[3] = new JMenuItem("万年历", jmi4_icon4);
		jmi[4] = new JMenuItem("退出", jmi5_icon5);

		jmi6_icon6 = new ImageIcon("image/toolBar_image/jb4.jpg");

		jm[1] = new JMenu("人事管理");

		jmi[5] = new JMenuItem("考勤记录", jmi6_icon6);
		jm[1].add(jmi[5]);

		jmi7_icon7 = new ImageIcon("image/toolBar_image/jb6.jpg");
		jm[2] = new JMenu("菜单服务");

		jmi[6] = new JMenuItem("菜单及价格录入", jmi7_icon7);
		jm[2].add(jmi[6]);
		jmi8_icon8 = new ImageIcon("image/toolBar_image/jb5.jpg");
		jm[3] = new JMenu("报表统计");

		jmi[7] = new JMenuItem("财务报表", jmi8_icon8);
		jm[3].add(jmi[7]);

		jmi9_icon9 = new ImageIcon("image/toolBar_image/jb7.jpg");
		jm[4] = new JMenu("成本管理");

		jmi[8] = new JMenuItem("成本控制", jmi9_icon9);
		jm[4].add(jmi[8]);

		jmi10_icon10 = new ImageIcon("image/toolBar_image/jb9.jpg");
		jmi11_icon11 = new ImageIcon("image/toolBar_image/jb10.jpg");
		jmi12_icon12 = new ImageIcon("image/toolBar_image/jb8.jpg");
		jm[5] = new JMenu("帮助服务");
		jmi[9] = new JMenuItem("动画帮助", jmi10_icon10);
		jmi[10] = new JMenuItem("版本升级", jmi11_icon11);
		jmi[11] = new JMenuItem("关于我们", jmi12_icon12);
		jm[5].add(jmi[9]);
		jm[5].add(jmi[10]);
		jm[5].add(jmi[11]);
		for (int x = 0; x < 5; x++) {
			jm[0].add(jmi[x]);
		}

		for (int x = 0; x < 12; x++) {
			jmi[x].setFont(MyTools.f2);
		}
		jmb = new JMenuBar();

		for (int x = 0; x < 6; x++) {
			jm[x].setFont(MyTools.f3);
			jmb.add(jm[x]);
		}
		this.setJMenuBar(jmb);
	}

	// 工具栏
	private void initToolBar() {
		jtb = new JToolBar();
		jtb.setFloatable(false);
		jb[0] = new JButton(new ImageIcon("image/jm1_icon1.jpg"));
		jb[1] = new JButton(new ImageIcon("image/jm1_icon2.jpg"));
		jb[2] = new JButton(new ImageIcon("image/jm1_icon3.jpg"));
		jb[3] = new JButton(new ImageIcon("image/jm1_icon4.jpg"));
		jb[4] = new JButton(new ImageIcon("image/toolBar_image/jb5.jpg"));
		jb[5] = new JButton(new ImageIcon("image/toolBar_image/jb6.jpg"));
		jb[6] = new JButton(new ImageIcon("image/toolBar_image/jb7.jpg"));
		jb[7] = new JButton(new ImageIcon("image/toolBar_image/jb8.jpg"));
		jb[8] = new JButton(new ImageIcon("image/toolBar_image/jb9.jpg"));
		jb[9] = new JButton(new ImageIcon("image/toolBar_image/jb10.jpg"));
		for (int x = 0; x < 10; x++) {
			jtb.add(jb[x]);
		}

	}

	private void initCenter() {

		// jp1
		jp1 = new JPanel(new BorderLayout());
		try {
			p1_bg = ImageIO.read(new File("image/center_image/jp1_bg.jpg"));
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		Cursor myCursor = new Cursor(Cursor.HAND_CURSOR);
		p1_bgImage = new ImagePanel(p1_bg);
		p1_bgImage.setLayout(new GridLayout(8, 1));
		p1_jl[0] = new JLabel(new ImageIcon("image/center_image/label_1.gif"));
		p1_jl[1] = new JLabel("人  事  管  理", new ImageIcon(
				"image/center_image/label_2.jpg"), 0);
		p1_jl[2] = new JLabel("登  陆  管  理", new ImageIcon(
				"image/center_image/label_3.jpg"), 0);
		p1_jl[3] = new JLabel("预  定  管  理", new ImageIcon(
				"image/center_image/label_4.jpg"), 0);
		p1_jl[4] = new JLabel("报  表  统  计", new ImageIcon(
				"image/center_image/label_5.jpg"), 0);
		p1_jl[5] = new JLabel("客  人  信  息", new ImageIcon(
				"image/center_image/label_6.jpg"), 0);
		p1_jl[6] = new JLabel("流  水  统  计", new ImageIcon(
				"image/center_image/label_7.jpg"), 0);
		p1_jl[7] = new JLabel("媒  体  播  放", new ImageIcon(
				"image/center_image/label_8.jpg"), 0);

		for (int x = 0; x < 8; x++) {
			p1_bgImage.add(p1_jl[x]);
			if (x == 0) {
				continue;
			}
			p1_jl[x].setFont(MyTools.f4);
			p1_jl[x].setCursor(myCursor);
			p1_jl[x].setEnabled(false);
			p1_jl[x].addMouseListener(this);
		}

		jp1.add(p1_bgImage);

		// jp4,jp2,jp3
		myCard = new CardLayout();
		jp4 = new JPanel(new BorderLayout());
		jp2 = new JPanel(new CardLayout());
		p2_jl1 = new JLabel(new ImageIcon("image/center_image/jp2_left.jpg"));
		// 图标切换
		p2_jl1.addMouseListener(this);
		p2_jl2 = new JLabel(new ImageIcon("image/center_image/jp2_right.jpg"));
		p2_jl2.addMouseListener(this);
		jp2.add(p2_jl1, "0");
		jp2.add(p2_jl2, "1");

		jp3 = new JPanel(myCard);
		// 先给jp3加入主界面卡片
		try {
			p3Icon = ImageIO.read(new File("image/center_image/jp1_bg.jpg"));
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		jp3Image = new ImagePanel(p3Icon);

		jp3.add(jp3Image, "0");
		// 人事管理
		EmpInfo ei = new EmpInfo();
		jp3.add(ei, "1");

		// 登录界面
		EmpLogin el = new EmpLogin();
		jp3.add(el, "2");

		// 菜单价格
		MenuInfo mi = new MenuInfo();
		jp3.add(mi, "3");

		// 报表统计
		try {
			chart = ImageIO.read(new File("image/chart.jpg"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ct = new ImagePanel(chart);
		// ct.setBounds(0, 0, this.getWidth()-50, this.getHeight()-10);
		jp3.add(ct, "4");
		// 成本及库房
		CostNum cn = new CostNum();
		jp3.add(cn, "5");

		// 系统设置
		OperatChoose oc = new OperatChoose();
		jp3.add(oc, "6");

		// 动画音乐
		jp4.add(jp2, "West");
		jp4.add(jp3, "Center");
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jp1, jp4);
		jsp.setDividerLocation(150);
		jsp.setDividerSize(0);
	}

	// 状态栏
	private void initEnd() {
		jp5 = new JPanel(new BorderLayout());
		t = new Timer(1000, this);// 每隔一秒触发ActonEvent
		showTime = new JLabel("当前时间："
				+ Calendar.getInstance().getTime().toString() + "   ");
		showTime.setFont(MyTools.f1);

		t.start();
		try {
			timeBg = ImageIO.read(new File("image/time_bg.jpg"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ImagePanel ip1 = new ImagePanel(timeBg);
		ip1.setLayout(new BorderLayout());
		ip1.add(showTime, "East");
		jp5.add(ip1);
	}

	public Window1() {
		try {
			titleIcon = ImageIO.read(new File("image/title.gif"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// 菜单
		this.initMenu();
		// 工具栏
		this.initToolBar();
		// 中间
		this.initCenter();
		// 状态栏
		this.initEnd();
		Container ct = this.getContentPane();
		ct.add(jtb, "North");
		ct.add(jp5, "South");
		ct.add(jsp, "Center");
		int width = BasicUtil.getSreenWidthAndHeight()[0];
		int height = BasicUtil.getSreenWidthAndHeight()[1];
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height - 40);
		this.setIconImage(titleIcon);
		this.setTitle("Partys 管理系统");
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		this.showTime.setText("当前时间："
				+ Calendar.getInstance().getTime().toLocaleString() + "   ");
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		if (arg0.getSource() == p1_jl[1]) {
			this.myCard.show(jp3, "1");
		} else if (arg0.getSource() == p1_jl[2]) {
			this.myCard.show(jp3, "2");
		} else if (arg0.getSource() == p1_jl[3]) {
			this.myCard.show(jp3, "3");
		} else if (arg0.getSource() == p1_jl[4]) {
			this.myCard.show(jp3, "4");
		} else if (arg0.getSource() == p1_jl[5]) {
			this.myCard.show(jp3, "5");
		} else if (arg0.getSource() == p1_jl[6]) {
			this.myCard.show(jp3, "6");
		} else if (arg0.getSource() == p1_jl[7]) {
			MediaHelp mh = new MediaHelp();
		} else if (arg0.getSource() == p2_jl1) {
			this.jsp.setDividerLocation(0);
		} else if (arg0.getSource() == p2_jl2) {
			this.jsp.setDividerLocation(Toolkit.getDefaultToolkit()
					.getScreenSize().width);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		for (int x = 1; x < 8; x++) {
			if (arg0.getSource() == p1_jl[x]) {
				this.p1_jl[x].setEnabled(true);
			}
		}
		;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		for (int x = 1; x < 8; x++) {
			if (arg0.getSource() == p1_jl[x]) {
				this.p1_jl[x].setEnabled(false);
			}
		}
		;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO 自动生成的方法存根

	}
}
