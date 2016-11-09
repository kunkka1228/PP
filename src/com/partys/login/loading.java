
package com.partys.login;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

import com.partys.tools.BasicUtil;
import com.tools.UpdateFromGitHub;
public class loading extends JWindow implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JProgressBar jpb;
	private JLabel jl1;
	public static void main(String []args){
		loading index=new loading();

		Thread t=new Thread(index);

		t.start();
	}

	public loading()
	{	

		jl1=new JLabel(new ImageIcon("image/index/index.png"));
		

		jpb=new JProgressBar();

		jpb.setStringPainted(true);
		jpb.setIndeterminate(false);
		jpb.setBorderPainted(false);
		jpb.setBackground(Color.pink);
		try {
			new UpdateFromGitHub().update(".", "https://github.com/kunkka1228/Management.git");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		this.add(jl1,BorderLayout.NORTH);
		this.add(jpb,BorderLayout.SOUTH);
		this.setSize(450,350);
		this.setLocation(BasicUtil.getSreenWidthAndHeight()[0]/2-200,BasicUtil.getSreenWidthAndHeight()[1]/2-150);

		this.setVisible(true);
	}

	public void run() {

		int []progressValue={0,1,5,8,14,17,26,35,38,43,49,56,65,71,75,78,86,94,98,99,100};
		for(int i=0;i<progressValue.length;i++)
		{
			try {

				Thread.sleep(100);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			jpb.setValue(progressValue[i]);
			if(i==progressValue.length-1){
				new UserLogin();

					this.dispose();
			}
		}
	}
}
