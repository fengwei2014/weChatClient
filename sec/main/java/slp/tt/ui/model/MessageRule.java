package slp.tt.ui.model;

/**
 * 回复消息规则
 * @author wei
 *
 */
public class MessageRule {
	
	/**
	 * 消息规则id
	 */
	private String id;
	
	/**
	 * 消息规则接收代码
	 */
	private String code;
	
	/**
	 * 消息回复
	 */
	private String reply;
	
	public String getId() {
		return id;
	}
	public void setId(String uid) {
		this.id = uid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	
}
