package com.partys.customer;
//这是人事管理界面

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
	//定义组件
	private JPanel p1,p2,p3,p4,p5,p6,p7,p8;
	private JLabel p1_l1,p3_l1,empt,recordNum_label;
	private JTextField p1_jtf;
	private JButton p1_jb,p4_jb1,p4_jb2,p4_jb3,p4_jb4,right,left;
	private JTable jtable;
	private JScrollPane jsp;
	private CustomerModel cm;
	private boolean detail=false;
	private JComboBox<String> dianmian,keyWords,recordNum_combobox;
	private int cursorIndexOld=0;
	private int cursorIndex=30;
	private MyJButtonMouseMoveListener mmml;
	private String number="";
	private String place="";
	private String allRow;
	
	
	
	public CustomerInfor(String allRow)
	{		
		this.allRow=allRow;
		cm=new CustomerModel();
		cm.querySimpleInfor(allRow);	
		iniListener();
		iniLabel();
		iniTextFeild();
		iniTable();		
		iniScollPane();
		iniComobox();
		iniBtn();
		iniPanel();
		ini();		
	}
	
	private void ini(){
		this.setLayout(new BorderLayout());
		this.add(p1,"North");
		this.add(p2,"Center");
		this.add(p5,"South");
		this.setVisible(true);		
	}
	
	private void iniTextFeild(){
		p1_jtf=new JTextField(11);
		p1_jtf.addKeyListener(this);
	}
	private void iniListener(){
		mmml=new MyJButtonMouseMoveListener();
	}
	
	private void iniScollPane(){
		jsp=new JScrollPane(jtable);
	}
	
	private void iniTable(){
		jtable= new JTable(cm);
		jtable.setRowHeight(20);
		BasicUtil.horizontal(jtable);
	}
	
	private void iniComobox(){
		String[] dianmianArr=cm.getAllPartys();
		dianmian=new JComboBox<String>(dianmianArr);
		dianmian.addActionListener(this);
		dianmian.setBounds(30, 30, 50, 50);
		String[] keys={"编    号","姓    名","联系方式"};
		keyWords=new JComboBox<String>(keys);
		String[] recordNumArr={"10","30","40","50","60","70"};
		recordNumArr=BasicUtil.caculatorNum(recordNumArr,allRow);
		recordNum_combobox=new JComboBox(recordNumArr);
		recordNum_combobox.setSelectedItem(allRow);
		recordNum_combobox.addActionListener(this);
		place=dianmian.getSelectedItem().toString();
		number=(String) recordNum_combobox.getSelectedItem();
	}
	
	private void iniBtn(){
		p1_jb=new JButton("查询");
		p1_jb.addActionListener(this);
		p1_jb.setFont(MyTools.f4);
		left=new JButton(new ImageIcon("image/left.png"));
		left.setEnabled(false);
		right=new JButton(new ImageIcon("image/right.png"));
		btnSetting(left);
		btnSetting(right);
		right.addMouseListener(mmml);
		right.addMouseListener(this);
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
	}
	
	private void iniLabel(){
		p1_l1=new JLabel("关键字:");		
		p1_l1.setFont(MyTools.f1);
		int sum=cm.getNum();
		p3_l1=new JLabel("总记录是"+sum+"条");	
		recordNum_label=new JLabel("每页显示记录数目:");		
		empt=new JLabel(" ");
	}
	private void iniPanel(){
		
		p2=new JPanel(new BorderLayout());
		p2.add(jsp);
		
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT,5,7));		
		p3.add(p3_l1);
		p4=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);
		p5=new JPanel(new BorderLayout());
		p5.add(p3,"West");
		p5.add(p4,"East");
		//中间		
		p6=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p6.add(dianmian);
		p7=new JPanel(new FlowLayout(FlowLayout.LEFT));			
		p7.add(p1_l1);
		p7.add(keyWords);
		p7.add(p1_jtf);
		p7.add(p1_jb);	
		p8=new JPanel(new FlowLayout(FlowLayout.RIGHT,13,9));
		p8.add(recordNum_label);
		p8.add(recordNum_combobox);
		p8.add(left);
		p8.add(right);
		p8.add(empt);
		p1=new JPanel(new GridLayout(1,3));
		p1.setBounds(0, 0, 600, 100);
		p1.add(p6);
		p1.add(p7);
		p1.add(p8);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		if (arg0.getSource().equals(p1_jb)) {
			queryContent();
		}

		else if (arg0.getSource().equals(p4_jb1)) {
			int rowNum = jtable.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选择一行！");
			}

			String customerId = (String)cm.getValueAt(rowNum, 0);
			String[] params = { customerId };
			if (!detail) {
				cm = new CustomerModel();
				cm.querryOnDataById(params);
				querry();
				p4_jb1.setText("简要信息");
			} else {
				cm = new CustomerModel();
				cm.querySimpleInforOneData(params);
				querry();
				p4_jb1.setText("详细信息");
			}
			detail = !detail;
		}
		


		else if (arg0.getSource().equals(p4_jb2)) {
			AddCustomerDialog emd = new AddCustomerDialog(null, "添加", true);
			if (!emd.getFlag()) {
				cm = new CustomerModel();
				
				try {
					dianmian.setSelectedItem("所有");
					cm.querySimpleInfor(place,0,number,true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				querry();
				querryCount();
				
				cursorIndexOld=0;
				if(!dianmian.isEnabled()){
					dianmian.setEnabled(true);
				}
				if(right.getMouseListeners().length==1){		
					enableBtn(right);											
				}	
				if(cursorIndexOld==0&&left.getMouseListeners().length==3){		
					disableBtn(left);	
					right.setFocusable(false);
					dianmian.setEnabled(true);
				}
			}
			if(detail){
				detail=!detail;
				p4_jb1.setText("详细信息");
			}
		}

		else if (arg0.getSource().equals(p4_jb3)) {
			int rowNum = jtable.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选择一行！");
			}

			else {
				CustomerModel cms=cm;
				cm = new CustomerModel();
				String customerId = (String)jtable.getValueAt(rowNum, 0);
				String[] params = { customerId };
				cm.querryOnDataById(params);
				UpdateCustomerDialog ucd = new UpdateCustomerDialog(null, "修改", true,
						0, cm);
				if (!ucd.getFlag()) {

					cm.querySimpleInfor(place,cursorIndexOld,number,true);
					querry();
					dianmian.setSelectedItem("所有");
					querryCount();
					detail = false;
					p4_jb1.setText("详细信息");
				}
				else{
					cm=cms;				
				}
			}			
		}

		else if (arg0.getSource().equals(p4_jb4)) {
			int rowNum = jtable.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选择一行！");
			} else {
				if(JOptionPane.showConfirmDialog(this, "确定要删除吗","删除信息",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){					
					String customerId =(String)cm.getValueAt(rowNum, 0);
					String[] params = { customerId };
					String tuangouhaoName=cm.getDataById("tuangouhao",params);
					
					cm = new CustomerModel();
					cm.deleteByID(params);					
					querryCount();
					cm.querySimpleInfor(place,cursorIndexOld,number,true);
					querry();
					dianmian.setSelectedItem("所有");
					cm.deleteFile(tuangouhaoName);
					if(detail){
						p4_jb1.setText("详细信息");
						detail=!detail;
					}
				}				
			}
		}
		
		else if(arg0.getSource()==recordNum_combobox ){
			number=recordNum_combobox.getSelectedItem().toString();
			changeCombo();
			
		}
		
		
		else if(arg0.getSource()==dianmian){
			place=dianmian.getSelectedItem().toString();
			changeCombo();
		}
				
	}

	private void queryContent() {
		String content=p1_jtf.getText().trim();
		cm = new CustomerModel();
		if (content.equals("")) {
			cm.querySimpleInfor(place,0,number,true);	
			cursorIndexOld=0;
			
			if(right.getMouseListeners().length==1){		
				enableBtn(right);											
			}	
			if(cursorIndexOld==0&&left.getMouseListeners().length==3){		
				disableBtn(left);	
				right.setFocusable(false);
				dianmian.setEnabled(true);
			}
			
		} else {
			String keywords=keyWords.getSelectedItem().toString();
			content+="%";
			String params[] = {content};				
			cm.queryByKeywords(keywords, params, place, 0, number, true);				
		}
		querry();
	}
	
	private void changeCombo(){
		cm = new CustomerModel();
		cursorIndex=cm.querySimpleInfor(place,cursorIndexOld,number,true);
		int maxNum=cm.getMaxCountByPlace(place);
		int numPerPage=Integer.parseInt((String)recordNum_combobox.getSelectedItem());
		if(maxNum-cursorIndexOld<numPerPage|(maxNum-cursorIndexOld==numPerPage)&(maxNum-cursorIndex<maxNum)){	
			disableRight();							
		}
		else{
			enableRight();
		}
		
		querry();
	}
	
	private void disableRight(){
		if(right.getMouseListeners().length==3){
			disableBtn(right);	
		}
	}
	
	private void enableRight(){
		if(right.getMouseListeners().length==1){	
			enableBtn(right);
		}
	}
		
	private void enableLeft(){
		if(left.getMouseListeners().length==1){
			enableBtn(left);		
		}
	}
	
	private void enableBtn(JButton btn){
		btn.addMouseListener(mmml);
		btn.addMouseListener(this);
		btn.setEnabled(true);	
		
	}
	
	private void disableBtn(JButton btn){
		btn.setEnabled(false);	
		btn.removeMouseListener(this);
		btn.removeMouseListener(mmml);					
		btn.setBorder(null);	
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
			queryContent();
		}		
	}
	
	private void btnSetting(JButton btn){
		btn.setContentAreaFilled(false);
		btn.setBorder(null);
		btn.setPreferredSize(new Dimension(18,15));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==left){
			if(cursorIndexOld==0&&left.getMouseListeners().length==3){		
				disableBtn(left);	
				right.setFocusable(false);
				dianmian.setEnabled(true);
				cursorIndexOld=0;
			}
		}		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
		
	}
	
	public int getPanelHight(){
		return p2.getHeight();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
		if(e.getSource()==right){
			cm = new CustomerModel();	
			enableLeft();		
			if(dianmian.isEnabled()){
				dianmian.setEnabled(false);
			}
						
			cursorIndexOld=cursorIndex;
			cursorIndex=cm.querySimpleInfor(place,cursorIndex,number,true);		
			querry();
			int maxNum=cm.getMaxCountByPlace(place);
			int numPerPage=Integer.parseInt((String)recordNum_combobox.getSelectedItem());
			if(maxNum-cursorIndexOld<numPerPage|(maxNum-cursorIndexOld==numPerPage)&(maxNum-cursorIndex<maxNum)){	
				disableRight();							
			}		
		}
		
		else if(e.getSource()==left){			
			if(cursorIndexOld>0){
				enableRight();		
				cm = new CustomerModel();
				cursorIndex=cursorIndexOld;
				cursorIndexOld=cm.querySimpleInfor(place,cursorIndex+1,number,false)-1;						
				querry();	
				
			}
		}		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
}
