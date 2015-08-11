package slp.tt.ui.web.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import slp.tt.ui.common.util.DBConfig;

public class SQL11 implements DBConfig{
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
	String sql = null;
	Connection conn = null;
	PreparedStatement pst = null;
	String returnResult = null;
	Class.forName(DBConfig.DRIVERCLASSNAME);
	conn = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);//获取连接
	
	sql = "insert into feedback (feedBackContent,createTime,weChatOpenId) values (?,?,?)";//SQL语句
	pst = conn.prepareStatement(sql);//准备执行语句
//	baseDao.insertVal(sql);
//	pst.setString(1, feedback);
	pst.setString(1, "测试汉字！");
	String ctime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.sql.Date(new Date().getTime()));
	pst.setString(2, ctime);
	pst.setString(3, null);
	int result = pst.executeUpdate();
	}
}
