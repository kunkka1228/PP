package com.partys.commonparent;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.partys.login.EmpInfo;
import com.partys.model.EmpModel;
import com.partys.tools.BasicUtil;

public class CommonDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2193474433653879621L;

	public CommonDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public CommonDialog(EmpInfo empInfo,String title,boolean model,EmpModel em,int rowNum){
		super();
	}
	

	
	public void initBasic(int width, int height){
		
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Image titleIcon = null;
		try {
			titleIcon = ImageIO.read(new File("image/title.gif"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		this.setIconImage(titleIcon);
		setCenter(width,height);
		this.setSize(width, height);
		this.setVisible(true);
	}
	
	private void setCenter(int width, int height){
		this.setLocation((BasicUtil.getSreenWidthAndHeight()[0]-width)/2, (BasicUtil.getSreenWidthAndHeight()[1]-height)/2);
	}

	
	
}