package Bean;

import java.sql.Timestamp;

public class Postbean {
	private long id;
	private long authorId;
	private long parentId;
	private String title;
	private String slug;
	private String summary;
	private boolean solved;
	private Timestamp createdAt;
	private Timestamp updatedAt;	
	private String content;
	private long view;
	private boolean type;
	private boolean show;
	
	public Postbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Postbean(long id, long authorId, long parentId, String title, String slug, String summary, boolean solved,
			Timestamp createdAt, Timestamp updatedAt, String content, long view, boolean type, boolean show) {
		super();
		this.id = id;
		this.authorId = authorId;
		this.parentId = parentId;
		this.title = title;
		this.slug = slug;
		this.summary = summary;
		this.solved = solved;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;		
		this.content = content;
		this.view = view;
		this.type = type;
		this.show = show;
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
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public boolean isSolved() {
		return solved;
	}
	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getView() {
		return view;
	}
	public void setView(long view) {
		this.view = view;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
}
