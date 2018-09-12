package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ProjectDao;
import entity.Project;
import net.sf.json.JSONArray;
import util.Constant;
import util.Pagination;

public class ProjectServlet extends HttpServlet {
	ProjectDao proDao = new ProjectDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {

			request.setCharacterEncoding("utf-8");

			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);
			} else if ("add".equals(type)) {
				add(request, response);
			} else if ("showModify".equals(type)) {
				showModify(request, response);
			} else if ("modify".equals(type)) {
				modify(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
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

	// public void show(HttpServletRequest request, HttpServletResponse response) {
	// try {
	// ProjectDao proDao = new ProjectDao();
	// int ye = 1;
	// if (request.getParameter("ye") != null) {
	// ye = Integer.parseInt(request.getParameter("ye"));
	// }
	// int count = proDao.searchCount();
	// Pagination p = new
	// Pagination(ye,count,Constant.EMP_NUM_IN_PAGE,Constant.EMP_NUM_OF_PAGE);
	//
	//
	//
	// List<Project> list = proDao.search(p.getBegin(),Constant.EMP_NUM_IN_PAGE);
	// request.setAttribute("list", list);
	// request.setAttribute("p", p);
	//
	// request.getRequestDispatcher("WEB-INF/list.jsp").forward(request, response);
	// } catch (ServletException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	//
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/project/add.jsp").forward(request, response);
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
		ProjectDao proDao = new ProjectDao();
		Project pro = proDao.search(id);
		request.setAttribute("pro", pro);
		try {
			request.getRequestDispatcher("WEB-INF/project/modify.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		Project pro = new Project();
		pro.setName(name);
		ProjectDao proDao = new ProjectDao();
		proDao.add(pro);

		try {
			response.sendRedirect("pro1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {

		Project condition = new Project();
		String name = request.getParameter("name");
		condition.setName(name);
		ProjectDao proDao = new ProjectDao();

		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = proDao.searchCount(condition);
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);

		List<Project> list = proDao.searchContidion(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		List<Project> proList = proDao.search();
		request.setAttribute("proList", proList);
		request.setAttribute("list", list);

		request.setAttribute("p", p);
		request.setAttribute("c", condition);

		try {
			request.getRequestDispatcher("WEB-INF/project/list.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modify(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));

		Project pro = new Project();
		pro.setName(name);
		pro.setId(id);
		ProjectDao proDao = new ProjectDao();
		proDao.modify(pro);

		try {
			response.sendRedirect("pro1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		ProjectDao proDao = new ProjectDao();
		boolean flag = proDao.delete(id);
		try {
			response.sendRedirect("pro1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
