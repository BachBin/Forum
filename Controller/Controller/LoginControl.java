package Controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import Bean.Userbean;
import Bo.Userbo;
import Captcha.VerifyRecaptcha;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet("/login")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {     
    		resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
     	 	resp.setCharacterEncoding("UTF-8");
     	 	
     	 	String from = req.getParameter("from");
     	 	if(from==null) from = "home";
     	 	
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
			
			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			
			Userbo userbo = new Userbo();
			Userbean user = userbo.getUser(email, passwordHash);
			HttpSession session = req.getSession();
			if(user == null) {				
				session.setAttribute("alert", "Thông tin đăng nhập không đúng!!!");
				resp.sendRedirect("logIn.jsp?from=home");
			}
			else {
				if(verify==false) {
					session.setAttribute("username", email);
					session.setAttribute("password", password);
					session.setAttribute("alert", "Vui lòng xác minh lại!!!");
					resp.sendRedirect("logIn.jsp?from=home");
				}				
				else{
					Date date = new Date();
					userbo.updateStatus(user.getId(), "Online");
					userbo.updateLastLogin(new Timestamp(date.getTime()), user.getId());		
					session.setAttribute("success", "Đăng nhập thành công!!!");				
					session.setAttribute("auth", userbo.getUser(email, passwordHash));
					resp.sendRedirect(from);
				}											
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }    
}
