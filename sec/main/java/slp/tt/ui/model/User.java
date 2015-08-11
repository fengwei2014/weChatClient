package slp.tt.ui.model;

public class User {
	private String userId;
	private String userName;
	private String passWord;
	private String weChatOpenId;
	private Boolean isBanded;
	public Boolean getIsBanded() {
		return isBanded;
	}
	public void setIsBanded(Boolean isBanded) {
		this.isBanded = isBanded;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getWeChatOpenId() {
		return weChatOpenId;
	}
	public void setWeChatOpenId(String weChatOpenId) {
		this.weChatOpenId = weChatOpenId;
	}
	
}
