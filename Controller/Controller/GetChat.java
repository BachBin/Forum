package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Chat;
import Bean.Userbean;
import Bo.Chatbo;
import Bo.Userbo;

/**
 * Servlet implementation class GetChat
 */
@WebServlet("/getchat")
public class GetChat extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
    		resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
	 	 	resp.setCharacterEncoding("UTF-8");
	 	 	
	 	 	PrintWriter out = resp.getWriter();
	 	 	
	 	 	HttpSession session = req.getSession();
			Userbean auth = (Userbean)session.getAttribute("auth");
			
			Long outgoing_id = auth.getUniqueId(); //nguoi gui
			Long incoming_id = Long.parseLong(req.getParameter("incoming_id")); //nguoi nhan	
			
			Userbo userbo = new Userbo();
			Userbean authto = userbo.getUserbyUniqueId(incoming_id);
			String img = authto.getImage()=="" || authto.getImage()==null ? "User-Linear-80px.png" : authto.getImage();
			
			Chatbo chatbo = new Chatbo();
			ArrayList<Chat> listchat = chatbo.getChat(outgoing_id, incoming_id);
			if(listchat.size() > 0) {
				for (Chat chat : listchat) {
					if(chat.getFromId() == outgoing_id){
						out.println("<div class=\"chat outgoing\">\r\n"
								+ "                                <div class=\"details\">\r\n"
								+ "                                    <p>"+chat.getMessage()+"</p>\r\n"
								+ "                                </div>\r\n"
								+ "                                </div>");	                   
	                }else{	                	
	                    out.println("<div class=\"chat incoming\">\r\n"
	                    		+ "                    <img src=\"images/"+img+"\" alt=\"\">\r\n"
	                    		+ "                                <div class=\"details\">\r\n"
	                    		+ "                                    <p>"+chat.getMessage()+"</p>\r\n"
	                    		+ "                                </div>\r\n"
	                    		+ "                                </div>");
	                }
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
