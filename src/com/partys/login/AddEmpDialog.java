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
//��ӽ���
public class AddEmpDialog extends CommonDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5368380479953346575L;
	private JPanel p1,p2,p3;
	private JLabel[] jl=new JLabel[11];
	private JTextField[] jtf=new JTextField[11];
	private JButton jb1,jb2;

	
	public AddEmpDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		// TODO �Զ����ɵĹ��캯�����
		iniAddEmpDialog();
	}

	private void iniAddEmpDialog(){
		p1=new JPanel(new GridLayout(12,1));
		jl[0]=new JLabel("    ���:      ");
		jl[1]=new JLabel("    ����:      ");
		jl[2]=new JLabel("    �Ա�:      ");
		jl[3]=new JLabel("    ��ַ:      ");
		jl[4]=new JLabel("    ����:      ");
		jl[5]=new JLabel("    ���֤:     ");
		jl[6]=new JLabel("    ѧ��:      ");
		jl[7]=new JLabel("    ְλ:       ");
		jl[8]=new JLabel("    ����:       ");
		jl[9]=new JLabel("    �绰:       ");
		jl[10]=new JLabel("    ���� :      ");
				
		for(int x=0;x<11;x++){
			p1.add(jl[x]);
		}
		p2=new JPanel(new GridLayout(12,1));
		for(int x=0;x<11;x++){
			jtf[x]=new JTextField();
		}
		
		for(int x=0;x<11;x++){
			p2.add(jtf[x]);
		}
		p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		jb1=new JButton("�_��");
		jb1.setFont(MyTools.f4);
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.setFont(MyTools.f4);
		jb2.addActionListener(this);
		p3.add(jb1);
		p3.add(jb2);
		this.setLayout(new BorderLayout());
		this.add(p1,"West");
		this.add(p2,"Center");
		this.add(p3,"South");
		this.setTitle("���Ա��");
		super.initBasic(400,430);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		if(arg0.getSource()==jb1)
		{
			String sql="insert into renshi values(?,?,?,?,?,?,?,?,?,?,?)";
			String []params=new String[11];
			for(int x=0;x<jtf.length;x++){
				params[x]=jtf[x].getText().trim();
			}
			EmpModel em=new EmpModel();
			if(!em.UpdateModel(sql, params))
			{
				JOptionPane.showMessageDialog(null, "���ʧ�ܣ���������ȷ�������ͣ�");
			}
			else{
				String sqladd="insert into login values (?,?)";
				String id=params[0];
				String password="1234";		
				String []paramadd={id,password};
				em.UpdateModel(sqladd, paramadd);
				JOptionPane.showMessageDialog(null, "��ϲ����ӳɹ���");
				this.dispose();
			}		
		}
		else if(arg0.getSource()==jb2)
		{
			super.setFlag(true);
			this.dispose();
		}
	}	
}
