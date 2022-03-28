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
import Bo.Reportbo;

/**
 * Servlet implementation class ReportPost
 */
@WebServlet("/report")
public class ReportPost extends HttpServlet {
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
				
				String pid = req.getParameter("pid");
				String content = req.getParameter("content");			
				
				
				if(content!=null && !content.equals("")) {
					Reportbo reportbo = new Reportbo();
					if(reportbo.reportPost(Long.parseLong(pid),content)) {					
						res.add("success");
						res.add("Tố cáo bài đăng thành công!!!");
						
						String json = new Gson().toJson(res);
						out.print(json);		
						out.close();					
					}
					else {
						res.add("alert");
						res.add("Tố cáo bài đăng thất bại!!!");
						
						String json = new Gson().toJson(res);
						out.print(json);
						out.close();
					}
				}
				else {
					res.add("alert");
					res.add("Nhập nội dung tố cáo!!!");
					
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
