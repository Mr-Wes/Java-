package controller;

import javafx.scene.control.TextArea;

/**
 * 实现全局对某一数据的使用
 * 单例模式
 * @author Administrator
 *
 */
public class SharedData {
	
	private TextArea text_area = null;
	
	private static class Holder{
		private static final SharedData INSTANCE = new SharedData();
	}
	private SharedData() {

	}
	
	public void init(TextArea text_area) {
		this.text_area = text_area;
	}
	
	public static SharedData getInstance() {
		return Holder.INSTANCE;
	}
	
	/**
	 * 负责主面板1的显示更新
	 * @param str：追加的字符串
	 */
	public synchronized void TextAreaAppend(String str) {
		//优化 共享数据的同步锁优化
		text_area.appendText(str);
	}

}
