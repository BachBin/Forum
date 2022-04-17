package Controller;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Bean.Userbean;
import Bo.Userbo;

/**
 * Servlet implementation class InforUser
 */
@WebServlet("/updateuser")
public class InforUser extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
 	 	resp.setCharacterEncoding("UTF-8"); 
		try {			   	 	
     	 	String id = null;
     	 	String firstname = null;
     	 	String middlename = null;
     	 	String lastname = null;
     	 	String mobile = null;
     	 	String email = null;
     	 	String password = null;
     	 	String intro = null;
     	 	String profile = null;  
     	 	String anh = null;     	 	
     	 	
     	 	
     	 	HttpSession session = req.getSession();
     	 	Userbean user = (Userbean)session.getAttribute("auth");     	 	
     	 	
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
			upload.setHeaderEncoding("UTF-8");			
			try {
				List<FileItem> fileItems = upload.parseRequest(req);
				for (FileItem fileItem : fileItems) {
					if (!fileItem.isFormField()) {						
						String nameimg = fileItem.getName();
						if (!nameimg.equals("")) {							
							String dirUrl = req.getServletContext().getRealPath("") +  File.separator + "images";							
							File dir = new File(dirUrl);
							if (!dir.exists()) {
								dir.mkdir();
							}
							String fileImg = dirUrl + File.separator + nameimg;
							File file = new File(fileImg);// tạo file
							if(!file.exists()) {
								try {
									fileItem.write(file);// lưu file	
									anh = nameimg;	
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							anh = nameimg;	
						}
						else {
							anh = user.getImage();
						}						
					} else// Neu la control
					{
						String tentk = fileItem.getFieldName();
						if (tentk.equals("id")) {
							id = fileItem.getString();							
						}	
						if (tentk.equals("firstname")) {
							firstname = !fileItem.getString("UTF-8").equals("")?fileItem.getString("UTF-8"):null;								
						}							
						if (tentk.equals("middlename")) {
							middlename = !fileItem.getString("UTF-8").equals("")?fileItem.getString("UTF-8"):null;							
						}							
						if (tentk.equals("lastname")) {
							lastname = !fileItem.getString("UTF-8").equals("")?fileItem.getString("UTF-8"):null;							
						}
							
						if (tentk.equals("mobile")) {
							mobile = !fileItem.getString("UTF-8").equals("")?fileItem.getString("UTF-8"):null;								
						}
							
						if (tentk.equals("email")) {
							email = !fileItem.getString("UTF-8").equals("")?fileItem.getString("UTF-8"):null;								
						}	
						if (tentk.equals("password")) {
							password = !fileItem.getString("UTF-8").equals("")?fileItem.getString("UTF-8"):null;							
						}
						if (tentk.equals("intro")) {
							intro = !fileItem.getString("UTF-8").equals("")?fileItem.getString("UTF-8"):null;							
						}
						if (tentk.equals("profile")) {
							profile = !fileItem.getString("UTF-8").equals("")?fileItem.getString("UTF-8"):null;								
						}
					}
				}			
				
	     	 	if(password!=null && !password.equals("")) {     	 		
	     	 		MessageDigest md = MessageDigest.getInstance("MD5");
	    			md.update(password.getBytes());
	    		    byte[] digest = md.digest();
	    		    String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase(); 
	    		    if(!passwordHash.equals(user.getPasswordHash())) {
	    		    	user.setPasswordHash(passwordHash);
	    		    }
	    		    user.setId(Long.parseLong(id));
	    		    user.setFirstName(firstname);
	    		    user.setMiddleName(middlename);
	    		    user.setLastName(lastname);
	    		    user.setMobile(mobile);
	    		    user.setEmail(email);    		    
	    		    user.setIntro(intro);
	    		    user.setProfile(profile);
	    		    user.setImage(anh);
	    		    Userbo userbo = new Userbo();
	    		    if(userbo.updateUser(user)) {
	    		    	session.setAttribute("success", "Cập nhật thành công!!!");				
	    				session.setAttribute("auth", user);
	    				resp.sendRedirect("InforUser.jsp");
	    		    }
	    		    else {
	    		    	session.setAttribute("alert", "Cập nhật thất bại!!!");
	    		    	resp.sendRedirect("InforUser.jsp");
	    		    }
	     	 	}
	     	 	else {
			    	session.setAttribute("alert", "Nhập mật khẩu!!!");
			    	resp.sendRedirect("InforUser.jsp");
			    }				
			} catch (Exception e) {
				e.printStackTrace();
			}
     	 	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
