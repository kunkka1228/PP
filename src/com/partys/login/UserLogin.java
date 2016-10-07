package com.partys.login;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.partys.model.UserModel;
import com.partys.tools.MyTools;
import com.partys.view.Window2;

public class UserLogin extends JDialog implements ActionListener{

	/**
	 * @param args
	 */
	JLabel jl1,jl2,jl3;
	JTextField jname;
	JPasswordField jpass;
	JButton jconfirm,jcancel;
	public static void main(String[] args) {
		UserLogin ul=new UserLogin();
	}
	public UserLogin(){
		Container ct=this.getContentPane();
		this.setLayout(null);
		jl1=new JLabel("请输入用户名:");
		jl1.setFont(MyTools.f1);
		jl1.setBounds(60, 190, 150, 30);
		ct.add(jl1);
		jname=new JTextField(20);
		jname.setFont(MyTools.f1);
		jname.setBounds(180, 190, 120, 30);
		jname.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jname);
		
		jl2=new JLabel("(用户Id)");
		jl2.setFont(MyTools.f2);
		jl2.setForeground(Color.red);
		jl2.setBounds(100, 210, 100, 30);
		ct.add(jl2);
		
		jl3=new JLabel("请 输入密 码 :");
		jl3.setFont(MyTools.f1);
		jl3.setBounds(60, 240, 150, 30);
		ct.add(jl3);
		jpass=new JPasswordField(20);
		jpass.setFont(MyTools.f1);
		jpass.setBounds(180, 240, 120, 30);
		jpass.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jpass);
		
		jconfirm=new JButton("确定");
		jconfirm.addActionListener(this);
		jconfirm.setFont(MyTools.f1);
		jconfirm.setBounds(110, 300, 70, 30);
		ct.add(jconfirm);
		
		jcancel=new JButton("取消");
		jcancel.addActionListener(this);
		jcancel.setFont(MyTools.f1);
		jcancel.setBounds(210, 300, 70, 30);
		ct.add(jcancel);
		BackImage bi=new BackImage();
		bi.setBounds(0, 0, 360, 170);
		ct.add(bi);
		this.setUndecorated(true);
		this.setSize(360, 360);
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200, height/2-200);
		this.setVisible(true);

	}
//	内部类 放图片
	public class BackImage extends JPanel{
		Image img;
		public BackImage()
		{
			try {
				img=ImageIO.read(new File("image/index/indexUserLogin12.jpg"));
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g)
		{
			g.drawImage(img, 0, 0, 360, 170,this);
		}
	}
	//响应登录请求
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
		if(e.getSource()==jconfirm)
		{
			String uid=this.jname.getText().trim();
			String p=new String(this.jpass.getPassword());
			UserModel um=new UserModel();
			String zhiwei=new String (um.getJoblevel(uid, p)[0].trim());
			String name=um.getJoblevel(uid, p)[1].trim();
			String empname=um.getNameById(uid);
			if(empname!=null){
			if("经理".equals(zhiwei))
			{
				this.dispose();
				new Window1(uid);				
				String welcome="欢迎您--"+name;
				JOptionPane.showMessageDialog(this, welcome);
				
			}
			else if("职员".equals(zhiwei))
			{
				Window2 w2=new Window2(empname,zhiwei);
				Thread a=new Thread(w2);
				a.start();
				String welcome="欢迎您--"+name;
				JOptionPane.showMessageDialog(this, welcome);
				this.dispose();
				
			}
			else 
			{
				JOptionPane.showMessageDialog(this, "对不起！您的权限不足无法登陆！");
			}
			
			}
		}
		else if(e.getSource()==jcancel)
			
		{
			
			//当点击取消按钮时，关闭登陆框，退出系统
			this.dispose();
		}
		
	}

}
