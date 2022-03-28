package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Postbean;
import Bo.Postbo;

/**
 * Servlet implementation class GetAllPostByCategory
 */
@WebServlet("/getpostbycategory")
public class GetAllPostByCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
    		resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
     	 	
     	 	String id = req.getParameter("uid");
     	 	
     	 	Postbo postbo = new Postbo();
     	 	ArrayList<Postbean> list = postbo.getAllPostByCategory(Long.parseLong(id));
     	 	
     	 	PrintWriter out = resp.getWriter();
     	 	if(list.size() > 0) {
     	 		long count = 1;
     	 		for (Postbean p : list) {
     	 			out.println("<tr>\r\n"
     	 					+ "											<td>"+count+"</td>\r\n"
         	 				+ "											<td>"+p.getTitle()+"</td>\r\n"
         	 				+ "											<td>\r\n"
         	 				+ "												<a href=\"askquestion?post="+p.getSlug()+"\"><i class=\"fa fa-edit\" aria-hidden=\"true\"></i></a>\r\n"
         	 				+ "												<a href=\"deletepost?post="+p.getSlug()+"\" onclick=\"return confirm('Bạn có chắc muốn xóa bài đăng?');\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></a>\r\n"
         	 				+ "											</td>\r\n"
         	 				+ "										</tr>");
				count++;
     	 		}
     	 	}
     	 	else {
     	 		out.println("<tr>\r\n"
     	 				+ "											<td></td>\r\n"
     	 				+ "											<td>Chưa có bài đăng</td>\r\n"
     	 				+ "											<td></td>											\r\n"
     	 				+ "										</tr>");
     	 	}
     	 	out.close();     	 	
     	 	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
