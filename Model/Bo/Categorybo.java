package Bo;

import java.util.ArrayList;

import Bean.Categorybean;
import Dao.Categorydao;

public class Categorybo {
	Categorydao catedao = new Categorydao();
	public Long getSLCategories() throws Exception {
		return catedao.getSLCategories();
	}
	public ArrayList<Categorybean> getCategoriesbyPage(long index, long sobai) throws Exception{
		return catedao.getCategoriesbyPage(index, sobai);
	}
	public ArrayList<Categorybean> getCategories() throws Exception {
		return catedao.getCategories();
	}
	public long getSLCategoriesbySearch(String search) throws Exception {
		return catedao.getSLCategoriesbySearch(search);
	}
	public ArrayList<Categorybean> getCategoriesbyPageSearch(long index, long sobai,String search) throws Exception {
		return catedao.getCategoriesbyPageSearch(index, sobai, search);
	}
	public boolean createCategory(String title, String content) throws Exception {
		return catedao.createCategory(title, content);
	}
	public boolean updateCategory(Long id, String title, String content) throws Exception {
		return catedao.updateCategory(id, title, content);
	}
	public boolean deleteCategory(Long id) throws Exception {
		return catedao.deleteCategory(id);
	}
	public boolean createPostCategory(Long postId, Long categoryId) throws Exception {
		return catedao.createPostCategory(postId, categoryId);
	}
}
