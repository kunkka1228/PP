package com.partys.customer;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.partys.commonparent.CommonDialog;

public class TuangouDialog extends CommonDialog implements WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3232261475062580722L;
	private File file;
	private boolean isSave;
	private String filename;
	private TextArea ta;
	public String getFilename() {
		return filename;
	}

	public boolean isSave() {
		return isSave;
	}
	
	public TuangouDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		// TODO 自动生成的构造函数存根
		init(title);
		this.filename=title;
	}
			
	private void init(String title){
		ta = new TextArea();
		this.add(ta,"Center");
		BufferedReader br=null;
		try {
			file=new File(title);
			br=new BufferedReader(new FileReader(file));
			String line=null;
			while((line=br.readLine())!=null){
				ta.append(line);
				ta.append("\r\n");
			}
		} catch (Exception e) {
		}
		finally{
			try {
				if(br!=null){
					br.close();
					br=null;
				}
			
			} catch (IOException e1) {
				br=null;
			}
		}	
		this.addWindowListener(this);		
		super.initBasic(300, 400);		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		BufferedWriter bw=null;
		try {
			bw=new BufferedWriter(new FileWriter(file));
			bw.write(ta.getText());
			bw.flush();
			this.isSave=true;
		} catch (IOException e1) {
			isSave=false;
			e1.printStackTrace();
		}
		finally{
			if(bw!=null){
				try {
					bw.close();
					bw=null;
				} catch (IOException e1) {
					bw=null;
				}
			}
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO 自动生成的方法存根
				
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}
	
}
