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

public class DepartmentDao {
	
	public boolean add(Department dep) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
//			int rs = state.executeUpdate("insert into  department(name,sex,age) values('" + dep.getName() + "','"
//					+ dep.getSex() + "'," + dep.getAge() + ")");
			String sql = "insert into  department(name) values(?)";
			
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, dep.getName());
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
	
	public boolean modify(Department dep) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			// Statement state = conn.createStatement();
			// int rs = state.executeUpdate("update department set name =?,set sex
			// =?,set age =? where id =?);
			String sql = "update department set name =? where id =?";
			
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, dep.getName());
			pstat.setInt(2, dep.getId());
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
	
	public Department search(int id){
		Department dep  = new Department();
		try {
			Class.forName("com.mysql.jdbc.Dr"
					+ "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select * from department where id ="+id;
//			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));

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
		return dep;
		
	}
	
	public List<Department> search(String ids){
		List<Department> list = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Dr"
					+ "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select * from department where id in ("+ids+")";
//			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));

				list.add(dep);

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
	
	
	public List<Department> search() {
		List<Department> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Dr"
					+ "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select * from department";
//			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));

				list.add(dep);
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
	
	public List<Department> search(int begin,int size) {
		List<Department> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Dr"
					+ "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select * from department limit "+begin+","+size;
//			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmp_count(rs.getInt("emp_count"));

				list.add(dep);
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
			String sql = "select count(*) from department ";
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
	
	public int searchCount(Department condition){
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
				if (condition.getEmp_count() != -1) {
					where += " and emp_count ="+condition.getEmp_count()+"";
				}
	
				String sql = "select count(*) from department "+where;
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
	
	public List<Department> searchContidion(Department condition,int begin,int size) {
		List<Department> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String where = " where 1=1 ";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
				where += " and name ='"+condition.getName()+"'";
			}
			if (condition.getEmp_count() != -1) {
				where += " and emp_count ="+condition.getEmp_count()+"";
			}

			
			String sql = "select * from department "+ where +" limit "+begin+","+size;
			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmp_count(rs.getInt("emp_count"));

				list.add(dep);
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
			conn.setAutoCommit(false);
//			 Statement state = conn.createStatement();
			// int rs = state.executeUpdate("update employee set name =?,set sex
			// =?,set age =? where id =?);
			String sql = "delete from department where id =?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			System.out.println(sql);
			pstat.setInt(1, id);
			int rs = pstat.executeUpdate();
			
			sql = "update employee  set d_id=null where d_id =?";
		    pstat = conn.prepareStatement(sql);
			pstat.setInt(1, id);
			rs = pstat.executeUpdate();
			
			sql = "delete from r_dep_pro where d_id =?";
		    pstat = conn.prepareStatement(sql);
			pstat.setInt(1, id);
			rs = pstat.executeUpdate();
			conn.commit();

			if(rs>0) {
				flag = true;
			}

//			rs.close();
			conn.close();
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}

	public boolean deleteBatch(String ids) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Dr"
					+ "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "delete  from department where id in ("+ids+")";
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

	public boolean modifyBatch1(String ids, Department dep) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			// Statement state = conn.createStatement();
			// int rs = state.executeUpdate("update department set name =?,set sex
			// =?,set age =? where id =?);
			String sql = "update department set name =?,sex =?,age =? where id in ("+ids+")";
			
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, dep.getName());

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

	public boolean modifyBatch2(List<Department> list) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");

			for(int i =0;i<list.size();i++) {
			Department dep = list.get(i);
			String sql = "update department set name =?,sex =?,age =? where id =?";
			
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, dep.getName());
			pstat.setInt(4, dep.getId());
			System.out.println(sql);
			
			int rs = pstat.executeUpdate();
			
			if (rs > 0) {
				flag = true;
			}
			}
			// rs.close();
			conn.close();
			
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
	

	


}
