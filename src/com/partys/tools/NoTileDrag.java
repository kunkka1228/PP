package com.partys.tools;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class NoTileDrag {
	private static Point origin = new Point(); 
	private static boolean isDraging = false;
	public static void setCanDraged(final Component currentFrame){
		currentFrame.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				isDraging = true;
				origin.x = e.getX();
				origin.y = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				isDraging = false;
			}
			
		});
		currentFrame.addMouseMotionListener(new MouseMotionAdapter(){

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO 自动生成的方法存根
				if(isDraging){
					Point p = currentFrame.getLocation();
					currentFrame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
				}
			}			
		} );
	}
}
