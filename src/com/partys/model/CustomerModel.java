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
		String[] arr=new String[len+1];
		
		for(int x=0;x<arr.length;x++){
			if(x==0){
				arr[0]="所有";
				continue;
			}
			arr[x]=al.get(x-1);
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
	
	public void queryByKeywords(String keywords,String[] params){
		if(keywords.equals("编    号")){
			keywords="id";
		}
		
		else if(keywords.equals("姓    名")){
			keywords="name";
		}
		
		else if(keywords.equals("联系方式")){
			keywords="tel";
		}
		String sql="";
		if(params[1].equals("所有")){
			sql="select id, name,tel,dianmian,bookdate,starttime,endtime from customer where "+keywords+" =?";
			String[] paramsArr={params[0]};
			query(sql, paramsArr);	
			
		}
		else {
			sql="select id, name,tel,dianmian,bookdate,starttime,endtime from customer where "+keywords+" =? and dianmian=?";
			query(sql, params);
		}
		
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
		String sql="select id, name,tel,dianmian,bookdate,starttime,endtime from customer where 1=1";
		query(sql,null);
	}
	
	public void querySimpleInforOneData(String[] params){
		String sql="select id, name,tel,dianmian,bookdate,starttime,endtime from customer where id=?";
		query(sql,params);
	}
	
	public boolean addItem(String[] params){
		String sql="insert into customer values(?,?,?,?,?,?,?,?,?,?,?)";
		
		return UpdateModel(sql,params);
	}
	
	public boolean updateItem(String[] params){
		String sql="update customer set name=?,sex=? ,address=? , birthday=? , IDCard=?,edu=?,joblevel=?,marriage=?,tel=?,mail=? where id=?";
		return UpdateModel(sql,params);
	}
	
	public void filterByPlace(String place){
		String sql="";
		String[] params=new String[1];
		if(place.equals("所有")){
			sql="select id,name,tel,dianmian,bookdate,starttime,endtime from customer where 1=1";
			params=null;
		}
		else{
			params[0]=place;
			sql="select id,name,tel,dianmian,bookdate,starttime,endtime from customer where dianmian=?";
		}
		
		query(sql,params);
	}
	
}
