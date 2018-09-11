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

import Dao.DepartmentDao;
import Dao.ProjectDao;
import Dao.ScoreDao;
import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import util.Constant;
import util.Pagination;

public class ScoreServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {

			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");

			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);
			} else if ("manage".equals(type)) {
				manage(request, response);
			} else if ("input".equals(type)) {
				input(request, response);
			} else if ("add".equals(type)) {
				add(request, response);
			} else if ("update".equals(type)) {
				update(request, response);
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			int empId = Integer.parseInt(request.getParameter("empId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			int value = Integer.parseInt(request.getParameter("value"));
			Score sc = new Score();
			ScoreDao scDao = new ScoreDao();
			Employee emp = new Employee();
			emp.setId(empId);
			sc.setEmp(emp);
			Project pro = new Project();
			pro.setId(proId);
			sc.setPro(pro);
			sc.setValue(value);
			scDao.add(sc);
			Score score = scDao.search(empId, proId);
			String str = score.getLast();
			PrintWriter out = response.getWriter();
			out.print(str);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int value = Integer.parseInt(request.getParameter("value"));
			Score sc = new Score();
			sc.setId(id);
			sc.setValue(value);
			ScoreDao scDao = new ScoreDao();
			scDao.update(sc);
			Score score = scDao.search(id);
			String str = score.getLast();
			PrintWriter out = response.getWriter();
			out.print(str);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void input(HttpServletRequest request, HttpServletResponse response) {

	}

	private void manage(HttpServletRequest request, HttpServletResponse response) {

		try {
			basic(request, response);
			request.getRequestDispatcher("WEB-INF/score/manage.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	public void basic(HttpServletRequest request, HttpServletResponse response) {

		ScoreDao scDao = new ScoreDao();
		DepartmentDao depDao = new DepartmentDao();
		List<Department> depList = depDao.search();
		ProjectDao proDao = new ProjectDao();
		List<Project> proList = proDao.search();
		String name = request.getParameter("name");
		int depId = -1;
		if (request.getParameter("depId") != null && !"".equals(request.getParameter("depId"))) {
			depId = Integer.parseInt(request.getParameter("depId"));
		}
		int proId = -1;
		if (request.getParameter("proId") != null && !"".equals(request.getParameter("proId"))) {
			proId = Integer.parseInt(request.getParameter("proId"));
		}
		int value = -1;
		if (request.getParameter("value") != null && !"".equals(request.getParameter("value"))) {
			value = Integer.parseInt(request.getParameter("value"));
		}
		String last = request.getParameter("last");

		Score condition = new Score();
		Employee emp = new Employee();
		emp.setName(name);
		Department dep = new Department();
		dep.setId(depId);
		emp.setDep(dep);
		condition.setEmp(emp);
		Project pro = new Project();
		pro.setId(proId);
		condition.setPro(pro);
		condition.setValue(value);
		condition.setLast(last);

		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = scDao.searchCount(condition);
		if (count == 0) {
			count = 1;
		}
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Score> list = scDao.searchContidion(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);

		request.setAttribute("depList", depList);
		request.setAttribute("proList", proList);
		request.setAttribute("list", list);
		request.setAttribute("p", p);
		request.setAttribute("c", condition);
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			basic(request, response);
			request.getRequestDispatcher("WEB-INF/score/list.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
