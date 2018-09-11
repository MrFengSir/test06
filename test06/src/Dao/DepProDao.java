package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Project;

public class DepProDao {
	
	public boolean add(int depId,int proId) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			int rs = state.executeUpdate("insert into r_dep_pro(d_id,p_id) values("+depId+","+proId+")");
			
//			while (rs.next()) {
//				Project pro = new Project();
//				pro.setId(rs.getInt("d_id"));
//				pro.setName(rs.getString("p_id"));
				
//			}
			if(rs > 0){
				flag = true;
			}

//			rs.close();
			conn.close();
			state.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	

	public boolean delete(int depId,int proId) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			
//			 Statement state = conn.createStatement();
			// int rs = state.executeUpdate("update employee set name =?,set sex
			// =?,set age =? where id =?);
			Statement state = conn.createStatement();
			String sql = "delete  from r_dep_pro where d_id ="+depId+" and "+" p_id = "+proId;
			int rs = state.executeUpdate(sql);
			
		
		
			if (rs > 0) {
				flag = true;
			}
			// rs.close();
			conn.close();
//			pstat.close();
			 state.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public List<Project> searchNoHave(int id) {
		List<Project> noHaveList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from project where id not in (select p_id from v_dep_pro where d_id =" +id+")");
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				noHaveList.add(pro);
			}

			rs.close();
			conn.close();
			state.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noHaveList;
	}
	

	public List<Project> search(int id) {
		List<Project> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from v_dep_pro where d_id = "+id);
			
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				list.add(pro);
			}

			rs.close();
			conn.close();
			state.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
