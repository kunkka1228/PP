package com.partys.login;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.partys.commonparent.CommonDialog;
import com.partys.model.UserModel;

public class ModifyPassword extends CommonDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[] jl=new JLabel[4];
	private JTextField jtf;
	private JPasswordField[] jpf=new JPasswordField[3];
	private JButton confirmBtn,cancelBtn;		
	private JPanel p1,p2,p3;
	private String uid;
	public ModifyPassword(Frame owner, String title, boolean modal,String uid) {
		super(owner, title, modal);
		this.uid=uid;
		ini();
	}

	
	private void ini(){
		p1=new JPanel(new GridLayout(4,1));
		p2=new JPanel(new GridLayout(4,1));
		p3=new JPanel();
		confirmBtn=new JButton("ȷ��");
		confirmBtn.addActionListener(this);
		cancelBtn=new JButton("ȡ��");
		cancelBtn.addActionListener(this);
		jl[0]=new JLabel("       ��    ��   ");
		jl[1]=new JLabel("      ������   ");
		jl[2]=new JLabel("      ������    ");
		jl[3]=new JLabel("      ȷ������     ");
		jtf=new JTextField(8);
		jtf.setText(uid);
		jtf.setEnabled(false);
		for(int x=0;x<3;x++){
			jpf[x]=new JPasswordField();
		}
		
		for(int x=0;x<4;x++){
			if(x==0){
				p2.add(jtf);
			}

			p1.add(jl[x]);
			if(x<3){
				p2.add(jpf[x]);
			}
			
		}
		p3.add(confirmBtn);
		p3.add(cancelBtn);
		this.add(p1,"West");
		this.add(p2,"Center");
		this.add(p3,"South");
		this.setResizable(false);
		super.initBasic(230, 200);		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==confirmBtn){
			String oldPassword=new String(jpf[0].getPassword());
			UserModel um=new UserModel();
			if(um.checkUser(uid, oldPassword)){
				String newPass=new String(jpf[1].getPassword());
				String confirmPass=new String(jpf[2].getPassword());
				if(newPass.equals(confirmPass)){
					if(newPass.equals("")){
						JOptionPane.showMessageDialog(null, "��������벻��Ϊ��");
					}
					else{
						if(newPass.length()<4){
							JOptionPane.showMessageDialog(null, "��������볤�Ȳ���С��4");
						}
						else{
							String sql ="update login set password=? where id=?";
							String[] params={newPass,uid};
							if(um.UpdateModel(sql, params)){
								JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
								this.dispose();
							}
						}							
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "������������벻һ��");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "ԭʼ���벻��ȷ");
			}
		}
		else{
			this.dispose();
		}
	}
}
