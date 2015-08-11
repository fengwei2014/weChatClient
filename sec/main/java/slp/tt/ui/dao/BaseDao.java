package slp.tt.ui.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import slp.tt.ui.common.util.DBConfig;

/**
 * 操作数据库
 */

public class BaseDao implements DBConfig{

	public void insertVal(String sql) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		
	}

	/**
	 * 查询sql并返回结果集
	 * @param sql
	 * @param list
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static ResultSet queryResult(String sql, List<String> list) throws ClassNotFoundException, SQLException {
		Class.forName(DBConfig.DRIVERCLASSNAME);
		Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
		PreparedStatement pst = connection.prepareStatement(sql);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				pst.setString(i+1, list.get(i));
			}
		}
		ResultSet rs = pst.executeQuery();
		return rs;
	}
	
	/**
	 * 创建或者更新操作
	 * @param sql
	 * @param list
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Boolean updateResult(String sql, List<String> list) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Boolean flag = false;
		Class.forName(DBConfig.DRIVERCLASSNAME);
		Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
		PreparedStatement pst = connection.prepareStatement(sql);
		for (int i = 0; i < list.size(); i++) {
			pst.setString(i+1, list.get(i));
		}		
		if (pst.executeUpdate() == 1) {
			flag = true;
		}
		return flag;
	}
	
	
	
}
