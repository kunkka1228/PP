package com.partys.model;

import java.util.ArrayList;

public class BookModel extends CommonModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6149291981386409506L;
	
	public void queryAllCurrentDateInfor(int year,int month){
		String m=month+"";
		if(month<10){
			m="0"+month;
		}
		String date=year+"-"+m;
		date+="%";
		String[] para={date};
		String sql="select * from customer where bookdate like ? order by dianmian";		
		query(sql, para);
	}
	
	public ArrayList<String[]> queryCurrentDateInfor(BookModel bm,int year,int month, int day){
		String numTran=day+"";
		String monthTran=month+"";
		if(day<10){
			numTran="0"+day;					
		}	
		if(month<10){
			monthTran="0"+month;
		}
		String date=year+"-"+monthTran+"-"+numTran;
		ArrayList<String[]> al=new ArrayList<String[]>();
		for(int x=0;x<bm.getRowCount();x++){			
			if(bm.getValueAt(x, 6).toString().contains(date)){
				String id=(String) bm.getValueAt(x, 1);
				String startTime=keepHourAndMin((String) bm.getValueAt(x, 7));
				String endTime=keepHourAndMin((String) bm.getValueAt(x, 8));
				String place=(String) bm.getValueAt(x, 4);
				String[] arr={id,startTime,endTime,place};
				al.add(arr);						
			}
		}
		
		return al;
	}
	
	private String keepHourAndMin(String time){
		String[] arr=time.split(" ");	
		String[] timeArr=arr[1].split(":");	
		return timeArr[0]+":"+timeArr[1];
	}
	
	public int findIndex(String word,String[] arr){
		for(int x=0;x<arr.length;x++){
			if(arr[x].equals(word)){
				return x;
			}
		}
		return -1;
	}

}
