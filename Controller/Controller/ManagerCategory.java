package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Userbean;
import Bo.Categorybo;
import Bo.Postbo;
import Bo.Userbo;

/**
 * Servlet implementation class ManagerCategory
 */
@WebServlet("/managercategory")
public class ManagerCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			HttpSession session = req.getSession();
			Userbean auth = (Userbean) session.getAttribute("auth");
			
			if(auth == null  || auth.getType() == 0) {
				resp.sendRedirect("logIn.jsp?from=home"); 
			}
			if (auth != null) {
				long sobai = 10;

				String indexPage = req.getParameter("page");
				if (indexPage == null) {
					indexPage = "1";
				}
				long index = Long.parseLong(indexPage);

				String txtSearch = req.getParameter("searchct");

				Categorybo catebo = new Categorybo();
				Userbo userbo = new Userbo();
				Postbo postbo = new Postbo();

				req.setAttribute("countQuestion", postbo.countQuestion());
				req.setAttribute("countAnswer", postbo.countAnswer());
				req.setAttribute("countUser", userbo.getSLUser(-1));

				req.setAttribute("listAdmin", userbo.getAdministrators());
				req.setAttribute("listCate", catebo.getCategories());
				req.setAttribute("listRecentPost", postbo.getRecentPost());

				long count = catebo.getSLCategories();
				if (txtSearch != null && txtSearch != "") {
					count = catebo.getSLCategoriesbySearch(txtSearch);
				}
				long endPage = count / sobai;
				if (count % sobai != 0) {
					endPage++;
				}
				req.setAttribute("endP", endPage);
				req.setAttribute("tag", index);
				if (txtSearch != null && txtSearch != "") {
					req.setAttribute("listCategories", catebo.getCategoriesbyPageSearch(index, sobai, txtSearch));
				} else {
					req.setAttribute("listCategories", catebo.getCategoriesbyPage(index, sobai));
				}
				req.getRequestDispatcher("managercategory.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("logIn.jsp?from=home");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
