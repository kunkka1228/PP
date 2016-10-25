package com.partys.model;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	
	public void queryByKeywords(String keywords,String[] params,String place,int index,String number,boolean flag){
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
		if(place.equals("所有")){
			sql="select id, name,tel,dianmian,bookdate,starttime,endtime from customer where "+keywords+" like ?";
			String[] paramsArr={params[0]};
			query(sql, paramsArr);	
			
		}
		else {
			sql="select id, name,tel,dianmian,bookdate,starttime,endtime from customer where "+keywords+" like ? and dianmian="+ place;
			query(sql, params);
		}
		query(sql,params,place,index,number,flag);
	}
	
	
	public String getDataById(String data,String[] params){
		String sql ="select "+data+" from customer where id=?";
		SqlHelper hp=new SqlHelper();
		ResultSet rs=hp.queryExecute(sql, params);
		String returnInfor = null;
		try {
			if(rs.next()){
				returnInfor = rs.getString(data);
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		finally{
			try {
					rs.close();
				
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return returnInfor;
		
	}
	
	public void deleteByID(String[] params){
		String sql="delete from customer where id=?";
		UpdateModel(sql,params);
	}
	
	public void querySimpleInfor(String num){
		String sql="select id, name,tel,dianmian,bookdate,starttime,endtime from customer where 1=1 limit 0,"+num;
		query(sql,null);
	}
	
	public void querySimpleInfor(String index, String num){
		String sql="select id, name,tel,dianmian,bookdate,starttime,endtime from customer limit "+index+ " , "+num;
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
		String sql="update customer set name=?,sex=? ,tel=? ,dianmian=?, day=? , bookdate=? , starttime=? , endtime=? , category=? , tuangouhao=? where id=?";
		return UpdateModel(sql,params);
	}
	

	public int querySimpleInfor(String place,int index,String number,boolean flag){
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
		return query(sql,params,place,index,number,flag);
	}
	
	
	public void deleteFile(String tuangouhaoName){
		try {					
			File file=new File(tuangouhaoName);
			file.delete();
			JOptionPane.showMessageDialog(null, "恭喜！删除成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		
	}
	
	
	public int getMaxCountByPlace(String place){
		int sum=0;
		if(place.equals("所有")){
			return getNum();
		}
		else{
			String sql="select count(*) from customer where dianmian = '"+place+"'";
			SqlHelper hp=new SqlHelper();
			sum=hp.queryExecute(sql);
			hp=null;
		}
			
		return sum;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return super.getColumnCount();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return super.getRowCount();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return super.getValueAt(arg0, arg1);
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return super.getColumnName(arg0);
	}
	
	
	
	
	
}
