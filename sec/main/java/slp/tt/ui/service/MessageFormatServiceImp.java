package slp.tt.ui.service;

import com.thoughtworks.xstream.XStream;


/**
 * 接受信息处理
 * @author wei
 *
 */
public class MessageFormatServiceImp<T> implements MessageFormatService<T> {


	/**
	 * 处理消息返回：拼接成微信指定接收的xml格式
	 */
	@Override
	public String messageToXml(T t) {
		XStream xstream = new XStream();
		xstream.alias("xml",t.getClass());
		return xstream.toXML(t);
	}

}
