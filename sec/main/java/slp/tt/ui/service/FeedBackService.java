package slp.tt.ui.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import slp.tt.ui.common.util.DBConfig;

public class FeedBackService {

	public String saveFeedBack(String feedback,String weChatOpenId) throws SQLException {
		// TODO Auto-generated method stub
//		BaseDao baseDao = new BaseDao();
		String sql = null;
		Connection conn = null;
		PreparedStatement pst = null;
		String returnResult = null;
		
		try {
			Class.forName(DBConfig.DRIVERCLASSNAME);
			conn = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);//获取连接
			
			sql = "insert into feedback (feedBackContent,createTime,weChatOpenId) values (?,?,?)";//SQL语句
			pst = conn.prepareStatement(sql);//准备执行语句
//			baseDao.insertVal(sql);
			pst.setString(1, feedback);
//			pst.setString(1, "测试汉字！");
			String ctime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.sql.Date(new Date().getTime()));
			pst.setString(2, ctime);
			pst.setString(3, weChatOpenId);
			int result = pst.executeUpdate();
			if (result == 1) {
				System.out.println("insert feedback data success!");
				returnResult = "{\"success\":true,\"msg\":\"成功提交反馈意见！\"}";
				String sqlWriteRepoter = "select userId from user where weChatOpenId = ?";
				pst = conn.prepareStatement(sqlWriteRepoter);
				pst.setString(1, weChatOpenId);
			} else {
				System.out.println("insert feedback data failed!");
				returnResult = "{\"success\":false,\"msg\":\"提交反馈意见失败！\"}";
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//指定连接类型
		return returnResult;
	}

}
