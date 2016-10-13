/**
 * 这是人事管理的操作
 */
package com.partys.model;


import com.partys.db.SqlHelper;
public class EmpModel extends CommonModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7055176924075699409L;

	public int getNum()
	{	SqlHelper hp=new SqlHelper();
		String sql="select count(*) from renshi";
		int sum=hp.queryExecute(sql);
		return sum;
	}
	
	public void querryOnDataById(String[] params){
		String sql="select * from renshi where id=?";
		query(sql, params);
	}
	
	public void queryByNameOrId(String[] params){
		String sql="select id,name,sex,joblevel,address,edu from renshi where id=? or name=?";
		query(sql, params);	
	}
	
	public void deleteByID(String[] params){
		SqlHelper hp=new SqlHelper();
		String sql="delete from renshi where id=?";
		String sqls="delete from login where id=?";
		String[] sqlarr={sql,sqls};
		String[][] paramArr={params,params};
		hp.updateExecete(sqlarr, paramArr);
	}
	
	public void querryAll(){
		String sql="select * from renshi where 1=1";
		query(sql,null);
	}
	
	public void querySimpleInfor(){
		String []params={"1"};
		String sql="select id,name,sex,joblevel,address,edu from renshi where 1=?";
		query(sql,params);
	}
	
	public void querySimpleInforOneData(String[] params){
		String sql="select id,name,sex,joblevel,address,edu from renshi where id=?";
		query(sql,params);
	}
	
	public boolean addItem(String[] params){
		SqlHelper hp=new SqlHelper();
		String sql="insert into renshi values(?,?,?,?,?,?,?,?,?,?,?)";
		String sqls="insert into login values (?,?)";
		String[] sqlarr={sql,sqls};
		
		String[] pa={params[0],"1234"};		
		String[][] paramArr={params,pa};
		return hp.updateExecete(sqlarr, paramArr) ;
	}
	
	public boolean updateItem(String[] params){
		String sql="update renshi set name=?,sex=? ,address=? , birthday=? , IDCard=?,edu=?,joblevel=?,marriage=?,tel=?,mail=? where id=?";
		return UpdateModel(sql,params);
	}

}
