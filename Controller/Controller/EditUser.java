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
import Bo.Userbo;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/edituser")
public class EditUser extends HttpServlet {
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
				
				String uid = req.getParameter("uid");

				String firstname = req.getParameter("firstname");
				String middlename = req.getParameter("middlename");
				String lastname = req.getParameter("lastname");			
				String mobile = req.getParameter("mobile");
				String email = req.getParameter("email");
				String intro = req.getParameter("intro");
				String profile = req.getParameter("profile");				
				String utype = req.getParameter("userType");				
					
				
				if(email!=null && !email.equals("")) {
					Userbo userbo = new Userbo();
					for(Userbean c : userbo.getUsers()) {
						if(c.getEmail().equals(email) && c.getId() != Long.parseLong(uid)) {
							res.add("alert");
							res.add("Email đã tồn tại!!!");
							
							String json = new Gson().toJson(res);
							out.print(json);
							out.close();
							return;
						}						
					}					
	    		    Userbean userupdate = userbo.getUserbyId(Long.parseLong(uid));
	    		    userupdate.setFirstName(firstname);
	    		    userupdate.setMiddleName(middlename);
	    		    userupdate.setLastName(lastname);
	    		    userupdate.setMobile(mobile);
	    		    userupdate.setEmail(email);
	    		    userupdate.setIntro(intro);
	    		    userupdate.setProfile(profile);
	    		    userupdate.setType(Integer.parseInt(utype));
	    		    if(userbo.updateUser(userupdate)) {    		    				
	    		    	res.add("success");
						res.add("Cập nhật tài khoản thành công!!!");					
						
						String json = new Gson().toJson(res);
						out.print(json);		
						out.close();	    				
	    		    }
	    		    else {
	    		    	res.add("alert");
						res.add("Cập nhật tài khoản thất bại!!!");
						
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
