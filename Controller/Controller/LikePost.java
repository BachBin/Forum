package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;

import Bean.Userbean;
import Bo.PostLikebo;

/**
 * Servlet implementation class LikePost
 */
@WebServlet("/likepost")
public class LikePost extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("application/json");
			req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
     	 	
     	 	PrintWriter out = resp.getWriter();
    		ArrayList<String> res = new ArrayList<String>();    		
     	 	
    		resp.setContentType("text/html");
    		HttpSession session = req.getSession();
    		Userbean auth = (Userbean)session.getAttribute("auth");	
    		if(auth == null) {
    			res.add("Bạn chưa đăng nhập.");
    			String json = new Gson().toJson(res);
    			out.print(json);
    			out.close();
    			return;
    		}
    		
    		Long idauthor = auth.getId();
			Long idpost = Long.parseLong(req.getParameter("idp"));
			
			PostLikebo plikebo = new PostLikebo();			
			
			int isLike =  plikebo.getIdLike(idpost, idauthor);
			
			if(isLike == 2) {
				plikebo.deleteisLike(idauthor, idpost);					
			}			
			else {				
				plikebo.deleteisLike(idauthor, idpost);		
				plikebo.updateIdentity();
				plikebo.isLike(idauthor, idpost, 2);
			}
						
			res.add("success");
			long solike = plikebo.soLikePost(idpost);
			long sodislike = plikebo.soDislikePost(idpost);
			res.add(String.valueOf(solike));	
			res.add(String.valueOf(sodislike));
			String json = new Gson().toJson(res);
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
