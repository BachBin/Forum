package Bean;

public class Chat {
	private long id;	
	private long fromId;
	private long toId;
	private String message;
	
	
	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Chat(long id, long fromId, long toId, String message) {
		super();
		this.id = id;
		this.fromId = fromId;
		this.toId = toId;
		this.message = message;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFromId() {
		return fromId;
	}
	public void setFromId(long fromId) {
		this.fromId = fromId;
	}
	public long getToId() {
		return toId;
	}
	public void setToId(long toId) {
		this.toId = toId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
	
}
