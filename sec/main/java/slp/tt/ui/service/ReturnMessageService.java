package slp.tt.ui.service;

import java.util.Date;
import java.util.Map;

import slp.tt.ui.common.util.BaseWeChatConfig;
import slp.tt.ui.model.TextMessage;

public class ReturnMessageService implements BaseWeChatConfig{
	
	//返回文本信息
	public String returnTextMessage(Map<String, String> map) {
		// TODO Auto-generated method stub
		TextMessage textMessage = new TextMessage();
		textMessage.setFromUserName(map.get("ToUserName"));
		textMessage.setToUserName(map.get("FromUserName"));
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setContent(map.get("Content"));
		textMessage.setMsgType(MESSAGE_TYPE_TEXT);
	
		MessageFormatServiceImp<TextMessage> messageFormated = new MessageFormatServiceImp<TextMessage>();
		System.out.println("return message:\n"+messageFormated.messageToXml(textMessage));
		return messageFormated.messageToXml(textMessage);
	}

}
