package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Postbean;
import Bean.Userbean;
import Bo.Categorybo;
import Bo.Commentbo;
import Bo.PostLikebo;
import Bo.PostVMbo;
import Bo.Postbo;
import Bo.Userbo;

/**
 * Servlet implementation class Detail
 */
@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;     
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
    		resp.setContentType("text/html;charset=UTF-8");
    		req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
     	 	String slug = req.getParameter("post");     	 	
        	HttpSession session = req.getSession();
    		Userbean auth = (Userbean)session.getAttribute("auth");	
        	if(slug!=null && !slug.equals("")) {
        		Userbo userbo = new Userbo();
        		Categorybo catebo = new Categorybo();   		
        		
        		Postbo postbo = new Postbo();
        		PostVMbo postvmbo = new PostVMbo();	
        		Commentbo cmtbo = new Commentbo();    		
        		PostLikebo postlike = new PostLikebo();
        		
        		req.setAttribute("countQuestion", postbo.countQuestion());
        		req.setAttribute("countAnswer", postbo.countAnswer());   
        		req.setAttribute("countUser", userbo.getSLUser(-1));
        		
        		req.setAttribute("listInvolve", postbo.getInvolvePost(slug));
        		req.setAttribute("listAdmin", userbo.getAdministrators());
        		req.setAttribute("listCate", catebo.getCategories());        		
        		req.setAttribute("listRecentPost", postbo.getRecentPost());        	
            	
            	Postbean post = postvmbo.getPostBySlug(slug);            	
            	postbo.subView(post.getId());
            	req.setAttribute("author", userbo.getUserbyId(post.getAuthorId()));
            	req.setAttribute("post", post);
            	req.setAttribute("solike", postlike.soLikePost(post.getId()));
            	req.setAttribute("sodislike", postlike.soDislikePost(post.getId()));
            	
            	if(auth != null ) {
            		boolean checkLike = postlike.getIdLikeList(post.getId()).contains(auth.getId())? true : false;
                	boolean checkDislike = postlike.getIdDislikeList(post.getId()).contains(auth.getId())? true : false;
                	
                	req.setAttribute("dalike", checkLike);
                	req.setAttribute("dadislike", checkDislike);
            	}            	
            	
            	req.setAttribute("listComment", cmtbo.getComment(post.getId()));            	
            	req.getRequestDispatcher("post-details.jsp").forward(req, resp);
        	}
        	else {
        		resp.sendRedirect("home");
        	}    	
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    }
}
