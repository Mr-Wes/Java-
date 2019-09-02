package server;

import java.net.Socket;
import java.util.HashSet;

/**
 * 负责socket的管理
 * @author Administrator
 *
 */
public class SocketManager {

	private SocketConnection socketConnection = null;
	private HashSet<SocketConnection> set = new HashSet<SocketConnection>();
	
	private static class Hollder{
		private static final SocketManager INSTANCE = new SocketManager();
	}
	private SocketManager() {
		// TODO 新建一个线程，时刻检测是否有连接中断
	}
	public static final SocketManager getInstance() {
		return Hollder.INSTANCE;
	}
	
	/**
	 * 
	 * @param socket
	 * @return
	 */
	public boolean add(Socket socket) {
		// TODO 添加到列队中，并为其新建一个线程，负责监听数据
		socketConnection = new SocketConnection(socket);
		socketConnection.start();
		set.add(socketConnection);		
		return true;
	}
	
	/**
	 * 
	 */
	public void clean() {
		// TODO 清空列队，关闭socket的读写线程
	}
}
