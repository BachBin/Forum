package Bean;

import java.sql.Timestamp;

public class Userbean {
	private long id;	
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobile;
	private String email;
	private String passwordHash;
	private Timestamp registeredAt;
	private Timestamp lastLogin;
	private String intro;
	private String profile;
	private String image;	
	private int type;
	private long uniqueId;	
	private String status;
	
	public Userbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Userbean(long id, String firstName, String middleName, String lastName, String mobile, String email,
			String passwordHash, Timestamp registeredAt, Timestamp lastLogin, String intro, String profile,
			String image, int type, long uniqueId, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.email = email;
		this.passwordHash = passwordHash;
		this.registeredAt = registeredAt;
		this.lastLogin = lastLogin;
		this.intro = intro;
		this.profile = profile;
		this.image = image;
		this.type = type;
		this.uniqueId = uniqueId;
		this.status = status;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		if(firstName != "" && firstName != null) {
			return firstName;
		}
		return "";		
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		if(middleName != "" && middleName != null) {
			return middleName;
		}
		return "";
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		if(lastName != "" && lastName != null) {
			return lastName;
		}
		return "";		
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public Timestamp getRegisteredAt() {
		return registeredAt;
	}
	public void setRegisteredAt(Timestamp registeredAt) {
		this.registeredAt = registeredAt;
	}
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}	
	public long getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(long uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
