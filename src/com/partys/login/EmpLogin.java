package com.partys.login;
//这是登陸管理界面

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.partys.model.EmpModel;
import com.partys.tools.MyTools;
public class EmpLogin extends JPanel implements ActionListener{
	//定义组件
	private JPanel p1,p2,p3,p4,p5;
	private JLabel p1_l1,p3_l1;
	private JTextField p1_jtf;
	private JButton p1_jb,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
	private JTable jtable;
	private JScrollPane jsp;
	private EmpModel em=null;
	public EmpLogin()
	{
		//创建组件
		
		//北
		p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_l1=new JLabel("请输入员工号）:");
		p1_l1.setFont(MyTools.f1);
		p1_jtf=new JTextField(20);
		p1_jb=new JButton("查询");
		p1_jb.addActionListener(this);
		p1_jb.setFont(MyTools.f4);
		p1.add(p1_l1);
		p1.add(p1_jtf);
		p1.add(p1_jb);
		
		
		//中间
		
		p2=new JPanel(new BorderLayout());
		String []params={"1"};
		String sql="select login.id,name,password,joblevel from login,renshi where login.id=renshi.id and 1=?";
		em=new EmpModel();
		em.query(sql, params);
		jtable= new JTable(em);
		jtable.setModel(em);
		jsp=new JScrollPane(jtable);

		p2.add(jsp);
		
		//南
		p5=new JPanel(new BorderLayout());
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		sql="select count(*) from login";
		em=new EmpModel();
		int sum=em.getNum(sql);

		p3_l1=new JLabel("总记录是"+sum+"条");
		
		p3.add(p3_l1);
		p4=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4_jb1=new JButton("详细信息");
		p4_jb1.addActionListener(this);
		p4_jb1.setFont(MyTools.f4);
		p4_jb2=new JButton("添加");
		p4_jb2.addActionListener(this);
		p4_jb2.setFont(MyTools.f4);
		p4_jb3=new JButton("修改");
		p4_jb3.addActionListener(this);
		p4_jb3.setFont(MyTools.f4);
		p4_jb4=new JButton("删除");
		p4_jb4.addActionListener(this);
		p4_jb4.setFont(MyTools.f4);
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);
		p5.add(p3,"West");
		p5.add(p4,"East");
		this.setLayout(new BorderLayout());
		this.add(p1,"North");
		this.add(p2,"Center");
		this.add(p5,"South");
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		
		if(arg0.getSource().equals(p1_jb))
		{
			
			if(p1_jtf.getText().trim().equals(""))
			{
				String[]params={"1"};
				String sql="select login.id,name,password,joblevel from login,renshi where login.id=renshi.id and 1=?";
				em=new EmpModel();
				em.query(sql, params);
				jtable.setModel(em);
			}
			else{
			String params[]={p1_jtf.getText().trim()};
			String sql="select login.id,name,password,joblevel from login,renshi where login.id=renshi.id and login.id=?";
			em=new EmpModel();
			em.query(sql, params);
			
			jtable.setModel(em);
			}
		}
		else if(arg0.getSource().equals(p4_jb1))
		{
			int rowNum=this.jtable.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "请选择一行！");
			}
			
			String empId=(String)this.jtable.getValueAt(rowNum, 0);
			String sql="select*from renshi where renshi.id=?";
			String []params={empId};
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
			
		}
		else if(arg0.getSource().equals(p4_jb2))
		{
			
			new AddLoginDialog(this,"添加",true);
			
			
			String sql="select login.id,name,passward,joblevel from login,renshi where login.id=renshi.id and 1=?";
			String[]params={"1"};
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
			
		}
		else if(arg0.getSource().equals(p4_jb3))
		{
			
			
			int rowNum=this.jtable.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "请选择一行！");
			}
				
//			String empId=(String)this.jtable.getValueAt(rowNum, 0);
//			String []params={empId};
			else
			{
			new UpdLoginDialog(this,"修改",true,em,rowNum);
			}
			
			
			String sql="select login.id,name,passward,joblevel from login,renshi where login.id=renshi.id and 1=?";
			String[]params={"1"};
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
		}
		else if(arg0.getSource().equals(p4_jb4))
		{
			int rowNum=this.jtable.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "请选择一行！");
			}
			else{	
			String empId=(String)this.jtable.getValueAt(rowNum, 0);
			String sql="delete from login where id=?";
			String []params={empId};
			JOptionPane.showMessageDialog(null, "恭喜！删除成功！");
			em=new EmpModel();
			em.UpdateModel(sql, params);
			}
			String[]params={"1"};
			String sql="select login.id,name,passward,joblevel from login,renshi where login.id=renshi.id and 1=?";
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
		}
	}
}
