package com.partys.emp;

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

public class EmpInfo extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4862664404523904129L;
	// �������
	private JPanel p1, p2, p3, p4, p5;
	private JLabel p1_l1, p3_l1;
	private JTextField p1_jtf;
	private JButton p1_jb, p4_jb1, p4_jb2, p4_jb3, p4_jb4;
	private JTable jtable;
	private JScrollPane jsp;
	private EmpModel em = null;
	private boolean detail = false;


	public EmpInfo() {
		// �������
		
		em = new EmpModel();
		em.querySimpleInfor();
		initLabel();
		initBtn();
		initTexFeild();
		initTable();
		initScrollPane();
		initPanel();
		ini();
	}
	

	
	private void initTexFeild(){
		p1_jtf = new JTextField(20);
		p1_jtf.addKeyListener(this);
	}
	
	private void initTable(){
		jtable = new JTable(em);
		jtable.setRowHeight(20);
	
		jtable.setOpaque(false);
		BasicUtil.horizontal(jtable);

	}
	
	private void initScrollPane(){
		jsp = new JScrollPane(jtable);
		jsp.getViewport().setOpaque(false);;
		jsp.setOpaque(false);
		
	}
	
	private void ini(){
		this.setLayout(new BorderLayout());
		this.add(p1, "North");
		this.add(p2, "Center");
		this.add(p5, "South");
		this.setOpaque(false);
		this.setVisible(true);
	}
	
	private void initLabel(){
		p1_l1 = new JLabel("������������Ա���Ż�ְλ��:");
		p1_l1.setFont(MyTools.f1);
		int sum = em.getNum();
		p3_l1 = new JLabel("�ܼ�¼��" + sum + "��");
	}
	
	private void initBtn(){
		p1_jb = new JButton("��ѯ");
		p1_jb.addActionListener(this);
		p1_jb.setFont(MyTools.f4);
		p4_jb1 = new JButton("��ϸ��Ϣ");
		p4_jb1.addActionListener(this);
		p4_jb1.setFont(MyTools.f4);
		p4_jb2 = new JButton("���");
		p4_jb2.addActionListener(this);
		p4_jb2.setFont(MyTools.f4);
		p4_jb3 = new JButton("�޸�");
		p4_jb3.addActionListener(this);
		p4_jb3.setFont(MyTools.f4);
		p4_jb4 = new JButton("ɾ��");
		p4_jb4.addActionListener(this);
		p4_jb4.setFont(MyTools.f4);
	}
	
	private void initPanel(){
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2 = new JPanel(new BorderLayout());				
		p3 = new JPanel(new FlowLayout(FlowLayout.LEFT,5,7));
	    p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p5 = new JPanel(new BorderLayout());
		
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		p5.setOpaque(false);
		p1.add(p1_l1);
		p1.add(p1_jtf);
		p1.add(p1_jb);
		p2.add(jsp);
		
		
		p3.add(p3_l1);
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);
		p5.add(p3, "West");
		p5.add(p4, "East");
		
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
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			}

			String empId = (String) this.jtable.getValueAt(rowNum, 0);
			String[] params = { empId };
			if (!detail) {
				em = new EmpModel();
				em.querryOnDataById(params);
				querry();
				p4_jb1.setText("��Ҫ��Ϣ");
			} else {
				em = new EmpModel();
				em.querySimpleInforOneData(params);
				querry();
				p4_jb1.setText("��ϸ��Ϣ");
			}
			detail = !detail;
		}

		else if (arg0.getSource().equals(p4_jb2)) {
			AddEmpDialog emd = new AddEmpDialog(null, "�����Ϣ", true);
			if (!emd.getFlag()) {
				em = new EmpModel();
				em.querySimpleInfor();
				querry();
				querryCount();
			}
			
			if(detail){
				detail=!detail;
				p4_jb1.setText("��ϸ��Ϣ");
			}
		}

		else if (arg0.getSource().equals(p4_jb3)) {
			int rowNum = this.jtable.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			}
			
			else {
				UpdateEmpDialog up=null;
				em = new EmpModel();
				String id = (String)jtable.getValueAt(rowNum, 0);
				String[] params = { id };		
					em.querryOnDataById(params);
					up= new UpdateEmpDialog(null, "�޸���Ϣ", true,
							0, em);
				
				if (!up.getFlag()) {
					em = new EmpModel();
					em.querySimpleInfor();
					querry();
					querryCount();
					detail = false;
					p4_jb1.setText("��ϸ��Ϣ");
				}				
			}
		}

		else if (arg0.getSource().equals(p4_jb4)) {
			int rowNum = this.jtable.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			} else {
					
				if(JOptionPane.showConfirmDialog(this, "ȷ��Ҫɾ����","ɾ����Ϣ",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){										
					String empId = (String) jtable.getValueAt(rowNum, 0);
					String[] params = { empId };
					em = new EmpModel();
					em.deleteByID(params);
					querryCount();
					em.querySimpleInfor();
					querry();
					JOptionPane.showMessageDialog(null, "��ϲ��ɾ���ɹ���");
					if(detail){
						p4_jb1.setText("��ϸ��Ϣ");
						detail=!detail;
					}
				}				
			}
		}
	}

	private void querryCount() {
		int sum = em.getNum();
		p3_l1.setText("�ܼ�¼��" + sum + "��");
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
