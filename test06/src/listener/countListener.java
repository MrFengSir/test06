package listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class countListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		ServletContext application = event.getSession().getServletContext();
		int num=0;
		if(application.getAttribute("num")!=null){
			num=(Integer)application.getAttribute("num");
		}
		num++;
		application.setAttribute("num", num);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		ServletContext application = event.getSession().getServletContext();
	
		//application.getAttribute("num", num);	
	}

}
