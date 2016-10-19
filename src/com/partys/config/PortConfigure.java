package com.partys.config;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.partys.commonparent.CommonJPanel;
import com.partys.model.ConfigureModel;

public class PortConfigure extends CommonJPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1074664372843138468L;
	private JLabel title, content, icon, username_label, port_label, password,
			status, url, database_label;
	private JTextField username_textfiled, port_textfield, url_textfiled,
			database_textfiled;
	private JPasswordField pwd;
	private JPanel jp[] = new JPanel[2];
	private JButton test, apply;
	private ConfigureModel conModel;

	public PortConfigure() {
		init();
	}

	private void init() {
		// TODO 自动生成的方法存根
		for (int x = 0; x < 2; x++) {
			jp[x] = new JPanel(null);
			if (x == 1) {
				continue;
			}
			jp[x].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
					Color.DARK_GRAY));
		}
		conModel = new ConfigureModel();
		title = new JLabel("端口设置");
		title.setBounds(30, 10, 100, 30);
		content = new JLabel("配置Mysql数据库参数");
		initComp(content, 30, 40, 300, 30);
		icon = new JLabel(new ImageIcon("image/configure/database.png"));
		icon.setBounds(360, 20, 50, 50);
		jp[0].add(title);
		jp[0].add(content);
		jp[0].add(icon);

		url_textfiled = new JTextField();
		url_textfiled.setText(conModel.getProperties("url"));
		port_textfield = new JTextField();
		port_textfield.setText(conModel.getProperties("port"));
		url_textfiled.setBounds(130, 17, 180, 24);
		database_label = new JLabel("数据库名称:");
		database_textfiled = new JTextField();
		database_textfiled.setBounds(130, 77, 70, 24);
		database_textfiled.setText(conModel.getProperties("DatabaseName"));
		initComp(database_label, 20, 70, 80, 30);
		port_textfield.setBounds(130, 47, 45, 24);
		port_textfield.setText("3306");
		username_label = new JLabel("用户名:");
		initComp(username_label, 20, 100, 80, 30);
		username_textfiled = new JTextField();
		username_textfiled.setBounds(130, 107, 150, 24);
		username_textfiled.setText(conModel.getProperties("username"));
		password = new JLabel("密码:");

		initComp(password, 20, 130, 80, 30);
		pwd = new JPasswordField();
		pwd.setBounds(130, 137, 150, 24);
		pwd.setText(conModel.getProperties("password"));
		url = new JLabel("url:");
		port_label = new JLabel("端口号:");
		initComp(url, 20, 10, 50, 30);
		initComp(port_label, 20, 40, 50, 24);

		status = new JLabel();
		initComp(status, 170, 230, 80, 65);
		test = new JButton("测试");
		test.addActionListener(this);
		apply = new JButton("应用");
		apply.addActionListener(this);
		initComp(test, 270, 320, 60, 28);
		initComp(apply, 347, 320, 60, 28);
		jp[1].add(url);
		jp[1].add(url_textfiled);
		jp[1].add(port_label);
		jp[1].add(port_textfield);
		jp[1].add(database_label);
		jp[1].add(database_textfiled);
		jp[1].add(username_label);
		jp[1].add(username_textfiled);
		jp[1].add(password);
		jp[1].add(pwd);
		jp[1].add(status);
		jp[1].add(test);
		jp[1].add(apply);

		jp[0].setBounds(0, 0, 430, 80);
		jp[1].setBounds(10, 80, 410, 360);

		for (int x = 0; x < 2; x++) {
			this.add(jp[x]);
		}
		this.setLayout(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource() == test) {
			String url = "jdbc:mysql://" + url_textfiled.getText() + ":"
					+ port_textfield.getText() + "/"
					+ database_textfiled.getText()
					+ "?characterEncoding=gbk&useSSL=true";
			String username = username_textfiled.getText();
			String pass = new String(pwd.getPassword());
			if (conModel.testDataBase(url, username, pass)) {

				new Thread(new Runnable() {

					@Override
					public void run() {
						for (int x = 0; x < 6; x++) {
							status.setIcon(new ImageIcon(
									"image/configure/right.png"));
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						status.setIcon(null);
					}
				}).start();

			} else {
				new Thread(new Runnable() {
					@Override
					public void run() {
						for (int x = 0; x < 6; x++) {
							status.setIcon(new ImageIcon(
									"image/configure/wrong.png"));
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						status.setIcon(null);
					}
				}).start();
			}

		} else if (e.getSource() == apply) {
			String url = url_textfiled.getText();
			String port = port_textfield.getText();
			String databaseName = database_textfiled.getText();
			String username = username_textfiled.getText();
			String pass = new String(pwd.getPassword());
			if (conModel.setDataBaseParameter(url, port, databaseName,
					username, pass)) {

				new Thread(new Runnable() {

					@Override
					public void run() {
						for (int x = 0; x < 3; x++) {
							status.setText("已保存");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						status.setText("");
					}
				}).start();
			}
		}

	}

}
