package Bean;

public class Categorybean {
	private long id;
	private long parentId;
	private String title;
	private String slug;
	private String content;
	public Categorybean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categorybean(long id, long parentId, String title, String slug, String content) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.title = title;
		this.slug = slug;
		this.content = content;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
}
