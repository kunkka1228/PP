package com.partys.tools;

import java.awt.Toolkit;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.partys.db.SqlHelper;

public class BasicUtil {
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
	
}
