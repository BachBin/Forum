package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bo.Reportbo;

/**
 * Servlet implementation class GetReport
 */
@WebServlet("/getReport")
public class GetReport extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		try {	
    			resp.setContentType("text/html");
    			req.setCharacterEncoding("UTF-8");
    	 	 	resp.setCharacterEncoding("UTF-8");    			
    			PrintWriter out = resp.getWriter();
    			String slug = req.getParameter("slug");    			
    			Reportbo rp = new Reportbo();    
    			
    			long count = 1;
    			for(String it : rp.getReportContent(slug)) {
    				out.println("<p>"+(count++)+". "+it+"</p>");
    			}
    			
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
}
