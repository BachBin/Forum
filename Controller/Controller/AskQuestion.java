package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bo.Categorybo;
import Bo.PostVMbo;
import Bo.Postbo;
import Bo.Userbo;

/**
 * Servlet implementation class AskQuestion
 */
@WebServlet("/askquestion")
public class AskQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
     	 	
     	 	String post = req.getParameter("post");
     	 	Userbo userbo = new Userbo();
    		Categorybo catebo = new Categorybo();   		
    		
    		Postbo postbo = new Postbo();
    		PostVMbo postvmbo = new PostVMbo();	 
    		
    		req.setAttribute("countQuestion", postbo.countQuestion());
    		req.setAttribute("countAnswer", postbo.countAnswer());   
    		req.setAttribute("countUser", userbo.getSLUser(-1));
    		req.setAttribute("listAdmin", userbo.getAdministrators());
    		req.setAttribute("listCate", catebo.getCategories());	    		
    		req.setAttribute("listRecentPost", postbo.getRecentPost());
    		
     	 	if(post == null || post.equals("")) {
	        	req.getRequestDispatcher("ask-question.jsp").forward(req, resp);
     	 	}
     	 	else {
     	 		req.setAttribute("mypost", postvmbo.getPostBySlug(post)); 
     	 		req.setAttribute("category", postbo.getCatebySlug(post)); 
     	 		req.getRequestDispatcher("ask-question.jsp").forward(req, resp);
     	 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
