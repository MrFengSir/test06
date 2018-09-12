package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Dao.DepartmentDao;
import Dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import net.sf.json.JSONArray;
import util.Constant;
import util.Pagination;

public class EmployeeServlet extends HttpServlet {
	DepartmentDao depDao = new DepartmentDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		//servlet 是单例模式的具体体现
		try {
		
			request.setCharacterEncoding("utf-8");

			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);
			} else if ("showAdd2".equals(type)) {
				showAdd2(request, response);
			} else if ("showAdd3".equals(type)) {
				showAdd3(request, response);
			} else if ("add".equals(type)) {
				add(request, response);
			} else if ("add2".equals(type)) {
				 add2(request, response);
			} else if ("add3".equals(type)) {
				 add3(request, response);
			} else if ("upload".equals(type)) {
				upload(request, response);
			} else if ("showModify".equals(type)) {
				showModify(request, response);
			} else if ("modify".equals(type)) {
				modify(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			} else if ("deleteBatch".equals(type)) {
				deleteBatch(request, response);
			} else if ("showModifyBatch1".equals(type)) {
				showModifyBatch1(request, response);
			} else if ("modifyBatch1".equals(type)) {
				modifyBatch1(request, response);
			} else if ("showModifyBatch2".equals(type)) {
				showModifyBatch2(request, response);
			} else if ("modifyBatch2".equals(type)) {
				modifyBatch2(request, response);
			} else if ("modifyBatch3".equals(type)) {
				modifyBatch3(request, response);
			
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add2(HttpServletRequest request, HttpServletResponse response) {
		try {
			// String path = request.getServletContext().getRealPath("")+"/pic";
			// System.out.println(path);
			String name = "";
			String sex = "";
			int age = 0;
			int depId = 0;
			String path = "e:/tu";
			String pic_name = "";
			request.setCharacterEncoding("UTF-8");
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("name")) {
					name = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
					System.out.println(name);
				} else if (item.getFieldName().equals("sex")) {
					sex = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if (item.getFieldName().equals("age")) {
					age = Integer.parseInt(item.getString());
				} else if (item.getFieldName().equals("depId")) {
					depId = Integer.parseInt(item.getString());
				} else if (item.getFieldName().equals("File")) {
				
				UUID uuid = UUID.randomUUID();
				String str = item.getName().substring(item.getName().lastIndexOf("."));
				pic_name = uuid.toString() + str;
				File savedFile = new File(path, pic_name);
				item.write(savedFile);
				}
			}
			Employee emp = new Employee();
			emp.setName(name);
			emp.setAge(age);
			Department dep = new Department();
			if (!"".equals(depId)) {
				dep.setId(depId);
			}
			emp.setDep(dep);
			emp.setSex(sex);
			emp.setPic_name(pic_name);
			EmployeeDao empDao = new EmployeeDao();
			empDao.add(emp);
			response.sendRedirect("emp1");

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {
			// String path = request.getServletContext().getRealPath("")+"/pic";
			// System.out.println(path);
			String path = "e:/tu";
			String pic_name="";
			request.setCharacterEncoding("UTF-8");
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
		     if(item.getFieldName().equals("pic_name")){
				UUID uuid = UUID.randomUUID();
				String str = item.getName().substring(item.getName().lastIndexOf("."));
				pic_name = uuid.toString() + str;
				File savedFile = new File(path, pic_name);
				item.write(savedFile);
				}
			}
		
			PrintWriter out =response.getWriter();
			out.print(pic_name);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		List<Department> depList = depDao.search();
		request.setAttribute("depList", depList);
		try {
			request.getRequestDispatcher("WEB-INF/employee/add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAdd2(HttpServletRequest request, HttpServletResponse response) {
		List<Department> depList = depDao.search();
		request.setAttribute("depList", depList);
		try {
			request.getRequestDispatcher("WEB-INF/employee/add2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showAdd3(HttpServletRequest request, HttpServletResponse response) {
		List<Department> depList = depDao.search();
		request.setAttribute("depList", depList);
		try {
			request.getRequestDispatcher("WEB-INF/employee/add3.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showModify(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		EmployeeDao empDao = new EmployeeDao();
		Employee emp = empDao.search(id);
		List<Department> depList = depDao.search();
		request.setAttribute("emp", emp);
		request.setAttribute("depList", depList);
		try {
			request.getRequestDispatcher("WEB-INF/employee/modify.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			Integer depId = null;
			if (!"".equals(request.getParameter("depId"))) {
				depId = Integer.parseInt(request.getParameter("depId"));
			}
			Department dep = new Department();
			dep.setId(depId);
			Employee emp = new Employee();
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			emp.setDep(dep);
			EmployeeDao empDao = new EmployeeDao();
			empDao.add(emp);

			response.sendRedirect("emp1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add3(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			Integer depId = null;
			if (!"".equals(request.getParameter("depId"))) {
				depId = Integer.parseInt(request.getParameter("depId"));
			}
			String pic_name = request.getParameter("photo");
			Department dep = new Department();
			dep.setId(depId);
			Employee emp = new Employee();
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			emp.setDep(dep);
			emp.setPic_name(pic_name);
			EmployeeDao empDao = new EmployeeDao();
			empDao.add(emp);

			response.sendRedirect("emp1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		List<Department> depList = depDao.search();
		Employee condition = new Employee();
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		int age = -1;
		if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
			age = Integer.parseInt(request.getParameter("age"));
		}
		String depName = request.getParameter("depName");
		Department dep = new Department();
		dep.setName(depName);

		condition.setName(name);
		condition.setSex(sex);
		condition.setAge(age);
		condition.setDep(dep);
		EmployeeDao empDao = new EmployeeDao();

		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = empDao.searchCount(condition);
		if (count == 0) {
			count = 1;
		}
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Employee> list = empDao.searchContidion(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		request.setAttribute("list", list);
		request.setAttribute("depList", depList);
		request.setAttribute("p", p);
		request.setAttribute("c", condition);

		try {
			request.getRequestDispatcher("WEB-INF/employee/list.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modify(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		int age = Integer.parseInt(request.getParameter("age"));
		Integer depId = null;
		if (!"".equals(request.getParameter("depId"))) {
			depId = Integer.parseInt(request.getParameter("depId"));
		}
		Employee emp = new Employee();
		emp.setName(name);
		emp.setSex(sex);
		emp.setAge(age);
		emp.setId(id);
		Department dep = new Department();
		dep.setId(depId);
		emp.setDep(dep);
		EmployeeDao empDao = new EmployeeDao();
		empDao.modify(emp);

		try {
			response.sendRedirect("emp1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modifyBatch1(HttpServletRequest request, HttpServletResponse response) {

		String ids = request.getParameter("ids");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		int age = Integer.parseInt(request.getParameter("age"));
		Employee emp = new Employee();
		emp.setName(name);
		emp.setSex(sex);
		emp.setAge(age);
		EmployeeDao empDao = new EmployeeDao();
		empDao.modifyBatch1(ids, emp);

		try {
			response.sendRedirect("emp1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modifyBatch2(HttpServletRequest request, HttpServletResponse response) {
		String emps = request.getParameter("emps");
		String[] arr = emps.split(";");
		List<Employee> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			Employee emp = new Employee();
			String[] temp = arr[i].split(",");
			emp.setId(Integer.parseInt(temp[0]));
			emp.setName(temp[1]);
			emp.setSex(temp[2]);
			emp.setAge(Integer.parseInt(temp[3]));
			list.add(emp);
		}
		EmployeeDao empDao = new EmployeeDao();
		empDao.modifyBatch2(list);

		try {
			response.sendRedirect("emp1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modifyBatch3(HttpServletRequest request, HttpServletResponse response) {
		String emps = request.getParameter("emps");
		JSONArray jsonArray = JSONArray.fromObject(emps);
		List<Employee> list = (List<Employee>) jsonArray.toCollection(jsonArray, Employee.class);

		EmployeeDao empDao = new EmployeeDao();
		empDao.modifyBatch2(list);

		try {
			response.sendRedirect("emp1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showModifyBatch1(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		EmployeeDao empDao = new EmployeeDao();
		List<Employee> list = empDao.search(ids);
		request.setAttribute("emp", list.get(0));
		request.setAttribute("ids", ids);
		try {
			request.getRequestDispatcher("WEB-INF/employee/showModifyBatch1.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showModifyBatch2(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		EmployeeDao empDao = new EmployeeDao();
		List<Employee> list = empDao.search(ids);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("WEB-INF/employee/showModifyBatch2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		EmployeeDao empDao = new EmployeeDao();
		boolean flag = empDao.delete(id);
		try {
			response.sendRedirect("emp1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteBatch(HttpServletRequest request, HttpServletResponse response) {

		String ids = request.getParameter("ids");
		EmployeeDao empDao = new EmployeeDao();
		boolean flag = empDao.deleteBatch(ids);
		try {
			response.sendRedirect("emp1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
