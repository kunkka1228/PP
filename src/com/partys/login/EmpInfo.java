package com.partys.login;
//�������¹������

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.partys.model.EmpModel;
import com.partys.tools.BasicUtil;
import com.partys.tools.MyTools;
public class EmpInfo extends JPanel implements ActionListener,KeyListener{
	//�������
	private JPanel p1,p2,p3,p4,p5;
	private JLabel p1_l1,p3_l1;
	private JTextField p1_jtf;
	private JButton p1_jb,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
	private JTable jtable;
	private JScrollPane jsp;
	private EmpModel em=null;

	public EmpInfo()
	{
		//�������
		
		//��
		p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_l1=new JLabel("������������Ա���Ż�ְλ��:");
		p1_l1.setFont(MyTools.f1);
		p1_jtf=new JTextField(20);
		p1_jtf.addKeyListener(this);
		p1_jb=new JButton("��ѯ");
		p1_jb.addActionListener(this);
		p1_jb.setFont(MyTools.f4);
		p1.add(p1_l1);
		p1.add(p1_jtf);
		p1.add(p1_jb);
		
		
		//�м�
		
		p2=new JPanel(new BorderLayout());
		String []params={"1"};
		String sql="select id,name,sex,joblevel,address,edu from renshi where 1=?";
		em=new EmpModel();
		em.query(sql, params);
		
		jtable= new JTable(em);
		BasicUtil.horizontal(jtable);
		jsp=new JScrollPane(jtable);
		p2.add(jsp);
		
		//��
		p5=new JPanel(new BorderLayout());
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		sql="select count(*) from renshi";
		em=new EmpModel();
		int sum=em.getNum(sql);
		

		p3_l1=new JLabel("�ܼ�¼��"+sum+"��");
		
		p3.add(p3_l1);
		p4=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4_jb1=new JButton("��ϸ��Ϣ");
		p4_jb1.addActionListener(this);
		p4_jb1.setFont(MyTools.f4);
		p4_jb2=new JButton("���");
		p4_jb2.addActionListener(this);
		p4_jb2.setFont(MyTools.f4);
		p4_jb3=new JButton("�޸�");
		p4_jb3.addActionListener(this);
		p4_jb3.setFont(MyTools.f4);
		p4_jb4=new JButton("ɾ��");
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
		// TODO �Զ����ɵķ������
		
		if(arg0.getSource().equals(p1_jb))
		{
			
			if(p1_jtf.getText().trim().equals(""))
			{
				String[]params={"1"};
				String sql="select id,name,sex,joblevel,address,edu from renshi where 1=?";
				em=new EmpModel();
				em.query(sql, params);				
				jtable.setModel(em);
				BasicUtil.horizontal(jtable);
			}
			else{
				String params[]={p1_jtf.getText().trim(),p1_jtf.getText().trim()};
				String sql="select id,name,sex,joblevel,address,edu from renshi where id=? or name=?";
				em=new EmpModel();
				em.query(sql, params);				
				jtable.setModel(em);
				BasicUtil.horizontal(jtable);
			}
		}
		else if(arg0.getSource().equals(p4_jb1))
		{
			int rowNum=this.jtable.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			}
			
			String empId=(String)this.jtable.getValueAt(rowNum, 0);
			String sql="select * from renshi where id=?";
			String []params={empId};
			em=new EmpModel();
			em.query(sql, params);
			
			jtable.setModel(em);
			BasicUtil.horizontal(jtable);
			
		}
		else if(arg0.getSource().equals(p4_jb2))
		{
			new AddEmpDialog(null,"���",true);			
			String sql="select id,name,sex,joblevel,address,edu from renshi where 1=?";
			String[]params={"1"};
			em=new EmpModel();
			em.query(sql, params);			
			jtable.setModel(em);
			BasicUtil.horizontal(jtable);
			
		}
		else if(arg0.getSource().equals(p4_jb3))
		{						
			int rowNum=this.jtable.getSelectedRow();
			em=new EmpModel();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			}

			else
			{
				new UpdEmpDialog(this,"�޸�",true,em,rowNum);
				
			}
			
			String[]params={"1"};
			String sql="select id,name,sex,joblevel,address,edu from renshi where 1=?";
			
			em.query(sql, params);			
			jtable.setModel(em);
			BasicUtil.horizontal(jtable);
		}
		else if(arg0.getSource().equals(p4_jb4))
		{
			int rowNum=this.jtable.getSelectedRow();			
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			}
			else{	
			String empId=(String)this.jtable.getValueAt(rowNum, 0);
			String sql="delete from renshi where id=?";
			String []params={empId};
			JOptionPane.showMessageDialog(null, "��ϲ��ɾ���ɹ���");
			em=new EmpModel();
			em.UpdateModel(sql, params);
			}			
			String sql="select id,name,sex,joblevel,address,edu from renshi where 1=?";
			String[]params={"1"};
			em=new EmpModel();
			em.query(sql, params);			
			jtable.setModel(em);
			BasicUtil.horizontal(jtable);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==p1_jtf){
			String sql="";
			String params[]={p1_jtf.getText().trim(),p1_jtf.getText().trim()};
			sql="select id,name,sex,joblevel,address,edu from renshi where id=? or name=?";
			if(p1_jtf.getText().trim().equals("")){
				sql="select id,name,sex,joblevel,address,edu from renshi";
				params=null;
			}
			em=new EmpModel();
			em.query(sql, params);		
			jtable.setModel(em);
			BasicUtil.horizontal(jtable);
		}
		
	}
}
