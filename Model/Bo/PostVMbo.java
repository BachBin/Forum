package Bo;

import java.util.ArrayList;

import Bean.PostVMbean;
import Dao.PostVMdao;

public class PostVMbo {
	PostVMdao postvmdao = new PostVMdao();
	
	
	public long getSLRecentQuestionbySearch(String search, int show) throws Exception {
		return postvmdao.getSLRecentQuestionbySearch(search, show);
	}	
	public ArrayList<PostVMbean> getRecentQuestionPagebySearch(long index, long sobai, String search, int show) throws Exception {
		return postvmdao.getRecentQuestionPagebySearch(index, sobai, search, show);
	}
	
	public long getSLRecentQuestion(int show) throws Exception {
		return postvmdao.getSLRecentQuestion(show);
	}
	
	public ArrayList<PostVMbean> getRecentQuestionPage(long index, long sobai, int show) throws Exception {
		return postvmdao.getRecentQuestionPage(index, sobai, show);
	}
	
	public long getSLRecentQuestionbyCate(String slugcate, int show) throws Exception {
		return postvmdao.getSLRecentQuestionbyCate(slugcate, show);
	}
	public ArrayList<PostVMbean> getRecentQuestionbyCatePage(String slugcate, long index, long sobai, int show) throws Exception {
		return postvmdao.getRecentQuestionbyCatePage(slugcate, index, sobai, show);
	}
	
	public long getSLRecentAnswerbySearch(String search, int show) throws Exception {
		return postvmdao.getSLRecentAnswerbySearch(search, show);
	}
	public ArrayList<PostVMbean> getRecentAnswerPagebySearch(long index, long sobai, String search, int show) throws Exception {
		return postvmdao.getRecentAnswerPagebySearch(index, sobai, search, show);
	}
	public long getSLRecentAnswer(int show) throws Exception {
		return postvmdao.getSLRecentAnswer(show);
	}
	public ArrayList<PostVMbean> getRecentAnswer(int show) throws Exception {
		return postvmdao.getRecentAnswer(show);
	}
	public ArrayList<PostVMbean> getRecentAnswerPage(long index, long sobai, int show) throws Exception {
		return postvmdao.getRecentAnswerPage(index, sobai, show);
	}
	
	public long getSLRecentAnswerbyCate(String slugcate, int show) throws Exception {
		return postvmdao.getSLRecentAnswerbyCate(slugcate, show);
	}
	public ArrayList<PostVMbean> getRecentAnswerbyCatePage(String slugcate, long index, long sobai, int show) throws Exception {
		return postvmdao.getRecentAnswerbyCatePage(slugcate, index, sobai, show);
	}
	
	public long getSLRecentAnswerbyTags(String slugtags, int show) throws Exception {
		return postvmdao.getSLRecentAnswerbyTags(slugtags, show);
	}
	public ArrayList<PostVMbean> getRecentAnswerbyTagsPage(String slugtags, long index, long sobai, int show) throws Exception {
		return postvmdao.getRecentAnswerbyTagsPage(slugtags, index, sobai, show);
	}
	
	public long getSLNoAnswerbySearch(String search, int show) throws Exception {
		return postvmdao.getSLNoAnswerbySearch(search, show);
	}
	public ArrayList<PostVMbean> getNoAnswerPagebySearch(long index, long sobai, String search, int show) throws Exception {
		return postvmdao.getNoAnswerPagebySearch(index, sobai, search, show);
	}
	public long getSLNoAnswer(int show) throws Exception {
		return postvmdao.getSLNoAnswer(show);
	} 
	public ArrayList<PostVMbean> getNoAnswerPage(long index, long sobai, int show) throws Exception {
		return postvmdao.getNoAnswerPage(index, sobai, show);
	}
	
	public long getSLNoAnswerbyCate(String slugcate, int show) throws Exception {
		return postvmdao.getSLNoAnswerbyCate(slugcate, show);
	}
	public ArrayList<PostVMbean> getNoAnswerbyCatePage(String slugcate, long index, long sobai, int show) throws Exception {
		return postvmdao.getNoAnswerbyCatePage(slugcate, index, sobai, show);
	}
	
	public long getSLNoAnswerbyTags(String slugtags, int show) throws Exception {
		return postvmdao.getSLNoAnswerbyTags(slugtags, show);
	}
	public ArrayList<PostVMbean> getNoAnswerbyTagsPage(String slugtags, long index, long sobai, int show) throws Exception {
		return postvmdao.getNoAnswerbyTagsPage(slugtags, index, sobai, show);
	}
	
	public long getSLRecentPostAllbySearch(String search, int type, int show) throws Exception {
		return postvmdao.getSLRecentPostAllbySearch(search, type, show);
	}
	public ArrayList<PostVMbean> getRecentPostAllPagebySearch(long index, long sobai, String search, int type, int show) throws Exception {		
		return postvmdao.getRecentPostAllPagebySearch(index, sobai, search, type, show);
	}
	public long getSLRecentPostAll(int type, int show) throws Exception {
		return postvmdao.getSLRecentPostAll(type, show);
	}
	public ArrayList<PostVMbean> getRecentPostAll(int show) throws Exception {
		return postvmdao.getRecentPostAll(show);
	}
	public ArrayList<PostVMbean> getRecentPostAllPage(long index, long sobai, int type, int show) throws Exception {
		return postvmdao.getRecentPostAllPage(index, sobai, type, show);
	}
	
	public long getSLRecentPostAllbyCate(String slugcate, int show) throws Exception {
		return postvmdao.getSLRecentPostAllbyCate(slugcate, show);
	}
	public ArrayList<PostVMbean> getRecentPostAllbyCate(String slugcate, long index, long sobai, int show) throws Exception {
		return postvmdao.getRecentPostAllbyCatePage(slugcate, index, sobai, show);
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
