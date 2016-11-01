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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DomXML.DOMParser;

import com.partys.model.BookModel;

public class CalendarFrame extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -15700584659172661L;
	private JPanel labelPanel[] = new JPanel[42];
	private JTextField text ;
	private JButton titleName[] = new JButton[7];
	private JButton button = new JButton();
	private String name[] = { "日", "一", "二", "三", "四", "五", "六" };
	private JButton nextMonth, previousMonth;
	private int year , month; // 启动程序显示的日期信息
	private CalendarBean calendar;
	private JLabel showMessage = new JLabel("", JLabel.CENTER);
	private JLabel lbl1 = new JLabel("请输入年份：");
	private JLabel lbl2 = new JLabel("      ");
	private JPanel pCenter, pNorth,pSouth;
	private BookModel bm;
	private String[] day;
	private String[] partys;
	private Color[] colorArr;
	public CalendarFrame() {
		initDate();
		initData();
		initPcenter();
		
		initTextField();
		initBtn();
		initPnorth();
		initPcenterData();
		initpSanel();
		this.setLayout(new BorderLayout());
		this.add(pNorth, BorderLayout.NORTH);// 窗口添加pNorth 在北面区域
		this.add(pCenter, BorderLayout.CENTER);// 窗口添加scrollPane在中心区域
		
		this.add(pSouth, BorderLayout.SOUTH);// 窗口添加pSouth 在南区域。
		this.setOpaque(false);

	}
	
	private void initDate(){
		year=Calendar.getInstance().get(Calendar.YEAR);
		month=Calendar.getInstance().get(Calendar.MONTH)+1;

	}
	private void initTextField(){
		text= new JTextField(10);
		text.addActionListener(this);
	}
	private void initBtn(){
		nextMonth = new JButton("下月");
		previousMonth = new JButton("上月");
		button = new JButton("确定");

		// 注册监听器
		nextMonth.addActionListener(this);
		previousMonth.addActionListener(this);
		button.addActionListener(this);
	}
	

	private void initPnorth(){		
		JPanel p1=new JPanel(null);
		p1.setOpaque(false);
		JPanel p2=new JPanel(new FlowLayout(FlowLayout.RIGHT,3,3));
		p2.setOpaque(false);
		pNorth = new JPanel(new GridLayout(1, 2));				
		DOMParser parser=new DOMParser("dianmian.xml");
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
		pSouth.add(text);
		pSouth.add(button);
		showMessage.setText("日历：" + year + "年"
				+ month + "月");
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
			labelPanel[i] = new JPanel(null);
			labelPanel[i].setOpaque(false);
			c.fill = GridBagConstraints.BOTH;
			c.gridx = k;
			c.gridy = j;
			c.weightx = 1;
			c.gridheight = 1;

			c.weighty = 1;
			if (day[i] != null) {
				labelPanel[i].setBorder(BorderFactory.createTitledBorder(day[i]
						+ "日"));
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
			settingDate();
		} else if (e.getSource() == button) {
			month++;
			if (month > 12)
				month = 1;
			calendar.setYear(Integer.parseInt(text.getText()));
			
			settingDate();
		}
		showMessage.setText("日历：" + year + "年"
				+ month + "月");
	}
	
	
	private void settingDate(){
		String day[] = calendar.getCalendar();
		for (int i = 0; i < 42; i++) {
			labelPanel[i].setBorder(null);
			if (day[i] != null) {
				labelPanel[i].setBorder(BorderFactory
						.createTitledBorder(day[i] + "日"));
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
				for(int y=0;y<arr.size();y++){
					String[] infor=arr.get(y);
					JLabel label=new JLabel(infor[1]+"~"+infor[2],JLabel.CENTER);
					int index=bm.findIndex(infor[3], partys);
					label.setOpaque(true);
					label.setBackground(colorArr[index]);	
					label.setBounds(5, 15, 100, 20);
					label.setCursor(new Cursor(Cursor.HAND_CURSOR));
//					labelPanel[x].setPreferredSize(new Dimension(86,101));
					labelPanel[x].add(label);
				}
						
				date++;
			}
		}
	}
}
