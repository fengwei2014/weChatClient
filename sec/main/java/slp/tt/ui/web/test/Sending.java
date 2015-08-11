package slp.tt.ui.web.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import slp.tt.ui.common.util.BaseWeChatConfig;
import slp.tt.ui.model.Article;
import slp.tt.ui.model.ArticleVo;
import slp.tt.ui.model.ArticlesMessage;
import slp.tt.ui.service.BaseService;
import slp.tt.ui.service.MessageService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Sending implements BaseWeChatConfig{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<String> userList = new ArrayList<String>();
		userList.add(0, "oYdcVt_pIx3V75AHNJY1fHWXY9Mc");
//		userList.add(1, "oYdcVt_EVPoj3ILjZHh8r7_WdwyA");
		
		String vehicleNo = "汽A22025";
		String vehicelInfo = MessageService.getVehicleInfo(vehicleNo);
		
		Map<String, String> vehicleMap = MessageService.handleVehicleInfoAndAddress(vehicelInfo);
		
		String vehicleAddress = vehicleMap.get("address");
		String lng = vehicleMap.get("lng");
		String lat = vehicleMap.get("lat");
		String vehicleLiveUrl = PROJECT_NAME+"/location.jsp?"+lng+","+lat+"&No="+vehicleNo+"&label="+vehicleAddress;
		System.out.println("vehicleLiveUrl: "+vehicleLiveUrl);

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		ArticlesMessage articleMessage = new ArticlesMessage();
		Article article = new Article();
		ArticleVo articleVo = new ArticleVo();
		List<Article> listArticles = new ArrayList<Article>();
		article.setTitle(vehicleNo);
		article.setDescription("杨梅实时信息");
		article.setPicurl(PICTURE_URL);
		article.setUrl(vehicleLiveUrl);
		listArticles.add(0, article);
		articleVo.setArticles(listArticles);
		articleMessage.setNews(articleVo);
		
		articleMessage.setMsgtype(MESSAGE_TYPE_NEWS);
		
		String baseUrl = SEND_MESSAGE_TO_USER+"?access_token="+BaseService.getAccessToken();
		for(String tmp:userList){
			articleMessage.setTouser(tmp);
			String jsonString = gson.toJson(articleMessage);
 	        System.out.println(tmp);
 	        BaseService.postJson(baseUrl, jsonString);
	    }
	}

}
