package com.partys.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class MyJButtonMouseMoveListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
			JButton btn=(JButton)e.getComponent();
			btn.setBorder(BorderFactory.createRaisedBevelBorder());
		}

	

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		JButton btn=(JButton)e.getComponent();
		btn.setBorder(null);

	}

	

}
