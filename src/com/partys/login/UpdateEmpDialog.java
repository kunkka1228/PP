package com.partys.login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.partys.commonparent.CommonDialog;
import com.partys.model.EmpModel;
import com.partys.tools.MyTools;

public class UpdateEmpDialog extends CommonDialog implements ActionListener {
	
	
	private static final long serialVersionUID = 5368380479953346575L;
	private JPanel p1,p2,p3;
	private JLabel[] jl=new JLabel[11];
	private JTextField[] jtf=new JTextField[11];
	private JButton jb1,jb2;
	private int rowNum;
	private EmpModel em;
	public UpdateEmpDialog(Frame owner, String title, boolean modal,int rowNum, EmpModel em) {
		super(owner, title, modal);
		this.rowNum=rowNum;
		this.em=em;
		iniAddEmpDialog();
	}
	
	private void iniAddEmpDialog(){		
		p1=new JPanel(new GridLayout(12,1));
		jl[0]=new JLabel("    编号:      ");
		jl[1]=new JLabel("    姓名:      ");
		jl[2]=new JLabel("    性别:      ");
		jl[3]=new JLabel("    地址:      ");
		jl[4]=new JLabel("    生日:      ");
		jl[5]=new JLabel("    身份证:     ");
		jl[6]=new JLabel("    学历:      ");
		jl[7]=new JLabel("    职位:       ");
		jl[8]=new JLabel("    婚姻:       ");
		jl[9]=new JLabel("    电话:       ");
		jl[10]=new JLabel("    邮箱 :      ");
				
		for(int x=0;x<11;x++){
			p1.add(jl[x]);
		}
		p2=new JPanel(new GridLayout(12,1));
		for(int x=0;x<11;x++){
			jtf[x]=new JTextField();			
		}
		jtf[0].setEditable(false);		
		for(int x=0;x<11;x++){
			p2.add(jtf[x]);
			jtf[x].setText((String)em.getValueAt(rowNum, x));
		}		
		p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		jb1=new JButton("確定");
		jb1.setFont(MyTools.f4);
		jb1.addActionListener(this);
		jb2=new JButton("取消");
		jb2.setFont(MyTools.f4);
		jb2.addActionListener(this);
		p3.add(jb1);
		p3.add(jb2);
		this.setLayout(new BorderLayout());
		this.add(p1,"West");
		this.add(p2,"Center");
		this.add(p3,"South");
		this.setTitle("添加员工");
		super.initBasic(400,450);
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1)
		{
			String[] params=new String[11];
			String sql="update renshi set name=?,sex=? ,address=? , birthday=? , IDCard=?,edu=?,joblevel=?,marriage=?,tel=?,mail=? where id=?";
			for(int x=0;x<11;x++){
				if(x==10){
					params[10]=jtf[0].getText().trim();
					continue;
				}
				params[x]=jtf[x+1].getText().trim();
			}						
						
			EmpModel em=new EmpModel();
			if(!em.UpdateModel(sql, params))
			{
				JOptionPane.showMessageDialog(null, "修改失败，请输入正确数据类型!");				
			}
			else{
				JOptionPane.showMessageDialog(null, "恭喜！修改成功！");
				this.dispose();
			}
				
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
	}			
}
