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
import entity.Employee;

public class EmployeeDao {

	public boolean add(Employee emp) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			// int rs = state.executeUpdate("insert into employee(name,sex,age) values('" +
			// emp.getName() + "','"
			// + emp.getSex() + "'," + emp.getAge() + ")");
			String sql = "insert into  employee(name,sex,age,d_id,pic_name) values(?,?,?,?,?)";

			PreparedStatement pstat = conn.prepareStatement(sql);
			System.out.println(sql);
			pstat.setString(1, emp.getName());
			pstat.setString(2, emp.getSex());
			pstat.setInt(3, emp.getAge());
			pstat.setObject(4, emp.getDep().getId());
			pstat.setString(5, emp.getPic_name());
			int rs = pstat.executeUpdate();

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

	public boolean modify(Employee emp) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			// Statement state = conn.createStatement();
			// int rs = state.executeUpdate("update employee set name =?,set sex
			// =?,set age =? where id =?);
			String sql = "update employee set name =?,sex =?,age =?,d_id=? where id =?";

			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, emp.getName());
			pstat.setString(2, emp.getSex());
			pstat.setInt(3, emp.getAge());
			pstat.setObject(4, emp.getDep().getId());
			pstat.setInt(5, emp.getId());

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

	public Employee search(int id) {
		Employee emp = new Employee();
		try {
			Class.forName("com.mysql.jdbc.Dr" + "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select e.*,d.name as dName from employee as e left join department as d on e.d_id=d.id where e.id ="
					+ id;
			// System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("dName"));
				emp.setDep(dep);
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
		return emp;

	}

	public List<Employee> search(String ids) {
		List<Employee> list = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Dr" + "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select * from employee where id in (" + ids + ")";
			// System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);

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

	public List<Employee> search() {
		List<Employee> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Dr" + "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select * from employee ";
			// System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);
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

	public List<Employee> search(int begin, int size) {
		List<Employee> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Dr" + "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select * from employee limit " + begin + "," + size;
			// System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);
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

	public int searchCount() {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Dr" + "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "select count(*) from employee ";
			// System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			if (rs.next()) {
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

	public int searchCount(Employee condition) {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Dr" + "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String where = " where 1=1 ";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and e.name ='" + condition.getName() + "'";
			}
			if (condition.getSex() != null && !condition.getSex().equals("")) {
				where += " and sex ='" + condition.getSex() + "'";
			}
			if (condition.getAge() != -1) {
				where += " and age =" + condition.getAge() + "";
			}
			if (condition.getDep().getName() != null && !condition.getDep().getName().equals("")) {
				where += " and d.name ='" + condition.getDep().getName() + "'";

			}
			if (condition.getPic_name() != null && !condition.getPic_name().equals("")) {
				where += " and e.pic_name ='" + condition.getPic_name() + "'";
			}
			String sql = "select count(*) from employee as e left join department as d on e.d_id=d.id " + where;
			// System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			if (rs.next()) {
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

	public List<Employee> searchContidion(Employee condition, int begin, int size) {
		List<Employee> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String where = " where 1=1 ";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and e.name ='" + condition.getName() + "'";
			}
			if (condition.getSex() != null && !condition.getSex().equals("")) {
				where += " and sex ='" + condition.getSex() + "'";
			}
			if (condition.getAge() != -1) {
				where += " and age =" + condition.getAge() + "";
			}
			if (condition.getDep().getName() != null && !condition.getDep().getName().equals("")) {
				where += " and d.name ='" + condition.getDep().getName() + "'";
			}
			

			String sql = "select e.*,d.name as dName, emp_count from employee as e left join department as d on e.d_id=d.id "
					+ where + " limit " + begin + "," + size;
			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				emp.setPic_name(rs.getString("pic_name"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("dName"));
				dep.setEmp_count(rs.getInt("emp_count"));
				emp.setDep(dep);
				list.add(emp);
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

	public boolean delete(int id) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Dr" + "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "delete  from employee where id =" + id;
			System.out.println(sql);
			int rs = state.executeUpdate(sql);

			if (rs > 0) {
				flag = true;
			}

			// rs.close();
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

	public boolean deleteBatch(String ids) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Dr" + "iver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			Statement state = conn.createStatement();
			String sql = "delete  from employee where id in (" + ids + ")";
			System.out.println(sql);
			int rs = state.executeUpdate(sql);

			if (rs > 0) {
				flag = true;
			}

			// rs.close();
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

	public boolean modifyBatch1(String ids, Employee emp) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");
			// Statement state = conn.createStatement();
			// int rs = state.executeUpdate("update employee set name =?,set sex
			// =?,set age =? where id =?);
			String sql = "update employee set name =?,sex =?,age =? where id in (" + ids + ")";

			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, emp.getName());
			pstat.setString(2, emp.getSex());
			pstat.setInt(3, emp.getAge());
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

	public boolean modifyBatch2(List<Employee> list) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comapny?characterEncoding=utf-8",
					"root", "456789");

			for (int i = 0; i < list.size(); i++) {
				Employee emp = list.get(i);
				String sql = "update employee set name =?,sex =?,age =? where id =?";

				PreparedStatement pstat = conn.prepareStatement(sql);
				pstat.setString(1, emp.getName());
				pstat.setString(2, emp.getSex());
				pstat.setInt(3, emp.getAge());
				pstat.setInt(4, emp.getId());
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
