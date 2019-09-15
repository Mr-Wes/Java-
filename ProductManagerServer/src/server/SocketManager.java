package server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;

import javafx.scene.control.TextArea;

/**
 * 负责socket的管理
 * 单例模式
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
		// TODO 新建一个线程，时刻检测是否有连接中断
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
		text_area.appendText(socket.getInetAddress()+"连接成功\n");
		return true;
	}
	
	/**
	 * 
	 */
	public void clean() {
		// TODO 清空列队，关闭socket的读写线程
	}
}
