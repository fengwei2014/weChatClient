/**
 * 
 */
package slp.tt.ui.web.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import slp.tt.ui.common.util.BaseWeChatConfig;
import slp.tt.ui.service.BaseService;

import com.ibm.slf.open.api.util.OpenUtil;

/**
 * @author wei
 *
 */
public class MyTest implements BaseWeChatConfig{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String clientTokenString = null;
		long timestamp = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.HOUR, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Long bTime = cal.getTimeInMillis();
		Long eTime = timestamp;
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("appId", "test");
		paramMap.put("timestamp",timestamp);
		paramMap.put("bTime",bTime);
		paramMap.put("eTime",eTime);
		paramMap.put("vehicleNo", "沪AUW265");
		
		
		Map<String, Object> map1 = OpenUtil.buildParamsMap(paramMap);
		String sign= OpenUtil.buildSign(map1, "123456");
		System.out.println("sign:"+sign);
		clientTokenString = sign;
		

		String searchVehicleUrl = null;
		try {
			searchVehicleUrl = GET_VEHICLE_HISTORY_REMOTE+"?appId=test&timestamp="+paramMap.get("timestamp")+"&vehicleNo="+URLEncoder.encode("沪AUW265", "UTF-8")+
					"&bTime="+paramMap.get("bTime")+"&eTime="+paramMap.get("eTime")+"&sign="+clientTokenString;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("searchVehicleUrl: "+searchVehicleUrl);
		String returnString = BaseService.postInfo(searchVehicleUrl);
		System.out.println(returnString);
	}

}
