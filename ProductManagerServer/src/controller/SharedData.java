package controller;

import javafx.scene.control.TextArea;

/**
 * ʵ��ȫ�ֶ�ĳһ���ݵ�ʹ��
 * ����ģʽ
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
	 * ���������1����ʾ����
	 * @param str��׷�ӵ��ַ���
	 */
	public synchronized void TextAreaAppend(String str) {
		//�Ż� �������ݵ�ͬ�����Ż�
		text_area.appendText(str);
	}

}
