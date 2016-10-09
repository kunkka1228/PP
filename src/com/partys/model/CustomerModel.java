package com.partys.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.partys.db.SqlHelper;

public class CustomerModel extends CommonModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6930601228372819119L;

	public String[] getAllPartys(){
		SqlHelper hp=new SqlHelper();
		String sql="select distinct dianmian from customer";
		ResultSet rs=hp.queryExecute(sql, null);
		ArrayList<String> al=new ArrayList<String>();
		try {
			while(rs.next()){
				al.add(rs.getString("dianmian"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int len=al.size();
		String[] arr=new String[len];
		
		for(int x=0;x<len;x++){
			arr[x]=al.get(x);
		}
		
		return arr;
	}
	
	public int getNum()
	{
		SqlHelper hp=new SqlHelper();
		String sql="select count(*) from customer";
		int sum=hp.queryExecute(sql);
		return sum;
	}
	
	public void querryOnDataById(String[] params){
		String sql="select * from customer where id=?";
		query(sql, params);
	}
	
	public void queryByNameOrId(String[] params){
		String sql="select id,name,sex,joblevel,address,edu from customer where id=? or name=?";
		query(sql, params);	
	}
	
	public void deleteByID(String[] params){
		String sql="delete from customer where id=?";
		UpdateModel(sql,params);
	}
	
	public void querryAll(){
		String sql="select * from customer where 1=1";
		query(sql,null);
	}
	
	public void querySimpleInfor(){
		String sql="select name,tel,dianmian,bookdate,starttime,endtime from customer where 1=1";
		query(sql,null);
	}
	
	public boolean addItem(String[] params){
		String sql="insert into customer values(?,?,?,?,?,?,?,?,?,?,?)";
		return UpdateModel(sql,params);
	}
	
	public boolean updateItem(String[] params){
		String sql="update customer set name=?,sex=? ,address=? , birthday=? , IDCard=?,edu=?,joblevel=?,marriage=?,tel=?,mail=? where id=?";
		return UpdateModel(sql,params);
	}
}
