package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Userbean;
import Bo.Categorybo;

/**
 * Servlet implementation class DeleteCategories
 */
@WebServlet("/deletecategories")
public class DeleteCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
     	 	HttpSession session = req.getSession();
    		String idc = req.getParameter("id");	
    		Userbean auth = (Userbean)session.getAttribute("auth");
    		if(auth != null) {
    			Categorybo catebo = new Categorybo();
    			try {
    				if(catebo.deleteCategory(Long.parseLong(idc))) {
    					session.setAttribute("success", "Xóa loại thành công!!!");
    					resp.sendRedirect("managercategory");
    				}				
    			} catch (Exception e) {
    				session.setAttribute("alert", "Xóa loại thất bại, loại đã có bài đăng!!!");
    				resp.sendRedirect("managercategory");
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
