package com.partys.commonparent;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.partys.tools.MyTools;

public class CommonJPanel extends JPanel {
	public void initComp(JComponent com,int x,int y,int witdh,int height){
		com.setBounds(x, y, witdh, height);
		com.setFont(MyTools.f2);	
	}
}
