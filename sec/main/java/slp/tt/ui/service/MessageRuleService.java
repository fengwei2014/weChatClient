package slp.tt.ui.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import slp.tt.ui.common.util.BaseUtil;
import slp.tt.ui.common.util.DBConfig;
import slp.tt.ui.dao.BaseDao;
import slp.tt.ui.model.MessageRule;

public class MessageRuleService {
	
	/**
	 * 获取所有回复规则
	 * @return
	 */
	public static String getAllRules() {
		ResultSet ret = null;
		List<MessageRule> list = new ArrayList<MessageRule>();
		String sql = "select * from texttalkrule";//SQL语句
		try {
			ret = BaseDao.queryResult(sql, null);
			while (ret.next()) {
				MessageRule mrRule = new MessageRule();
				String uid = ret.getString(1);
				String ucode = ret.getString(2);
				String ureply = ret.getString(3);
				mrRule.setId(uid);
				mrRule.setCode(ucode);
				mrRule.setReply(ureply);
				list.add(mrRule);
			}
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return BaseUtil.listToJson(list);
	}
	
	public static List<MessageRule> getAllRulesResult() {
		ResultSet ret = null;
		List<MessageRule> list = new ArrayList<MessageRule>();
		String sql = "select * from texttalkrule";//SQL语句
		try {
			ret = BaseDao.queryResult(sql, null);
			while (ret.next()) {
				MessageRule mrRule = new MessageRule();
				String uid = ret.getString(1);
				String ucode = ret.getString(2);
				String ureply = ret.getString(3);
				mrRule.setId(uid);
				mrRule.setCode(ucode);
				mrRule.setReply(ureply);
				list.add(mrRule);
			}
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	public static Boolean createRule(String code, String reply) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		MessageRule mrMessageRule = new MessageRule();
		mrMessageRule.setCode(code);
		mrMessageRule.setReply(reply);
		List<String> list  = new ArrayList<String>();
		String sql = "insert into texttalkrule (code,reply) values (?,?)";//SQL语句
		list.add(code);
		list.add(reply);
		return BaseDao.updateResult(sql, list);
	}
	
	/**
	 * 检测回复规则是否已存在
	 * @param str
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Boolean ruleIncluded(String str) throws ClassNotFoundException, SQLException{
		Boolean flag = false;
		Class.forName(DBConfig.DRIVERCLASSNAME);
		Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
		String sql = "select reply from texttalkrule where code = ?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, str);
		ResultSet rSet = pst.executeQuery();
		System.out.println("rSet: "+rSet);
		if (rSet != null) {
			flag = true;
		}
		return flag;
	}

}
