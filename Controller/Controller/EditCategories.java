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

import Bean.Categorybean;
import Bean.Userbean;
import Bo.Categorybo;

/**
 * Servlet implementation class EditCategories
 */
@WebServlet("/editCategories")
public class EditCategories extends HttpServlet {
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
				String cid = req.getParameter("cid");
				String name = req.getParameter("name");
				String content = req.getParameter("content");

				if (name != null && !name.equals("") && content != null && !content.equals("")) {
					Categorybo catebo = new Categorybo();
					for (Categorybean c : catebo.getCategories()) {
						if (c.getTitle().equals(name) && c.getId() != Long.parseLong(cid)) {
							res.add("alert");
							res.add("Tên loại bài đăng đã tồn tại!!!");

							String json = new Gson().toJson(res);
							out.print(json);
							out.close();
							return;
						}
					}
					if (catebo.updateCategory(Long.parseLong(cid), name, content)) {
						res.add("success");
						res.add("Cập nhật loại thành công!!!");

						res.add(cid);
						res.add(name);
						res.add(content);

						String json = new Gson().toJson(res);
						out.print(json);
						out.close();
					} else {
						res.add("alert");
						res.add("Cập nhật loại thất bại!!!");

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
