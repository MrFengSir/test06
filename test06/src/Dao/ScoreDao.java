package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;

public class ScoreDao {

	public List<Score> searchCondition(Score condition) {
		List<Score> selectlist = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String where = " where 1=1 ";
			if (!condition.getEmp().getName().equals("")) {
				where += " and emp.e_name ='" + condition.getEmp().getName() + "'";
			}
			if (!condition.getEmp().getDep().getName().equals("")) {
				where += " and emp.d_name ='" + condition.getEmp().getDep().getName() + "'";
			}
			if (condition.getPro().getId() != -1) {
				where += " and emp.p_id =" + condition.getPro().getId() + "";
			}
			if (condition.getValue() != -1) {
				where += " and value =" + condition.getValue() + "";
			}
			if (!condition.getLast().equals("")) {
				where += " and last ='" + condition.getLast() + "'";
			}
			String sql = "select * from  emp_grade as emp LEFT JOIN grade as g on g.e_id=emp.e_id and g.p_id=emp.p_id "
					+ where;
			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Score sc = new Score();
				Employee emp = new Employee();
				emp.setName(rs.getString("emp.e_name"));
				sc.setEmp(emp);
				Department dep = new Department();
				dep.setName(rs.getString("emp.d_name"));
				emp.setDep(dep);
				sc.setEmp(emp);
				Project pro = new Project();
				pro.setName(rs.getString("emp.p_name"));
				sc.setPro(pro);
				sc.setValue((Integer) rs.getObject("value"));
				sc.setLast(rs.getString("last"));
				selectlist.add(sc);
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
		return selectlist;
	}

	public List<Project> showProject() {
		List<Project> prolist = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from Project");

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				prolist.add(pro);
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
		return prolist;
	}

	

	public void save(Set<Score> set) {
		for (Score sc : set) {
			if (sc.getId() == 0) {
				add(sc);
			} else {
				update(sc);
			}
		}
	}

	public boolean add(Score sc) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			
			String sql = "insert into grade(e_id,p_id,value) values(?,?,?)";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sc.getEmp().getId());
			pstat.setInt(2, sc.getPro().getId());
			pstat.setInt(3, sc.getValue());
			int rs = pstat.executeUpdate();
			if(rs>0){
				flag = true;
			}
		
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
	
	public boolean update(Score sc) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			
			String sql = "update grade set value =? where id=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sc.getValue());
			pstat.setInt(2, sc.getId());
			int rs = pstat.executeUpdate();
			if(rs>0){
				flag = true;
			}
		
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

	public int searchCount(Score condition){
		int count = 0;
			try {
				Class.forName("com.mysql.jdbc.Dr"
						+ "iver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
						"root", "456789");
				Statement state = conn.createStatement();
				String where = " where 1=1 ";
				if (condition.getEmp().getName()!=null&&!condition.getEmp().getName().equals("")) {
					where += " and emp.e_name ='"+condition.getEmp().getName()+"'";
				}
				if (condition.getEmp().getDep().getId()!=-1) {
					where += " and emp.d_id ='"+condition.getEmp().getDep().getId()+"'";
				}
				if (condition.getPro().getId()!=-1) {
					where += " and emp.p_id ='"+condition.getPro().getId()+"'";
				}
				if (condition.getValue()!=-1) {
					where += " and value ='"+condition.getValue()+"'";
				}
				if (condition.getLast()!=null&&!condition.getLast().equals("")) {
					where += " and last ='"+condition.getLast()+"'";
				}
				
				String sql = "select count(*) from emp_grade as emp LEFT JOIN grade as g on g.e_id=emp.e_id and g.p_id=emp.p_id"+where;
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
	
	
	public List<Score> searchContidion(Score condition,int begin,int size) {
		List<Score> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String where = " where 1=1 ";
			if (condition.getEmp().getName()!=null&&!condition.getEmp().getName().equals("")) {
				where += " and emp.e_name ='"+condition.getEmp().getName()+"'";
			}
			if (condition.getEmp().getDep().getId()!=-1) {
				where += " and emp.d_id ='"+condition.getEmp().getDep().getId()+"'";
			}
			if (condition.getPro().getId()!=-1) {
				where += " and emp.p_id ='"+condition.getPro().getId()+"'";
			}
			if (condition.getValue()!=-1) {
				where += " and value ='"+condition.getValue()+"'";
			}
			if (condition.getLast()!=null&&!condition.getLast().equals("")) {
				where += " and last ='"+condition.getLast()+"'";
			}
			String sql = "select g.id as gId,emp.e_id as empId,emp.p_id as empPid,emp.e_name as eName,emp.d_id as dId,emp.d_name as dName,emp.p_name as pName,"
					+ "value,last from  emp_grade as emp LEFT JOIN grade as g on g.e_id=emp.e_id and g.p_id=emp.p_id "+ where +" limit "+begin+","+size;
			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				Score sc = new Score();
				Employee emp = new Employee();
				emp.setName(rs.getString("eName"));
				emp.setId(rs.getInt("empId"));
				sc.setEmp(emp);
				Department dep = new Department();
				dep.setId(rs.getInt("dId"));
				dep.setName(rs.getString("dName"));
				emp.setDep(dep);
				sc.setEmp(emp);
				Project pro = new Project();
				pro.setName(rs.getString("PName"));
				pro.setId(rs.getInt("empPid"));
				sc.setId(rs.getInt("gId"));
				sc.setPro(pro);
				sc.setValue((Integer) rs.getObject("value"));
				sc.setLast(rs.getString("last"));
				list.add(sc);
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
	
	public Score search(int empId,int proId) {
		Score score = new Score();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			
			String sql = "select last from grade where e_id ="+empId+" and p_id ="+proId;
			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				score.setLast(rs.getString("last"));
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
		return score;
	}
	
	public Score search(int id) {
		Score score = new Score();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			
			String sql = "select last from grade where id ="+id;
			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				score.setLast(rs.getString("last"));
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
		return score;
	}

}
