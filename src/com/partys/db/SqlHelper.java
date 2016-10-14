//package com.mhl.db;
//import java.sql.*;
//public class SqlHelper {
//	Connection con=null;
//	PreparedStatement ps=null;
//	ResultSet rs=null;
//	public SqlHelper()
//	{
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=restaurant","sa","");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	public ResultSet query(String sql,String []params)
//	{
//	try {
//			ps=con.prepareStatement(sql);
//		
//			for(int i=0;i<params.length;i++)
//			{
//			ps.setString(i+1, params[i]);
//			}
//			rs=ps.executeQuery();
//		} catch (SQLException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//	return rs;
//	}
//	public void close()
//	{
//	try{
//	if(rs!=null)
//	{
//	rs.close();
//	}
//	if(ps!=null)
//	{
//		ps.close();
//	}
//	if(con!=null)
//	{
//		con.close();
//	}
//	}
//	catch(Exception e)
//	{
//		e.printStackTrace();
//	}
//	}
//}
/*
 * 对数据库操作的类
 * 对数据库的操作，就是crud
 * 调用存储过程
 * 
 *注意：如果连接数据库时出现如下异常则表示未引入三个JAR驱动包，另外一个原因就是SQL语句有语法错误
 *java.lang.ClassNotFoundException: com.microsoft.jdbc.sqlserver.SQLServerDviver
 * */
package com.partys.db;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
public class SqlHelper {
	//定义需要的对象
	private Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	private static String url = "";
	private static String username = "";
	private static String password = "";
	private static String driveName = "com.mysql.jdbc.Driver";
	private final static String fileName = "db/db.properties";
	
	int sum=0;
	//构造函数，初始化ct
	public SqlHelper()
	{
		try {
			//得到连接
			con=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	static {
		try {
			Class.forName(driveName);
			
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("驱动加载失败");
		}
		getProperties();
	}

	// 加载配置文件
	private static void getProperties() {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(new File(fileName)));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		url = prop.getProperty("url");
		username = prop.getProperty("username");
		password = prop.getProperty("password");

	}
	
	//[]params，通过?赋值方式可以防止漏洞注入方式，保证安全性
	public ResultSet queryExecute(String sql,String []params)
	{
		try {
			ps=con.prepareStatement(sql);
			//对sql的参数赋值
			if(params!=null){
				for(int i=0;i<params.length;i++)
				{
					ps.setString(i+1, params[i]);
				}
			}
			
			//执行查询
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		//返回结果集
		return rs;
	}
	public int queryExecute(String sql)
	{
		try {
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			if(rs.next())
			{
				sum=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally{
			close();
		}
		//返回结果集
		return sum;
	}
	
	public boolean updateExecete(String[] sql,String[][]params){
		boolean b=true;		
		try {
			con.setAutoCommit(false);
			for(int x=0;x<sql.length;x++){
				ps=con.prepareStatement(sql[x]);
				if(params!=null){
					for(int y=0;y<params[x].length;y++){									
						ps.setString(y+1, params[x][y]);
					}
				}
				int i=ps.executeUpdate();
				if(i==0){
					b=false;
				}
			}
			con.commit();			
		} catch (Exception e) {
			b=false;
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			this.close();
		}
		//返回结果集
		return b;
	}
	
	public boolean updateExecete(String sql,String []params)
	{
		boolean b=true;
		try {
			ps=con.prepareStatement(sql);
			//对sql的参数赋值
			if(params!=null){
				for(int i=0;i<params.length;i++)
				{
					ps.setString(i+1, params[i]);
				}
			}			
			//执行查询
			if(ps.executeUpdate()==0)
			{
				b=false;
			}
		} catch (Exception e) {
			b=false;
			e.printStackTrace();
			// TODO: handle exception
		}
		finally
		{
			this.close();
		}
		//返回结果集
		return b;
		
	}
	//关闭资源的方法
	public void close()
	{
		if(rs!=null) {
			try {			
				rs.close();
				rs=null;
				}
			catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				rs=null;
						
			}						
		} 
		
		if(ps!=null) {
			try {			
				ps.close();
				ps=null;
				}
			catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				ps=null;
						
			}						
		} 
		
		if(con!=null) {
			try {			
				con.close();
				con=null;
				}
			catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				con=null;
						
			}						
		} 
		
	}
}
