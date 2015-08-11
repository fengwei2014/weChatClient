package slp.tt.ui.model;

public class NewsMessage extends BaseMessage{
	private int ArticleCount;
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;
	private String Articles;
	
	public String getArticles() {
		return Articles;
	}
	public void setArticles(String articles) {
		Articles = articles;
	}
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
}
