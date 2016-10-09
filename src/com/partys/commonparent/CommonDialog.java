package com.partys.commonparent;

import java.awt.Frame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;

import com.partys.tools.BasicUtil;

public class CommonDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2193474433653879621L;
	private boolean flag = false;

	public void setFlag(boolean s) {
		flag = s;
	}

	public boolean getFlag() {
		return flag;
	}

	public CommonDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		// TODO 自动生成的构造函数存根
	}


	public void initBasic(int width, int height) {

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Image titleIcon = null;
		try {
			titleIcon = ImageIO.read(new File("image/title.gif"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		this.setIconImage(titleIcon);
		setCenter(width, height);
		this.setResizable(false);
		this.setSize(width, height);
		this.setVisible(true);
		
	}

	private void setCenter(int width, int height) {
		this.setLocation((BasicUtil.getSreenWidthAndHeight()[0] - width) / 2,
				(BasicUtil.getSreenWidthAndHeight()[1] - height) / 2);
	}

}
