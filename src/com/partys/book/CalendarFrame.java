package com.partys.book;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import com.partys.customer.UpdateCustomerDialog;
import com.partys.listener.MyJButtonMouseMoveListener;
import com.partys.model.BookModel;

public class CalendarFrame extends JPanel implements ActionListener,
		MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -15700584659172661L;
	private JScrollPane labelPanel[] = new JScrollPane[42];
	private JPanel contentPanel[] = new JPanel[42];
	private JTextField year_text, month_text;
	private JButton titleName[] = new JButton[7];
	private JButton button;
	private String name[] = { "日", "一", "二", "三", "四", "五", "六" };
	private JButton nextMonth, previousMonth;
	private int year, month; // 启动程序显示的日期信息
	private CalendarBean calendar;
	private JLabel showMessage, lbl1, year_label, month_label;
	private JPanel pCenter, pNorth, pSouth;
	private BookModel bm;
	private String[] day, partys;
	private Color[] colorArr;
	private MyJButtonMouseMoveListener mmml;
	private ArrayList<String[]> information;

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
		this.add(pNorth, BorderLayout.NORTH);// 窗口添加pNorth 在北面区域
		this.add(pCenter, BorderLayout.CENTER);// 窗口添加scrollPane在中心区域

		this.add(pSouth, BorderLayout.SOUTH);// 窗口添加pSouth 在南区域。
		this.setOpaque(false);

	}

	private void iniListener() {
		mmml = new MyJButtonMouseMoveListener();
	}

	private void initLabel() {
		year_label = new JLabel("年");
		month_label = new JLabel("月");
		lbl1 = new JLabel("请输入年份：");
		showMessage = new JLabel("", JLabel.CENTER);
	}

	private void initDate() {
		year = Calendar.getInstance().get(Calendar.YEAR);
		month = Calendar.getInstance().get(Calendar.MONTH) + 1;

	}

	private void initTextField() {
		year_text = new JTextField(4);
		year_text.addActionListener(this);
		month_text = new JTextField(2);
		month_text.addActionListener(this);
	}

	private void initBtn() {
		nextMonth = new JButton(new ImageIcon("image/book/right.png"));
		setBtn(nextMonth);
		previousMonth = new JButton(new ImageIcon("image/book/left.png"));
		setBtn(previousMonth);
		button = new JButton(new ImageIcon("image/book/ConfirmBtn.png"));
		button.setPreferredSize(new Dimension(69, 28));
		setBtn(button);
		// 注册监听器
		nextMonth.addActionListener(this);
		previousMonth.addActionListener(this);
		button.addActionListener(this);
		button.addMouseListener(mmml);
	}

	private void setBtn(JButton btn) {
		btn.setContentAreaFilled(false);
		btn.setBorder(null);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	private void initPnorth() {
		JPanel p1 = new JPanel(null);
		p1.setOpaque(false);
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 3));
		p2.setOpaque(false);
		pNorth = new JPanel(new GridLayout(1, 2));
		DOMParser parser = new DOMParser("settings.xml");
		partys = parser.getAttributeByTagName("party", "name");
		colorArr = new Color[partys.length];
		for (int i = 0; i < partys.length; i++) {
			JLabel icon = new JLabel(partys[i]);
			icon.setBounds(32 + 120 * i, 5, 50, 20);
			JLabel color = new JLabel();
			color.setOpaque(true);
			color.setBounds(10 + 120 * i, 8, 15, 15);
			int[] arr = parser.getColorByID("party", (i + 1) + "");
			Color newColor = new Color(arr[0], arr[1], arr[2]);
			colorArr[i] = newColor;
			color.setBackground(newColor);
			p1.add(color);
			p1.add(icon);
		}
		p2.add(previousMonth);
		p2.add(showMessage);
		p2.add(nextMonth);
		pNorth.add(p1);
		pNorth.add(p2);
		pNorth.setOpaque(false);
	}

	private void initpSanel() {
		pSouth = new JPanel();
		pSouth.add(lbl1);
		pSouth.add(year_text);
		pSouth.add(year_label);
		pSouth.add(month_text);
		pSouth.add(month_label);
		pSouth.add(button);
		showMessage.setText("日历：" + year + "年" + month + "月");
		pCenter.setOpaque(false);

		pSouth.setOpaque(false);
	}

	private void initPcenter() {
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
			contentPanel[i] = new JPanel(null);
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
				labelPanel[i].setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createEtchedBorder(), day[i] + "日"));
				labelPanel[i].setViewportView(contentPanel[i]);
			}
			pCenter.add(labelPanel[i], c);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nextMonth) {
			month++;
			if (month > 12) {
				month = 1;
				year++;
				calendar.setYear(year);
			}
			calendar.setMonth(month);
			flashData();
		}

		else if (e.getSource() == previousMonth) {
			month--;
			if (month < 1) {
				month = 12;
				year--;
				calendar.setYear(year);
			}
			calendar.setMonth(month);
			flashData();
		
		} else if (e.getSource() == button) {
			button.setFocusable(false);
			String y = year_text.getText();
			if (!(y.trim().equals(""))) {
				year = Integer.parseInt(y);
				String m = month_text.getText();
				if (m.trim().equals("")) {
					month = 1;
				} else {
					month = Integer.parseInt(m);
				}

				calendar.setYear(year);
				calendar.setMonth(month);
				flashData();
			}

		}
		showMessage.setText("日历：" + year + "年" + month + "月");
	}

	public void flashData() {
		day = calendar.getCalendar();
		initData();
		for (int x = 0; x < contentPanel.length; x++) {
			contentPanel[x].removeAll();
		}
		initPcenterData();
		this.validate();
		settingDate();
	}

	private void settingDate() {
		String day[] = calendar.getCalendar();
		for (int i = 0; i < 42; i++) {
			labelPanel[i].setBorder(null);
			if (day[i] != null) {
				labelPanel[i].setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createEtchedBorder(), day[i] + "日"));
			}
		}
	}

	private void initData() {
		bm = new BookModel();
		bm.queryAllCurrentDateInfor(year, month);
	}

	private void initPcenterData() {
		int date = 1;
		information = new ArrayList<String[]>();
		for (int x = 0; x < labelPanel.length; x++) {
			if (day[x] != null) {
				ArrayList<String[]> arr = bm.queryCurrentDateInfor(bm, year,
						month, date);

				int len = arr.size();
				contentPanel[x].setPreferredSize(new Dimension(100, 23 * len));
				labelPanel[x].setPreferredSize(new Dimension(100, 192));
				for (int y = 0; y < len; y++) {
					String[] infor = arr.get(y);
					JLabel label = new JLabel(infor[1] + "~" + infor[2],
							JLabel.CENTER);
					int index = bm.findIndex(infor[3], partys);
					label.setOpaque(true);
					label.setBackground(colorArr[index]);
					label.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0,
							Color.pink));
					label.setBounds(5, 3 + 20 * y + 3 * y, 150, 20);
					label.setCursor(new Cursor(Cursor.HAND_CURSOR));
					label.addMouseListener(this);
					contentPanel[x].add(label);
					String[] info = { x + "", y + "", infor[0] };
					information.add(info);
				}

				date++;
			} else {
				contentPanel[x].setPreferredSize(new Dimension(0, 0));
			}
		}
	}

	public int getScollHeight() {
		return labelPanel[1].getHeight();
	}

	public int getScollWidth() {
		return labelPanel[1].getWidth();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().getClass().getSimpleName().equals("JLabel")) {

			for (int x = 0; x < contentPanel.length; x++) {
				Component[] lables = contentPanel[x].getComponents();
				if (lables.length > 0) {
					for (int y = 0; y < lables.length; y++) {
						if (e.getSource().hashCode() == lables[y].hashCode()) {
							showInformation(x, y);
						}
					}
				}
			}
		}
	}

	private void showInformation(int x, int y) {
		// TODO Auto-generated method stub
		for (int z = 0; z < information.size(); z++) {
			String[] inforItem = information.get(z);
			if ((x + "").equals(inforItem[0]) & (y + "").equals(inforItem[1])) {
				String id=inforItem[2];
				new UpdateCustomerDialog(null, "详细信息",
						true, id,false);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


}
