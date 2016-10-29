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
	private String name[] = { "��", "һ", "��", "��", "��", "��", "��" };
	private JButton nextMonth, previousMonth;
	private int year = 2016, month = 10; // ����������ʾ��������Ϣ
	private CalendarBean calendar;
	private JLabel showMessage = new JLabel("", JLabel.CENTER);
	private JLabel lbl1 = new JLabel("��������ݣ�");
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
						+ "��"));
			}
			pCenter.add(labelPanel[i], c);

		}

		text.addActionListener(this);

		nextMonth = new JButton("����");
		previousMonth = new JButton("����");
		button = new JButton("ȷ��");

		// ע�������
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
		showMessage.setText("������" + calendar.getYear() + "��"
				+ calendar.getMonth() + "��");
		pCenter.setOpaque(false);
		pNorth.setOpaque(false);
		pSouth.setOpaque(false);
		this.setLayout(new BorderLayout());
		add(pCenter, BorderLayout.CENTER);// �������scrollPane����������
		add(pNorth, BorderLayout.NORTH);// �������pNorth �ڱ�������
		add(pSouth, BorderLayout.SOUTH);// �������pSouth ��������
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
		showMessage.setText("������" + calendar.getYear() + "��"
				+ calendar.getMonth() + "��");
	}
	
	
	private void settingDate(){
		String day[] = calendar.getCalendar();
		for (int i = 0; i < 42; i++) {
			labelPanel[i].setBorder(null);
			if (day[i] != null) {
				labelPanel[i].setBorder(BorderFactory
						.createTitledBorder(day[i] + "��"));
			}
		}
	}
}
