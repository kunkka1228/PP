package com.partys.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import com.partys.db.SqlHelper;

public class ConfigureModel {
	private Properties prop=null;
	private File file=null;
	
	public ConfigureModel(){
		prop=new Properties();
		file=new File("db/db.properties");
	}
	
	public String getProperties(String key){
		
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		try {
			prop.load(fr);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		finally{
			try {
				if(fr!=null){
					fr.close();
				}
				
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return (String) prop.get(key);
	}
	
	public boolean testDataBase(String url,String username,String pwd){
		SqlHelper sh=new SqlHelper();
		return sh.connect(url,username,pwd);
	}
	
	public boolean setDataBaseParameter(String url,String port,String databaseName,String username,String pwd){	
		boolean flag=true;
		FileWriter fw = null;
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fw = new FileWriter(file);
			prop.setProperty("url", url);
			prop.setProperty("port", port);
			prop.setProperty("DatabaseName", databaseName);
			prop.setProperty("username", username);
			prop.setProperty("password", pwd);
			
			prop.store(fw, "asd");
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			flag=false;
			e1.printStackTrace();
			return flag;
		}
		finally{
			try {
				if(fw!=null){
					fw.close();
				}
				
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				fw=null;
			}
		}
		return flag;
		
	}
	
	
}
