/**
 * 这是人事管理的操作
 */
package com.partys.model;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.partys.db.SqlHelper;
public class EmpModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7055176924075699409L;
	public Vector<String> colums;
	public Vector<Vector<String>>rows;
	
	public boolean UpdateModel(String sql,String []params)
	{
		SqlHelper hp=new SqlHelper();
		return hp.updateExecete(sql,params);
	}
	public int getNum(String sql)
	{
		SqlHelper hp=new SqlHelper();
		int sum=hp.queryExecute(sql);
		return sum;
	}
	public void query(String sql,String[]params)
	{
		//初始化
		
		colums=new Vector<String>();
		rows=new Vector<Vector<String>>();
		SqlHelper hp=new SqlHelper();
		ResultSet rs=hp.queryExecute(sql, params);
		try {
			ResultSetMetaData rsmd=rs.getMetaData();
			for(int i=0;i<rsmd.getColumnCount();i++)
			{
				this.colums.add(rsmd.getColumnName(i+1));	
			}	
			while(rs.next())
			{
				Vector<String> temp=new Vector<String>();
				for(int i=0;i<rsmd.getColumnCount();i++)
				{
					temp.add(rs.getString(i+1));
				}
				rows.add(temp);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		finally
		{
			hp.close();
		}
	}
	


	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return this.colums.size();
	}

	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.rows.size();
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO 自动生成的方法存根
		String name="";
		if(this.colums.get(arg0).toString().equals("id")){
			name="编号";
		}
		else if(this.colums.get(arg0).toString().equals("name")){
			name="姓名";
		}
		else if(this.colums.get(arg0).toString().equals("sex")){
			name="性别";
		}
		else if(this.colums.get(arg0).toString().equals("address")){
			name="地址";
		}
		else if(this.colums.get(arg0).toString().equals("birthday")){
			name="生日";
		}
		else if(this.colums.get(arg0).toString().equals("IDCard")){
			name="身份证号";
		}
		else if(this.colums.get(arg0).toString().equals("edu")){
			name="学历";
		}
		else if(this.colums.get(arg0).toString().equals("joblevel")){
			name="职位";
		}
		else if(this.colums.get(arg0).toString().equals("marriage")){
			name="婚姻";
		}
		else if(this.colums.get(arg0).toString().equals("tel")){
			name="联系方式";
		}
		else if(this.colums.get(arg0).toString().equals("mail")){
			name="邮箱";
		}

		return name;
	}
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO 自动生成的方法存根
		Object obj=rows.get(arg0).get(arg1);
		return obj;
	}

}
