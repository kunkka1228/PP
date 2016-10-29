package com.partys.book;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalendarFrame extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -15700584659172661L;
	private JPanel labelPanel[] = new JPanel[42];
	private JTextField text = new JTextField(10);
	private JButton titleName[] = new JButton[7];
	private JButton button = new JButton();
	private String name[] = { "日", "一", "二", "三", "四", "五", "六" };
	private JButton nextMonth, previousMonth;
	private int year = 2016, month = 10; // 启动程序显示的日期信息
	private CalendarBean calendar;
	private JLabel showMessage = new JLabel("", JLabel.CENTER);
	private JLabel lbl1 = new JLabel("请输入年份：");
	private JLabel lbl2 = new JLabel("      ");

	public CalendarFrame() {
		JPanel pCenter = new JPanel(new GridBagLayout());
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
		String day[] = calendar.getCalendar();

		for (int i = 0; i < 42; i++) {
			if (i != 0 & i % 7 == 0) {
				j++;
			}
			k = i % 7;
			labelPanel[i] = new JPanel();
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

		text.addActionListener(this);

		nextMonth = new JButton("下月");
		previousMonth = new JButton("上月");
		button = new JButton("确定");

		// 注册监听器
		nextMonth.addActionListener(this);
		previousMonth.addActionListener(this);
		button.addActionListener(this);
		JPanel pNorth = new JPanel(), pSouth = new JPanel();
		pNorth.add(showMessage);
		pNorth.add(lbl2);
		pNorth.add(previousMonth);
		pNorth.add(nextMonth);
		pSouth.add(lbl1);
		pSouth.add(text);
		pSouth.add(button);
		showMessage.setText("日历：" + calendar.getYear() + "年"
				+ calendar.getMonth() + "月");
		pCenter.setOpaque(false);
		pNorth.setOpaque(false);
		pSouth.setOpaque(false);
		this.setLayout(new BorderLayout());
		add(pCenter, BorderLayout.CENTER);// 窗口添加scrollPane在中心区域
		add(pNorth, BorderLayout.NORTH);// 窗口添加pNorth 在北面区域
		add(pSouth, BorderLayout.SOUTH);// 窗口添加pSouth 在南区域。
		this.setOpaque(false);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nextMonth) {
			month++;
			if (month > 12)
				month = 1;
			calendar.setMonth(month);
			settingDate();
		}

		else if (e.getSource() == previousMonth) {
			month--;
			if (month < 1)
				month = 12;
			calendar.setMonth(month);			
			settingDate();
		} else if (e.getSource() == button) {
			month++;
			if (month > 12)
				month = 1;
			calendar.setYear(Integer.parseInt(text.getText()));
			
			settingDate();
		}
		showMessage.setText("日历：" + calendar.getYear() + "年"
				+ calendar.getMonth() + "月");
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
}
