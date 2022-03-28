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
import Bo.Commentbo;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("application/json");
    		req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
     	 	
     	 	HttpSession session = req.getSession();
     	 	Userbean auth = (Userbean)session.getAttribute("auth");	
     	 	
			String idp = req.getParameter("idp");
			Long idu = auth.getId();			
			String content = req.getParameter("content");
			
			PrintWriter out = resp.getWriter();
			ArrayList<String> res = new ArrayList<String>();
			
			if(auth != null) {		
				if(content==null || content.equals("")) {
					res.add("alert");
					res.add("Nhập bình luận của bạn!!!");
					String json = new Gson().toJson(res);
					out.print(json);
					out.close();
				}
				Commentbo cmtbo = new Commentbo();
				if(cmtbo.createCmt(Long.parseLong(idp), idu, content)) {
					res.add("success");
					res.add("Đã đăng bình luận!!!");				
				}
				else {
					res.add("alert");
					res.add("Bình luận thất bại!!!");										
				}	
				String json = new Gson().toJson(res);
				out.print(json);
				out.close();
			}
			else {
				res.add("alert");
				res.add("Vui lòng đăng nhập!!!");		
				String json = new Gson().toJson(res);
				out.print(json);
				out.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
