package com.partys.login;



import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

import org.eclipse.jgit.api.errors.CanceledException;
import org.eclipse.jgit.api.errors.DetachedHeadException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidConfigurationException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.RefNotAdvertisedException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;

import com.partys.tools.BasicUtil;
import com.partys.tools.UpdateFromGitHub;

public class loading extends JWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JProgressBar jpb;
	private JLabel jl1;

	public static void main(String[] args) {
		new loading();
	}

	public loading() {

		jl1 = new JLabel(new ImageIcon("image/index/index.png"));

		jpb = new JProgressBar();

		jpb.setStringPainted(true);
		jpb.setIndeterminate(false);
		jpb.setBorderPainted(false);
		jpb.setBackground(Color.pink);
		jpb.setValue(0);
		jpb.setString("检查更新中");
		this.add(jl1, BorderLayout.NORTH);
		this.add(jpb, BorderLayout.SOUTH);
		this.setSize(450, 357);
		this.setLocation(BasicUtil.getSreenWidthAndHeight()[0]/2-200,BasicUtil.getSreenWidthAndHeight()[1]/2-150);
		this.setVisible(true);
		UpdateFromGitHub ufg = new UpdateFromGitHub();

		try {			
			ufg.update(".", "https://github.com/kunkka1228/HongPaManagement.git");
			try {

				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			jpb.setValue(100);
			jpb.setBackground(Color.green);
			jpb.setString("更新完成 ");
		} 
		 catch (WrongRepositoryStateException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (DetachedHeadException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvalidRemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (CanceledException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (RefNotAdvertisedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NoHeadException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO 自动生成的 catch 块
			jpb.setString("更新失败 ,无法连接服务器");
			e.printStackTrace();
			return;
			
		} catch (GitAPIException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		finally{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			new UserLogin();
			this.dispose();
		}	
	}
}
