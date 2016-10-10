package com.partys.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class MyJButtonMouseMoveListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		JButton btn=(JButton)e.getComponent();
		btn.setBorder(BorderFactory.createLoweredBevelBorder());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		JButton btn=(JButton)e.getComponent();
		btn.setBorder(BorderFactory.createRaisedBevelBorder());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
			JButton btn=(JButton)e.getComponent();
			btn.setBorder(BorderFactory.createRaisedBevelBorder());
		}

	

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		JButton btn=(JButton)e.getComponent();
		btn.setBorder(null);

	}

	

}
