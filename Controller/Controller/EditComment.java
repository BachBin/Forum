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
import Bo.Reportbo;

/**
 * Servlet implementation class EditComment
 */
@WebServlet("/editcmt")
public class EditComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
    		resp.setContentType("application/json");
			req.setCharacterEncoding("UTF-8");
	 	 	resp.setCharacterEncoding("UTF-8");
			HttpSession session = req.getSession();
			Userbean auth = (Userbean)session.getAttribute("auth");	
			PrintWriter out = resp.getWriter();
			ArrayList<String> res = new ArrayList<String>();
			if(auth != null ) {
				
				String cmtid = req.getParameter("cmtid");
				String content = req.getParameter("cmtcontent");
				
				if(content!=null && !content.equals("")) {
					Commentbo cmtbo = new Commentbo();
					if(cmtbo.updateCmt(Long.parseLong(cmtid), content)) {					
						res.add("success");
						res.add("Cập nhật bình luận thành công!!!");
						
						String json = new Gson().toJson(res);
						out.print(json);		
						out.close();					
					}
					else {
						res.add("alert");
						res.add("Cập nhật bình luận thất bại!!!");
						
						String json = new Gson().toJson(res);
						out.print(json);
						out.close();
					}
				}
				else {
					res.add("alert");
					res.add("Nhập nội dung bình luận!!!");
					
					String json = new Gson().toJson(res);
					out.print(json);
					out.close();				
				}
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
