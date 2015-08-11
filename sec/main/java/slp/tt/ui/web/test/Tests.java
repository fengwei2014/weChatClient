package slp.tt.ui.web.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Tests {

	public static void main(String[] args) throws FileNotFoundException {
//		Properties weChatSet = new Properties();
//		FileInputStream ins = new FileInputStream("src/main/resources/wechat.properties");
//		try {
//			weChatSet.load(ins);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String vehicleInfoRemote = weChatSet.getProperty("vehicleInfoRemote");
//		
//		// 输出properties文件的内容
//		System.out.println("vehicleInfoRemote:" + vehicleInfoRemote);
		
//		String str= "{\"type\": \"image\", \"media_id\": _F1GBRaIizL0CpvEq5dwEL3qLUJgPOOdVBH22UYfXAYPPjDafAxDoerwWLiLyMj\", \"created_at\": 1434425620}";
//		int stratIndex = str.indexOf(", \"media_id");
//		int endIndex = str.indexOf(", \"created_at");
//		System.out.println("stratIndex:"+stratIndex);
//		System.out.println("endIndex:"+endIndex);
//		System.out.println("media_id:"+str.substring(stratIndex+14, endIndex-1));
		
		System.out.println(System.getProperty("catalina.home"));
		
	}

}
