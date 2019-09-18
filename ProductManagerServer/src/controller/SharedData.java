package controller;

import java.net.Socket;
import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import server.SocketConnection;

/**
 * ʵ��ȫ�ֶ�ĳһ���ݵ�ʹ��
 * ����ģʽ
 * @author Administrator
 *
 */
public class SharedData {
	
	private TextArea text_area = null;
	private ListView<String> user_list = null;
	private HashMap<Socket, SocketConnection> map = null;
	
	private static class Holder{
		private static final SharedData INSTANCE = new SharedData();
	}
	private SharedData() {

	}
	
	public static SharedData getInstance() {
		return Holder.INSTANCE;
	}
	
	public void setTextArea(TextArea text_area) {
		this.text_area = text_area;
	}
	
	public void setList(ListView<String> arg0) {
		this.user_list = arg0;
	}
	
	public void setMap(HashMap<Socket, SocketConnection> arg0) {
		this.map = arg0;
	}
	
	/**
	 * ���������1����ʾ����
	 * @param str��׷�ӵ��ַ���
	 */
	public synchronized void TextAreaAppend(String str) {
		//�Ż� �������ݵ�ͬ�����Ż�
		text_area.appendText(str);
	}

	public void addList(Socket socket, String str1, String str2) {
		SocketConnection sc = map.get(socket);
		sc.user_name = str1;
		ObservableList<String> olist = user_list.getItems();
		olist.add(String.format("%1$-10s", str1)+socket.getInetAddress());
		user_list.setItems(olist);
		//sys String.format("%1$14s", str1)
		System.out.println(String.format("%1$14s", str1));
	}

}
