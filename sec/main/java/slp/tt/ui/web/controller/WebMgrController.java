package slp.tt.ui.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import slp.bss.comn.war.web.BaseController;
import slp.tt.ui.common.util.BaseWeChatConfig;
import slp.tt.ui.model.WeChatConfig;
import slp.tt.ui.service.BaseService;
import slp.tt.ui.service.FeedBackService;
import slp.tt.ui.service.WebMgrService;
/**
 * 微信后台信息接口
 * @author wei
 */
@Controller
@RequestMapping("/web")
public class WebMgrController extends BaseController implements BaseWeChatConfig{
	
	private ApplicationContext context;

	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public void contentWeChat(HttpServletRequest request,
			HttpServletResponse response,@RequestBody Map<String, String> map) throws IOException, ClassNotFoundException, SQLException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		String userName = map.get("userName");
		String passWord = map.get("passWord");
		
		context = new ClassPathXmlApplicationContext("wechat.xml");  
	    WeChatConfig weChatConfig = (WeChatConfig)context.getBean("weChatConfig");   
	    
		
		PrintWriter outWriter = response.getWriter();
		String returnStr;
		
		if (BaseService.userCheck(userName, passWord)) {
			returnStr = "{\"success\":true,\"msg\":\"登陆成功\"}";
		}else{
			returnStr = "{\"success\":false,\"msg\":\"用户名或者密码错误\"}";
		}
		outWriter.print(returnStr);
	}
	
	@RequestMapping(value="/uploadmedia", method=RequestMethod.POST)
	@ResponseBody
	public void getWeChatMessage(HttpServletRequest request,
			HttpServletResponse response,@RequestBody MultipartFile media) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		MultipartFile mpfile =  multipartHttpServletRequest.getFile("file");
		String fileName = mpfile.getOriginalFilename();
		CommonsMultipartFile cmfile= (CommonsMultipartFile)mpfile; 
	    DiskFileItem dfile = (DiskFileItem)cmfile.getFileItem();
	    String mediaType = dfile.getContentType();
	    String returnString = "";
		String media_id = "";
		WebMgrService webMgrService= new WebMgrService();
	    if (mediaType.indexOf("image") !=-1) {
	    	//图片格式限制为4M
	    	if (cmfile.getSize() > 4194304) {
	    		returnString = "{\"success\":false,\"msg\":\"请上传小于4M的图片文件\",\"media_id\":\""+media_id+"\",\"media_type\":\"image\"}";
			}else{
				media_id = webMgrService.uploadImage(dfile,fileName,"image");
				returnString = "{\"success\":true,\"msg\":\"上传成功\",\"media_id\":\""+media_id+"\",\"media_type\":\"image\"}";
			}
		}else if(mediaType.indexOf("video") !=-1){
			//视频限制为10M
			if (cmfile.getSize() > 20971520) {
				returnString = "{\"success\":false,\"msg\":\"请上传小于10M的视频文件\",\"media_id\":\""+media_id+"\",\"media_type\":\"video\"}";
			}else{
				media_id = webMgrService.uploadVideo(dfile,fileName,"video");
				returnString = "{\"success\":true,\"msg\":\"上传成功\",\"media_id\":\""+media_id+"\",\"media_type\":\"video\"}";
			}
		}
		out.print(returnString);
		out.close();
	}
	
	@RequestMapping(value="/posttouser", method=RequestMethod.POST)
	@ResponseBody
	public void postImageToUser(HttpServletRequest request,
			HttpServletResponse response,@RequestBody Map<String, String> map) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String token = BaseService.getAccessToken();
		String returnString = "";
		WebMgrService webMgrService= new WebMgrService();
		String media_id = map.get("media_id");
		String media_type = map.get("media_type");
		if (media_type.indexOf("image") != -1) {
			webMgrService.postImageToUser(token,media_id);
		}else if(media_type.indexOf("video") != -1){
			webMgrService.postVideoToUser(token,media_id);
		}
		returnString = "{\"success\":true,\"msg\":\"发送成功\",\"media_id\":\"\"}";
		out.print(returnString);
		out.close();
	}
	
	@RequestMapping(value="/sendmsg", method=RequestMethod.POST)
	@ResponseBody
	public void sendTextToUser(HttpServletRequest request,
			HttpServletResponse response,@RequestBody Map<String, String> map) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String token = BaseService.getAccessToken();
		String returnString = "";
		WebMgrService webMgrService= new WebMgrService();
		String textMsg = map.get("textMsg");
		try {
			webMgrService.sendMsg(token,textMsg);
			returnString = "{\"success\":true,\"msg\":\"发送成功\",\"media_id\":\"\"}";
		} catch (Exception e) {
			returnString = "{\"success\":false,\"msg\":\"发送失败\",\"media_id\":\"\"}";
			e.printStackTrace();
		}
		
		out.print(returnString);
		out.close();
	}
	
	@RequestMapping(value="/sendartic", method=RequestMethod.POST)
	@ResponseBody
	public void sendArticToUser(HttpServletRequest request,
			HttpServletResponse response,@RequestBody Map<String, String> map) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String token = BaseService.getAccessToken();
		String returnString = "";
		WebMgrService webMgrService= new WebMgrService();
		String title = map.get("articTitle");
		String desc = map.get("articDesc");
		try {
			webMgrService.sendArticMsg(token,title,desc);
			returnString = "{\"success\":true,\"msg\":\"发送成功\",\"media_id\":\"\"}";
		} catch (Exception e) {
			returnString = "{\"success\":false,\"msg\":\"发送失败\",\"media_id\":\"\"}";
			e.printStackTrace();
		}
		
		out.print(returnString);
		out.close();
	}
	
	@RequestMapping(value="/feedback", method=RequestMethod.POST)
	@ResponseBody
	public void createFeedBack(HttpServletRequest request,
			HttpServletResponse response,@RequestBody Map<String, String> map) throws IOException, ClassNotFoundException, SQLException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		String pageUrl = request.getHeader("REFERER");
		String weChatOpenId = pageUrl.substring(pageUrl.indexOf("=")+1, pageUrl.length());
		String feedback = map.get("feedback");
		PrintWriter outWriter = response.getWriter();
		FeedBackService fbs = new FeedBackService();
		String returnStr;
		try {
			returnStr = fbs.saveFeedBack(feedback,weChatOpenId);
			outWriter.println(returnStr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outWriter.close();
	}
	
	@RequestMapping(value="/history", method=RequestMethod.GET)
	@ResponseBody
	public void getHistoryTrace(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter outWriter = response.getWriter();
		WebMgrService webMgrService= new WebMgrService();
		String returnStr;
		returnStr = webMgrService.getHistoryTrace();
		outWriter.println(returnStr);
		outWriter.close();
	}
	
	@RequestMapping(value="/temp", method=RequestMethod.GET)
	@ResponseBody
	public void getTemp(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter outWriter = response.getWriter();
		WebMgrService webMgrService= new WebMgrService();
		String returnStr;
		returnStr = webMgrService.getHistoryTemp();
		outWriter.println(returnStr);
		outWriter.close();
	}
	
	
}