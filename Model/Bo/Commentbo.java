package Bo;

import java.util.ArrayList;

import Bean.CommentVMbean;
import Bean.Commentbean;
import Dao.Commentdao;

public class Commentbo {
	Commentdao cmtdao = new Commentdao();
	
	public Commentbean getCommentByIdCmt(Long cmtid) throws Exception {
		return cmtdao.getCommentByIdCmt(cmtid);
	}
	public ArrayList<CommentVMbean> getComment(Long postid) throws Exception {
		return cmtdao.getComment(postid);
	}	
	public boolean createCmt(Long postId, Long authorId, String content) throws Exception {
		return cmtdao.createCmt(postId, authorId, content);
	}
	public boolean updateCmt(Long id, String content) throws Exception {
		return cmtdao.updateCmt(id, content);
	}
	public boolean deleteCmt(Long id) throws Exception{
		return cmtdao.deleteCmt(id);
	}
	public long getSLCmt(Long id) throws Exception {
		return cmtdao.getSLCmt(id);
	}
	public long getSLLikeAllCmt(Long idpost) throws Exception {
		return cmtdao.getSLLikeAllCmt(idpost);
	}
	//Xu ly xoa like cmt
	public long soLikeCmt(Long commentId) throws Exception {
		return cmtdao.soLikeCmt(commentId);
	}
	public long soDislikeCmt(Long commentId) throws Exception {
		return cmtdao.soDislikeCmt(commentId);
	}
	public boolean isLikeCmt(Long authorId, Long commentId, int islike) throws Exception {
		return cmtdao.isLikeCmt(authorId, commentId, islike);
	}
	public long getIdMaxLikeCmt() throws Exception {
		return cmtdao.getIdMaxLikeCmt();
	}
	public boolean updateIdentity() throws Exception {
		return cmtdao.updateIdentity();
	}
	public boolean deleteisLike(Long authorId, Long commentId) throws Exception {
		return cmtdao.deleteisLike(authorId, commentId);
	}
	public int getIdLike(Long authorId, Long commentId) throws Exception {
		return cmtdao.getIdLike(authorId, commentId);
	}
	public ArrayList<Long> getIdLikeList(Long commentId) throws Exception {
		return cmtdao.getIdLikeList(commentId);
	}
	public ArrayList<Long> getIdDislikeList(Long commentId) throws Exception {
		return cmtdao.getIdDislikeList(commentId);
	}
	public boolean deleteAllLikeCmt(Long id) throws Exception {
		return cmtdao.deleteAllLikeCmt(id);
	}
	
}
