package slp.tt.ui.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.fileupload.disk.DiskFileItem;

import slp.tt.ui.common.util.BaseWeChatConfig;
import slp.tt.ui.dao.BaseDao;
import slp.tt.ui.model.AllUser;

import com.google.gson.Gson;

public class BaseService implements BaseWeChatConfig{
	/**
	 * 信息规则验证(车牌)
	 * @param str
	 * @return
	 * 
	 */
	public static boolean filterMessageVehicleNo(String str){
		
		//验证车牌
		String vehicleNoExgString = "^[\\u4E00-\\u9FA5][\\da-zA-Z]{6}";
		Pattern pattern = Pattern.compile(vehicleNoExgString); 
	    Matcher matcher = pattern.matcher(str); 
	    return matcher.matches();
		
	}
	

	/**
	 * 信息规则验证(运单)
	 * @param str
	 * @return
	 * 
	 */
	public static boolean filterMessageWayBill(String str){
		
		//验证运单
		String wayBillExgString = "^[Y][D][\\d]{7}";
		Pattern pattern = Pattern.compile(wayBillExgString); 
	    Matcher matcher = pattern.matcher(str); 
	    return matcher.matches();
		
	}
	
	/**
	 * 获取经纬度对应的位置信息
	 * @param String
	 * @return
	 * 
	 */
	public static String getVehicleAddress(String lng, String lat) {
		// TODO Auto-generated method stub
		String urlString = "http://api.map.baidu.com/geocoder/v2/?ak=6UcbcaBTk6Dm5lauUlljqlAA&location="+lat+","+lng+"&output=json";
		System.out.println("getAddressByLonAndLat: "+urlString);
		String vehicleAddress =  postInfo(urlString);
		int addressSta,addresssEnd;
		addressSta = vehicleAddress.indexOf("address");
		addresssEnd = vehicleAddress.indexOf("\"business");
		
		return vehicleAddress.substring(addressSta+10, addresssEnd-2);
	}
	
	/**
	 * post请求
	 * @param url
	 * @return String
	 * 
	 */
	public static String postInfo(String searchVehicleUrl) {
		BufferedReader in = null;
		try {
			URL gpsUrl = new URL(searchVehicleUrl);
			in = new BufferedReader(new InputStreamReader(gpsUrl.openStream(),"UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			String str = sb.toString();
			return str;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * post json格式数据
	 * @param baseUrl
	 * @param json
	 * @throws IOException
	 */
	public static void postJson(String baseUrl,String json) throws IOException{
		  URL url = new URL(baseUrl);
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setDoOutput(true);
          connection.setDoInput(true);
          connection.setRequestMethod("POST");
          connection.setUseCaches(false);
          connection.setInstanceFollowRedirects(true);
          connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
          connection.connect();

          //POST请求
          DataOutputStream out = new DataOutputStream(connection.getOutputStream());
          
          out.write(json.getBytes("UTF-8"));
          out.flush();
          out.close();

          //读取响应
          BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          String lines;
          StringBuffer sb = new StringBuffer("");
          while ((lines = reader.readLine()) != null) {
              lines = new String(lines.getBytes(), "utf-8");
              sb.append(lines);
          }
          System.out.println(sb);
          reader.close();
          // 断开连接
          connection.disconnect();
	}
	
	/**
	 * 推送文章消息
	 */
	public static String getAccessToken() {
		// TODO Auto-generated method stub
		//get Access_token  https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxeca22eeb46126b54&secret=4a732ebfb02a6d2b63305401f2053025
		String grant_typeString = "client_credential";
		String getAccessTokenUrl = CREATE_MENU_TOKENURL+"?grant_type="+grant_typeString+"&appid="+APP_ID+"&secret="+APP_SECRET;
		BufferedReader in = null;
		int accIndexBefore,accIndexAfter;
		String access_token = null;
		
		try {
			URL gpsUrl = new URL(getAccessTokenUrl);
			in = new BufferedReader(new InputStreamReader(gpsUrl.openStream(),"UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			String str = sb.toString();
			accIndexBefore = str.indexOf("access_token\":");
			accIndexAfter = str.indexOf(",\"expires_in");
			access_token = str.substring(accIndexBefore+15, accIndexAfter-1);
			
			System.out.println(str.substring(accIndexBefore+15, accIndexAfter-1));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return access_token;
	}
	
	/**
	 * post json格式数据
	 * @param baseUrl
	 * @param json
	 * @throws IOException
	 */

	public static String postFile(String baseUrl,DiskFileItem dfile,String fileName,ByteArrayOutputStream bas) throws IOException{
		//读取文件上传到服务器
		
		URL url=new URL(baseUrl);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("content-type", "text/html");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Charset", "UTF-8");  
         
        String BOUNDARY = "-----------WebKitFormBoundary" + System.currentTimeMillis();  
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);
        
        StringBuilder sb = new StringBuilder();  
        sb.append("--"); // 必须多两道线  
        sb.append(BOUNDARY);  
        sb.append("\r\n");  
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ fileName + "\"\r\n");  
        sb.append("Content-Type:"+dfile.getContentType()+"\r\n\r\n"); 
        
        System.out.println("sb.head: "+sb.toString());
        
        byte[] head = sb.toString().getBytes("utf-8");  
        // 获得输出流  
        OutputStream out = new DataOutputStream(connection.getOutputStream());  
        // 输出表头  
        out.write(head);  
        // 文件正文部分  
        // 把文件已流文件的方式 推入到url中
        byte[] byteArray = bas.toByteArray();
        for (int i = 0; i < byteArray.length; i++) {
			out.write(byteArray[i]);
		}
        bas.close();
        //结尾部分  
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
        
        System.out.println("sb.head: "+("\r\n--" + BOUNDARY + "--\r\n"));
        out.write(foot);  
        out.flush();  
        out.close(); 
        StringBuffer buffer = new StringBuffer();  
        BufferedReader reader = null; 
        String result = null;
        try {  
	        // 定义BufferedReader输入流来读取URL的响应  
	        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
	        String line = null;  
	        while ((line = reader.readLine()) != null) {  
		        //System.out.println(line);  
		        buffer.append(line);  
	        }
	        
	        result = buffer.toString();  
        } catch (IOException e) {  
	        System.out.println("发送POST请求出现异常！" + e);  
	        e.printStackTrace();  
	        throw new IOException("数据读取异常");  
        } finally {  
	        if(reader!=null){  
	        	reader.close();  
	        }  
        }
        
        int stratIndex = result.indexOf("media_id");
		int endIndex = result.indexOf("created_at");
		String media_id = result.substring(stratIndex+11, endIndex-3);
		System.out.println("media_id:"+media_id);
		return media_id;  
       
        //读取URLConnection的响应
//        DataInputStream in=new DataInputStream(fileInputStream);
//        String res;
//        StringBuilder sb = new StringBuilder("");
//        while ((res = in.readLine()) != null) {
//			sb.append(res.trim());
//		}
//		String str = sb.toString();
//		System.out.println("str: "+str);
//		out.flush();
//		return result;
//			System.out.println("File: "+file1);
//			((CommonsMultipartFile) file1).getFileItem();
//			File file = new File("");
//			/** 
//	        * 第一部分 
//	        */  
//	        URL urlObj = new URL("http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+ BaseService.getAccessToken() + "&type=image");  
//	        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();  
//	        con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式  
//	        con.setDoInput(true);  
//	        con.setDoOutput(true);  
//	        con.setUseCaches(false); // post方式不能使用缓存  
//	        // 设置请求头信息  
//	        con.setRequestProperty("Connection", "Keep-Alive");  
//	        con.setRequestProperty("Charset", "UTF-8");  
//	        // 设置边界  
//	        String BOUNDARY = "----------" + System.currentTimeMillis();  
//	        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);  
//	        // 请求正文信息  
//	        // 第一部分：  
//	        StringBuilder sb = new StringBuilder();  
//	        sb.append("--"); // 必须多两道线  
//	        sb.append(BOUNDARY);  
//	        sb.append("\r\n");  
//	        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ file.getName() + "\"\r\n");  
//	        sb.append("Content-Type:application/octet-stream\r\n\r\n");  
//	        byte[] head = sb.toString().getBytes("utf-8");  
//	        // 获得输出流  
//	        OutputStream out = new DataOutputStream(con.getOutputStream());  
//	        // 输出表头  
//	        out.write(head);  
//	        // 文件正文部分  
//	        // 把文件已流文件的方式 推入到url中  
//	        DataInputStream in = new DataInputStream(new FileInputStream(file));  
//	        int bytes = 0;  
//	        byte[] bufferOut = new byte[1024];  
//	        while ((bytes = in.read(bufferOut)) != -1) {  
//	        out.write(bufferOut, 0, bytes);  
//	        }  
//	        in.close();  
//	        // 结尾部分  
//	        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
//	        out.write(foot);  
//	        out.flush();  
//	        out.close();  
//	        StringBuffer buffer = new StringBuffer();  
//	        BufferedReader reader = null; 
//	        String result = null;
//	        try {  
//		        // 定义BufferedReader输入流来读取URL的响应  
//		        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
//		        String line = null;  
//		        while ((line = reader.readLine()) != null) {  
//			        //System.out.println(line);  
//			        buffer.append(line);  
//		        }
//		        
//		        result = buffer.toString();  
//	        } catch (IOException e) {  
//		        System.out.println("发送POST请求出现异常！" + e);  
//		        e.printStackTrace();  
//		        throw new IOException("数据读取异常");  
//	        } finally {  
//		        if(reader!=null){  
//		        	reader.close();  
//		        }  
//	        }  
//			return result;  
//	    }
	}

	/**
	 * 上传视频文件
	 * @param baseUrl
	 * @param dfile
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	public static String postVideoFile(String baseUrl, DiskFileItem dfile, String fileName) throws IOException {
		URL url=new URL(baseUrl);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("content-type", "text/html");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Charset", "UTF-8");  
         
        String BOUNDARY = "-----------WebKitFormBoundary" + System.currentTimeMillis();  
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);
        
        StringBuilder sb = new StringBuilder();  
        sb.append("--"); // 必须多两道线  
        sb.append(BOUNDARY);  
        sb.append("\r\n");  
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ fileName + "\"\r\n");  
        sb.append("Content-Type:"+dfile.getContentType()+"\r\n\r\n"); 
        
        System.out.println("sb.head: "+sb.toString());
        
        byte[] head = sb.toString().getBytes("utf-8");  
        // 获得输出流  
        OutputStream out = new DataOutputStream(connection.getOutputStream());  
        // 输出表头  
        out.write(head);  
//      文件正文部分  
        // 把文件已流文件的方式 推入到url中  
        DataInputStream in = new DataInputStream(dfile.getInputStream());  
        int bytes = 0;  
        byte[] bufferOut = new byte[1024];  
        while ((bytes = in.read(bufferOut)) != -1) {  
        	out.write(bufferOut, 0, bytes);  
        }  
        in.close();
        //结尾部分  
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
        
        System.out.println("sb.head: "+("\r\n--" + BOUNDARY + "--\r\n"));
        out.write(foot);  
        out.flush();  
        out.close(); 
        StringBuffer buffer = new StringBuffer();  
        BufferedReader reader = null; 
        String result = null;
        try {  
	        // 定义BufferedReader输入流来读取URL的响应  
	        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
	        String line = null;  
	        while ((line = reader.readLine()) != null) {  
		        //System.out.println(line);  
		        buffer.append(line);  
	        }
	        
	        result = buffer.toString();  
        } catch (IOException e) {  
	        System.out.println("发送POST请求出现异常！" + e);  
	        e.printStackTrace();  
	        throw new IOException("数据读取异常");  
        } finally {  
	        if(reader!=null){  
	        	reader.close();  
	        }  
        }
        
        int stratIndex = result.indexOf("media_id");
		int endIndex = result.indexOf("created_at");
		String media_id = result.substring(stratIndex+11, endIndex-3);
		System.out.println("media_id:"+media_id);
		return media_id;  
	}
	
	/**
	 * 获取全部用户信息
	 * @param token
	 * @return
	 */
	public static List<String> getAllUserList(String token) {
		String getAllUserURL =  GET_ALL_USERS+"?access_token="+token+"&next_openid=";
		String returnString  = BaseService.postInfo(getAllUserURL);
		Gson gson = new Gson();
		AllUser allUser = gson.fromJson(returnString, AllUser.class);

		//获取全部关注用户列表
		List<String> openIdList = allUser.getData().getOpenid();
		return openIdList;
	}
	
	/**
	 * 检测用户是否是系统用户
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Boolean userCheck(String username,String password) throws ClassNotFoundException, SQLException {
		boolean flag = false;
		List<String> list = new ArrayList<String>();
		String sql = "select passWord from user where userName = ?";
		list.add(0, username);
		
		ResultSet rs = BaseDao.queryResult(sql, list);
		
		if (rs.next()) {
			if (password.equals(rs.getString("passWord"))) {
				flag = true;
			}
		}
		return flag;
	}
	
}

