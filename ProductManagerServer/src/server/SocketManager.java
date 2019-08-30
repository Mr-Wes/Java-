package server;

import java.net.Socket;
import java.util.HashSet;

/**
 * ����socket�Ĺ���
 * @author Administrator
 *
 */
public class SocketManager {

	private HashSet<Connection> set = new HashSet<Connection>();
	
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
		set.add(new Connection(socket,new ReadThread(socket),new WriteThread(socket)));
		return true;
	}
	
	/**
	 * 
	 */
	public void clean() {
		// TODO ����жӣ��ر�socket�Ķ�д�߳�
	}
	
	class Connection {
		Socket socket;
		ReadThread read;
		WriteThread write;
		Connection(Socket socket,ReadThread read,WriteThread write){
			this.socket = socket;
			this.read = read;
			this.write = write;
		}
	}
}
