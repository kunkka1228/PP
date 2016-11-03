package com.partys.book;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import DomXML.DOMParser;

import com.partys.listener.MyJButtonMouseMoveListener;
import com.partys.model.BookModel;

public class CalendarFrame extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -15700584659172661L;
	private JScrollPane labelPanel[] = new JScrollPane[42];
	private JPanel contentPanel[]=new JPanel[42];
	private JTextField year_text,month_text ;
	private JButton titleName[] = new JButton[7];
	private JButton button;
	private String name[] = { "��", "һ", "��", "��", "��", "��", "��" };
	private JButton nextMonth, previousMonth;
	private int year , month; // ����������ʾ��������Ϣ
	private CalendarBean calendar;
	private JLabel showMessage,lbl1,lbl2,year_label,month_label;
	private JPanel pCenter, pNorth,pSouth;
	private BookModel bm;
	private String[] day,partys;
	private Color[] colorArr;
	private MyJButtonMouseMoveListener mmml;
	public CalendarFrame() {
		iniListener();
		initLabel();
		initDate();
		initData();
		initPcenter();
		
		initTextField();
		initBtn();
		initPnorth();
		initPcenterData();
		initpSanel();
		this.setLayout(new BorderLayout());
		this.add(pNorth, BorderLayout.NORTH);// �������pNorth �ڱ�������
		this.add(pCenter, BorderLayout.CENTER);// �������scrollPane����������
		
		this.add(pSouth, BorderLayout.SOUTH);// �������pSouth ��������
		this.setOpaque(false);

	}
	
	private void iniListener(){
		mmml=new MyJButtonMouseMoveListener();
	}
	
	private void initLabel(){
		year_label=new JLabel("��");
		month_label=new JLabel("��");
		lbl1 = new JLabel("��������ݣ�");
		lbl2 = new JLabel("      ");
		showMessage = new JLabel("", JLabel.CENTER);
	}
	
	private void initDate(){
		year=Calendar.getInstance().get(Calendar.YEAR);
		month=Calendar.getInstance().get(Calendar.MONTH)+1;

	}
	private void initTextField(){
		year_text= new JTextField(4);
		year_text.addActionListener(this);
		month_text= new JTextField(2);
		month_text.addActionListener(this);
	}
	private void initBtn(){
		nextMonth = new JButton("����");
		previousMonth = new JButton("����");
		button = new JButton(new ImageIcon("image/book/ConfirmBtn.png"));
		button.setPreferredSize(new Dimension(69, 28));
		button.setContentAreaFilled(false);
		button.setBorder(null);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		// ע�������
		nextMonth.addActionListener(this);
		previousMonth.addActionListener(this);
		button.addActionListener(this);
		button.addMouseListener(mmml);
	}
	

	private void initPnorth(){		
		JPanel p1=new JPanel(null);
		p1.setOpaque(false);
		JPanel p2=new JPanel(new FlowLayout(FlowLayout.RIGHT,3,3));
		p2.setOpaque(false);
		pNorth = new JPanel(new GridLayout(1, 2));				
		DOMParser parser=new DOMParser("settings.xml");
		partys=parser.getAttributeByTagName("party", "name");
		colorArr=new Color[partys.length];
		for(int i=0;i<partys.length;i++){
			JLabel icon=new JLabel(partys[i]);
			icon.setBounds(32+120*i, 8, 50, 20);
			JLabel color=new JLabel();
			color.setOpaque(true);
			color.setBounds(10+120*i, 11, 15, 15);
			int[] arr=parser.getColorByID("party", (i+1)+"");
			Color newColor=new Color(arr[0],arr[1],arr[2]);
			colorArr[i]=newColor;
			color.setBackground(newColor);
			p1.add(color);
			p1.add(icon);		
		}		
		p2.add(showMessage);
		p2.add(lbl2);
		p2.add(previousMonth);
		p2.add(nextMonth);
		pNorth.add(p1);
		pNorth.add(p2);
		pNorth.setOpaque(false);
	}
	
	private void initpSanel(){		
		pSouth = new JPanel();
		pSouth.add(lbl1);
		pSouth.add(year_text);
		pSouth.add(year_label);
		pSouth.add(month_text);
		pSouth.add(month_label);
		pSouth.add(button);
		showMessage.setText("������" + year + "��"
				+ month + "��");
		pCenter.setOpaque(false);
		
		pSouth.setOpaque(false);
	}
	
	private void initPcenter(){
		pCenter = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		for (int i = 0; i < 7; i++) {
			titleName[i] = new JButton(name[i]);
			titleName[i].setContentAreaFilled(false);
			titleName[i].setFocusable(false);
			pCenter.add(titleName[i]);
			c.fill = GridBagConstraints.BOTH;
			c.gridx = i;
			c.gridy = 0;
			c.weightx = 1;
			pCenter.add(titleName[i], c);
		}
		int j = 1;
		int k = 0;
		calendar = new CalendarBean();
		calendar.setYear(year);
		calendar.setMonth(month);
		day = calendar.getCalendar();

		for (int i = 0; i < 42; i++) {
			if (i != 0 & i % 7 == 0) {
				j++;
			}
			k = i % 7;
			contentPanel[i]=new JPanel(null);
			contentPanel[i].setOpaque(false);
			labelPanel[i] = new JScrollPane();
			
			labelPanel[i].setBorder(null);
			
			labelPanel[i].getViewport().setOpaque(false);
			labelPanel[i].setOpaque(false);
			c.fill = GridBagConstraints.BOTH;
			c.gridx = k;
			c.gridy = j;
			c.weightx = 1;
			c.gridheight = 1;

			c.weighty = 1;
			if (day[i] != null) {
				labelPanel[i].setBorder(BorderFactory
						.createTitledBorder(BorderFactory.createEtchedBorder(),day[i] + "��"));
				labelPanel[i].setViewportView(contentPanel[i]);
			}	
			pCenter.add(labelPanel[i], c);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nextMonth) {
			
			month++;
			if (month > 12){
				month = 1;
				year++;
				calendar.setYear(year);
			}
			calendar.setMonth(month);
			day = calendar.getCalendar();
			bm=new BookModel();	
			bm.queryAllCurrentDateInfor(year,month);
			for(int x=0;x<contentPanel.length;x++){
				contentPanel[x].removeAll();
			}
			
			initPcenterData();
			settingDate();
		}

		else if (e.getSource() == previousMonth) {
			month--;
			if (month < 1)
			{
				month = 12;
				year--;
				calendar.setYear(year);		
			}
			calendar.setMonth(month);
			day = calendar.getCalendar();
			bm=new BookModel();	
			bm.queryAllCurrentDateInfor(year,month);
			for(int x=0;x<contentPanel.length;x++){
				contentPanel[x].removeAll();
			}
					
			initPcenterData();
			settingDate();
		} else if (e.getSource() == button) {
			button.setFocusable(false);
			String y=year_text.getText();
			if(!(y.trim().equals(""))){				
				year=Integer.parseInt(y);
				String m=month_text.getText();
				if(m.trim().equals("")){
					month=1;
				}
				else{
					month=Integer.parseInt(m);
				}

				calendar.setYear(year);
				calendar.setMonth(month);
				day = calendar.getCalendar();
				bm=new BookModel();	
				bm.queryAllCurrentDateInfor(year,month);
				for(int x=0;x<contentPanel.length;x++){
					contentPanel[x].removeAll();
				}				
				initPcenterData();
				settingDate();
			}
			
		}
		showMessage.setText("������" + year + "��"
				+ month + "��");
	}
	
	
	private void settingDate(){
		String day[] = calendar.getCalendar();
		for (int i = 0; i < 42; i++) {
			labelPanel[i].setBorder(null);
			if (day[i] != null) {
				labelPanel[i].setBorder(BorderFactory
						.createTitledBorder(BorderFactory.createEtchedBorder(),day[i] + "��"));
			}
		}
	}
	
	private void initData(){
		bm=new BookModel();	
		bm.queryAllCurrentDateInfor(year,month);
	}
	
	
	private void initPcenterData(){
		int date=1;
		
		for(int x=0;x<labelPanel.length;x++){
			if(day[x]!=null){	
				
				ArrayList<String[]> arr=bm.queryCurrentDateInfor(bm,year, month, date);
			
				int len=arr.size();
				contentPanel[x].setPreferredSize(new Dimension(100, 23*len));
				labelPanel[x].setPreferredSize(new Dimension(100, 192));
				for(int y=0;y<len;y++){
					String[] infor=arr.get(y);
					JLabel label=new JLabel(infor[1]+"~"+infor[2],JLabel.CENTER);
					int index=bm.findIndex(infor[3], partys);
					label.setOpaque(true);
					label.setBackground(colorArr[index]);	
					label.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.pink));
					label.setBounds(5, 3+20*y+3*y, 150, 20);
					label.setCursor(new Cursor(Cursor.HAND_CURSOR));
					contentPanel[x].add(label);
					
			
				}
					
				date++;
			}
			else{
				contentPanel[x].setPreferredSize(new Dimension(0,0));
			}
		}
	}
	public int getScollHeight(){
		return labelPanel[1].getHeight();
	}
	
	public int getScollWidth(){
		return labelPanel[1].getWidth();
	}
}
