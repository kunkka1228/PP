package com.partys.tools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
public class ImagePanel extends JPanel{
/**
	 * 
	 */
	private static final long serialVersionUID = 6250884716239460746L;
Image img;
public ImagePanel(Image img)
{
	this.img=img;
	int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	this.setSize(width,height);
}
public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	g.drawImage(img, 0,0, this.getWidth(), this.getHeight(),this);
}


}
