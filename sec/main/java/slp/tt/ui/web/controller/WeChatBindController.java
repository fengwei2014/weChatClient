package slp.tt.ui.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import slp.bss.comn.war.web.BaseController;
import slp.comn.model.UserInfo;
/**
 * 微信openId绑定到平台用户
 * @author wei
 */
@Controller
@RequestMapping("/bind")
public class WeChatBindController extends BaseController {
	

	@RequestMapping(value="/bindWeChat", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request,
			HttpServletResponse response,@RequestBody Map<String, String> map) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		String userName = map.get("userName");
		String passWord  = map.get("passWord");
		String pageUrl = request.getHeader("REFERER");
		String weChatOpenId = pageUrl.substring(pageUrl.indexOf("=")+1, pageUrl.length());
		System.out.println("wechatOpenId: "+weChatOpenId);
		String returnJsonString = null;
		
		//用户接口 TO-DO,验证是否为平台用户，并取得用户IP
		Boolean flag = true;
		Long userId= (long) 2333;
		
		
		if (flag) {
			returnJsonString = "{\"success\":true,\"msg\":\"bind success\"}";
			
		}else{
			returnJsonString = "{\"success\":false,\"msg\":\"bind failed\"}";
		}
	
		return returnJsonString;
	}
	
	
}