package com.partys.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.partys.db.SqlHelper;

public class CustomerModel {
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
}
