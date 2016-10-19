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
	
	public void setDataBaseParameter(String url,String port,String databaseName,String username,String pwd){		
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			prop.store(fw, "asd");
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
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
		
	}
	
	
}
