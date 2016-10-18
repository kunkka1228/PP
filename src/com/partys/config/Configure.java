package com.partys.config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.partys.tools.EclipseTabbedPaneUI;
import com.partys.tools.MyTools;

public class Configure extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3491513522536239267L;
	private JTabbedPane tab;
	private JButton finish;
	private JPanel mb;
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		new Configure();
		
	}
	
	public Configure(){
		init();
	}

	private void init() {
		// TODO �Զ����ɵķ������
		mb=new JPanel(new FlowLayout(FlowLayout.RIGHT,23,12));
		tab=new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		SubscriberInformation subscriberInformation=new SubscriberInformation();
		tab.add("�û���Ϣ",subscriberInformation);
		tab.add("�˿�����",new JPanel());

		finish=new JButton("���");
		finish.setBounds(380, 10, 50, 50);
		finish.setFont(MyTools.f2);
		mb.add(finish);
		mb.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
		tab.setUI(new EclipseTabbedPaneUI());
		this.add(tab,BorderLayout.CENTER);	
		this.add(mb,BorderLayout.SOUTH);
		this.getContentPane().setBackground(Color.white);
		this.getContentPane().setVisible(true);//
		this.setSize(447, 580);
		this.setTitle("����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
	}

}
