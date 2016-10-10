package com.partys.customer;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
//添加界面
public class AddCustomerDialog extends CommonDialog implements ActionListener{

	/**
	 * 
	 */	
	private static final long serialVersionUID = 5368380479953346575L;
	private JLabel[] jl=new JLabel[11];
	private JTextField[] jtf=new JTextField[7];
	private JButton jb1,jb2;
	private JRadioButton male,female;
	private ButtonGroup bg;
	private JComboBox<String> year,month,day,place,categroy,hour,min,h,m,y,mo,da;
	private JPanel[] jp=new JPanel[11];
	private JLabel year_lable,month_lable,day_lable,tuangou_lable;
	private JLabel add,delete;
	

	
	public AddCustomerDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		// TODO 自动生成的构造函数存根
		iniAddEmpDialog();
	}
	
	public static void main(String[] args) {
		new AddCustomerDialog(null,"",false);
	}

	private void iniAddEmpDialog(){
		
		jl[0]=new JLabel("编号:");		
		jl[1]=new JLabel("姓名:");				
		jl[2]=new JLabel("性别:");
		jl[3]=new JLabel("电话:");
		jl[4]=new JLabel("日期:");
		jl[5]=new JLabel("预定日期:");
		jl[6]=new JLabel("起始时间:");
		jl[7]=new JLabel("预定场地:");
		jl[8]=new JLabel("团购种类:");
		jl[9]=new JLabel("结束时间:");
		jl[10]=new JLabel("团购号:");		
		
		for(int x=0;x<11;x++){
			if(x==8){
				continue;
			}
			jl[x].setBounds(30,10,80,30);
		}
		
		for(int x=0;x<3;x++){
			jtf[x]=new JTextField(20);	
			jtf[x].setBounds(95,10,215, 30);
		}
				
		male=new JRadioButton("男");
		male.setBounds(95, 10, 40, 30);
		female=new JRadioButton("女");
		female.setBounds(145, 10, 40, 30);
		bg=new ButtonGroup();
		bg.add(male);
		bg.add(female);

		
		String[] years=new String[50];
		String[] months=new String[12];
		String[] days=new String[31];
		
		String[] hours=new String[25];
		String[] mins=new String[61];
		for(int x=0;x<50;x++){
			years[x]=(x+2016)+"";
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
		
		for(int x=0;x<25;x++){
			if(x<10){
				hours[x]=BasicUtil.toDouble(x);
			}
			else{
				hours[x]=x+"";
			}			
		}
		
		for(int x=0;x<61;x++){
			if(x<10){
				mins[x]=BasicUtil.toDouble(x);
			}
			else{
				mins[x]=x+"";
			}			
		}
		
		
		year=new JComboBox(years);
		year.setBounds(95, 10, 60, 30);
		month=new JComboBox(months);
		month.setBounds(180, 10, 45, 30);
		day=new JComboBox(days);
		day.setBounds(250, 10, 45, 30);
		
		y=new JComboBox(years);
		y.setBounds(95, 10, 60, 30);
		mo=new JComboBox(months);
		mo.setBounds(180, 10, 45, 30);
		da=new JComboBox(days);
		da.setBounds(250, 10, 45, 30);
		
		hour=new  JComboBox(hours);
		hour.setBounds(95, 10, 45, 30);
		min=new  JComboBox(mins);
		min.setBounds(150, 10, 45, 30);
		
		h=new  JComboBox(hours);
		h.setBounds(95, 10, 45, 30);
		m=new  JComboBox(mins);
		m.setBounds(150, 10, 45, 30);
		
		
		year_lable=new JLabel("年");
		year_lable.setBounds(160, 10, 30, 30);
		month_lable=new JLabel("月");
		month_lable.setBounds(230, 10, 30, 30);
		day_lable=new JLabel("日");
		day_lable.setBounds(300, 10, 30, 30);
		String[] marr={"朝悦1号","朝悦2号","高碑店","双井"};				
		String[] jobs={"无","点评","糯米","美团","拉手","其他"};		
		place=new JComboBox(marr);
		place.setBounds(95, 10, 80, 30);
		
		categroy=new JComboBox(jobs);
		categroy.setBounds(250, 10, 60, 30);
		
		jb1=new JButton("確定");
		jb1.setBounds(95, 10, 70, 30);
		jb1.setFont(MyTools.f4);
		jb1.addActionListener(this);
		
		jb2=new JButton("取消");
		jb2.setBounds(200, 10, 70, 30);
		jb2.setFont(MyTools.f4);
		jb2.addActionListener(this);
		Color color=new Color(198,222,246);
		male.setBackground(color);
		female.setBackground(color);
		for(int x=0;x<11;x++){

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
		jp[5].add(y);
		jp[5].add(year_lable);
		jp[5].add(mo);
		jp[5].add(month_lable);
		jp[5].add(da);
		jp[5].add(day_lable);
		
		jp[6].add(jl[6]);
		jp[6].add(hour);
		jp[6].add(min);
		
		jp[7].add(jl[9]);
		jp[7].add(h);
		jp[7].add(m);
		
		jp[8].add(jl[7]);
		jp[8].add(place);
		jl[8].setBounds(190, 10, 80, 30);
		jp[8].add(jl[8]);
		jp[8].add(categroy);
		
		tuangou_lable=new JLabel();
		tuangou_lable.setBounds(30,10,180,30);
		add=new JLabel(new ImageIcon("image/customer/add.png"));
		add.setBounds(250, 14, 24, 24);
		delete=new JLabel(new ImageIcon("image/customer/trash.png"));
		delete.setBounds(290, 16, 24, 24);
		jp[9].add(jl[10]);
		jp[9].add(tuangou_lable);
		jp[9].add(add);
		jp[9].add(delete);
		
		jp[10].add(jb1);
		
		jp[10].add(jb2);
		
		
		this.setLayout(null);
		for(int x=0;x<11;x++){
		
			this.add(jp[x]);
		}
	
		this.setTitle("添加信息");		
		super.initBasic(355,530);
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
			params[7]=(String)place.getSelectedItem();
			params[8]=(String)categroy.getSelectedItem();
			
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
