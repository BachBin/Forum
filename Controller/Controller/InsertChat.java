package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Userbean;
import Bo.Chatbo;

/**
 * Servlet implementation class InsertChat
 */
@WebServlet("/insertchat")
@MultipartConfig
public class InsertChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
	 	 	resp.setCharacterEncoding("UTF-8");
	 	 	
	 	 	HttpSession session = req.getSession();
			Userbean auth = (Userbean)session.getAttribute("auth");
			
			Long outgoing_id = auth.getUniqueId(); //nguoi gui
			Long incoming_id = Long.parseLong(req.getParameter("incoming_id")); //nguoi nhan			
			String message = req.getParameter("message");		
			
			
			if(message != "" && message != null) {
				Chatbo chatbo = new Chatbo();
				chatbo.insertChat(outgoing_id, incoming_id, message);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
