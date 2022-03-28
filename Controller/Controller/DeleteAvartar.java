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
 * Servlet implementation class DeleteAvartar
 */
@WebServlet("/delavartar")
public class DeleteAvartar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
     	 	req.setCharacterEncoding("utf-8");
     	 	resp.setCharacterEncoding("utf-8");
     	 	
     	 	String id = req.getParameter("id");
     	 	Userbo userbo = new Userbo();
     	 	HttpSession session = req.getSession();
     	 	if(userbo.deleteImage(Long.parseLong(id))) {
     	 		Userbean user = (Userbean)session.getAttribute("auth");
     	 		user.setImage(null);
     	 		session.setAttribute("auth", user);
     	 		session.setAttribute("success", "Xóa ảnh thành công!!!");	
				resp.sendRedirect("InforUser.jsp");
     	 	}
     	 	else {
     	 		session.setAttribute("alert", "Xóa ảnh thất bại!!!");
		    	resp.sendRedirect("InforUser.jsp");
     	 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
