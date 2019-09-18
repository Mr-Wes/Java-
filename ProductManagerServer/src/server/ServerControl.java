package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controller.SharedData;

/**
 * ����Socket����������������ر�
 * @author Administrator
 *
 */
public class ServerControl {
	
	private ServerSocket server;
	private int port = 9950;
	private StartServerThread startThread;
	
	private static class Holder{
		private static final ServerControl INSTANCE = new ServerControl();
	}
	private ServerControl() {	
		
	}
	public static final ServerControl getInstance() {
		return Holder.INSTANCE;
	}
	
	/**
	 * �½��̣߳�������������
	 * @return
	 */
	public boolean startServer() {
		try {
			server = new ServerSocket(port);
			startThread = new StartServerThread();
			startThread.start();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * ����Socket�߳�
	 * @author Administrator
	 *
	 */
	public class StartServerThread extends Thread {
		
		private Socket socket;
		
		@Override
		public void run() {
			while(true) {
				try {
					socket = server.accept();
					//��⵽socket�󣬿���̨�����־����ӵ�SocketManager��
					SharedData.getInstance().TextAreaAppend(socket.getInetAddress().getHostAddress()+"��������...\n");
					SocketManager.getInstance().add(socket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}					
		}
	}
	
	/**
	 * �رշ���
	 * @throws IOException 
	 */
	public void close() throws IOException {
		if(server!=null&&server.isBound()) {
			server.close();
		}
		server = null;
		if(startThread!=null&&startThread.isAlive()) {
			startThread.socket.close();
			startThread.stop();
		}		
		
	}
}
