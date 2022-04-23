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
import Bo.Userbo;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/inbox")
public class Chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
    		resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
     	 	
     	 	HttpSession session = req.getSession();
     	 	Userbean auth = (Userbean)session.getAttribute("auth");	
     	 	
     	 	Configbo configbo = new Configbo();			
			if(configbo.getConfig().isAllowChat() == false && auth.getType() != 2) {
				req.setAttribute("chatOff", 0);
				req.getRequestDispatcher("forumoff.jsp").forward(req, resp);
				return;
			}
     	 	
     	 	String authorId = req.getParameter("user");     	
     	 	
			if(auth != null) {		
				
				if(authorId != "" && authorId != null && Long.parseLong(authorId) != auth.getUniqueId()) {
					Userbo userbo = new Userbo();
					Userbean authorto = userbo.getUserbyUniqueId(Long.parseLong(authorId));		
					
					req.setAttribute("ibfrom", auth);
					req.setAttribute("ibto", authorto);					
					
					req.getRequestDispatcher("Chat.jsp").forward(req, resp);
				}
				else {					
					resp.sendRedirect("home");
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
