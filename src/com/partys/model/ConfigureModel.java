package com.partys.model;

import com.partys.db.SqlHelper;

public class ConfigureModel {

	public boolean testDataBase(String url,String username,String pwd){
		SqlHelper sh=new SqlHelper();
		return sh.connect(url,username,pwd);
	}
}
