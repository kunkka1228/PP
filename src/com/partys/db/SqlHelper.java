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
//			// TODO �Զ����ɵ� catch ��
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
 * �����ݿ��������
 * �����ݿ�Ĳ���������crud
 * ���ô洢����
 * 
 *ע�⣺����������ݿ�ʱ���������쳣���ʾδ��������JAR������������һ��ԭ�����SQL������﷨����
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
	//������Ҫ�Ķ���
	private Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	private static String url = "";
	private static String username = "";
	private static String password = "";
	private static String driveName = "com.mysql.jdbc.Driver";
	private final static String fileName = "db/db.properties";
	
	int sum=0;
	//���캯������ʼ��ct
	public SqlHelper()
	{
		try {
			//�õ�����
			con=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	static {
		try {
			Class.forName(driveName);
			
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("��������ʧ��");
		}
		getProperties();
	}

	// ���������ļ�
	private static void getProperties() {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(new File(fileName)));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		url = prop.getProperty("url");
		username = prop.getProperty("username");
		password = prop.getProperty("password");

	}
	
	//[]params��ͨ��?��ֵ��ʽ���Է�ֹ©��ע�뷽ʽ����֤��ȫ��
	public ResultSet queryExecute(String sql,String []params)
	{
		try {
			ps=con.prepareStatement(sql);
			//��sql�Ĳ�����ֵ
			if(params!=null){
				for(int i=0;i<params.length;i++)
				{
					ps.setString(i+1, params[i]);
				}
			}
			
			//ִ�в�ѯ
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		//���ؽ����
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
		//���ؽ����
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
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			this.close();
		}
		//���ؽ����
		return b;
	}
	
	public boolean updateExecete(String sql,String []params)
	{
		boolean b=true;
		try {
			ps=con.prepareStatement(sql);
			//��sql�Ĳ�����ֵ
			if(params!=null){
				for(int i=0;i<params.length;i++)
				{
					ps.setString(i+1, params[i]);
				}
			}			
			//ִ�в�ѯ
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
		//���ؽ����
		return b;
		
	}
	//�ر���Դ�ķ���
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
