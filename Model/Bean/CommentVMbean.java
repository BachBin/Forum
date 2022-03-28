package Bean;

public class CommentVMbean extends Commentbean{
	
	private long uniqueId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String image;
	private Long like;	
	private Long dislike;	
	
	
	public long getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(long uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}	
	public Long getLike() {
		return like;
	}
	public void setLike(Long like) {
		this.like = like;
	}
	public Long getDislike() {
		return dislike;
	}
	public void setDislike(Long dislike) {
		this.dislike = dislike;
	}
}
