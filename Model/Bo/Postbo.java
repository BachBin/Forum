package Bo;

import java.util.ArrayList;

import Bean.Categorybean;
import Bean.Postbean;
import Dao.Postdao;

public class Postbo {
	Postdao postdao = new Postdao();
	public ArrayList<Postbean> getPost() throws Exception {
		return postdao.getPost();
	}	
	public ArrayList<Postbean> getRecentPost() throws Exception {
		return postdao.getRecentPost();
	}
	public Long countQuestion() throws Exception {
		return postdao.countQuestion();
	}
	public Long countAnswer() throws Exception {
		return postdao.countAnswer();
	}
	public boolean subView(long id) throws Exception {
		return postdao.subView(id);
	}
	public boolean updateSolved(long id, boolean solved) throws Exception {
		return postdao.updateSolved(id, solved);
	}
	public boolean createPost(Long authorId, String title, String summary, String content, boolean type, Long categoryId, boolean show) throws Exception {
		return postdao.createPost(authorId, title, summary, content, type, categoryId, show);
	}
	public boolean updatePost(Long id, String title, String summary, String content, boolean type, Long categoryId) throws Exception {
		return postdao.updatePost(id, title, summary, content, type, categoryId);
	}
	public boolean deletePost(String post) throws Exception{
		return postdao.deletePost(post);
	}
	public Categorybean getCatebySlug(String slug) throws Exception {
		return postdao.getCatebySlug(slug);
	}
	public boolean admitPost(String post) throws Exception {		
		return postdao.admitPost(post);
	}
	public ArrayList<Postbean> getInvolvePost(String slug) throws Exception {
		return postdao.getInvolvePost(slug);
	}
	public ArrayList<Postbean> getAllPostByCategory(long idcategory) throws Exception {
		return postdao.getAllPostByCategory(idcategory);
	}
}
