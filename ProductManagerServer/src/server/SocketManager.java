package server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import controller.SharedData;

/**
 * 负责socket的管理
 * 单例模式
 * @author Administrator
 *
 */
public class SocketManager {

	private SocketConnection socketConnection = null;
	private HashMap<Socket, SocketConnection> map = new HashMap<Socket, SocketConnection>();
	
	private static class Hollder{
		private static final SocketManager INSTANCE = new SocketManager();
	}
	
	public static final SocketManager getInstance() {
		return Hollder.INSTANCE;
	}
	
	private SocketManager() {
		//分享map到SharedData
		SharedData.getInstance().setMap(map);
		// TODO 新建一个线程，时刻检测是否有连接中断
	}
	
	/**
	 * 由socket新建一个SocketConnection，并将其加入到列队中
	 * @param socket
	 * @return
	 * @throws IOException 
	 */
	public boolean add(Socket socket) throws IOException {
		socketConnection = new SocketConnection(socket);
		socketConnection.start();
		map.put(socket, socketConnection);
		SharedData.getInstance().TextAreaAppend(socket.getInetAddress()+"连接成功\n");
		return true;
	}
	
	/**
	 * 调用所有连接对象的close方法，并清空列表
	 * @throws IOException 
	 */
	public void clean() throws IOException {
		//历遍集合，调用SocketConnection的close方法，清空列队，关闭socket的监听线程
		for (SocketConnection value : map.values()) {
			value.close();
		}
		map.clear();
		
	}
}
