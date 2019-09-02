package server;

import java.net.Socket;
import java.util.HashSet;

/**
 * ����socket�Ĺ���
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
		// TODO �½�һ���̣߳�ʱ�̼���Ƿ��������ж�
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
		// TODO ��ӵ��ж��У���Ϊ���½�һ���̣߳������������
		socketConnection = new SocketConnection(socket);
		socketConnection.start();
		set.add(socketConnection);		
		return true;
	}
	
	/**
	 * 
	 */
	public void clean() {
		// TODO ����жӣ��ر�socket�Ķ�д�߳�
	}
}
