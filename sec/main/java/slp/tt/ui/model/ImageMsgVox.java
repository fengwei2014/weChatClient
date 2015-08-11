package slp.tt.ui.model;

/**
 * 发送图片消息模型
 * @author wei
 *
 */
public class ImageMsgVox extends MessageVox{
   
    private ImageMsgVo image;
	
	public ImageMsgVo getImage() {
		return image;
	}
	public void setImage(ImageMsgVo image) {
		this.image = image;
	}
}
