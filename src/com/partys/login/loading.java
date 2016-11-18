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
import org.eclipse.jgit.errors.CheckoutConflictException;

import com.partys.tools.BasicUtil;
import com.partys.tools.UpdateFromGitHub;

public class loading {
	private JProgressBar jpb;
	private JLabel jl1;
	private JWindow load;

	public static void main(String[] args) {
		new loading();
	}

	public loading() {
		load = new JWindow();
		jl1 = new JLabel(new ImageIcon("image/index/partys.png"));

		jpb = new JProgressBar();

		jpb.setStringPainted(true);
		jpb.setIndeterminate(false);
		jpb.setBorderPainted(false);
		jpb.setBackground(Color.pink);
		jpb.setValue(0);
		jpb.setString("检查更新中");
		load.add(jl1, BorderLayout.NORTH);
		load.add(jpb, BorderLayout.SOUTH);
		int width = 360;
		int height = 360;
		load.setSize(width, height);
		load.setLocation((BasicUtil.getSreenWidthAndHeight()[0] - width) / 2,
				(BasicUtil.getSreenWidthAndHeight()[1] - height) / 2);
		load.setVisible(true);

		final Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				String str = "检查更新中";
				String doc = ".";
				String blank = " ";
				int num = 0;
				while (true) {
					num++;
					str = blank + str + doc;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if (num == 4) {
						num = 0;
						str = str.trim().substring(0, 5);
					}
					jpb.setString(str);
				}
			}
		});
		t1.start();

		final Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				UpdateFromGitHub ufg = null;
				if (ufg == null) {
					ufg = new UpdateFromGitHub();

					try {
						ufg.update(".",
								"https://github.com/kunkka1228/HongPaManagement.git");
						try {

							Thread.sleep(1000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						jpb.setValue(100);
						jpb.setBackground(Color.green);

						jpb.setString("更新完成 ");
						t1.stop();

					} catch (WrongRepositoryStateException e) {
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
						t1.stop();
						jpb.setString("更新失败 ,无法连接服务器");
						e.printStackTrace();

					} catch (CheckoutConflictException e1) {
						// TODO 自动生成的 catch 块
						t1.stop();
						jpb.setString("更新失败 ,版本错误");
						e1.printStackTrace();
					} catch (GitAPIException e) {
						// TODO 自动生成的 catch 块
						t1.stop();
						jpb.setString("更新失败 ,程序异常");
						e.printStackTrace();

					} finally {
						new UserLogin();
						load.dispose();
					}
				}
			}
		});
		t2.start();
	}
}
