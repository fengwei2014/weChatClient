package slp.tt.ui.common.util;

/**
 * 数据库操作常量配置
 * @author wei
 *
 */
public interface DBConfig {
	
	/**
	  * 本地开发配置
	  */
	 /** 数据库驱动 */
//	 public static final String DRIVERCLASSNAME = "com.mysql.jdbc.Driver";
//	 /** 数据库URL */
//	 public static final String URL = "jdbc:mysql://localhost:3306/wechat";
//	 /** 数据库用户名 */
//	 public static final String USERNAME = "root";
//	 /** 数据库密码 */
//	 public static final String PASSWORD = "123456";
	 
	 /**
	  * 发布配置
	  */
	 /** 数据库驱动 */
	 public static final String DRIVERCLASSNAME = "com.mysql.jdbc.Driver";
	 /** 数据库URL */
	 public static final String URL = "jdbc:mysql://192.168.200.7:3306/wechat?useUnicode=true&characterEncoding=UTF-8";
	 /** 数据库用户名 */
	 public static final String USERNAME = "ttdemo";
	 /** 数据库密码 */
	 public static final String PASSWORD = "ttdemo2015";
}
