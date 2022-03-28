package Bean;

public class Report {
	private long postId;
	private String content;
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Report(long postId, String content) {
		super();
		this.postId = postId;
		this.content = content;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
