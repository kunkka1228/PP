package com.partys.login;
//这是人事管理界面

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
public class MenuInfo extends JPanel implements ActionListener{
	//定义组件
	JPanel p1,p2,p3,p4,p5;
	JLabel p1_l1,p3_l1;
	JTextField p1_jtf;
	JButton p1_jb,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
	JTable jtable;
	JScrollPane jsp;
	EmpModel em=null;
	public MenuInfo()
	{
		//创建组件
		
		//北
		p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_l1=new JLabel("请输入菜名（编号或厨师）:");
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
		String sql="select foodid,foodname,price,chief from menu where 1=?";
		em=new EmpModel();
		em.query(sql, params);
		jtable= new JTable(em);
//		jtable.setModel(em);
		jsp=new JScrollPane(jtable);

		p2.add(jsp);
		
		//南
		p5=new JPanel(new BorderLayout());
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		sql="select count(*) from menu";
		em=new EmpModel();
		int sum=em.getNum(sql);
		
//		jtable.setModel(em);
		//System.out.print(sum);
		p3_l1=new JLabel("总记录是"+sum+"条");
		
		//jsp=new JScrollPane(jtable);
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
		//this.setBackground(Color.pink);
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
				String sql="select foodid,foodname,price,chief from menu where 1=?";
				em=new EmpModel();
				em.query(sql, params);
				jtable.setModel(em);
			}
			else{
			String params[]={p1_jtf.getText().trim(),p1_jtf.getText().trim(),p1_jtf.getText().trim()};
			String sql="select foodid,foodname,price,chief from menu where foodid=? or foodname=? or chief=?";
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
			
			String foodId=(String)this.jtable.getValueAt(rowNum, 0);
			String sql="select*from menu where foodid=?";
			String []params={foodId};
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
			
		}
		else if(arg0.getSource().equals(p4_jb2))
		{
			if(true)
			{
				new AddFoodDialog(this,"添加",true);
			}
			String[]params={"1"};
			String sql="select*from menu where 1=?";
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
			new UpdFoodDialog(this,"修改",true,em,rowNum);
			}
			
			String[]params={"1"};
			String sql="select*from menu where 1=?";
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
			String foodId=(String)this.jtable.getValueAt(rowNum, 0);
			String sql="delete from menu where foodid=?";
			String []params={foodId};
			JOptionPane.showMessageDialog(null, "恭喜！删除成功！");
			em=new EmpModel();
			em.UpdateModel(sql, params);
			}
			String[]params={"1"};
			String sql="select*from menu where 1=?";
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
		}
	}
}

