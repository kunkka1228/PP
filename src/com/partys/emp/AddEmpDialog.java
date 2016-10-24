package com.partys.emp;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.partys.commonparent.CommonDialog;
import com.partys.model.EmpModel;
import com.partys.tools.BasicUtil;
import com.partys.tools.MyTools;
import com.partys.tools.NoTileDrag;
//添加界面
public class AddEmpDialog extends CommonDialog implements ActionListener{

	/**
	 * 
	 */	
	private static final long serialVersionUID = 5368380479953346575L;
	private JLabel[] jl=new JLabel[11];
	private JTextField[] jtf=new JTextField[7];
	private JButton jb1,jb2;
	private JRadioButton male,female;
	private ButtonGroup bg;
	private JComboBox<String> year,month,day,joblevel,marriage;
	private JPanel[] jp=new JPanel[12];
	private JLabel year_lable,month_lable,day_lable;
	
	public static void main(String[] args) {
		new AddEmpDialog(null,"",true);
	}
	
	public AddEmpDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		// TODO 自动生成的构造函数存根
		initLabel();
		initJtextFiled();
		initBg();
		initBtn();
		initCombobox();
		initPanel();
		iniAddEmpDialog();		
	}
	
	private void initLabel(){
		jl[0]=new JLabel("编号:");		
		jl[1]=new JLabel("姓名:");				
		jl[2]=new JLabel("性别:");
		jl[3]=new JLabel("地址:");
		jl[4]=new JLabel("生日:");
		jl[5]=new JLabel("身份证:");
		jl[6]=new JLabel("学历:");
		jl[7]=new JLabel("职位:");
		jl[8]=new JLabel("婚姻:");
		jl[9]=new JLabel("电话:");
		jl[10]=new JLabel("邮箱 :");		
		
		for(int x=0;x<11;x++){
			if(x==8){
				continue;
			}
			jl[x].setBounds(30,10,80,30);
		}
		year_lable=new JLabel("年");
		month_lable=new JLabel("月");
		day_lable=new JLabel("日");
		year_lable.setBounds(160, 10, 30, 30);		
		month_lable.setBounds(230, 10, 30, 30);		
		day_lable.setBounds(300, 10, 30, 30);
	}
	
	private void initJtextFiled(){
		for(int x=0;x<7;x++){
			jtf[x]=new JTextField(20);	
			jtf[x].setBounds(95,10,215, 30);
		}
		
		jtf[0].setEditable(false);
		jtf[0].setText(BasicUtil.getAutoNumber(4,"renshi"));		
	}
	
	private void initBg(){
		male=new JRadioButton("男");
		male.setBounds(95, 10, 40, 30);
		female=new JRadioButton("女");
		female.setBounds(145, 10, 40, 30);
		bg=new ButtonGroup();
		bg.add(male);
		bg.add(female);
		Color color=new Color(198,222,246);
		male.setBackground(color);
		female.setBackground(color);
	}
	
	private void initCombobox(){
		String[] years=new String[50];
		String[] months=new String[12];
		String[] days=new String[31];
		for(int x=0;x<50;x++){
			years[x]=(x+1970)+"";
		}
		
		for(int x=1;x<13;x++){
			if(x<10){
				months[x-1]=BasicUtil.toDouble(x);
			}
			else{
				months[x-1]=x+"";
			}			
		}
		
		for(int x=1;x<32;x++){
			if(x<10){
				days[x-1]=BasicUtil.toDouble(x);
			}
			else{
				days[x-1]=x+"";
			}			
		}
		
		year=new JComboBox(years);
		year.setBounds(95, 10, 60, 30);
		month=new JComboBox(months);
		month.setBounds(180, 10, 45, 30);
		day=new JComboBox(days);
		day.setBounds(250, 10, 45, 30);
						
		String[] jobs={"经理","员工"};		
		joblevel=new JComboBox(jobs);
		joblevel.setBounds(95, 10, 60, 30);
		String[] marr={"已婚","未婚","离异","丧偶"};
		marriage=new JComboBox(marr);
		marriage.setBounds(250, 10, 60, 30);
	}
	
	private void initBtn(){
		jb1=new JButton("確定");
		jb1.setBounds(95, 10, 70, 30);
		jb1.setFont(MyTools.f4);
		jb1.addActionListener(this);
		
		jb2=new JButton("取消");
		jb2.setBounds(200, 10, 70, 30);
		jb2.setFont(MyTools.f4);
		jb2.addActionListener(this);
	}
	
	private void initPanel(){
		Color color=new Color(198,222,246);
			
		for(int x=0;x<12;x++){
			jp[x]=new JPanel();
			jp[x].setLayout(null);
			jp[x].setForeground(Color.cyan);
			jp[x].setBounds(0, x*45, 350, 50);
			jp[x].setBackground(color);
			jp[x].setBorder(new EtchedBorder());
		}
		
		jp[0].add(jl[0]);
		jp[0].add(jtf[0]);
		
		
		jp[1].add(jl[1]);
		jp[1].add(jtf[1]);
		
		jp[2].add(jl[2]);
		jp[2].add(male);
		jp[2].add(female);
		
		jp[3].add(jl[3]);
		jp[3].add(jtf[2]);
		
		jp[4].add(jl[4]);
		jp[4].add(year);
		jp[4].add(year_lable);
		jp[4].add(month);
		jp[4].add(month_lable);
		jp[4].add(day);
		jp[4].add(day_lable);
		
		jp[5].add(jl[5]);
		jp[5].add(jtf[3]);
		
		jp[6].add(jl[6]);
		jp[6].add(jtf[4]);
		
		jp[7].add(jl[7]);
		jp[7].add(joblevel);
		jl[8].setBounds(190, 10, 80, 30);
		jp[7].add(jl[8]);
		jp[7].add(marriage);
		
		jp[8].add(jl[9]);
		jp[8].add(jtf[5]);
		
		jp[9].add(jl[10]);
		jp[9].add(jtf[6]);
		jp[10].add(jb1);
		jp[10].add(jb2);
	}
	
	private void iniAddEmpDialog(){		
		this.setLayout(null);
		for(int x=0;x<12;x++){
			this.add(jp[x]);
		}
		this.setUndecorated(true);
		NoTileDrag.setCanDraged(this);
		super.initBasic(350,500);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource()==jb1)
		{
			String[] params=new String[11];
			
			params[0]=jtf[0].getText();			
			params[1]=jtf[1].getText();
			if(male.isSelected()){
				params[2]=male.getText();
			}		
			else{
				params[2]=female.getText();
			}
			params[3]=jtf[2].getText();
			params[4]=(String)year.getSelectedItem()+"-"+(String)month.getSelectedItem()+"-"+(String)day.getSelectedItem();
			
		
			params[5]=jtf[3].getText();
			params[6]=jtf[4].getText();
			params[7]=(String)joblevel.getSelectedItem();
			params[8]=(String)marriage.getSelectedItem();
			
			params[9]=jtf[5].getText();
			params[10]=jtf[6].getText();
			EmpModel em=new EmpModel();
			if(!em.addItem(params))
			{
				JOptionPane.showMessageDialog(null, "添加失败，请输入正确数据类型!");				
			}
			else{
				JOptionPane.showMessageDialog(null, "恭喜！添加成功！");
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
