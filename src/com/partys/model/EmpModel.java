/**
 * �������¹���Ĳ���
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
		//��ʼ��
		
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		finally
		{
			hp.close();
		}
	}
	


	@Override
	public int getColumnCount() {
		// TODO �Զ����ɵķ������
		return this.colums.size();
	}

	@Override
	public int getRowCount() {
		// TODO �Զ����ɵķ������
		return this.rows.size();
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO �Զ����ɵķ������
		String name="";
		if(this.colums.get(arg0).toString().equals("id")){
			name="���";
		}
		else if(this.colums.get(arg0).toString().equals("name")){
			name="����";
		}
		else if(this.colums.get(arg0).toString().equals("sex")){
			name="�Ա�";
		}
		else if(this.colums.get(arg0).toString().equals("address")){
			name="��ַ";
		}
		else if(this.colums.get(arg0).toString().equals("birthday")){
			name="����";
		}
		else if(this.colums.get(arg0).toString().equals("IDCard")){
			name="���֤��";
		}
		else if(this.colums.get(arg0).toString().equals("edu")){
			name="ѧ��";
		}
		else if(this.colums.get(arg0).toString().equals("joblevel")){
			name="ְλ";
		}
		else if(this.colums.get(arg0).toString().equals("marriage")){
			name="����";
		}
		else if(this.colums.get(arg0).toString().equals("tel")){
			name="��ϵ��ʽ";
		}
		else if(this.colums.get(arg0).toString().equals("mail")){
			name="����";
		}

		return name;
	}
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO �Զ����ɵķ������
		Object obj=rows.get(arg0).get(arg1);
		return obj;
	}

}
