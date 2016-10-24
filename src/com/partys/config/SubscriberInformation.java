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
		initLabel();
		initTextFeild();
		initPassword();
		initTextArea();
		initBtn();
		initPanel();
		ini();
	}
	
	private void initLabel(){
		title=new JLabel("�û���Ϣ");		
		title.setBounds(30, 10, 100, 30);		
		content=new JLabel("����������ע����Ϣ���Ա㼤������");
		initComp(content,30, 40, 300, 30);
		icon=new JLabel(new ImageIcon("image/configure/users.png"));
		icon.setBounds(360, 20, 50, 50);		
		username=new JLabel("Mac��ַ:");
		initComp(username,20, 13, 50, 30);
		code=new JLabel("ע����:");	
		initComp(code,20, 43, 50, 30);
		register=new JLabel("<html><a href='http://www.baidu.com' style='list-style:none'>���ע����?</a></html>");		
		register.setCursor(new Cursor(Cursor.HAND_CURSOR));
		initComp(register,20, 70, 80, 30);
		detail=new JLabel("�û���Ϣ:");
		initComp(detail,30, 10, 70, 30);
		status=new JLabel("����״̬:");
		initComp(status,30, 130, 80, 30);
		result=new JLabel("�Ѽ���");
		initComp(result,140,135,100,20);
	}
	
	private void initTextFeild(){
		user=new JTextField();
		user.setEditable(false);
		user.setText(BasicUtil.getLocalMac());
		user.setBounds(130, 17, 280, 24);
	}
	
	private void initPanel(){
		for(int x=0;x<3;x++){
			jp[x]=new JPanel(null);
			if(x==2){
				continue;
			}
			jp[x].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		}
		
		
		jp[0].add(title);
		jp[0].add(content);
		jp[0].add(icon);
		jp[1].add(username);
		jp[1].add(user);
		jp[1].add(code);
		jp[1].add(pwd);
		jp[1].add(register);
		jp[2].add(detail);
		jp[2].add(status);
		jp[2].add(detaiInfor);
		jp[2].add(result);
		jp[2].add(finish);
		
		jp[0].setBounds(0, 0, 430, 80);
		jp[1].setBounds(10, 80, 410, 110);
		jp[2].setBounds(0, 190, 430, 250);

	}
	
	private void initPassword(){
		pwd=new JPasswordField();
		pwd.setBounds(130, 47, 280, 24);
	}
	
	private void initTextArea(){
		detaiInfor=new JTextArea();		
		initComp(detaiInfor,140, 20, 280, 100);
		detaiInfor.setBorder(BorderFactory.createEtchedBorder());
		detaiInfor.setEditable(false);		
	}
	
	private void initBtn(){
		finish=new JButton("����");
		initComp(finish,347, 215, 60, 28);
	}

	private void ini() {
		// TODO �Զ����ɵķ������	
		for(int x=0;x<3;x++){
			this.add(jp[x]);
		}
		this.setLayout(null);					
	}
	
}
