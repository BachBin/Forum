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
import Bo.Configbo;
import Bo.Postbo;
import Bo.Userbo;

/**
 * Servlet implementation class ManagerForum
 */
@WebServlet("/managerforum")
public class ManagerForum extends HttpServlet {
	private static final long serialVersionUID = 1L;       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			HttpSession session = req.getSession();
			Userbean auth = (Userbean) session.getAttribute("auth");
			
			if(auth == null  || auth.getType() != 2) {
				resp.sendRedirect("logIn.jsp?from=home"); 
			}
			Categorybo catebo = new Categorybo();
			Userbo userbo = new Userbo();
			Postbo postbo = new Postbo();
			Configbo configbo = new Configbo();
			
			req.setAttribute("countQuestion", postbo.countQuestion());
			req.setAttribute("countAnswer", postbo.countAnswer());
			req.setAttribute("countUser", userbo.getSLUser(-1));
			
			req.setAttribute("listAdmin", userbo.getAdministrators());
			req.setAttribute("listCate", catebo.getCategories());
			req.setAttribute("listRecentPost", postbo.getRecentPost());
			
			req.setAttribute("config", configbo.getConfig());
			
			req.getRequestDispatcher("managerforum.jsp").forward(req, resp);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
