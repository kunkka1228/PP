package com.partys.customer;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class AddTuangouDialog implements ActionListener{

	private JFrame frame;

	private TextArea ta;


	public static void main(String[] args) {
		new AddTuangouDialog("asd");
	}
	public AddTuangouDialog(String name){
		init(name);
	}
	
	
	private void init(String name){
		frame = new JFrame(name);
		ta = new TextArea();
		frame.add(ta,"Center");
		
		frame.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
			frame.dispose();
			}
		});
		frame.setSize(600,400);
		frame.setLocation(300,100);
		frame.setVisible( true);
		frame.setIconImage(new ImageIcon("image/title.gif").getImage());
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
	}
	
	
	
	
}
