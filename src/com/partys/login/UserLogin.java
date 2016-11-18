package com.partys.login;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import com.partys.tools.BasicUtil;
import com.partys.tools.MyTools;

public class UserLogin extends JDialog implements ActionListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6729466496122147407L;
	/**
	 * @param args
	 */
	JLabel jl1,jl2,jl3,prop1,prop2;
	JTextField jname;
	JPasswordField jpass;
	JButton jconfirm,jcancel;
	public static void main(String[] args) {
		new UserLogin();
	}
	public UserLogin(){
		Container ct=this.getContentPane();
		prop1=new JLabel();
		prop2=new JLabel();
		this.setLayout(null);
		jl1=new JLabel("请输入用户名:");
		jl1.setFont(MyTools.f1);
		jl1.setBounds(40, 190, 150, 30);
		ct.add(jl1);
		jname=new JTextField(20);
		
		jname.setFont(MyTools.f1);
		jname.setBounds(160, 190, 120, 30);
		jname.setBorder(BorderFactory.createLoweredBevelBorder());
		jname.addKeyListener(this);
		ct.add(jname);
		
		prop1.setFont(MyTools.f2);
		prop1.setForeground(Color.red);
		prop1.setBounds(290, 190, 120, 30);

		ct.add(prop1);
		
		jl2=new JLabel("(用户Id)");
		jl2.setFont(MyTools.f2);
		jl2.setForeground(Color.red);
		jl2.setBounds(100, 210, 100, 30);
		ct.add(jl2);
		
		jl3=new JLabel("请 输入密 码 :");
		jl3.setFont(MyTools.f1);
		jl3.setBounds(40, 240, 150, 30);
		ct.add(jl3);
		prop2.setFont(MyTools.f2);
		prop2.setForeground(Color.red);
		prop2.setBounds(285, 240, 120, 30);
		
		ct.add(prop2);
		jpass=new JPasswordField(20);
		jpass.setFont(MyTools.f1);
		jpass.setBounds(160, 240, 120, 30);
		jpass.setBorder(BorderFactory.createLoweredBevelBorder());
		jpass.addKeyListener(this);
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
		int width=360;
		int height=360;
		this.setSize(width, height);
		this.setLocation((BasicUtil.getSreenWidthAndHeight()[0]-width) / 2 ,
				(BasicUtil.getSreenWidthAndHeight()[1] -height)/ 2);
		this.setVisible(true);
		

	}
//	内部类 放图片
	public class BackImage extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = -4460924725919428759L;
		Image img;
		public BackImage()
		{
			try {
				img=ImageIO.read(new File("image/index/indexUserLogin.jpg"));
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
			LoginToWindow();
		}
		else if(e.getSource()==jcancel)
			
		{
			
			//当点击取消按钮时，关闭登陆框，退出系统
			this.dispose();
		}
		
	}
	private void LoginToWindow() {
		String uid=this.jname.getText().trim();
		String p=new String(this.jpass.getPassword());
		if(uid.equals("")){
			prop1.setText("不能为空");
		}
		
		if(p.equals("")){
			prop2.setText("不能为空");
			return;
		}
		UserModel um=new UserModel();
		String zhiwei=new String (um.getJoblevel(uid, p)[0].trim());
		if(zhiwei.equals("无")){
			prop2.setText("ID或密码错误");
		}
		String name=um.getJoblevel(uid, p)[1].trim();
		String empname=um.getNameById(uid);
		if(empname!=null){
		if("经理".equals(zhiwei))
		{
			this.dispose();
			new Window1(uid,empname);				
			String welcome="欢迎您--"+name;
			JOptionPane.showMessageDialog(this, welcome);
			
		}
			else 
		{
			JOptionPane.showMessageDialog(this, "对不起！您的权限不足无法登陆！");
		}
		
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!prop1.equals("")){
			prop1.setText("");
		}
		
		if(!prop2.equals("")){
			prop2.setText("");
		}
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			LoginToWindow();
		}	
	}

}
