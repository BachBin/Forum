package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Bo.Userbo;

/**
 * Servlet implementation class MailRegistry
 */
@WebServlet("/mailregistry")
public class MailRegistry extends HttpServlet {
	private static final long serialVersionUID = 1L;           
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("application/json");
			req.setCharacterEncoding("UTF-8");
	 	 	resp.setCharacterEncoding("UTF-8");
	 	 	
	 	 	PrintWriter out = resp.getWriter();
			ArrayList<String> res = new ArrayList<String>();

     	 	HttpSession session = req.getSession();
     	 	
     	 	String email = req.getParameter("email");
     	 	Userbo user = new Userbo();
     	 	if(email.equals("") || email == null) {
     	 		res.add("alert");
				res.add("Nhập email để đăng ký!!!");
				String json = new Gson().toJson(res);
				out.print(json);
				out.close();
				return;
     	 	}
     	 	if(!user.checkUser(email)) {
     	 		Random rad = new Random();
 	 			int randomCode = rad.nextInt(999999);
 	 			
 	 			final String fromEmail = "websiteforumhusc@gmail.com";
 	 			final String password = "VanManh672??";
 	 			
 	 			String toEmail = email;
 	 			final String subject = "Mã xác nhận đăng ký Forum Website";
 	 			String body = "Mã xác nhận đăng ký của bạn là: "+randomCode;         	 			
 	 			
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
 	 			
 	 	        res.add("success");
				res.add("Mã xác nhận đã gửi đến mail!!!");				
 	 			
				session.setAttribute("codeRegistry", randomCode);				
     	 	}
     	 	else {
     	 		res.add("alert");
				res.add("Tài khoản đã tồn tại!!!");   
     	 	}
     	 	String json = new Gson().toJson(res);
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
