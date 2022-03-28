package Bo;

import java.util.ArrayList;

import Dao.PostLikedao;

public class PostLikebo {
	PostLikedao likebo = new PostLikedao();
	public boolean isLike(Long authorId, Long postId, int islike) throws Exception{
		return likebo.isLike(authorId, postId, islike);
	}
	public boolean deleteisLike(Long authorId, Long postId) throws Exception{
		return likebo.deleteisLike(authorId, postId);
	}	
	public long soLikePost(Long postId) throws Exception {
		return likebo.soLikePost(postId);
	}
	public long soDislikePost(Long postId) throws Exception {
		return likebo.soDislikePost(postId);
	}
	public int getIdLike(Long postId, Long authorId) throws Exception {
		return likebo.getIdLike(postId, authorId);
	}
	public ArrayList<Long> getIdLikeList(Long postId) throws Exception {	
		return likebo.getIdLikeList(postId);
	}
	public ArrayList<Long> getIdDislikeList(Long postId) throws Exception {	
		return likebo.getIdDislikeList(postId);
	}
	public boolean updateIdentity() throws Exception {
		return likebo.updateIdentity();
	}
}
