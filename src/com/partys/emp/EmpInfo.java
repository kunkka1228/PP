package com.partys.emp;

//这是人事管理界面

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
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

public class EmpInfo extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4862664404523904129L;
	// 定义组件
	private JPanel p1, p2, p3, p4, p5;
	private JLabel p1_l1, p3_l1;
	private JTextField p1_jtf;
	private JButton p1_jb, p4_jb1, p4_jb2, p4_jb3, p4_jb4;
	private JTable jtable;
	private JScrollPane jsp;
	private EmpModel em = null;
	private boolean detail = false;

	public EmpInfo() {
		// 创建组件

		// 北
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_l1 = new JLabel("请输入姓名（员工号或职位）:");
		p1_l1.setFont(MyTools.f1);
		p1_jtf = new JTextField(20);
		p1_jtf.addKeyListener(this);
		p1_jb = new JButton("查询");
		p1_jb.addActionListener(this);
		p1_jb.setFont(MyTools.f4);
		p1.add(p1_l1);
		p1.add(p1_jtf);
		p1.add(p1_jb);
		// 中间
		p2 = new JPanel(new BorderLayout());

		em = new EmpModel();
		em.querySimpleInfor();
		jtable = new JTable(em);
		BasicUtil.horizontal(jtable);
		jsp = new JScrollPane(jtable);
		p2.add(jsp);

		// 南
		p5 = new JPanel(new BorderLayout());
		p3 = new JPanel(new FlowLayout(FlowLayout.LEFT,5,7));

		em = new EmpModel();
		int sum = em.getNum();
		p3_l1 = new JLabel("总记录是" + sum + "条");
		p3.add(p3_l1);
		p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4_jb1 = new JButton("详细信息");
		p4_jb1.addActionListener(this);
		p4_jb1.setFont(MyTools.f4);
		p4_jb2 = new JButton("添加");
		p4_jb2.addActionListener(this);
		p4_jb2.setFont(MyTools.f4);
		p4_jb3 = new JButton("修改");
		p4_jb3.addActionListener(this);
		p4_jb3.setFont(MyTools.f4);
		p4_jb4 = new JButton("删除");
		p4_jb4.addActionListener(this);
		p4_jb4.setFont(MyTools.f4);
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);
		p5.add(p3, "West");
		p5.add(p4, "East");
		this.setLayout(new BorderLayout());
		this.add(p1, "North");
		this.add(p2, "Center");
		this.add(p5, "South");
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(p1_jb)) {
			if (p1_jtf.getText().trim().equals("")) {
				em = new EmpModel();
				em.querySimpleInfor();
				querry();
			} else {
				String params[] = { p1_jtf.getText().trim(),
						p1_jtf.getText().trim() };
				em = new EmpModel();
				em.queryByNameOrId(params);
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
				em = new EmpModel();
				em.querryOnDataById(params);
				querry();
				p4_jb1.setText("简要信息");
			} else {
				em = new EmpModel();
				em.querySimpleInfor();
				querry();
				p4_jb1.setText("详细信息");
			}
			detail = !detail;
		}

		else if (arg0.getSource().equals(p4_jb2)) {
			AddEmpDialog emd = new AddEmpDialog(null, "添加信息", true);
			if (!emd.getFlag()) {
				em = new EmpModel();
				em.querySimpleInfor();
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
				em = new EmpModel();
				if (detail) {
					rowNum = 0;
					String id = (String) jtable.getValueAt(rowNum, 0);
					String[] params = { id };
					em.querryOnDataById(params);
				} else {
					em.querryAll();
				}
				UpdateEmpDialog up = new UpdateEmpDialog(null, "修改信息", true,
						rowNum, em);
				if (!up.getFlag()) {
					em = new EmpModel();
					em.querySimpleInfor();
					querry();
					querryCount();
					detail = false;
				}
			}
		}

		else if (arg0.getSource().equals(p4_jb4)) {
			int rowNum = this.jtable.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选择一行！");
			} else {
					
				if(JOptionPane.showConfirmDialog(this, "确定要删除吗","删除信息",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){					
					String empId = (String) jtable.getValueAt(rowNum, 0);
					String[] params = { empId };
					em = new EmpModel();
					em.deleteByID(params);
					querryCount();
					em.querySimpleInfor();
					querry();
					JOptionPane.showMessageDialog(null, "恭喜！删除成功！");
				}				
			}
		}
	}

	private void querryCount() {
		int sum = em.getNum();
		p3_l1.setText("总记录是" + sum + "条");
	}

	private void querry() {
		jtable.setModel(em);
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
			em = new EmpModel();
			String params[] = { p1_jtf.getText().trim(),
					p1_jtf.getText().trim() };
			em.queryByNameOrId(params);
			if (p1_jtf.getText().trim().equals("")) {
				em.querySimpleInfor();
			}
			querry();
		}
	}
}
