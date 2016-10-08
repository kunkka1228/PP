package com.partys.tools;

import java.awt.Toolkit;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

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
}
