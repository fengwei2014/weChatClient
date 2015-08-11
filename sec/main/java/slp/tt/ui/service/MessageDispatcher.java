package slp.tt.ui.service;

/**
 * 消息分发器，处理来自微信的消息
 * @author wei
 *
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import slp.tt.ui.common.util.BaseWeChatConfig;
import slp.tt.ui.model.MessageRule;
import slp.tt.ui.model.WeChatConfig;

public class MessageDispatcher implements BaseWeChatConfig{
	
	private static ApplicationContext weChatConfigcontext;
	/**
	 * 信息类型分发(暂时支持文本信息，对其他信息进行过滤。TO-DO:支持其他类型信息处理)
	 * @param request
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * 
	 */
	public static String messageDispatcher(Map<String, String> map) throws ClassNotFoundException, SQLException, IOException{
		String msgType = map.get("MsgType");
		
		System.out.println("I have got a message from wechat: "+map.get("FromUserName"));
		System.out.println("Message Type: "+ msgType);
		System.out.println("Time: "+ new Date());
		
		switch (msgType) {
		case MESSAGE_TYPE_TEXT:
			return MessageService.talkAndReply(map);
			
		case MESSAGE_EVENT:
			return MessageService.handleEvent(map);
			
		case MESSAGE_TYPE_IMAGE:

			break;
		case MESSAGE_TYPE_VOICE:
			System.out.println("i have got a voice message: "+map);
			return MessageService.handleVoice(map);
			
		case MESSAGE_TYPE_VIDEO:
			
			break;
		case MESSAGE_TYPE_SHORTVIDEO:
			
			break;
		case MESSAGE_TYPE_LOCATION:
			
			break;
		case MESSAGE_TYPE_LINK:
			
			break;
		
		default:
			break;
		}
		return null;

	}
	
	
	/**
	 * 事件类型分发
	 * @param map
	 * @return 
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * 
	 */
	public static String eventDispatcher(Map<String, String> map) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		String event = map.get("Event");
		System.out.println("event:"+event);
		switch (event) {
		
		case MESSAGE_EVENT_SUBSCRIBE:
		
			return EventService.handleSubscribeEvent(map);
			
		case MESSAGE_EVENT_CLICK:
			//验证账号是否已被绑定
//			User u= BindService.weChatBindCheck(weChatOpenId);
//			if (u.getIsBanded()) {
//				return EventService.handleClickEvent(map,u.getUserName());
//			} else {
//				return EventService.handleBindEvent(map);
//			}
			return EventService.handleClickEvent(map,null);
			
		case MESSAGE_EVENT_VIEW:
			//验证账号是否已被绑定
			
			break;
		case MESSAGE_EVENT_SCANCODE_PUSH:
			
			break;
		case MESSAGE_EVENT_SCANCODE_WAITMSG:
			
			break;
		case MESSAGE_EVENT_PIC_SYSPHOTO:
			
			break;
		case MESSAGE_EVENT_PIC_SYSPHOTO_OR_ALBUM:
			
			break;
		case MESSAGE_EVENT_PIC_WEIXIN:
			
			
		case MESSAGE_EVENT_LOCATION_SELECT:
			
			break;
		
		default:
			break;
		}
		return null;

	}

	/**
	 * 点击事件分发
	 * @param map 
	 * @param map
	 * @return 
	 * @return
	 * @throws IOException 
	 * 
	 */
	public static String clickEventDispatcher(Map<String, String> map,String userName) throws IOException {
		weChatConfigcontext = new ClassPathXmlApplicationContext("wechat.xml");  
	    WeChatConfig weChatConfig = (WeChatConfig)weChatConfigcontext.getBean("weChatConfig");
		if (userName != null) {
			userName = userName+"： 您好！";
		}else{
			userName = "";
		}
		String eventKey = map.get("EventKey");
		ReturnMessageService rm = new ReturnMessageService();
		
		switch (eventKey) {
		case MENU_KEY_LIVE_MESSAGE:
			String returnMsg = userName+"活点地图为您服务\n"+
					"测试群发消息：\n"+
					"------------------------\n"+
					"<a href=\""+weChatConfig.getProjectName()+"/sendToAllUser.jsp\">点击查看消息详细</a>";
			map.put("Content", returnMsg);
			return rm.returnTextMessage(map);
		case MENU_KEY_REAL_TRACK:
			String content = "沪AUW265";
			String vehicelInfo = MessageService.getVehicleInfo(content);
			
			Map<String, String> vehicleMap = MessageService.handleVehicleInfoAndAddress(vehicelInfo);
			
			String vehicleAddress = vehicleMap.get("address");
			String lng = vehicleMap.get("lng");
			String lat = vehicleMap.get("lat");
			String totalKm = vehicleMap.get("totalKm");
			
			String returnVehicleStr = userName+"活点地图为您服务\n"+
					  "------------------------\n"+
					  "您查询的车牌是: \n"+content+"\n\n"+
					  "车辆实时信息为：\n\n"+
					  "地址："+vehicleAddress+"\n"+
					  "经度："+lng+"\n"+
					  "经度："+lat+"\n"+
					  "总里程："+totalKm+"\n"+
//					  "车辆位置："+PROJECT_NAME+"/location.jsp?"+lng+","+lat+"&No="+content+"&label="+vehicleAddress+"";
					  "车辆位置："+"<a href=\""+weChatConfig.getProjectName()+"/location.jsp?"+lng+","+lat+"&No="+content+"&label="+vehicleAddress+"\">点击查看</a>";
			map.put("Content", returnVehicleStr);
			return rm.returnTextMessage(map);
		case MENU_KEY_HISTORY_TRACK:
			String historyString = userName+"活点地图为您服务\n"+
					  "------------------------\n"+
					  "历史轨迹和温度曲线：\n"+
//					  PROJECT_NAME+"/tempChart.jsp\"";
					  "<a href=\""+weChatConfig.getProjectName()+"/tempChart.jsp\">点击查看</a>";
			map.put("Content", historyString);
			return rm.returnTextMessage(map);
		case MENU_KEY_HELP:
			List<MessageRule> list  = MessageRuleService.getAllRulesResult();
			System.out.println("before:=====================================");
			System.out.println("resultString:"+list);
			
			System.out.println("after:=====================================");
			String allRule = "";
			for (int i = 0; i < list.size(); i++) {
				MessageRule mRule = new MessageRule();
				mRule = list.get(i);
				allRule +=mRule.getCode()+" ===> "+mRule.getReply()+"\n";
			}
			String returnHelpStr = userName+"活点地图为您服务\n"+"请按以下格式回复: \n"+
					"------------------------\n"+
					allRule+"\n"+
					"------------------------\n"+
					"本系统处于测试调试阶段\n";
			map.put("Content", returnHelpStr);
			return rm.returnTextMessage(map);
		case MENU_KEY_FEEDBACK:
			String returnFeedBack = userName+"欢迎您给活点地图留言\n\n"+
					"<a href=\""+weChatConfig.getProjectName()+"/feedBack.jsp?wechat="+map.get("FromUserName")+"\">评价并留言</a>";
			map.put("Content", returnFeedBack);
			return rm.returnTextMessage(map);
					
		default:
			break;
		}
		
		return null;
		
			
	}
	
}
