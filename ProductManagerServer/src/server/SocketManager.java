package server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;

import controller.SharedData;

/**
 * ����socket�Ĺ���
 * ����ģʽ
 * @author Administrator
 *
 */
public class SocketManager {

	private SocketConnection socketConnection = null;
	private HashSet<SocketConnection> set = new HashSet<SocketConnection>();
	
	private static class Hollder{
		private static final SocketManager INSTANCE = new SocketManager();
	}
	
	public static final SocketManager getInstance() {
		return Hollder.INSTANCE;
	}
	
	private SocketManager() {
		// TODO �½�һ���̣߳�ʱ�̼���Ƿ��������ж�
	}
	
	/**
	 * ��socket�½�һ��SocketConnection����������뵽�ж���
	 * @param socket
	 * @return
	 * @throws IOException 
	 */
	public boolean add(Socket socket) throws IOException {
		socketConnection = new SocketConnection(socket);
		socketConnection.start();
		set.add(socketConnection);
		SharedData.getInstance().TextAreaAppend(socket.getInetAddress()+"���ӳɹ�\n");
		return true;
	}
	
	/**
	 * 
	 */
	public void clean() {
		// TODO ���鼯�ϣ�����SocketConnection��close����������жӣ��ر�socket�ļ����߳�
		
	}
}
