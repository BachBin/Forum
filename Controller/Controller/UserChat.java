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

import Bean.Chat;
import Bean.Userbean;
import Bo.Chatbo;
import Bo.Userbo;

/**
 * Servlet implementation class UserChat
 */
@WebServlet("/userchat")
public class UserChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {			
    		resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
	 	 	resp.setCharacterEncoding("UTF-8");	 	 	
	 	 	
	 	 	HttpSession session = req.getSession();
			Userbean auth = (Userbean)session.getAttribute("auth");
			if(auth == null) {
				resp.sendRedirect("logIn.jsp?from=home");
				return;
			}
	 	 	
	 	 	PrintWriter out = resp.getWriter();
	 	 	
	 	 	Userbo userbo = new Userbo();	
	 	 	Chatbo chatbo = new Chatbo();
	 	 	
	 	 	ArrayList<Userbean> listUser = userbo.getUsers();
	 	 	if(listUser.size() == 0) {
	 	 		out.println("Không có người dùng có thể trò chuyện");
	 	 	}
	 	 	else {
	 	 		for (Userbean u : listUser) {	
					if(u.getId() != auth.getId()) {
						Chat chat = chatbo.getOneChat(u.getUniqueId(), auth.getUniqueId());
						
						String mess = "";
						if(chat.getMessage() != null && chat.getMessage() != "") {
							 mess = chat.getMessage();			 	 			
							 mess = chat.getMessage().length() > 18 ? mess.substring(0, 18)+"..." : mess;	 
						}	
						else {
							mess = "Chưa có tin nhắn nào";
						}
						String ibyou = auth.getUniqueId() == chat.getFromId() ? "You: " : "";
						
						String status = u.getStatus().equals("Offline") ? "offline" : "";
		 	 			
		 	 			String img = u.getImage()=="" || u.getImage()==null ? "User-Linear-80px.png" : u.getImage();
		 	 			String name = u.getFirstName() != "" && u.getFirstName() != null  
		            			|| u.getMiddleName() != "" && u.getMiddleName() != null
		            			|| u.getLastName() != "" && u.getLastName() != null
		            			? u.getFirstName() +" "+ u.getMiddleName() +" "+ u.getLastName() : u.getEmail();
		 	 			out.println("<a href=\"inbox?user="+u.getUniqueId()+"\">\r\n"
								+ "                    <div class=\"content\">\r\n"
								+ "                    <img src=\"images/"+img+"\" alt=\"\">\r\n"
								+ "                    <div class=\"details\">\r\n"
								+ "                        <span>"+name+"</span>\r\n"
								+ "                        <p>"+ibyou+mess+"</p>\r\n"
								+ "                    </div>\r\n"
								+ "                    </div>\r\n"
								+ "                    <div class=\"status-dot "+status+"\"><i class=\"fas fa-circle\"></i></div>\r\n"
								+ "                </a>");
					}		 	 			
				}
	 	 	}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
