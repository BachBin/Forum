package Bean;

public class PostLike {
	private long id;	
	private long authorId;
	private long postId;
	private int isLike;	
	
	public PostLike() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostLike(long id, long authorId, long postId, int isLike) {
		super();
		this.id = id;
		this.authorId = authorId;
		this.postId = postId;
		this.isLike = isLike;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public int getIsLike() {
		return isLike;
	}
	public void setIsLike(int isLike) {
		this.isLike = isLike;
	}
}
