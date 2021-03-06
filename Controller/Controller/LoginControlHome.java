package Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import Bean.Userbean;
import Bo.Configbo;
import Bo.Userbo;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet("/loginhome")
public class LoginControlHome extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
    		resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
    		
     	 	String from = req.getParameter("from");
     	 	if(from==null || from=="") from = "home";        	 	
     	 	
     	 	
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			
			Userbo userbo = new Userbo();
			
			Userbean user = userbo.getUser(email, passwordHash);
			
			Configbo configbo = new Configbo();			
			if(configbo.getConfig().isForum() == false && user.getType() != 2 ) {
				req.setAttribute("forumOff", 0);
				req.getRequestDispatcher("forumoff.jsp").forward(req, resp);
				return;
			}
			if(configbo.getConfig().isAllowLogin() == false && user.getType() != 2 ) {
				req.setAttribute("loginOff", 0);
				req.getRequestDispatcher("forumoff.jsp").forward(req, resp);
				return;
			}
			
			HttpSession session = req.getSession();
			if(user == null) {				
				session.setAttribute("alert", "Th??ng tin ????ng nh???p kh??ng ????ng!!!");
				
				if(from.equals("askquestion"))
					resp.sendRedirect("/Forum/askquestion");
				else if(from.equals("home"))
					resp.sendRedirect("/Forum/home");
				else
					resp.sendRedirect("/Forum/detail?post="+URLEncoder.encode(from, "UTF-8"));				
			}
			else {
				Date date = new Date();
				userbo.updateStatus(user.getId(), "Online");
				userbo.updateLastLogin(new Timestamp(date.getTime()), user.getId());
				session.setAttribute("success", "????ng nh???p th??nh c??ng!!!");				
				session.setAttribute("auth", userbo.getUser(email, passwordHash));				
				
				if(from.equals("askquestion"))
					resp.sendRedirect("/Forum/askquestion");
				else if(from.equals("home"))
					resp.sendRedirect("/Forum/home");
				else
					resp.sendRedirect("/Forum/detail?post="+URLEncoder.encode(from, "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
