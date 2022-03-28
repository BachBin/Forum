package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;


import Bean.Userbean;
import Bo.Userbo;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/adduser")
public class AddUser extends HttpServlet {
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
				
				String email = req.getParameter("email");
				String password  = req.getParameter("password");
				String utype = req.getParameter("userType");				
					
				
				if(email!=null && !email.equals("") && password!=null && !password.equals("")) {
					Userbo userbo = new Userbo();
					for(Userbean c : userbo.getUsers()) {
						if(c.getEmail().equals(email)) {
							res.add("alert");
							res.add("Email đã tồn tại!!!");
							
							String json = new Gson().toJson(res);
							out.print(json);
							out.close();
							return;
						}						
					}
					MessageDigest md = MessageDigest.getInstance("MD5");
	    			md.update(password.getBytes());
	    		    byte[] digest = md.digest();
	    		    String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase(); 
	    		    
	    		    if(utype.equals("2") && auth.getType() != 2) {
	    		    	res.add("alert");
						res.add("Thêm tài khoản thất bại!!!");
						
						String json = new Gson().toJson(res);
						out.print(json);
						out.close();
						return;
	    		    }
	    		    
	    		    if(userbo.createUser(email,passwordHash, Integer.parseInt(utype))) {    		    				
	    		    	res.add("success");
						res.add("Thêm tài khoản thành công!!!");					
						
						String json = new Gson().toJson(res);
						out.print(json);		
						out.close();	    				
	    		    }
	    		    else {
	    		    	res.add("alert");
						res.add("Thêm tài khoản thất bại!!!");
						
						String json = new Gson().toJson(res);
						out.print(json);
						out.close();
	    		    }
					
				}
				else {
					res.add("alert");
					res.add("Nhập đầy đủ thông tin!!!");
					
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
