package Bo;

import java.util.ArrayList;

import Bean.PostVMbean;
import Dao.PostVMdao;

public class PostVMbo {
	PostVMdao postvmdao = new PostVMdao();
	
	
	public long getSLRecentQuestionbySearch(String search) throws Exception {
		return postvmdao.getSLRecentQuestionbySearch(search);
	}	
	public ArrayList<PostVMbean> getRecentQuestionPagebySearch(long index, long sobai, String search) throws Exception {
		return postvmdao.getRecentQuestionPagebySearch(index, sobai, search);
	}
	
	public long getSLRecentQuestion() throws Exception {
		return postvmdao.getSLRecentQuestion();
	}
	
	public ArrayList<PostVMbean> getRecentQuestionPage(long index, long sobai) throws Exception {
		return postvmdao.getRecentQuestionPage(index, sobai);
	}
	
	public long getSLRecentQuestionbyCate(String slugcate) throws Exception {
		return postvmdao.getSLRecentQuestionbyCate(slugcate);
	}
	public ArrayList<PostVMbean> getRecentQuestionbyCatePage(String slugcate, long index, long sobai) throws Exception {
		return postvmdao.getRecentQuestionbyCatePage(slugcate, index, sobai);
	}	
	
	public long getSLRecentQuestionbyTags(String slugtags) throws Exception {
		return postvmdao.getSLRecentQuestionbyTags(slugtags);
	}
	public ArrayList<PostVMbean> getRecentQuestionbyTagsPage(String slugtags, long index, long sobai) throws Exception {
		return postvmdao.getRecentQuestionbyTagsPage(slugtags, index, sobai);
	}
	
	public long getSLRecentAnswerbySearch(String search) throws Exception {
		return postvmdao.getSLRecentAnswerbySearch(search);
	}
	public ArrayList<PostVMbean> getRecentAnswerPagebySearch(long index, long sobai, String search) throws Exception {
		return postvmdao.getRecentAnswerPagebySearch(index, sobai, search);
	}
	public long getSLRecentAnswer() throws Exception {
		return postvmdao.getSLRecentAnswer();
	}
	public ArrayList<PostVMbean> getRecentAnswer() throws Exception {
		return postvmdao.getRecentAnswer();
	}
	public ArrayList<PostVMbean> getRecentAnswerPage(long index, long sobai) throws Exception {
		return postvmdao.getRecentAnswerPage(index, sobai);
	}
	
	public long getSLRecentAnswerbyCate(String slugcate) throws Exception {
		return postvmdao.getSLRecentAnswerbyCate(slugcate);
	}
	public ArrayList<PostVMbean> getRecentAnswerbyCatePage(String slugcate, long index, long sobai) throws Exception {
		return postvmdao.getRecentAnswerbyCatePage(slugcate, index, sobai);
	}
	
	public long getSLRecentAnswerbyTags(String slugtags) throws Exception {
		return postvmdao.getSLRecentAnswerbyTags(slugtags);
	}
	public ArrayList<PostVMbean> getRecentAnswerbyTagsPage(String slugtags, long index, long sobai) throws Exception {
		return postvmdao.getRecentAnswerbyTagsPage(slugtags, index, sobai);
	}
	
	public long getSLNoAnswerbySearch(String search) throws Exception {
		return postvmdao.getSLNoAnswerbySearch(search);
	}
	public ArrayList<PostVMbean> getNoAnswerPagebySearch(long index, long sobai, String search) throws Exception {
		return postvmdao.getNoAnswerPagebySearch(index, sobai, search);
	}
	public long getSLNoAnswer() throws Exception {
		return postvmdao.getSLNoAnswer();
	} 
	public ArrayList<PostVMbean> getNoAnswerPage(long index, long sobai) throws Exception {
		return postvmdao.getNoAnswerPage(index, sobai);
	}
	
	public long getSLNoAnswerbyCate(String slugcate) throws Exception {
		return postvmdao.getSLNoAnswerbyCate(slugcate);
	}
	public ArrayList<PostVMbean> getNoAnswerbyCatePage(String slugcate, long index, long sobai) throws Exception {
		return postvmdao.getNoAnswerbyCatePage(slugcate, index, sobai);
	}
	
	public long getSLNoAnswerbyTags(String slugtags) throws Exception {
		return postvmdao.getSLNoAnswerbyTags(slugtags);
	}
	public ArrayList<PostVMbean> getNoAnswerbyTagsPage(String slugtags, long index, long sobai) throws Exception {
		return postvmdao.getNoAnswerbyTagsPage(slugtags, index, sobai);
	}
	
	public long getSLRecentPostAllbySearch(String search, int type) throws Exception {
		return postvmdao.getSLRecentPostAllbySearch(search, type);
	}
	public ArrayList<PostVMbean> getRecentPostAllPagebySearch(long index, long sobai, String search, int type) throws Exception {		
		return postvmdao.getRecentPostAllPagebySearch(index, sobai, search, type);
	}
	public long getSLRecentPostAll(int type) throws Exception {
		return postvmdao.getSLRecentPostAll(type);
	}
	public ArrayList<PostVMbean> getRecentPostAll() throws Exception {
		return postvmdao.getRecentPostAll();
	}
	public ArrayList<PostVMbean> getRecentPostAllPage(long index, long sobai, int type) throws Exception {
		return postvmdao.getRecentPostAllPage(index, sobai, type);
	}
	
	public long getSLRecentPostAllbyCate(String slugcate) throws Exception {
		return postvmdao.getSLRecentPostAllbyCate(slugcate);
	}
	public ArrayList<PostVMbean> getRecentPostAllbyCate(String slugcate, long index, long sobai) throws Exception {
		return postvmdao.getRecentPostAllbyCatePage(slugcate, index, sobai);
	}
	
	public long getSLRecentPostAllbyTags(String slugtags) throws Exception {
		return postvmdao.getSLRecentPostAllbyTags(slugtags);
	}
	public ArrayList<PostVMbean> getRecentPostAllbyTagsPage(String slugtags, long index, long sobai) throws Exception {
		return postvmdao.getRecentPostAllbyTagsPage(slugtags, index, sobai);
	}
	
	public PostVMbean getPostBySlug(String slug) throws Exception {
		return postvmdao.getPostBySlug(slug);
	}	
	public long getSLMyPost(long idu, int type, String search) throws Exception {
		return postvmdao.getSLMyPost(idu, type, search);
	}
	public ArrayList<PostVMbean> getMyPost(long index, long sobai, long idu, int type, String search) throws Exception {
		return postvmdao.getMyPost(index, sobai, idu, type, search);
	}
}
