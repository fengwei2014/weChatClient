package slp.tt.ui.service;

/**
 * 消息格式化接口
 * @author wei
 *
 */
public interface MessageFormatService<T> {
	/**
	 * 消息对象变为XML
	 * @param 消息对象泛型
	 * @return String
	 * 
	 */
	public String messageToXml(T t);
	
}
