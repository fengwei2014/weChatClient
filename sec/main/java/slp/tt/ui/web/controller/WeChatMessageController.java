package slp.tt.ui.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import slp.bss.comn.war.web.BaseController;
import slp.tt.ui.common.util.BaseUtil;
import slp.tt.ui.common.util.BaseWeChatConfig;
import slp.tt.ui.service.MessageDispatcher;
import slp.tt.ui.service.MessageService;
/**
 * 微信后台信息接口
 * @author wei
 */
@Controller
@RequestMapping("/wechat")
public class WeChatMessageController extends BaseController implements BaseWeChatConfig{
	
	@RequestMapping(value="/message", method=RequestMethod.GET)
	@ResponseBody
	public void contentWeChat(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		try {
			if(BaseUtil.checkSignature(signature,timestamp,nonce)){
				out.print(echostr);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@RequestMapping(value="/message", method=RequestMethod.POST)
	@ResponseBody
	public void getWeChatMessage(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		//验证消息来自微信后台
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		
		try {
			if(BaseUtil.checkSignature(signature,timestamp,nonce)){
				out.print(echostr);
				
				//创建自定义菜单
				MessageService.createMenu(MENU_CONFIG);
				
				//接收微信后台的XML信息转成map
				Map<String,String> map = BaseUtil.xmlToMap(request);
				
				//分发信息并处理获得返回
				String returnMessage= MessageDispatcher.messageDispatcher(map);
				
				//输出给微信后台
				out.print(returnMessage);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();
	
	}
	
	
}