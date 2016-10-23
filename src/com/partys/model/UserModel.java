package com.partys.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.partys.db.SqlHelper;

/**
 * @param uid
 *            用户编号
 * @param p
 *            用户密码
 * @return 用户职位
 * @author Administrator 这是用户表 数据模型，用它完成对用户的各种操作
 */
public class UserModel {
	
	public boolean UpdateModel(String sql,String []params)
	{
		SqlHelper hp=new SqlHelper();
		return hp.updateExecete(sql,params);
	}
	
	public boolean checkUser(String uid, String p){
		boolean flag=false;
		SqlHelper hp = null;
		String sql = "select * from renshi where id= (select id from login where id=? and password=?)";
		String[] params = { uid, p };
		hp = new SqlHelper();
		ResultSet rs = hp.queryExecute(sql, params);
		try {
			if(rs.next()){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public String[] getJoblevel(String uid, String p) {
		String[] loginInfor=new String[2];
		SqlHelper hp = null;
		try {
			String sql = "select joblevel,name from renshi where id= (select id from login where id=? and password=?)";
			String[] params = { uid, p };
			hp = new SqlHelper();
			ResultSet rs = hp.queryExecute(sql, params);
			if (rs.next()) {
				loginInfor[0]=rs.getString("joblevel");
				loginInfor[1]=rs.getString("name");
			}
			else{
				loginInfor[0]="无";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hp.close();
		}
		return loginInfor;
	}

	public String getNameById(String uid) {
		String empname = null;
		SqlHelper hp = null;
		try {
			String sql = "select name from renshi where id=?";
			String[] params = { uid };
			hp = new SqlHelper();
			ResultSet rs = hp.queryExecute(sql, params);
			if (rs.next()) {
				empname = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hp.close();
		}
		return empname;
	}
}
