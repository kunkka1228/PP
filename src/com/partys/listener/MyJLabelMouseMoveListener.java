package com.partys.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MyJLabelMouseMoveListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		JLabel label=(JLabel)e.getComponent();
		label.setBorder(BorderFactory.createLoweredBevelBorder());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		JLabel label=(JLabel)e.getComponent();
		label.setBorder(BorderFactory.createRaisedBevelBorder());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		JLabel label=(JLabel)e.getComponent();
		label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		}

	

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		JLabel label=(JLabel)e.getComponent();
		label.setBorder(null);

	}

}
