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

import Bo.Userbo;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/resetpassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		try {
    			resp.setContentType("text/html;charset=UTF-8");
    			req.setCharacterEncoding("UTF-8");
         	 	resp.setCharacterEncoding("UTF-8");
         	 	
         	 	HttpSession session = req.getSession();
         	 	String code = req.getParameter("code");
         	 	String pass1 = req.getParameter("pass1");         	 	
         	 	String pass2 = req.getParameter("pass2");     
         	 	if(code == "" || code == null || pass1 == "" || pass1 == null || pass2 == "" || pass2 == null) {         	 		
         	 		session.setAttribute("error", "Nhập đầy đủ thông tin.");
         	 		resp.sendRedirect("ResetPassword.jsp");
         	 	}   
         	 	if(!pass1.equals(pass2)) {
         	 		session.setAttribute("error", "Xác nhận mật khẩu.");
         	 		resp.sendRedirect("ResetPassword.jsp");
         	 	}
         	 	int codeSession = (int)session.getAttribute("codeRecover");
         	 	String emailRecover = (String)session.getAttribute("emailRecover");
         	 	if(codeSession == Integer.parseInt(code) && pass1.equals(pass2)) {
         	 		Userbo ubo = new Userbo();
         	 		MessageDigest md = MessageDigest.getInstance("MD5");
        			md.update(pass1.getBytes());
        			byte[] digest = md.digest();
        			String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
         	 		if(ubo.updatePassword(emailRecover, passwordHash)) {         	 			
         	 			resp.sendRedirect("logIn.jsp?from=home");
         	 		}
         	 		else {
         	 			session.setAttribute("error", "Đặt lại mật khẩu thất bại.");
             	 		resp.sendRedirect("ResetPassword.jsp");
         	 		}
         	 	}
         	 	else {
         	 		session.setAttribute("error", "Mã khôi phục không đúng.");
         	 		resp.sendRedirect("ResetPassword.jsp");
         	 	}
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
}
