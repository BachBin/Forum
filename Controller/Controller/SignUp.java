package Controller;

import java.io.IOException;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import Bean.Userbean;
import Bo.Configbo;
import Bo.Userbo;
import Captcha.VerifyRecaptcha;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
     	 	
     	 	HttpSession session = req.getSession();
     	 	Userbean auth = (Userbean)session.getAttribute("auth");
     	 	
     	 	Configbo configbo = new Configbo();			
			if(configbo.getConfig().isAllowRegistry() == false && (auth == null || auth.getType() != 2)) {
				req.setAttribute("registryOff", 0);
				req.getRequestDispatcher("forumoff.jsp").forward(req, resp);
				return;
			}
     	 	
     	 	String email = req.getParameter("email");
     	 	String maxacnhan = req.getParameter("maxacnhan");
			String password = req.getParameter("password");
			String repassword = req.getParameter("repassword");
			String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
			
			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);			
			
			if(maxacnhan.equals("") || maxacnhan == null) {
				session.setAttribute("username1", email);
				session.setAttribute("alert", "Nhập mã xác nhận gửi về email!!!");
				resp.sendRedirect("signup.jsp");
				return;
			}
			if(!password.equals(repassword)) {
				session.setAttribute("username1", email);
				session.setAttribute("alert", "Kiểm tra lại mật khẩu!!!");
				resp.sendRedirect("signup.jsp");
				return;
			}				
			Userbo userbo = new Userbo();
			for(Userbean u:userbo.getUsers()) {
				if(u.getEmail().equals(email)) {
					session.setAttribute("alert", "Tài khoản đã tồn tại!!!");
					resp.sendRedirect("signup.jsp");
					return;
				}					
			}
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
		    byte[] digest = md.digest();
		    String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		    
		    if(verify==false) {
				session.setAttribute("username1", email);
				session.setAttribute("password1", password);
				session.setAttribute("repassword1", repassword);
				session.setAttribute("alert", "Vui lòng xác minh lại!!!");
				resp.sendRedirect("signup.jsp");
			}
		    else {
		    	int codeSession = (int)session.getAttribute("codeRegistry");         	 	
		    	if(codeSession == Integer.parseInt(maxacnhan)) {
		    		if(userbo.createUser(email, passwordHash, 0)) {
			    		session.setAttribute("username", email);
						session.setAttribute("password", password);			    	
						resp.sendRedirect("logIn.jsp?from=home");
				    }
		    	}
		    	else {
		    		session.setAttribute("username1", email);
		    		session.setAttribute("alert", "Mã xác nhận không đúng!!!");
					resp.sendRedirect("signup.jsp");
		    	}
		    }		    
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
