package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UserDao;
import util.RandomNumber;
import util.ValidateCode;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String type = request.getParameter("type");
			if (type == null) {
				showLogin(request, response);
			} else if ("doLogin".equals(type)) {
				doLogin(request, response);
			} else if ("randomImage".equals(type)) {
				randomImage(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			HttpSession session = request.getSession();
			String usename = request.getParameter("usename");
			String code = request.getParameter("code");
			String random = request.getParameter("random");
			if (random.equals(session.getAttribute("random"))) {
				UserDao ud = new UserDao();
				boolean flag = ud.search(usename, code);
				session.setAttribute("user", usename);
				session.setAttribute("random", random);
				Cookie cookie = new Cookie("username", usename);
				cookie.setMaxAge(70);
				response.addCookie(cookie);
				PrintWriter out = response.getWriter();
				out.print(flag);
			} else {
				request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			Cookie[] cookies = request.getCookies();
			String name = "";
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if ("username".equals(cookies[i].getName())) {
						name = cookies[i].getValue();
					}
				}
			}
			request.setAttribute("name", name);
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void randomImage(HttpServletRequest request, HttpServletResponse response) {
		RandomNumber rn = new RandomNumber();
		try {
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			ValidateCode vc = rn.generateImage();
			ServletOutputStream outStream = response.getOutputStream();
			// 输出图像到页面
			ImageIO.write(vc.getImage(), "JPEG", outStream);
			outStream.close();
			request.getSession().setAttribute("random", vc.getRand());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
