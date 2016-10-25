package com.partys.config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.partys.tools.EclipseTabbedPaneUI;
import com.partys.tools.MyTools;

public class Configure extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3491513522536239267L;
	private JTabbedPane tab;
	private JButton finish;
	private JPanel mb;
	private SubscriberInformation subscriberInformation;
	private PortConfigure portConfigure;
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new Configure();		
	}
	
	public Configure(){
		getSubscriberInformation();
		getPortConfigure();
		initTab();
		initBtn();
		iniPanel();
		init();
	}
	
	private SubscriberInformation getSubscriberInformation(){
		subscriberInformation=new SubscriberInformation();
		return subscriberInformation;
	}
	
	private PortConfigure getPortConfigure(){
		portConfigure=new PortConfigure();
		return portConfigure;
	}
	
	private void initTab(){
		tab=new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		tab.add("用户信息",subscriberInformation);
		tab.add("端口设置",portConfigure);
		tab.setUI(new EclipseTabbedPaneUI());
	}
	
	private void initBtn(){
		finish=new JButton("完成");
		finish.setBounds(380, 10, 50, 50);
		finish.setFont(MyTools.f2);
		finish.addActionListener(this);
	}
	
	private void iniPanel(){
		mb=new JPanel(new FlowLayout(FlowLayout.RIGHT,23,12));
		mb.add(finish);
		mb.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
	}
	
	private void init() {
		// TODO 自动生成的方法存根
		this.add(tab,BorderLayout.CENTER);	
		this.add(mb,BorderLayout.SOUTH);
		this.setSize(447, 580);
		this.setTitle("配置");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==finish){
			this.dispose();
		}		
	}

	
}
