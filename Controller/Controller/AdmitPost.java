package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Userbean;
import Bo.Postbo;


/**
 * Servlet implementation class AdmitPost
 */
@WebServlet("/admit")
public class AdmitPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	try {	    		
	    		req.setCharacterEncoding("UTF-8");
	     	 	resp.setCharacterEncoding("UTF-8");
	    		HttpSession session = req.getSession();
				String post = req.getParameter("post");				
				Userbean auth = (Userbean)session.getAttribute("auth");
				if(auth != null) {
					Postbo postbo = new Postbo();
					if(postbo.admitPost(post)) {
						session.setAttribute("success", "Duyệt bài đăng thành công!");
						resp.sendRedirect("managerpost");
					}
					else {
						session.setAttribute("alert", "Duyệt bài đăng thất bại!");
						resp.sendRedirect("managerpost");
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
