package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Bean.Postbean;
import Bean.Userbean;
import Bo.Postbo;

/**
 * Servlet implementation class CreatePost
 */
@WebServlet("/createpost")
public class CreatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("application/json");
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			HttpSession session = req.getSession();
			Userbean auth = (Userbean) session.getAttribute("auth");
			PrintWriter out = resp.getWriter();
			ArrayList<String> res = new ArrayList<String>();
			if (auth != null) {
				Long authorId = auth.getId();
				String type = req.getParameter("type");
				String title = req.getParameter("title");
				String summary = req.getParameter("summary");
				String category = req.getParameter("category");
				String content = req.getParameter("content");
				Pattern checkKitu = Pattern.compile("^.*[#?!@$%^&*-]+.*$");
				boolean show = auth.getType() == 0 ? false : true;

				if (checkKitu.matcher(title).find()) {
					res.add("alert");
					res.add("Tiêu đề bài không được chứa kí tự đặc biệt.");

					String json = new Gson().toJson(res);
					out.print(json);
					out.close();
					return;
				}

				if (type != null && !type.equals("") && title != null && !title.equals("") && summary != null
						&& !summary.equals("") && category != null && !category.equals("") && content != null
						&& !content.equals("")) {
					Postbo postbo = new Postbo();
					for (Postbean p : postbo.getPost()) {
						if (p.getTitle().equals(title)) {
							res.add("alert");
							res.add("Tiêu đề bài đã tồn tại!!!");

							String json = new Gson().toJson(res);
							out.print(json);
							out.close();
							return;
						}
					}					
					if (postbo.createPost(authorId, title, summary, content, Boolean.parseBoolean(type), Long.parseLong(category), show)) {
						res.add("success");
						res.add("Đăng bài thành công!!!");

						String json = new Gson().toJson(res);
						out.print(json);
						out.close();
					} else {
						res.add("alert");
						res.add("Đăng bài thất bại!!!");

						String json = new Gson().toJson(res);
						out.print(json);
						out.close();
					}
				} else {
					res.add("alert");
					res.add("Nhập đầy đủ thông tin!!!");

					String json = new Gson().toJson(res);
					out.print(json);
					out.close();
				}
			} else {
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
