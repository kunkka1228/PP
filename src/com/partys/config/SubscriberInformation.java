package com.partys.config;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.partys.commonparent.CommonJPanel;
import com.partys.tools.BasicUtil;
import com.partys.tools.MyTools;

public class SubscriberInformation extends CommonJPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6417159577663065198L;
	private JLabel title,content,icon,username,code,register,detail,status,result;
	private JTextField user;
	private JPasswordField pwd;
	private JPanel jp[]=new JPanel[3];
	private JTextArea detaiInfor;
	private JButton finish;
		
	public SubscriberInformation(){
		ini();
	}

	private void ini() {
		// TODO 自动生成的方法存根
		for(int x=0;x<3;x++){
			jp[x]=new JPanel(null);
			if(x==2){
				continue;
			}
			jp[x].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		}
		
		title=new JLabel("用户信息");		
		title.setBounds(30, 10, 100, 30);		
		content=new JLabel("请输入您的注册信息，以便激活各项功能");
		initComp(content,30, 40, 300, 30);
		icon=new JLabel(new ImageIcon("image/configure/users.png"));
		icon.setBounds(360, 20, 50, 50);		
		jp[0].add(title);
		jp[0].add(content);
		jp[0].add(icon);		

		user=new JTextField();
		user.setEditable(false);
		user.setText(BasicUtil.getLocalMac());
		user.setBounds(130, 17, 280, 24);
		pwd=new JPasswordField();
		pwd.setBounds(130, 47, 280, 24);
		username=new JLabel("Mac地址:");
		code=new JLabel("注册码:");
		initComp(username,20, 13, 50, 30);
		initComp(code,20, 43, 50, 30);
		register=new JLabel("<html><a href='http://www.baidu.com' style='list-style:none'>获得注册码?</a></html>");		
		register.setCursor(new Cursor(Cursor.HAND_CURSOR));
		initComp(register,20, 70, 80, 30);
		jp[1].add(username);
		jp[1].add(user);
		jp[1].add(code);
		jp[1].add(pwd);
		jp[1].add(register);
				
		detail=new JLabel("用户信息:");
		initComp(detail,30, 10, 70, 30);
		status=new JLabel("激活状态:");
		initComp(status,30, 130, 80, 30);
		detaiInfor=new JTextArea();		
		initComp(detaiInfor,140, 20, 280, 100);
		detaiInfor.setBorder(BorderFactory.createEtchedBorder());
		detaiInfor.setEditable(false);		
		result=new JLabel("已激活");
		initComp(result,140,135,100,20);
		jp[2].add(detail);
		jp[2].add(status);
		jp[2].add(detaiInfor);
		jp[2].add(result);
		
		finish=new JButton("激活");
		initComp(finish,347, 215, 60, 28);
		
		jp[2].add(finish);
		
		jp[0].setBounds(0, 0, 430, 80);
		jp[1].setBounds(10, 80, 410, 110);
		jp[2].setBounds(0, 190, 430, 250);

		for(int x=0;x<3;x++){
			this.add(jp[x]);
		}
		this.setLayout(null);					
	}
	
	
	
}
