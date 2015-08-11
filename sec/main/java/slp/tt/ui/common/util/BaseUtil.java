package slp.tt.ui.common.util;

/**
 * 签名和token认证以及XML转Map
 * @author wei
 *
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import slp.tt.ui.model.MessageRule;

import com.google.gson.Gson;



public class BaseUtil {
	
	//token 验证签名
	private static final String token = "ttweixin";
	
	/**
	 * 验证服务器地址有效
	 * @throws NoSuchAlgorithmException 
	 * **/
	
	public static boolean checkSignature(String signature,String timestamp,String nonce) throws NoSuchAlgorithmException{
		String[] arr = new String[]{token,timestamp,nonce};
		
//		排序
		Arrays.sort(arr);
//		拼接字符串
		StringBuffer content = new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}
		
		//加密
		String temp = getSha1(content.toString());
		
//		签名比对
		return temp.equals(signature);
		
	}
	
	/**
	 * SHA1对字符串加密
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSha1(String str) throws NoSuchAlgorithmException {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j*2];
			int k = 0;
			for (int i=0; i<j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			
			return new String(buf);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * XML转化为Map
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		SAXReader reader = new SAXReader(); 
		InputStream ins = request.getInputStream();
		System.out.println("ins: "+ins);
			
		Document doc = null;
		try {
			doc = reader.read(ins);
			System.out.println("doc: "+doc);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();
		List<Element> list = root.elements();

	    for(Element e: list){
	    	map.put(e.getName(),e.getText());
	    }
		
		ins.close();
		return map;
		
	}
	
	/**
	 * 将Map转化为Json
	 * @param <E>
	 * 
	 * @param e
	 * @return String
	 */
	public static <T, E> String mapToJson(E e) {
	 Gson gson = new Gson();
	 String jsonStr = gson.toJson(e);
	 return jsonStr;
	}
	
	/**
	 * 将ArrayList转化为Json
	 * 
	 * @param map
	 * @return String
	 */
	public static <T> String listToJson(List<MessageRule> list) {
	 Gson gson = new Gson();
	 String jsonStr = gson.toJson(list);
	 return jsonStr;
	}
}
