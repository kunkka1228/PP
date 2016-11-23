package com.partys.chart
;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import DomXML.DOMParser;

import com.partys.model.ChartModel;
import com.partys.tools.BasicUtil;

public class Chart extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private ChartPanel chartPanel;
	private JPanel pNorth;
	private String[] partys;
	private JPanel pSouth;
	private JButton lineBtn,BarBtn;
	
	public Chart(){
		iniBtn();
		iniPnorth();
		iniPsouth();
		iniPcenter();
		ini();
	}
	
	private void iniBtn(){
		lineBtn=new JButton(new ImageIcon("image/chart/line.png"));
		int width=BasicUtil.getSreenWidthAndHeight()[0];
		lineBtn.setBounds(width/2-45, 5, 20, 21);
		lineBtn.setFocusable(false);
		lineBtn.addActionListener(this);
		BarBtn=new JButton(new ImageIcon("image/chart/zhu.png"));
		BarBtn.setBounds(width/2-70, 5, 20, 21);
		BarBtn.setFocusable(false);
		BarBtn.addActionListener(this);
		setBtn(BarBtn);
		setBtn(lineBtn);
		BarBtn.setBorder(BorderFactory.createLoweredBevelBorder());
	}
	private void iniPnorth(){
		JPanel p1 = new JPanel(null);

		p1.setOpaque(false);
		JPanel p2 = new JPanel(null);
		p2.setOpaque(false);
		pNorth = new JPanel(new GridLayout(1, 2));
		pNorth.setPreferredSize(new Dimension(0,30));
		DOMParser parser = new DOMParser("settings.xml");
		partys = parser.getAttributeByTagName("party", "name");
	
		for (int i = 0; i < partys.length; i++) {
			JLabel icon = new JLabel(partys[i]);
			icon.setBounds(32 + 120 * i, 5, 50, 20);
			JLabel color = new JLabel();
			color.setOpaque(true);
			color.setBounds(10 + 120 * i, 8, 15, 15);
			int[] arr = parser.getColorByID("party", (i + 1) + "");
			Color newColor = new Color(arr[0], arr[1], arr[2]);
			color.setBackground(newColor);
			p1.add(color);
			p1.add(icon);
		}
		
		p2.add(BarBtn);
		p2.add(lineBtn);		
		pNorth.add(p1);
		pNorth.add(p2);
		pNorth.setOpaque(false);

	}
	
	private void iniPsouth(){
		pSouth = new JPanel();
		pSouth.add(new JLabel("asd"));

		pSouth.setOpaque(false);
	}

	private void iniPcenter() {
		// TODO 自动生成的方法存根		
		ChartModel chart=new ChartModel();	
		chartPanel=new ChartPanel(chart.getJFreeChart());
		chartPanel.setOpaque(false);

	}
	
	private void ini(){
		this.setLayout(new BorderLayout());	
		
		this.add(pNorth,BorderLayout.NORTH);
		this.add(chartPanel,BorderLayout.CENTER);
		this.add(pSouth,BorderLayout.SOUTH);
		this.setOpaque(false);
	}
	
	private void setBtn(JButton btn) {
		btn.setContentAreaFilled(false);
		btn.setBorder(null);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==lineBtn){
			lineBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			BarBtn.setBorder(null);
		}
		
		else if(e.getSource()==BarBtn){
			BarBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			lineBtn.setBorder(null);
		}
		
	}

}
