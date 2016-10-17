package com.partys.config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.partys.tools.MyTools;

public class Configure extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6417159577663065198L;
	private JLabel title,content,icon;
	private JPanel p1;
	

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		new Configure();

	}
	
	public Configure(){
		ini();
	}

	private void ini() {
		// TODO �Զ����ɵķ������
		title=new JLabel("�û���Ϣ");
		
		title.setBounds(30, 10, 100, 30);
		content=new JLabel("����������ע����Ϣ���Ա㼤������");
		content.setFont(MyTools.f2);
		content.setBounds(30, 40, 300, 30);
		icon=new JLabel(new ImageIcon("image/configure/users.png"));
		icon.setBounds(360, 20, 50, 50);
		p1=new JPanel(null);
		p1.add(title);
		p1.add(content);
		p1.add(icon);
		p1.setBounds(0, 0, 500, 80);
		p1.setBackground(Color.white);
		p1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		this.add(p1);
		
		this.setLayout(null);
		this.setSize(450, 550);
		this.setTitle("�û���Ϣ");
		this.setVisible(true);
		
		
		
	}

	
	
	
	
}
