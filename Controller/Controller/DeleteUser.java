package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Userbean;
import Bo.Userbo;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/deleteuser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
     	 	HttpSession session = req.getSession();
    		String idu = req.getParameter("id");	
    		Userbean auth = (Userbean)session.getAttribute("auth");
    		if(auth != null) {
    			Userbo userbo = new Userbo();
    			try {
    				if(userbo.deleteUser(Long.parseLong(idu))) {
    					session.setAttribute("success", "Xóa tài khoản thành công!!!");
    					resp.sendRedirect("manageruser");
    				}				
    			} catch (Exception e) {
    				session.setAttribute("alert", "Xóa tài khoản thất bại, tài khoản đã có bài đăng!!!");
    				resp.sendRedirect("manageruser");
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
