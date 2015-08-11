package slp.tt.ui.common.util;


/**
 * 微信消息常量设置和菜单自定义配置
 * @author wei
 *
 */
public interface BaseWeChatConfig {
	
	public String LIVE_MESSAGE = "This is just a Test Message to all user";
	
	public String MESSAGE_TYPE_TEXT = "text" ;
	public String MESSAGE_TYPE_IMAGE = "image" ;
	public String MESSAGE_TYPE_NEWS = "news" ;
	public String MESSAGE_TYPE_VOICE = "voice" ;
	public String MESSAGE_TYPE_VIDEO = "video" ;
	public String MESSAGE_TYPE_SHORTVIDEO = "shortvideo" ;
	public String MESSAGE_TYPE_LOCATION = "location" ;
	public String MESSAGE_TYPE_LINK = "link" ;
	
	public String MESSAGE_EVENT = "event";
	public String MESSAGE_EVENT_CLICK = "CLICK";
	public String MESSAGE_EVENT_VIEW = "VIEW";
	public String MESSAGE_EVENT_SCANCODE_PUSH = "scancode_push";
	public String MESSAGE_EVENT_SCANCODE_WAITMSG = "scancode_waitmsg";
	public String MESSAGE_EVENT_PIC_SYSPHOTO = "pic_sysphoto";
	public String MESSAGE_EVENT_PIC_SYSPHOTO_OR_ALBUM = "pic_photo_or_album";
	public String MESSAGE_EVENT_PIC_WEIXIN = "pic_weixin";
	public String MESSAGE_EVENT_LOCATION_SELECT = "location_select";
	public String MESSAGE_EVENT_SUBSCRIBE = "subscribe";
	
	
//	菜单key值
	public String MENU_KEY_LIVE_MESSAGE = "LIVE";
	
	public String MENU_KEY_REAL_TRACK = "REAL_TRACK";
	public String MENU_KEY_HISTORY_TRACK = "HISTORY_TRACK";
	
	public String MENU_KEY_APPRAISAL = "APPRAISAL";
	public String MENU_KEY_HELP = "HELP";
	public String MENU_KEY_FEEDBACK = "FEEDBACK";
	
//	public String PROJECT_NAME = "http://119.254.101.149/weChat";
	public String PROJECT_NAME = "http://119.254.101.149/weChat";
	
	
	//订阅默认回复文字
	public String SUB_BASE_STRING = "感谢您关注活点地图微信公众号！杨梅运输状况尽在掌握！";
	
	//公众号应用ID和密钥
	public String APP_ID = "wxe8859d088819a3d8";
	public String APP_SECRET = "97da3c6bbb982f78a558e077859c236a";
	
	//createMenu token url
	public String CREATE_MENU_TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";
	
	public String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create";
	
//	获取车辆数据url
//	public String GET_VEHICLE_INFO_REMOTE = "http://10.98.0.194:8080/public/v3/GetGpsInfo";
	public String GET_VEHICLE_INFO_REMOTE = "http://183.136.128.45/public/v3/GetGpsInfo";
	public String GET_VEHICLE_HISTORY_REMOTE = "http://183.136.128.45/public/v3/GetHisTrack";
	
	//获取所有用户openID
	public String GET_ALL_USERS = "https://api.weixin.qq.com/cgi-bin/user/get";
	
	//多媒体文件上传URL
	public String UPLOAD_FILE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload";
	//客户接口给用户发送消息
	public String SEND_MESSAGE_TO_USER = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
	
	//杨梅图片
	public String PICTURE_URL = "http://travel.0768.gd/upload/store/2011-05/3584399618832500_.JPG";
	
	//自定义菜单设置
	public String MENU_CONFIG = "{"+
		     "\"button\":["+
		       "{"+	
		          "\"name\":\"追踪杨梅\","+
		          "\"sub_button\":["+
		           "{"+	
		               "\"type\":\"click\","+
		               "\"name\":\"实时追踪\","+
		               "\"key\":\"REAL_TRACK\""+
		            "},"+
		            "{"+
		               "\"type\":\"click\","+
		               "\"name\":\"历史追踪\","+
		               "\"key\":\"HISTORY_TRACK\""+
		            "}"+
		          "]"+
			    "},"+
		       "{"+	
			       "\"type\":\"click\","+
	               "\"name\":\"评价留言\","+
	               "\"key\":\"FEEDBACK\""+
			    "},"+
		       "{"+
	               "\"type\":\"view\","+
	               "\"name\":\"关于我们\","+
	               "\"url\":\"http://119.254.101.149/weChat/index.jsp\""+
	            "}]"+
			    "}";
}
