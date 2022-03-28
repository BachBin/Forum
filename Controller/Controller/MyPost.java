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
import Bo.PostVMbo;
import Bo.Postbo;
import Bo.Userbo;

/**
 * Servlet implementation class MyPost
 */
@WebServlet("/mypost")
public class MyPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
	 	 	resp.setCharacterEncoding("UTF-8");
	 	 	
			HttpSession session = req.getSession();
			Userbean auth = (Userbean)session.getAttribute("auth");
			if(auth != null) {			
				long sobai = 10;
				
				String indexPage = req.getParameter("page");
				if(indexPage==null) {
					indexPage = "1";
				}
				long index = Long.parseLong(indexPage);
				
				String txtSearch = req.getParameter("searchmp") != null ? req.getParameter("searchmp") : "";
				String slType = req.getParameter("type") != null ? req.getParameter("type") : "-1";
				
				Categorybo catebo = new Categorybo();
				Userbo userbo = new Userbo();			
				Postbo postbo = new Postbo();
				PostVMbo postvmbo = new PostVMbo();	
				
				req.setAttribute("countQuestion", postbo.countQuestion());
				req.setAttribute("countAnswer", postbo.countAnswer());
				req.setAttribute("countUser", userbo.getSLUser(-1));
				
				req.setAttribute("listAdmin", userbo.getAdministrators());
				req.setAttribute("listCate", catebo.getCategories());			
				req.setAttribute("listRecentPost", postbo.getRecentPost());
				
				
				long count = postvmbo.getSLMyPost(auth.getId(), Integer.parseInt(slType), txtSearch);
				long endPage = count/sobai;
				if(count % sobai != 0) {
					endPage++;
				}
				req.setAttribute("endP", endPage);	
				req.setAttribute("tag", index);
				req.setAttribute("listMyPost", postvmbo.getMyPost(index, sobai, auth.getId(), Integer.parseInt(slType), txtSearch)); 
				req.getRequestDispatcher("mypost.jsp").forward(req, resp);
			}
			else {
				resp.sendRedirect("logIn.jsp?from=home");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
