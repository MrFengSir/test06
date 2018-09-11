package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.DepProDao;
import Dao.DepartmentDao;
import entity.Department;
import entity.Project;
import util.Constant;
import util.Pagination;

public class DepartmentServlet extends HttpServlet {
	DepartmentDao depDao = new DepartmentDao();
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
		
			request.setCharacterEncoding("utf-8");

			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);
			} else if ("showAddDep".equals(type)) {
				showAddDep(request, response);
			} else if ("addDep".equals(type)) {
				addDep(request, response);
			} else if ("showModifyDep".equals(type)) {
				showModifyDep(request, response);
			} else if ("modifyDep".equals(type)) {
				modifyDep(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			} else if ("showManagePro".equals(type)) {
				showManagePro(request, response);
			} else if ("showManagePro2".equals(type)) {
				showManagePro2(request, response);
			} else if ("showManagePro3".equals(type)) {
				showManagePro3(request, response);
			} else if ("addPro".equals(type)) {
				addPro(request, response);
			} else if ("addPro2".equals(type)) {
				addPro2(request, response);
			} else if ("deletePro2Batch".equals(type)) {
				deletePro2Batch(request, response);
			} else if ("addPro2Batch".equals(type)) {
				addPro2Batch(request, response);
			} else if ("deletePro".equals(type)) {
				deletePro(request, response);
			}else if ("deletePro2".equals(type)) {
				deletePro2(request, response);
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

//	public void show(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			DepartmentDao depDao = new DepartmentDao();
//			int ye = 1;
//			if (request.getParameter("ye") != null) {
//				ye = Integer.parseInt(request.getParameter("ye"));
//			}
//			int count = depDao.searchCount();
//			Pagination p = new Pagination(ye,count,Constant.EMP_NUM_IN_PAGE,Constant.EMP_NUM_OF_PAGE);
//
//			
//
//			List<Department> list = depDao.search(p.getBegin(),Constant.EMP_NUM_IN_PAGE);
//			request.setAttribute("list", list);
//			request.setAttribute("p", p);
//			
//			request.getRequestDispatcher("WEB-INF/list.jsp").forward(request, response);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void showAddDep(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/department/addDep.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showModifyDep(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		DepartmentDao depDao = new DepartmentDao();
		Department dep = depDao.search(id);
		request.setAttribute("dep", dep);
		try {
			request.getRequestDispatcher("WEB-INF/department/modifyDep.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addDep(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		Department dep = new Department();
		dep.setName(name);
		DepartmentDao depDao = new DepartmentDao();
		depDao.add(dep);

		try {
			response.sendRedirect("dep1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addPro(HttpServletRequest request, HttpServletResponse response) {
		int proId = Integer.parseInt(request.getParameter("proId"));
		int depId= Integer.parseInt(request.getParameter("depId"));
		DepProDao dpDao = new DepProDao();
		dpDao.add(depId, proId);
		
		try {
			response.sendRedirect("dep1?type=showManagePro&id="+depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addPro2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			
			
			int depId= Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			DepProDao dpDao = new DepProDao();
			boolean flag = dpDao.add(depId, proId);
			out.print(flag);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	public void addPro2Batch(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			int depId= Integer.parseInt(request.getParameter("depId"));
			int proId = 0;
			String proIds = request.getParameter("proIds");
			proIds = proIds.substring(0,proIds.length()-1);
			DepProDao dpDao = new DepProDao();
			String[] arr =proIds.split(",");
			for(int i = 0;i <arr.length;i++) {
				proId = Integer.parseInt(arr[i]);
			boolean flag = dpDao.add(depId, proId);
			if(i==arr.length-1){
			out.print(flag);
			}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	
	
	public void deletePro2Batch(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			int depId= Integer.parseInt(request.getParameter("depId"));
			int proId = 0;
			String proIds = request.getParameter("proIds");
			proIds = proIds.substring(0,proIds.length()-1);
			DepProDao dpDao = new DepProDao();
			String[] arr =proIds.split(",");
			for(int i = 0;i <arr.length;i++) {
				proId = Integer.parseInt(arr[i]);
			boolean flag = dpDao.delete(depId, proId);
			if(i==arr.length-1){
			out.print(flag);
			}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	
	public void search(HttpServletRequest request, HttpServletResponse response) {
		
		Department condition = new Department();
		String name = request.getParameter("name");
		int emp_count = -1;
		if(request.getParameter("emp_count")!=null&&!"".equals(request.getParameter("emp_count"))){
			 emp_count = Integer.parseInt(request.getParameter("emp_count"));
		}
		
		condition.setName(name);
		condition.setEmp_count(emp_count);
		DepartmentDao depDao = new DepartmentDao();
		
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = depDao.searchCount(condition);
		if(count==0){
			count=1;
		}
		Pagination p = new Pagination(ye,count,Constant.EMP_NUM_IN_PAGE,Constant.EMP_NUM_OF_PAGE);

		List<Department> list= depDao.searchContidion(condition,p.getBegin(),Constant.EMP_NUM_IN_PAGE);
		List<Department> depList = depDao.search(); 
		request.setAttribute("depList", depList);
		request.setAttribute("list", list);
		
		request.setAttribute("p", p);
		request.setAttribute("c", condition);

		try {
			request.getRequestDispatcher("WEB-INF/department/deplist.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public void showManagePro(HttpServletRequest request, HttpServletResponse response) {
	   int id = Integer.parseInt(request.getParameter("id"));
	   DepProDao dpDao = new DepProDao();
	   List<Project> list = dpDao.search(id);
	   DepartmentDao depDao = new DepartmentDao();
	   Department dep = depDao.search(id);
	   List<Project> noHaveList = dpDao.searchNoHave(id);
	   request.setAttribute("list", list);
	   request.setAttribute("dep", dep);
	   request.setAttribute("noHaveList", noHaveList);
		
		try {
			request.getRequestDispatcher("WEB-INF/department/managePro.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


public void showManagePro3(HttpServletRequest request, HttpServletResponse response) {
	   int id = Integer.parseInt(request.getParameter("id"));
	   DepProDao dpDao = new DepProDao();
	   List<Project> list = dpDao.search(id);
	   DepartmentDao depDao = new DepartmentDao();
	   Department dep = depDao.search(id);
	   List<Project> noHaveList = dpDao.searchNoHave(id);
	   request.setAttribute("list", list);
	   request.setAttribute("dep", dep);
	   request.setAttribute("noHaveList", noHaveList);
		
		try {
			request.getRequestDispatcher("WEB-INF/department/managePro3.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void showManagePro2(HttpServletRequest request, HttpServletResponse response) {
	   int id = Integer.parseInt(request.getParameter("id"));
	   DepProDao dpDao = new DepProDao();
	   List<Project> list = dpDao.search(id);
	   DepartmentDao depDao = new DepartmentDao();
	   Department dep = depDao.search(id);
	   List<Project> noHaveList = dpDao.searchNoHave(id);
	   request.setAttribute("list", list);
	   request.setAttribute("dep", dep);
	   request.setAttribute("noHaveList", noHaveList);
		
		try {
			request.getRequestDispatcher("WEB-INF/department/managePro2.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modifyDep(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));

		Department dep = new Department();
		dep.setName(name);
		dep.setId(id);
		DepartmentDao depDao = new DepartmentDao();
		depDao.modify(dep);

		try {
			response.sendRedirect("dep1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public void delete(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		DepartmentDao depDao = new DepartmentDao();
		boolean flag = depDao.delete(id);
		try {
			response.sendRedirect("dep1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void deletePro(HttpServletRequest request, HttpServletResponse response) {
		int proId = Integer.parseInt(request.getParameter("proId"));
		int depId= Integer.parseInt(request.getParameter("depId"));
		DepProDao dpDao = new DepProDao();
		dpDao.delete(depId, proId);
		
		try {
			response.sendRedirect("dep1?type=showManagePro&id="+depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletePro2(HttpServletRequest request, HttpServletResponse response) {
		try {
		PrintWriter out = response.getWriter();
		int proId = Integer.parseInt(request.getParameter("proId"));
		int depId= Integer.parseInt(request.getParameter("depId"));
		DepProDao dpDao = new DepProDao();
		boolean flag = dpDao.delete(depId, proId);
		
		out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
