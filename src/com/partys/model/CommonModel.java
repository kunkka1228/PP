package com.partys.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.partys.db.SqlHelper;

public class CommonModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<String> colums;
	private Vector<Vector<String>> rows;

	public boolean UpdateModel(String sql, String[] params) {
		SqlHelper hp = new SqlHelper();
		return hp.updateExecete(sql, params);
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
	public Object getValueAt(int arg0, int arg1) {
		// TODO 自动生成的方法存根
		Object obj = rows.get(arg0).get(arg1);
		return obj;
	}

	public String query(String sql, String[] params) {
		// 初始化

		colums = new Vector<String>();
		rows = new Vector<Vector<String>>();
		SqlHelper hp = new SqlHelper();
		ResultSet rs = hp.queryExecute(sql, params);
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				this.colums.add(rsmd.getColumnName(i + 1));
			}
			while (rs.next()) {
				Vector<String> temp = new Vector<String>();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					temp.add(rs.getString(i + 1));
				}
				rows.add(temp);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			hp.close();
		}
		String maxId=(rows.get(rows.size()-1)).get(0);
		return maxId;
	}
	
	
	public int query(String sql, String[] params, String place, int index, String number,boolean flag) {
		int[] arr=new int[2];
		// 初始化
		Vector<Vector<String>> t = new Vector<Vector<String>>();
		colums = new Vector<String>();
		rows = new Vector<Vector<String>>();
		SqlHelper hp = new SqlHelper();
		ResultSet rs = hp.queryExecute(sql, params);
		int sum=0;
		int all=0;
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnNum=rsmd.getColumnCount();
			for (int i = 0; i <columnNum ; i++) {
				this.colums.add(rsmd.getColumnName(i + 1));
			}
				
			if(flag){
				rs.absolute(index);	
				while (rs.next()&&sum<Integer.parseInt(number)) {
					all++;
					if(place.equals("所有")){
						Vector<String> temp = new Vector<String>();
						for (int i = 0; i < columnNum; i++) {
							temp.add(rs.getString(i + 1));
						}
						rows.add(temp);
						sum++;
					}
					else{
						if(rs.getString("dianmian").equals(place)){
							Vector<String> temp = new Vector<String>();
							for (int i = 0; i < columnNum; i++) {
								temp.add(rs.getString(i + 1));
							}
							rows.add(temp);
							sum++;
						}		
					}								
				}
			}
			
			else{
				rs.absolute(index);	
				while (rs.previous()&&sum<Integer.parseInt(number)) {
					all--;
					if(place.equals("所有")){
						Vector<String> temp = new Vector<String>();
						for (int i = 0; i < columnNum; i++) {
							temp.add(rs.getString(i + 1));
						}
						t.add(temp);
						sum++;
					}
					else{
						if(rs.getString("dianmian").equals(place)){
							Vector<String> temp = new Vector<String>();
							for (int i = 0; i < columnNum; i++) {
								temp.add(rs.getString(i + 1));
							}
							t.add(temp);
							sum++;
						}		
					}		
				}
				
				for(int x=t.size()-1;x>=0;x--){
					rows.add(t.get(x));
				}					
			}				
	
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			hp.close();
		}
		
		return index+all;
	}
	

	public String getColumnName(int arg0) {
		// TODO 自动生成的方法存根
		String name = "";
		if (this.colums.get(arg0).toString().equals("id")) {
			name = "编号";
		} else if (this.colums.get(arg0).toString().equals("name")) {
			name = "姓名";
		} else if (this.colums.get(arg0).toString().equals("sex")) {
			name = "性别";
		} else if (this.colums.get(arg0).toString().equals("address")) {
			name = "地址";
		} else if (this.colums.get(arg0).toString().equals("birthday")) {
			name = "生日";
		} else if (this.colums.get(arg0).toString().equals("IDCard")) {
			name = "身份证号";
		} else if (this.colums.get(arg0).toString().equals("edu")) {
			name = "学历";
		} else if (this.colums.get(arg0).toString().equals("joblevel")) {
			name = "职位";
		} else if (this.colums.get(arg0).toString().equals("marriage")) {
			name = "婚姻";
		} else if (this.colums.get(arg0).toString().equals("tel")) {
			name = "联系方式";
		} else if (this.colums.get(arg0).toString().equals("mail")) {
			name = "邮箱";
		} else if (this.colums.get(arg0).toString().equals("password")) {
			name = "密码";
		}
		else if (this.colums.get(arg0).toString().equals("dianmian")) {
			name = "场地";
		}
		else if (this.colums.get(arg0).toString().equals("bookdate")) {
			name = "预定时间";
		}
		else if (this.colums.get(arg0).toString().equals("starttime")) {
			name = "起始时间";
		}
		else if (this.colums.get(arg0).toString().equals("endtime")) {
			name = "结束时间";
		}
		
		else if (this.colums.get(arg0).toString().equals("category")) {
			name = "团购种类";
		}
		else if (this.colums.get(arg0).toString().equals("tuangouhao")) {
			name = "团购号";
		}
		else if (this.colums.get(arg0).toString().equals("day")) {
			name = "日期";
		}

		return name;
	}
	
	

}
