package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Project;

public class ProjectDao {
	
	public boolean add(Project pro) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
//			int rs = state.executeUpdate("insert into  project(name,sex,age) values('" + pro.getName() + "','"
//					+ pro.getSex() + "'," + pro.getAge() + ")");
			String sql = "insert into  project(name) values(?)";
			
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, pro.getName());
			int rs = pstat.executeUpdate();
			
			if (rs > 0) {
				flag = true;
			}

			conn.close();
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
	
	public boolean modify(Project pro) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			// Statement state = conn.createStatement();
			// int rs = state.executeUpdate("update project set name =?,set sex
			// =?,set age =? where id =?);
			String sql = "update project set name =? where id =?";
			
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, pro.getName());
			pstat.setInt(2, pro.getId());
			System.out.println(sql);
			
			int rs = pstat.executeUpdate();
			
			if (rs > 0) {
				flag = true;
			}
			// rs.close();
			conn.close();
			pstat.close();
			// state.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public Project search(int id){
		Project pro  = new Project();
		try {
			Class.forName("com.mysql.jdbc.Dr"
					+ "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select * from project where id ="+id;
//			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));

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
		return pro;
		
	}
	
	public List<Project> search(String ids){
		List<Project> list = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Dr"
					+ "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select * from project where id in ("+ids+")";
//			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));

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
	
	
	public List<Project> search() {
		List<Project> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Dr"
					+ "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select * from project ";
//			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));

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
	
	public List<Project> search(int begin,int size) {
		List<Project> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Dr"
					+ "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select * from project limit "+begin+","+size;
//			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
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
	
	public int searchCount(){
	int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Dr"
					+ "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select count(*) from project ";
//			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

		 if(rs.next()) {
			 count = rs.getInt(1);
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
		return count;
	}
	
	public int searchCount(Project condition){
		int count = 0;
			try {
				Class.forName("com.mysql.jdbc.Dr"
						+ "iver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
						"root", "456789");
				Statement state = conn.createStatement();
				String where = " where 1=1 ";
				if (condition.getName()!=null&&!condition.getName().equals("")) {
					where += " and name ='"+condition.getName()+"'";
				}
	
				String sql = "select count(*) from project "+where;
//				System.out.println(sql);
				ResultSet rs = state.executeQuery(sql);

			 if(rs.next()) {
				 count = rs.getInt(1);
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
			return count;
		}
	
	public List<Project> searchContidion(Project condition,int begin,int size) {
		List<Project> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String where = " where 1=1 ";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
				where += " and name ='"+condition.getName()+"'";
			}
			String sql = "select * from project "+ where +" limit "+begin+","+size;
			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
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
	
	
	
	public boolean delete(int id){
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Dr"
					+ "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "delete  from project where id ="+id;
			System.out.println(sql);
			int rs = state.executeUpdate(sql);

			if(rs>0) {
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





	


}
