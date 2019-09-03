package server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;

import javafx.scene.control.TextArea;

/**
 * ����socket�Ĺ���
 * @author Administrator
 *
 */
public class SocketManager {

	private SocketConnection socketConnection = null;
	private HashSet<SocketConnection> set = new HashSet<SocketConnection>();
	private TextArea text_area;
	
	private static class Hollder{
		private static final SocketManager INSTANCE = new SocketManager();
	}
	
	private SocketManager() {
		// TODO �½�һ���̣߳�ʱ�̼���Ƿ��������ж�
	}
	
	public static final SocketManager getInstance() {
		return Hollder.INSTANCE;
	}
	
	public void setTextArea1(TextArea text_area) {
		this.text_area = text_area;
	}
	
	/**
	 * 
	 * @param socket
	 * @return
	 * @throws IOException 
	 */
	public boolean add(Socket socket) throws IOException {
		socketConnection = new SocketConnection(socket);
		socketConnection.start();
		set.add(socketConnection);
		text_area.appendText(socket.getInetAddress()+"���ӳɹ�\n");
		return true;
	}
	
	/**
	 * 
	 */
	public void clean() {
		// TODO ����жӣ��ر�socket�Ķ�д�߳�
	}
}
