package slp.tt.ui.model;


/**
 * 微信文本消息
 * @author wei
 *
 */

public class TextMessage extends BaseMessage{
	
	/*文本消息内容
	 * */
	private String Content;
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;	
	}
	
}
