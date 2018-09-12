package listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import util.MyWebSocket;

public class countListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("session¥¥Ω®");
		//ServletContext application = event.getSession().getServletContext();
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
		int num=0;
		if(application.getAttribute("num")!=null){
			num=(Integer)application.getAttribute("num");
		}
		num++;
		application.setAttribute("num", num);
		MyWebSocket.sendMessageAll(String.valueOf(num));
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("session ß–ß");
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
		int num=0;
		if(application.getAttribute("num")!=null){
			num=(Integer)application.getAttribute("num");
		}
		num--;
		application.setAttribute("num", num);
		MyWebSocket.sendMessageAll(String.valueOf(num));
		//application.getAttribute("num", num);	
	}

}
