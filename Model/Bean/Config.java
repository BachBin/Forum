package Bean;

public class Config {
	public boolean allowLogin;
	public boolean allowRegistry;
	public boolean allowPost;
	public boolean allowChat;
	public boolean Forum;
	public Config() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Config(boolean allowLogin, boolean allowRegistry, boolean allowPost, boolean allowChat, boolean forum) {
		super();
		this.allowLogin = allowLogin;
		this.allowRegistry = allowRegistry;
		this.allowPost = allowPost;
		this.allowChat = allowChat;
		Forum = forum;
	}

	public boolean isAllowLogin() {
		return allowLogin;
	}
	public void setAllowLogin(boolean allowLogin) {
		this.allowLogin = allowLogin;
	}
	public boolean isAllowRegistry() {
		return allowRegistry;
	}
	public void setAllowRegistry(boolean allowRegistry) {
		this.allowRegistry = allowRegistry;
	}
	public boolean isAllowPost() {
		return allowPost;
	}
	public void setAllowPost(boolean allowPost) {
		this.allowPost = allowPost;
	}
	public boolean isAllowChat() {
		return allowChat;
	}
	public void setAllowChat(boolean allowChat) {
		this.allowChat = allowChat;
	}
	public boolean isForum() {
		return Forum;
	}
	public void setForum(boolean forum) {
		Forum = forum;
	}
}
