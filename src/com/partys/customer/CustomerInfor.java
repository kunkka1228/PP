package com.partys.customer;
//这是人事管理界面

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.partys.emp.AddEmpDialog;
import com.partys.model.CustomerModel;
import com.partys.tools.BasicUtil;
import com.partys.tools.MyTools;
public class CustomerInfor extends JPanel implements ActionListener,KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4862664404523904129L;
	//定义组件
	private JPanel p1,p2,p3,p4,p5;
	private JLabel p1_l1,p3_l1;
	private JTextField p1_jtf;
	private JButton p1_jb,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
	private JTable jtable;
	private JScrollPane jsp;
	private CustomerModel cm;
	private boolean detail=false;
	private JComboBox<String> dianmian,keyWords;
	private JPanel p6,p7,p8,p9;
	private JLabel right,left;
	public CustomerInfor()
	{
		//创建组件
		
		//北
		p7=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p6=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p8=new JPanel(null);
		p9=new JPanel(null);
		p1=new JPanel(new GridLayout(1,3));
		p1_l1=new JLabel("关键字:");		
		p1_l1.setFont(MyTools.f1);
		cm=new CustomerModel();
		dianmian=new JComboBox<String>(cm.getAllPartys());
		String[] keys={"编    号","姓    名","联系方式"};
		keyWords=new JComboBox<String>(keys);
		p1_jtf=new JTextField(11);
		p1_jtf.addKeyListener(this);
		p1_jb=new JButton("查询");
		p1_jb.addActionListener(this);
		p1_jb.setFont(MyTools.f4);
		dianmian.setBounds(30, 30, 50, 50);
		p1.setBounds(0, 0, 600, 100);
		p6.add(dianmian);		
		p7.add(p1_l1);
		p7.add(keyWords);
		p7.add(p1_jtf);
		p7.add(p1_jb);	
		left=new JLabel(new ImageIcon("image/left.png"));
		right=new JLabel(new ImageIcon("image/right.png"));
		left.setBounds(400, 15, 13, 12);
		right.setBounds(440, 15, 13, 12);
		p8.add(right);
		p8.add(left);
		
		p1.add(p6);
		p1.add(p7);
		p1.add(p9);

		//中间		
		p2=new JPanel(new BorderLayout());
		cm=new CustomerModel();
		cm.querySimpleInfor();	
		jtable= new JTable(cm);
		BasicUtil.horizontal(jtable);
		jsp=new JScrollPane(jtable);
		p2.add(jsp);
		
		//南
		p5=new JPanel(new BorderLayout());
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));		

		int sum=cm.getNum();
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
		p5.add(p8,"Center");
		this.setLayout(new BorderLayout());
		this.add(p1,"North");
		this.add(p2,"Center");
		this.add(p5,"South");
		this.setVisible(true);		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(p1_jb)) {
			if (p1_jtf.getText().trim().equals("")) {
				cm = new CustomerModel();
				cm.querySimpleInfor();
				querry();
			} else {
				String params[] = { p1_jtf.getText().trim(),
						p1_jtf.getText().trim() };
				cm = new CustomerModel();
				cm.queryByNameOrId(params);
				querry();
			}
		}

		else if (arg0.getSource().equals(p4_jb1)) {
			int rowNum = jtable.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选择一行！");
			}

			String empId = (String) this.jtable.getValueAt(rowNum, 0);
			String[] params = { empId };
			if (!detail) {
				cm = new CustomerModel();
				cm.querryOnDataById(params);
				querry();
				p4_jb1.setText("简要信息");
			} else {
				cm = new CustomerModel();
				cm.querySimpleInfor();
				querry();
				p4_jb1.setText("详细信息");
			}
			detail = !detail;
		}

		else if (arg0.getSource().equals(p4_jb2)) {
			AddEmpDialog emd = new AddEmpDialog(null, "添加", true);
			if (!emd.getFlag()) {
				cm = new CustomerModel();
				cm.querySimpleInfor();
				querry();
				querryCount();
			}
		}

		else if (arg0.getSource().equals(p4_jb3)) {
			int rowNum = this.jtable.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选择一行！");
			}

			else {
				cm = new CustomerModel();
				if (detail) {
					rowNum = 0;
					String id = (String) jtable.getValueAt(rowNum, 0);
					String[] params = { id };
					cm.querryOnDataById(params);
				} else {
					cm.querryAll();
				}
//				UpdateEmpDialog up = new UpdateEmpDialog(null, "修改", true,
//						rowNum, cm);
//				if (!up.getFlag()) {
//					cm = new CustomerModel();
//					cm.querySimpleInfor();
//					querry();
//					querryCount();
//					detail = false;
//				}
			}
		}

		else if (arg0.getSource().equals(p4_jb4)) {
			int rowNum = this.jtable.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选择一行！");
			} else {
				String empId = (String) jtable.getValueAt(rowNum, 0);
				String[] params = { empId };
				cm = new CustomerModel();
				cm.deleteByID(params);

				JOptionPane.showMessageDialog(null, "恭喜！删除成功！");
				querryCount();
				cm.querySimpleInfor();

				querry();
			}
		}
	}

	private void querryCount() {
		int sum = cm.getNum();
		p3_l1.setText("总记录是" + sum + "条");
	}

	private void querry() {
		jtable.setModel(cm);
		BasicUtil.horizontal(jtable);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == p1_jtf) {
			cm = new CustomerModel();
			String params[] = { p1_jtf.getText().trim(),
					p1_jtf.getText().trim() };
			cm.queryByNameOrId(params);
			if (p1_jtf.getText().trim().equals("")) {
				cm.querySimpleInfor();
			}
			querry();
		}
	}
}
