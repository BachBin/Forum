package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Userbean;
import Bo.Configbo;

/**
 * Servlet implementation class HandleConfig
 */
@WebServlet("/handleconfig")
public class HandleConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {			
			resp.setContentType("application/json");
    		req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
     	 	
     	 	HttpSession session = req.getSession();
     	 	Userbean auth = (Userbean)session.getAttribute("auth");	
     	 	
     	 	
     	 	String isLogin = req.getParameter("isLogin");
     	 	String isRegistry = req.getParameter("isRegistry");
     	 	String isPost = req.getParameter("isPost");
     	 	String isChat = req.getParameter("isChat");
     	 	String isForum = req.getParameter("isForum");
     	 	
     	 	if(auth != null) {
     	 		Configbo configbo = new Configbo();
     	 		if(isLogin != null) {
     	 			configbo.updateConfig(Integer.parseInt(isLogin) != 0, 0);
     	 		}
     	 		if(isRegistry != null) {
     	 			configbo.updateConfig(Integer.parseInt(isRegistry) != 0, 1);
     	 		}
     	 		if(isPost != null) {
     	 			configbo.updateConfig(Integer.parseInt(isPost) != 0, 2);
     	 		}
     	 		if(isChat != null) {
     	 			configbo.updateConfig(Integer.parseInt(isChat) != 0, 3);
     	 		}
     	 		if(isForum != null) {
     	 			configbo.updateConfig(Integer.parseInt(isForum) != 0, 4);
     	 		}    	      	 		
     	 	}
     	 	else {
     	 		resp.sendRedirect("logIn.jsp?from=home");
     	 	}
     	 	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
