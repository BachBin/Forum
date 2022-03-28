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

import Bean.Commentbean;
import Bean.Userbean;
import Bo.Commentbo;
import Bo.Postbo;

/**
 * Servlet implementation class DisLikeComment
 */
@WebServlet("/dislikecmt")
public class DisLikeComment extends HttpServlet {
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
			Long idcmt = Long.parseLong(req.getParameter("idcmt"));
			String idpost = req.getParameter("idpost");		
			
			Commentbo cmtbo = new Commentbo();	
			Postbo pbo = new Postbo();
			
			Commentbean cmthanlde = cmtbo.getCommentByIdCmt(idcmt);
			if(cmthanlde.getAuthorId() == auth.getId()) {
				return;
			}
			
			int isLike =  cmtbo.getIdLike(idauthor, idcmt);
			
			if(isLike == 1) {
				cmtbo.deleteisLike(idauthor, idcmt);					
			}			
			else {				
				cmtbo.deleteisLike(idauthor, idcmt);		
				cmtbo.updateIdentity();
				cmtbo.isLikeCmt(idauthor, idcmt, 1);
			}			
						
			res.add("success");
			long solike = cmtbo.soLikeCmt(idcmt);
			long sodislike = cmtbo.soDislikeCmt(idcmt);
			res.add(String.valueOf(solike));	
			res.add(String.valueOf(sodislike));
			if(cmtbo.getSLCmt(Long.parseLong(idpost)) == 0 || (cmtbo.getSLCmt(Long.parseLong(idpost)) >= 1 && cmtbo.getSLLikeAllCmt(Long.parseLong(idpost)) == 0)) 
			{
				pbo.updateSolved(Long.parseLong(idpost), false);
				res.add("ok");
			}
			else {
				pbo.updateSolved(Long.parseLong(idpost), true);
				res.add("nok");
			}
			String json = new Gson().toJson(res);
			out.print(json);
			out.close(); 		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
