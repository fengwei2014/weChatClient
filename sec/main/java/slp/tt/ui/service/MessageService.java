package slp.tt.ui.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import slp.tt.ui.common.util.BaseWeChatConfig;
import slp.tt.ui.dao.BaseDao;
import slp.tt.ui.model.WeChatConfig;

import com.ibm.slf.open.api.util.OpenUtil;

public class MessageService implements BaseWeChatConfig{
	private static ApplicationContext weChatConfigcontext;
	/**	
	 * 创建菜单
	 * @throws IOException 
	 */
	public static void createMenu(String menuBodyData) throws IOException {
		// TODO Auto-generated method stub
		String access_tokenString = BaseService.getAccessToken();
		// create menu https://api.weixin.qq.com/cgi-bin/menu/create?access_token=QixD4p4agka33tJT4vJIrOQ09VQMYhKTSHhkV02qq9N-p1cW-F4OxvYbrbS8RTPBXm3H4ZIPuZLyI6pmeHNXhUBbYWZ8I8kcgxfEAhgPymA
		String createMenuUrl = CREATE_MENU_URL+"?access_token="+access_tokenString;
		BaseService.postJson(createMenuUrl, menuBodyData);
	}
	
	/**
	 * 文本规则处理
	 * @param String
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * 
	 */
	public static String handleContent(Map<String, String> map) throws ClassNotFoundException, SQLException, FileNotFoundException {
		weChatConfigcontext = new ClassPathXmlApplicationContext("wechat.xml");  
	    WeChatConfig weChatConfig = (WeChatConfig)weChatConfigcontext.getBean("weChatConfig");
		
		String content = map.get("Content");
		System.out.println("map: "+map);
		ReturnMessageService rm = new ReturnMessageService();
		
		if (BaseService.filterMessageVehicleNo(content)) {
			
			String vehicelInfo = getVehicleInfo(content);
			
			Map<String, String> vehicleMap = handleVehicleInfoAndAddress(vehicelInfo);
			
			String vehicleAddress = vehicleMap.get("address");
			String lng = vehicleMap.get("lng");
			String lat = vehicleMap.get("lat");
			String totalKm = vehicleMap.get("totalKm");
			
			String returnVehicleStr = "活点地图为您服务\n"+
					  "------------------------\n"+
					  "您查询的车牌是: \n"+content+"\n\n"+
					  "车辆实时信息为：\n\n"+
					  "地址："+vehicleAddress+"\n"+
					  "经度："+lng+"\n"+
					  "经度："+lat+"\n"+
					  "总里程："+totalKm+"\n"+
					  "车辆位置："+"<a href=\""+weChatConfig.getProjectName()+"/location.jsp?"+lng+","+lat+"&No="+content+"&label="+vehicleAddress+"\">点击查看</a>";
			map.put("Content", returnVehicleStr);
			rm.returnTextMessage(map);
//			生成静态地图： http://api.map.baidu.com/staticimage?center=121.632909,29.887084&width=320&height=568&zoom=14
			
//			http://api.map.baidu.com/staticimage?center=121.632909,29.887084&width=320&height=568&zoom=14&markers=121.632909,29.887084&scale=2&markerStyles=l,A|m
//			查车url  http://10.98.0.194:8080/public/v3/GetGpsInfo?appId=test&timestamp=1429867556783&vehicleNo=%E6%B5%99B00437&sign=C6BB79F165F3DD278CB808D7B26993F5FC9E5FF8
			
		} else if(BaseService.filterMessageWayBill(content)){
			String wayBillNo = content.substring(2, content.length());
			String wayBillAddress = null;

			String returnWayBillStr = "活点地图为您服务\n"+
									  "------------------------\n"+
									  "您查询的运单是: \n"+wayBillNo+"\n\n"+
										"地址："+wayBillAddress+"\n"+
										"运单位置："+"<a href=\"http://api.map.baidu.com/staticimage?center="+
										121.632909+","+29.887084+
										"&width=320&height=568&zoom=15&scale=2&markers="+
										121.632909+","+29.887084+
										"&markerStyles=l,A|m"+"\">点击查看</a>\n"+
									  "------------------------\n";
			map.put("Content", returnWayBillStr);
		} else if (MessageRuleService.ruleIncluded(content)) {
			String returnStr = "活点地图为您服务\n"+"请按以下格式回复: \n"+
					"------------------------\n"+
					"1.查车请回复车牌号：\n"+
					"如 浙B00437\n"+
					"2.查运单请回复运单号：\n"+
					"如 YD1234567\n"+
					"------------------------\n"+
					"本系统处于测试调试阶段\n";
			map.put("Content", returnStr);
		}else{
			String returnStr = "活点地图为您服务\n"+"请按以下格式回复: \n"+
					"------------------------\n"+
					"1.查车请回复车牌号：\n"+
					"如 浙B00437\n"+
					"2.查运单请回复运单号：\n"+
					"如 YD1234567\n"+
					"------------------------\n"+
					"本系统处于测试调试阶段\n";
			map.put("Content", returnStr);
		}
		return rm.returnTextMessage(map);
	}
	
	/**
	 * 回复对话规则
	 * @param map
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String talkAndReply(Map<String, String> map) throws ClassNotFoundException, SQLException{
		String content = map.get("Content");
		ReturnMessageService rm = new ReturnMessageService();
		String returnStr = "未找到对应的应答";
		if(MessageRuleService.ruleIncluded(content)){
			String sql = "select reply from texttalkrule where code = ?";
			List<String> list = new ArrayList<String>();
			list.add(content);
			ResultSet rSet = BaseDao.queryResult(sql, list);
			if (rSet.next()) {
				returnStr = rSet.getString("reply");
			}
			map.put("Content", returnStr);
		}
		return rm.returnTextMessage(map);
	}
	/**
	 * 反编译经纬度位置信息
	 * @param vehicelInfo
	 * @return
	 */
	public static Map<String, String> handleVehicleInfoAndAddress(String vehicelInfo) {
		// TODO Auto-generated method stub
		String lng = null,lat  = null,totalKm = null,vehicleAddress = null;
		Map<String,String> map=new HashMap<String,String>();
		if (vehicelInfo.length() != 0) {
			int lngStart = vehicelInfo.indexOf("longitude\":");
			int lngEnd = vehicelInfo.indexOf(",\"lat");
			int latEnd = vehicelInfo.indexOf(",\"totalKM");
			int totalKmStart = vehicelInfo.indexOf("totalKM\":");
			int totalKmEnd = vehicelInfo.indexOf(",\"satelliteNum");
			
			if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
				lng = vehicelInfo.substring(lngStart + 11, lngEnd);
				lat = vehicelInfo.substring(lngEnd + 12, latEnd);
				vehicleAddress = BaseService.getVehicleAddress(lng,lat);
				totalKm = vehicelInfo.substring(totalKmStart+9, totalKmEnd)+"m";
				map.put("lng", lng);
				map.put("lat",lat);				
				map.put("address", vehicleAddress);
				map.put("totalKm", totalKm);
			};
		}
		return map;
	}

	//获取车辆信息
	public static String getVehicleInfo(String content) throws FileNotFoundException {
		// TODO Auto-generated method stub
		weChatConfigcontext = new ClassPathXmlApplicationContext("wechat.xml");  
	    WeChatConfig weChatConfig = (WeChatConfig)weChatConfigcontext.getBean("weChatConfig");
		String clientTokenString = null;
		long timestamp = System.currentTimeMillis();
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appId", "test");
		paramMap.put("timestamp", timestamp);
//		paramMap.put("bTime", 1425881182792l);
//		paramMap.put("eTime", 1425881882792l);
		paramMap.put("vehicleNo", "沪AUW265");
		try {
			Map<String, Object> map1 = OpenUtil.buildParamsMap(paramMap);
			String sign= OpenUtil.buildSign(map1, "123456");
			System.out.println("sign:"+sign);
			clientTokenString = sign;
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		String searchVehicleUrl = null;
		try {
			searchVehicleUrl = weChatConfig.getVehicleInfoUrl()+"?appId=test&timestamp="+paramMap.get("timestamp")+"&vehicleNo="+URLEncoder.encode(content, "UTF-8")+"&sign="+clientTokenString;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("searchVehicleUrl: "+searchVehicleUrl);
		String returnString = BaseService.postInfo(searchVehicleUrl);
		return returnString;
	}
	
	/**
	 * 图片规则处理
	 * @param str
	 * @return
	 * 
	 */
	public static String handleImg(String str) {
		return str;
		
	}
	
	/**
	 * 语音规则处理
	 * @param str
	 * @return
	 * 
	 */
	public static String handleVoice(Map<String, String> map) {
		String content = map.get("Recognition");
		System.out.println("Recognition: "+content);
		ReturnMessageService rm = new ReturnMessageService();
		
		if(content.length() != 0 ){
			map.put("Content", "活点地图为您服务\n"+
					"------------------------\n"+
					"你发送的消息为：\n"+
					content+"\n"+
					"------------------------\n"+
					"本系统处于测试调试阶段\n");
			
		}else{
			map.put("Content", "活点地图为您服务\n"+
					"------------------------\n"+
					"你发送的消息未识别出来，请重新发送……\n"+
					"------------------------\n"+
					"本系统处于测试调试阶段\n");
		}
		return rm.returnTextMessage(map);
	}

	/**
	 * 视频规则处理
	 * @param str
	 * @return
	 * 
	 */
	public static String handleVideo(String str) {
		return str;
		
	}

	/**
	 * 小视频规则处理
	 * @param str
	 * @return
	 * 
	 */
	public static String handleShortVideo(String str) {
		return str;
		
	}
	

	/**
	 * 地理位置规则处理
	 * @param str
	 * @return
	 * 
	 */
	public static String handleLocation(String str) {
		return str;
		
	}

	/**
	 * 链接规则处理
	 * @param str
	 * @return
	 * 
	 */
	public static String handleLink(String str) {
		return str;
		
	}

	/**
	 * 接收事件处理规则
	 * @param map
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * 
	 */
	public static String handleEvent(Map<String, String> map) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		//点击菜单事件拉取消息
		return MessageDispatcher.eventDispatcher(map);
		
	}
	
	/** 
	 * 接收运单并查询  TO-DO
	 * @param String 
	 * @return
	 * 
	 */
	public static String getWayBillInfo(String wayBill) {
		// TODO Auto-generated method stub
		return null;
	}
}
