package Bean;

import java.sql.Timestamp;

public class Commentbean {
	private long id;
	private long postId;
	private long parentId;
	private long authorId;
	private Timestamp createdAt;
	private Timestamp updateAt;
	private String content;	
	public Commentbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Commentbean(long id, long postId, long parentId, long authorId, Timestamp createdAt, Timestamp updateAt,
			String content, long like, long dislike) {
		super();
		this.id = id;
		this.postId = postId;
		this.parentId = parentId;
		this.authorId = authorId;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
		this.content = content;		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
