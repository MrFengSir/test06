package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao {
	public boolean search(String usename,String code) {
		boolean flag = false;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
		"root", "456789");
		Statement state = conn.createStatement();
		String sql = "select * from user where usename ='"+usename+"' and password = '"+code+"'";
		
		ResultSet rs = state.executeQuery(sql);
		if(rs.next()){
			flag=true;
		}	
		
		conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return flag;
		
	}

}
