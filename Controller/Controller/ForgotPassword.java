package Controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bo.Userbo;
import Captcha.VerifyRecaptcha;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/forgotpassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		try {
    			resp.setContentType("text/html;charset=UTF-8");
         	 	req.setCharacterEncoding("utf-8");
         	 	resp.setCharacterEncoding("utf-8");
         	 	
         	 	String email = req.getParameter("email");
         	 	String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
         	 	
         	 	boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
         	 	
         	 	HttpSession session = req.getSession();
         	 	if(verify==false) {         	 		
					session.setAttribute("alert", "Vui lòng xác minh lại!!!");
					resp.sendRedirect("ForgotPassword.jsp");
         	 	}
         	 	else {
         	 		Userbo user = new Userbo();
         	 		if(user.checkUser(email)) {
         	 			Random rad = new Random();
         	 			int randomCode = rad.nextInt(999999);        	 			
         	 			
         	 			final String fromEmail = "websiteforumhusc@gmail.com";
         	 			final String password = "VanManh672???";
         	 			
         	 			String toEmail = email;
         	 			final String subject = "Mã khôi phục mật khẩu Forum Website";
         	 			String body = "Mã khôi phục mật khẩu của bạn là: "+randomCode;         	 			
         	 			
         	 			Properties prop = System.getProperties();
         	 			prop.put("mail.smtp.host", "smtp.gmail.com");
         	 			prop.put("mail.smtp.port", "587"); //TLS Port
         	 			prop.put("mail.smtp.auth", "true"); //enable authentication
         	 			prop.put("mail.smtp.starttls.enable", "true");     
         	 			
         	 			Authenticator auth = new Authenticator() {
         	 	            protected PasswordAuthentication getPasswordAuthentication() {
         	 	                return new PasswordAuthentication(fromEmail, password);
         	 	            }
         	 	        };
         	 	        Session sessionMail = Session.getInstance(prop, auth);
         	 	        MimeMessage msg = new MimeMessage(sessionMail);
         	 	        //set message headers
         	 	        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
         	 	        msg.addHeader("format", "flowed");
         	 	        msg.addHeader("Content-Transfer-Encoding", "8bit");
         	 	        msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));
         	 	        msg.setReplyTo(InternetAddress.parse(fromEmail, false));
         	 	        msg.setSubject(subject, "UTF-8");
         	 	        msg.setText(body, "UTF-8");
         	 	        msg.setSentDate(new Date());
         	 	        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
         	 	        Transport.send(msg);
         	 			
         	 			session.setAttribute("success", "Kiểm tra hòm thư để lấy mã khôi phục.");
    					session.setAttribute("codeRecover", randomCode);
    					session.setAttribute("emailRecover", email);    					
    					
    					resp.sendRedirect("ResetPassword.jsp");
         	 		}
         	 		else {
         	 			session.setAttribute("alert", "Tài khoản không tồn tại.");
         	 			resp.sendRedirect("ForgotPassword.jsp");
         	 		}   
         	 	}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}

}
