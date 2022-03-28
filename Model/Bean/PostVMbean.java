package Bean;


public class PostVMbean extends Postbean{	
	private String image;
	private int typeUser;	
	private long answer;	
	private long uniqueId;	
	public PostVMbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostVMbean(String image) {
		super();
		this.image = image;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getTypeUser() {
		return typeUser;
	}
	public void setTypeUser(int typeuser) {
		this.typeUser = typeuser;
	}
	public long getAnswer() {
		return answer;
	}
	public void setAnswer(long answer) {
		this.answer = answer;
	}
	public long getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(long uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	
}
