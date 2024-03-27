/*================
 	DBConn.java
==================*/

// 예외 처리 → throws 구성
package com.test.mvc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn
{
	private static Connection dbConn;
	
	public static Connection getConnection() 
	{	
		if (dbConn == null)
		{
			try {
				
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "scott";
				String pwd = "tiger";
				
				Class.forName("oracle.jdbc.driver.OracleDriver"); //forName : 클래스를 찾아주는 클래스
				dbConn = DriverManager.getConnection(url,user,pwd);
				
			} catch (Exception e) 
			{
				System.out.println(e.toString());
			}
		}
		
		return dbConn;
	}
	
	// 오버로딩
	public static Connection getConnection(String url, String user, String pwd) 
	{
		
		if(dbConn == null)
		{
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url,user,pwd);
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		return dbConn;
	}
	
	public static void close() 
	{
		if(dbConn != null)
		{
			try {
				
				if(!dbConn.isClosed()) // is 인지 아닌지 
					dbConn.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		dbConn = null;
	}

}
