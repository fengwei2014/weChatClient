package slp.tt.ui.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import slp.tt.ui.common.util.BaseWeChatConfig;

public class EventService implements BaseWeChatConfig{
	private static String projectName;
	
	public EventService() throws IOException{
		Properties weChatSet = new Properties();
		try {
			FileInputStream ins = new FileInputStream("src/main/resources/wechat.properties");
			weChatSet.load(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		projectName = weChatSet.getProperty("name");
	}
	/**
	 * 处理点击事件
	 * @param map
	 * @return
	 * @throws IOException 
	 */
	public static String handleClickEvent(Map<String, String> map,String userName) throws IOException {
		// TODO Auto-generated method stub
		return MessageDispatcher.clickEventDispatcher(map,userName);
	}
	
	
	
	/**
	 * 处理订阅关注事件
	 * @param map
	 * @return
	 */
	public static String handleSubscribeEvent(Map<String, String> map) {
		// TODO Auto-generated method stub
		ReturnMessageService rm = new ReturnMessageService();
		
		String returnSubStr = SUB_BASE_STRING;
		map.put("Content", returnSubStr);
		
		return rm.returnTextMessage(map);
	}



	public static String handleBindEvent(Map<String, String> map) {
		// TODO Auto-generated method stub
		ReturnMessageService rm = new ReturnMessageService();
		
		String returnBindStr = "您还未绑定活点地图的账号(查车查货尽在掌握！)\n\n"+
				"<a href=\""+projectName+"/bind.jsp?wechat="+map.get("FromUserName")+"\">点击这里绑定账号</a>";
		map.put("Content", returnBindStr);
		
		return rm.returnTextMessage(map);
	}

}
