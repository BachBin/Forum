package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bo.Categorybo;
import Bo.PostVMbo;
import Bo.Postbo;
import Bo.Userbo;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			long sobai = 5;

			String indexPage1 = req.getParameter("page1");
			if (indexPage1 == null) {
				indexPage1 = "1";
			}
			long index1 = Long.parseLong(indexPage1);

			String indexPage2 = req.getParameter("page2");
			if (indexPage2 == null) {
				indexPage2 = "1";
			}
			long index2 = Long.parseLong(indexPage2);

			String indexPage3 = req.getParameter("page3");
			if (indexPage3 == null) {
				indexPage3 = "1";
			}
			long index3 = Long.parseLong(indexPage3);

			String indexPage4 = req.getParameter("page4");
			if (indexPage4 == null) {
				indexPage4 = "1";
			}
			long index4 = Long.parseLong(indexPage4);

			Categorybo catebo = new Categorybo();
			Userbo userbo = new Userbo();
			Postbo postbo = new Postbo();
			PostVMbo postvmbo = new PostVMbo();

			req.setAttribute("countQuestion", postbo.countQuestion());
			req.setAttribute("countAnswer", postbo.countAnswer());
			req.setAttribute("countUser", userbo.getSLUser(-1));

			String slugcate = req.getParameter("cate");
			String slugtag = req.getParameter("tags");
			String search = req.getParameter("search");

			if (slugcate != null && !slugcate.equals("")) {
				long count1 = postvmbo.getSLRecentQuestionbyCate(slugcate, 1);
				long endPage1 = count1 / sobai;
				if (count1 % sobai != 0) {
					endPage1++;
				}
				req.setAttribute("endP1", endPage1);
				req.setAttribute("tag1", index1);
				req.setAttribute("listPostQuestion", postvmbo.getRecentQuestionbyCatePage(slugcate, index1, sobai, 1)); // 1

				long count2 = postvmbo.getSLRecentAnswerbyCate(slugcate, 1);
				long endPage2 = count2 / sobai;
				if (count2 % sobai != 0) {
					endPage2++;
				}
				req.setAttribute("endP2", endPage2);
				req.setAttribute("tag2", index2);
				req.setAttribute("listPostTip", postvmbo.getRecentAnswerbyCatePage(slugcate, index2, sobai, 1)); // 2

				long count3 = postvmbo.getSLNoAnswerbyCate(slugcate, 1);
				long endPage3 = count3 / sobai;
				if (count3 % sobai != 0) {
					endPage3++;
				}
				req.setAttribute("endP3", endPage3);
				req.setAttribute("tag3", index3);
				req.setAttribute("listNoAnswer", postvmbo.getNoAnswerbyCatePage(slugcate, index3, sobai, 1)); // 3

				long count4 = postvmbo.getSLRecentPostAllbyCate(slugcate, 1);
				long endPage4 = count4 / sobai;
				if (count4 % sobai != 0) {
					endPage4++;
				}
				req.setAttribute("endP4", endPage4);
				req.setAttribute("tag4", index4);
				req.setAttribute("listRecentPostAll", postvmbo.getRecentPostAllbyCate(slugcate, index4, sobai, 1)); // 4
			} 
			else if (search != null && !search.equals("")) {
				long count1 = postvmbo.getSLRecentQuestionbySearch(search, 1);
				long endPage1 = count1 / sobai;
				if (count1 % sobai != 0) {
					endPage1++;
				}
				req.setAttribute("endP1", endPage1);
				req.setAttribute("tag1", index1);
				req.setAttribute("listPostQuestion", postvmbo.getRecentQuestionPagebySearch(index1, sobai, search, 1)); // 1

				long count2 = postvmbo.getSLRecentAnswerbySearch(search, 1);
				long endPage2 = count2 / sobai;
				if (count2 % sobai != 0) {
					endPage2++;
				}
				req.setAttribute("endP2", endPage2);
				req.setAttribute("tag2", index2);
				req.setAttribute("listPostTip", postvmbo.getRecentAnswerPagebySearch(index2, sobai, search, 1)); // 2

				long count3 = postvmbo.getSLNoAnswerbySearch(search, 1);
				long endPage3 = count3 / sobai;
				if (count3 % sobai != 0) {
					endPage3++;
				}
				req.setAttribute("endP3", endPage3);
				req.setAttribute("tag3", index3);
				req.setAttribute("listNoAnswer", postvmbo.getNoAnswerPagebySearch(index3, sobai, search, 1)); // 3

				long count4 = postvmbo.getSLRecentPostAllbySearch(search, -1, 1);
				long endPage4 = count4 / sobai;
				if (count4 % sobai != 0) {
					endPage4++;
				}
				req.setAttribute("endP4", endPage4);
				req.setAttribute("tag4", index4);
				req.setAttribute("listRecentPostAll", postvmbo.getRecentPostAllPagebySearch(index4, sobai, search, -1, 1)); // 4
			} else {
				long count1 = postvmbo.getSLRecentQuestion(1);
				long endPage1 = count1 / sobai;
				if (count1 % sobai != 0) {
					endPage1++;
				}
				req.setAttribute("endP1", endPage1);
				req.setAttribute("tag1", index1);
				req.setAttribute("listPostQuestion", postvmbo.getRecentQuestionPage(index1, sobai, 1)); // 1

				long count2 = postvmbo.getSLRecentAnswer(1);
				long endPage2 = count2 / sobai;
				if (count2 % sobai != 0) {
					endPage2++;
				}
				req.setAttribute("endP2", endPage2);
				req.setAttribute("tag2", index2);
				req.setAttribute("listPostTip", postvmbo.getRecentAnswerPage(index2, sobai, 1)); // 2

				long count3 = postvmbo.getSLNoAnswer(1);
				long endPage3 = count3 / sobai;
				if (count3 % sobai != 0) {
					endPage3++;
				}
				req.setAttribute("endP3", endPage3);
				req.setAttribute("tag3", index3);
				req.setAttribute("listNoAnswer", postvmbo.getNoAnswerPage(index3, sobai, 1)); // 3

				long count4 = postvmbo.getSLRecentPostAll(-1, 1);
				long endPage4 = count4 / sobai;
				if (count4 % sobai != 0) {
					endPage4++;
				}
				req.setAttribute("endP4", endPage4);
				req.setAttribute("tag4", index4);
				req.setAttribute("listRecentPostAll", postvmbo.getRecentPostAllPage(index4, sobai, -1, 1)); // 4
			}

			req.setAttribute("listAdmin", userbo.getAdministrators());
			req.setAttribute("listCate", catebo.getCategories());
			req.setAttribute("listRecentPost", postbo.getRecentPost());
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
