package com.partys.customer;
//�������¹������

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.partys.listener.MyJButtonMouseMoveListener;
import com.partys.model.CustomerModel;
import com.partys.tools.BasicUtil;
import com.partys.tools.MyTools;
public class CustomerInfor extends JPanel implements ActionListener,KeyListener,MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4862664404523904129L;
	//�������
	private JPanel p1,p2,p3,p4,p5;
	private JLabel p1_l1,p3_l1;
	private JTextField p1_jtf;
	private JButton p1_jb,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
	private JTable jtable;
	private JScrollPane jsp;
	private CustomerModel cm;
	private boolean detail=false;
	private JComboBox<String> dianmian,keyWords;
	private JPanel p6,p7,p8;
	private JButton right,left;
	private JLabel empt;
	public CustomerInfor()
	{
		//�������
		
		//��
		p7=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p6=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p8=new JPanel(new FlowLayout(FlowLayout.RIGHT,13,9));
		p1=new JPanel(new GridLayout(1,3));
		p1_l1=new JLabel("�ؼ���:");		
		p1_l1.setFont(MyTools.f1);
		cm=new CustomerModel();
		String[] dianmianArr=cm.getAllPartys();
		
		dianmian=new JComboBox<String>(dianmianArr);
		dianmian.addActionListener(this);
		String[] keys={"��    ��","��    ��","��ϵ��ʽ"};
		keyWords=new JComboBox<String>(keys);
		p1_jtf=new JTextField(11);
		p1_jtf.addKeyListener(this);
		p1_jb=new JButton("��ѯ");
		p1_jb.addActionListener(this);
		p1_jb.setFont(MyTools.f4);
		dianmian.setBounds(30, 30, 50, 50);
		p1.setBounds(0, 0, 600, 100);
		p6.add(dianmian);		
		p7.add(p1_l1);
		p7.add(keyWords);
		p7.add(p1_jtf);
		p7.add(p1_jb);	
		left=new JButton(new ImageIcon("image/left.png"));
		right=new JButton(new ImageIcon("image/right.png"));
		btnSetting(left);
		btnSetting(right);
		left.addMouseListener(new MyJButtonMouseMoveListener());
		right.addMouseListener(new MyJButtonMouseMoveListener());
		
		empt=new JLabel(" ");
		p8.add(left);
		p8.add(right);
		p8.add(empt);
		
		
		p1.add(p6);
		p1.add(p7);
		p1.add(p8);

		//�м�		
		p2=new JPanel(new BorderLayout());
		cm=new CustomerModel();
		cm.querySimpleInfor();	
		jtable= new JTable(cm);
		BasicUtil.horizontal(jtable);
		jsp=new JScrollPane(jtable);
		p2.add(jsp);
		
		//��
		p5=new JPanel(new BorderLayout());
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT,5,7));		

		int sum=cm.getNum();
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
		
		
		
		if (arg0.getSource().equals(p1_jb)) {
			String content=p1_jtf.getText().trim();
			if (content.equals("")) {
				cm = new CustomerModel();
				cm.querySimpleInfor();
				querry();
			} else {
				String place=dianmian.getSelectedItem().toString();
				String keywords=keyWords.getSelectedItem().toString();
				String params[] = {content,place};
				cm = new CustomerModel();
				cm.queryByKeywords(keywords,params);
				querry();
			}
		}

		else if (arg0.getSource().equals(p4_jb1)) {
			int rowNum = jtable.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			}

			String customerId = (String) this.jtable.getValueAt(rowNum, 0);
			String[] params = { customerId };
			if (!detail) {
				cm = new CustomerModel();
				cm.querryOnDataById(params);
				querry();
				p4_jb1.setText("��Ҫ��Ϣ");
			} else {
				cm = new CustomerModel();
				cm.querySimpleInforOneData(params);
				querry();
				p4_jb1.setText("��ϸ��Ϣ");
			}
			detail = !detail;
		}

		else if (arg0.getSource().equals(p4_jb2)) {
			AddCustomerDialog emd = new AddCustomerDialog(null, "���", true);
			if (!emd.getFlag()) {
				cm = new CustomerModel();
				cm.querySimpleInfor();
				querry();
				querryCount();
			}
		}

		else if (arg0.getSource().equals(p4_jb3)) {
			int rowNum = jtable.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
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
//				UpdateEmpDialog up = new UpdateEmpDialog(null, "�޸�", true,
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
			int rowNum = jtable.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			} else {
				if(JOptionPane.showConfirmDialog(this, "ȷ��Ҫɾ����","ɾ����Ϣ",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
					String customerId = (String) jtable.getValueAt(rowNum, 0);
					String tuangouhaoName = (String) jtable.getValueAt(rowNum, 10);
					String[] params = { customerId };
					cm = new CustomerModel();
					cm.deleteByID(params);					
					querryCount();
					cm.querySimpleInfor();
					querry();
					try {					
						File file=new File(tuangouhaoName);
						file.delete();
						JOptionPane.showMessageDialog(null, "��ϲ��ɾ���ɹ���");
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
					
				}				
			}
		}
		
		else if(arg0.getSource()==dianmian){
			String place=dianmian.getSelectedItem().toString();
			cm = new CustomerModel();
			cm.filterByPlace(place);			
			querry();
		}
	}

	private void querryCount() {
		int sum = cm.getNum();
		p3_l1.setText("�ܼ�¼��" + sum + "��");
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
//		if (e.getSource() == p1_jtf) {
//			cm = new CustomerModel();
//			String params[] = { p1_jtf.getText().trim(),
//					p1_jtf.getText().trim() };
////			cm.queryByKeywords(params);
//			if (p1_jtf.getText().trim().equals("")) {
//				cm.querySimpleInfor();
//			}
//			querry();
//		}
	}
	
	private void btnSetting(JButton btn){
		btn.setContentAreaFilled(false);
		btn.setBorder(null);
		btn.setPreferredSize(new Dimension(18,15));
		btn.addMouseListener(new MyJButtonMouseMoveListener());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
}
