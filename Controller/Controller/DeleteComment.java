package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Bo.Commentbo;
import Bo.Postbo;

/**
 * Servlet implementation class DeleteComment
 */
@WebServlet("/deletecomment")
public class DeleteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
    		resp.setContentType("application/json");
    		req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
     	 	
     	 	ArrayList<String> res = new ArrayList<String>();
     	 	PrintWriter out = resp.getWriter();
     	 	
     	 	String idcmt = req.getParameter("idc");	
     	 	String idpost = req.getParameter("idpost");
			Commentbo cmtbo = new Commentbo();
			Postbo pbo = new Postbo();
			
			if(cmtbo.deleteCmt(Long.parseLong(idcmt))) {				
				res.add("success");
				res.add("Đã xóa bình luận!!!");
			}		
			if(cmtbo.getSLCmt(Long.parseLong(idpost)) == 0 || (cmtbo.getSLCmt(Long.parseLong(idpost)) >= 1 && cmtbo.getSLLikeAllCmt(Long.parseLong(idpost)) == 0)) 
			{					
				pbo.updateSolved(Long.parseLong(idpost), false);
			}
			String json = new Gson().toJson(res);
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
