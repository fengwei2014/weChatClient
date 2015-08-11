package slp.tt.ui.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import slp.tt.ui.common.util.BaseWeChatConfig;
import slp.tt.ui.model.AllUser;
import slp.tt.ui.model.Article;
import slp.tt.ui.model.ArticleVo;
import slp.tt.ui.model.ArticlesMessage;
import slp.tt.ui.model.ImageMsgVo;
import slp.tt.ui.model.ImageMsgVox;
import slp.tt.ui.model.TextMsgVo;
import slp.tt.ui.model.TextMsgVox;
import slp.tt.ui.model.VideoMsgVo;
import slp.tt.ui.model.VideoMsgVox;
import slp.tt.ui.model.WeChatConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.slf.open.api.util.OpenUtil;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class WebMgrService implements BaseWeChatConfig{
	
	private ApplicationContext weChatConfigcontext;
	
	/**
	 * 视频上传
	 * @param token
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String uploadVideo(DiskFileItem dfile, String fileName, String mediaType) throws IOException {
		// TODO Auto-generated method stub
		String token = BaseService.getAccessToken();
		String baseUrl = UPLOAD_FILE_URL+"?access_token="+token+"&type="+mediaType;
		return BaseService.postVideoFile(baseUrl, dfile, fileName);
	}
	/**
	 * 图片上传
	 * @param token
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String uploadImage(DiskFileItem dfile,String fileName,String	mediaType) throws IOException {
		String token = BaseService.getAccessToken();
		String baseUrl = UPLOAD_FILE_URL+"?access_token="+token+"&type="+mediaType;
		//压缩文件流
//		ByteArrayOutputStream bas = compressImage(dfile,1024,1024);
		
		return BaseService.postVideoFile(baseUrl,dfile,fileName);
	}

	/**
	 * 压缩图片
	 * @param dfile
	 * @return
	 * @throws IOException 
	 * @throws ImageFormatException 
	 */
	private ByteArrayOutputStream compressImage(DiskFileItem dfile,int outputWidth,int outputHeight) throws ImageFormatException, IOException {
		 FileInputStream fs = new FileInputStream(dfile.getStoreLocation());
		 Image img = ImageIO.read(fs); 
		 
		 int newWidth; int newHeight; 
		
		 // 为等比缩放计算输出的图片宽度及高度 
		 double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1; 
		 double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1; 
		 // 根据缩放比率大的进行缩放控制 
		 double rate = rate1 > rate2 ? rate1 : rate2; 
		 newWidth = (int) (((double) img.getWidth(null)) / rate); 
		 newHeight = (int) (((double) img.getHeight(null)) / rate); 
		
	 	 BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB); 
	 	
	 	/*
		 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
		 * 优先级比速度高 生成的图片质量比较好 但速度慢
		 */ 
	 	tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
	 	
	 	ByteArrayOutputStream bos = new ByteArrayOutputStream();  
      
	 	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos); 
	 	encoder.encode(tag); 
	 	  
	 	return bos;
	}


	/**
	 * 发送素材文件给关注用户
	 * @param token
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public void postImageToUser(String token, String media_id) throws IOException {
		
		List<String> openIdList = BaseService.getAllUserList(token);
		
		String sendUrl = SEND_MESSAGE_TO_USER+"?access_token="+token;
		
		//测试群发用户列表
		List<String> userList = new ArrayList<String>();
		userList.add(0, "oYdcVt_EVPoj3ILjZHh8r7_WdwyA");
		userList.add(1, "oYdcVt_pIx3V75AHNJY1fHWXY9Mc");
		
		//准备素材
		ImageMsgVox imageVox = new ImageMsgVox();
		ImageMsgVo imageVo = new ImageMsgVo();
		imageVo.setMedia_id(media_id);
		imageVox.setImage(imageVo);
		imageVox.setMsgtype("image");
		
		Gson gson = new Gson();
		//群发
		for(String tmp:userList){
			imageVox.setTouser(tmp);
 	        System.out.println(tmp);
// 	     	String jsonString = 
// 	     			"{"+
// 	     			    "\"touser\": \""+tmp+"\", "+
// 	     			    "\"msgtype\": \"text\","+ 
// 	     			    "\"text\": {"+
// 	     			        "\"content\": \""+content+"\""+
// 	     			    "}"+
// 	     			"}";
 	     			
 	     	BaseService.postJson(sendUrl, gson.toJson(imageVox));
	    }
	}
	

	/**
	 * 发送视频文件给关注用户
	 * @param token
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public void postVideoToUser(String token, String media_id) throws IOException {
		
		List<String> openIdList = BaseService.getAllUserList(token);
		String sendUrl = SEND_MESSAGE_TO_USER+"?access_token="+token;
		
		//测试群发用户列表
		List<String> userList = new ArrayList<String>();
		userList.add(0, "oYdcVt_EVPoj3ILjZHh8r7_WdwyA");
		userList.add(1, "oYdcVt_pIx3V75AHNJY1fHWXY9Mc");
		
		//准备素材
		VideoMsgVo vMsgVo = new VideoMsgVo();
		VideoMsgVox vmsgVox = new VideoMsgVox();
		vMsgVo.setMedia_id(media_id);
		vMsgVo.setTitle("测试视频文件的发送");
		vMsgVo.setDescription("应该会看到一段小视频");
		vMsgVo.setThumb_media_id("");
		
		vmsgVox.setVideo(vMsgVo);
		vmsgVox.setMsgtype("video");
		
		Gson gson = new Gson();
		//群发小视频
		for(String tmp:openIdList){
			vmsgVox.setTouser(tmp);
 	     	BaseService.postJson(sendUrl, gson.toJson(vmsgVox));
	    }
		
	}
	
	/**
	 * 发送文本信息
	 * @param token
	 * @param textMsg
	 * @throws IOException 
	 */
	public void sendMsg(String token, String textMsg) throws IOException {
		List<String> openIdList = BaseService.getAllUserList(token);
		String sendUrl = SEND_MESSAGE_TO_USER+"?access_token="+token;
		
		//测试群发用户列表
		List<String> userList = new ArrayList<String>();
		userList.add(0, "oYdcVt_EVPoj3ILjZHh8r7_WdwyA");
		userList.add(1, "oYdcVt_pIx3V75AHNJY1fHWXY9Mc");
		
		//准备素材
		TextMsgVox tmsVox = new TextMsgVox();
		TextMsgVo tmsVo = new TextMsgVo();
		tmsVox.setMsgtype("text");
		tmsVo.setContent(textMsg);
		tmsVox.setText(tmsVo);
		
		Gson gson = new Gson();
		//群发文本信息
		for(String tmp:openIdList){
			tmsVox.setTouser(tmp);
 	     	BaseService.postJson(sendUrl, gson.toJson(tmsVox));
	    }
	}
	
	/**
	 * 发送文章信息
	 * @param token
	 * @param textMsg
	 * @throws IOException 
	 */
	public void sendArticMsg(String token,String title,String desc) throws IOException {
		// TODO Auto-generated method stub
		weChatConfigcontext = new ClassPathXmlApplicationContext("wechat.xml");  
	    WeChatConfig weChatConfig = (WeChatConfig)weChatConfigcontext.getBean("weChatConfig");  
	    
		List<String> openIdList = BaseService.getAllUserList(token);
		List<String> userList = new ArrayList<String>();
		userList.add(0, "oYdcVt_pIx3V75AHNJY1fHWXY9Mc");
//		userList.add(1, "oYdcVt_EVPoj3ILjZHh8r7_WdwyA");
		
		String vehicleNo = "沪AUW265";
		String vehicelInfo = MessageService.getVehicleInfo(vehicleNo);
		
		Map<String, String> vehicleMap = MessageService.handleVehicleInfoAndAddress(vehicelInfo);
		
		String vehicleAddress = vehicleMap.get("address");
		String lng = vehicleMap.get("lng");
		String lat = vehicleMap.get("lat");
		String vehicleLiveUrl = weChatConfig.getProjectName()+"/location.jsp?"+lng+","+lat+"&No="+vehicleNo+"&label="+vehicleAddress;
		System.out.println("vehicleLiveUrl: "+vehicleLiveUrl);

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		ArticlesMessage articleMessage = new ArticlesMessage();
		Article article = new Article();
		ArticleVo articleVo = new ArticleVo();
		List<Article> listArticles = new ArrayList<Article>();
		article.setTitle(title);
		article.setDescription(desc);
		article.setPicurl(PICTURE_URL);
		article.setUrl(vehicleLiveUrl);
		listArticles.add(0, article);
		articleVo.setArticles(listArticles);
		articleMessage.setNews(articleVo);
		
		articleMessage.setMsgtype(MESSAGE_TYPE_NEWS);
		
		String baseUrl = SEND_MESSAGE_TO_USER+"?access_token="+token;
		for(String tmp:openIdList){
			articleMessage.setTouser(tmp);
			String jsonString = gson.toJson(articleMessage);
 	        System.out.println(tmp);
 	        BaseService.postJson(baseUrl, jsonString);
	    }	
	}
	
	/**
	 * 获取历史数据
	 * @return
	 * @throws IOException
	 */
	public String getHistoryTrace() throws IOException {
		return historyResult();
	}
	
	/**
	 * 获取历史温度记录
	 * @return
	 * @throws IOException 
	 */
	public String getHistoryTemp() throws IOException {
		String historyResult = historyResult();
		return historyResult;
	}
	
	/**
	 * 返回历史数据结果
	 * @return
	 * @throws IOException
	 */
	protected String historyResult() throws IOException {
		weChatConfigcontext = new ClassPathXmlApplicationContext("wechat.xml");  
	    WeChatConfig weChatConfig = (WeChatConfig)weChatConfigcontext.getBean("weChatConfig");
	    
		String clientTokenString = null;
		long timestamp = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.HOUR, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Long bTime = (cal.getTimeInMillis()/1000);
		Long eTime = timestamp;
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("appId", "test");
		paramMap.put("timestamp",timestamp);
		paramMap.put("bTime",bTime);
		paramMap.put("eTime",eTime);
		paramMap.put("vehicleNo", "沪AUW265");
		
		
		Map<String, Object> map1 = OpenUtil.buildParamsMap(paramMap);
		String sign= OpenUtil.buildSign(map1, "123456");
		System.out.println("sign:"+sign);
		clientTokenString = sign;
		

		String searchVehicleUrl = null;
		try {
			searchVehicleUrl = weChatConfig.getHistoryTrackUrl()+"?appId=test&timestamp="+paramMap.get("timestamp")+"&vehicleNo="+URLEncoder.encode("沪AUW265", "UTF-8")+
					"&bTime="+paramMap.get("bTime")+"&eTime="+paramMap.get("eTime")+"&sign="+clientTokenString;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("searchVehicleUrl: "+searchVehicleUrl);
		String returnString = BaseService.postInfo(searchVehicleUrl);
		System.out.println(returnString);
		return returnString;
	}
	
}
