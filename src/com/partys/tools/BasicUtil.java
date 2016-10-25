package com.partys.tools;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.partys.db.SqlHelper;


public class BasicUtil {
	private static final File FILE=new File("setting.ini");
	public static void horizontal(JTable table){
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		for(int x=0;x<table.getColumnCount();x++){
			table.getColumn(table.getColumnName(x)).setCellRenderer(render);
		}
		
	}
	
	public static int[] getSreenWidthAndHeight(){
		int[] arr=new int[2];
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		arr[0]=width;
		arr[1]=height;
		return arr;
	}
	
	public static String toDouble(int a){
		return "0"+a;
		
	}
	
	
	public static String getAutoNumber(int l,String tableName){
		String sql ="select max(id) from "+tableName;
		SqlHelper sh=new SqlHelper();
		String n=(sh.queryExecute(sql)+1)+"";
		while(n.length()<l){
			n="0"+n;
		}
		return n;
	}
	
	public static String getLocalMac(){
		InetAddress ia=null;
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		byte[] mac=null;
		try {
			mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		} catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<mac.length; i++) {
			if(i!=0) {
				sb.append("-");
			}
			//字节转换为整数
			int temp = mac[i]&0xff;
			String str = Integer.toHexString(temp);
			if(str.length()==1) {
				sb.append("0"+str);
			}else {
				sb.append(str);
			}
		}
		
		return sb.toString().toUpperCase();
		
	}
	
	
	public static void setTableHeight(String key,String value){
		FileWriter fw=null;
		Properties prop=null;
		try {
			fw=new FileWriter(FILE);
			prop=new Properties();
			prop.setProperty(key, value);
			prop.store(fw, "save");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
		finally{
			if(fw!=null){
				try {
					fw.close();
					prop=null;
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getTableHeight(String key){
		FileReader fr=null;
		String value="";
		try {
			fr=new FileReader(FILE);
			Properties prop=new Properties();
			prop.load(fr);
			value=prop.getProperty(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return value;
	}
	
	public static String caculateRow(int allHight){
		int row=20;
		return ((int)Math.floor(allHight/row)-1)+"";
	}
	
	public static String[] caculatorNum(String[] numItem,String num){
		
		
		TreeSet<String> ts=new TreeSet<String>();
		for(int x=0;x<numItem.length;x++){
			ts.add(numItem[x]);
		}
		ts.add(num);
		int size=ts.size();
		String[] arr=new String[size];
		Iterator<String> it=ts.iterator();
		for(int x=0;x<size;x++){
			arr[x]=it.next();
		}
		return arr;
		
	}
}
