package slp.tt.ui.model;

public class ArticlesMessage {
	private String touser;
	private String msgtype;
	private ArticleVo news;
	public ArticleVo getNews() {
		return news;
	}
	public void setNews(ArticleVo news) {
		this.news = news;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
}
