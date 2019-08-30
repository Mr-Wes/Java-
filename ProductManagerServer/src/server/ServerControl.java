package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.control.TextArea;

/**
 * ���Ʒ�����������ر�
 * @author Administrator
 *
 */
public class ServerControl {
	
	private ServerSocket server;
	private int port = 9950;
	private StartServerThread startThread;
	private TextArea text_area;
	
	private static class Holder{
		private static final ServerControl INSTANCE = new ServerControl();
	}
	private ServerControl() {		
	}
	public static final ServerControl getInstance() {
		return Holder.INSTANCE;
	}
	
	public void setTextArea1(TextArea text_area) {
		this.text_area = text_area;
	}
	
	/**
	 * ������������
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
				} catch (IOException e) {
					e.printStackTrace();
				}
				text_area.appendText(socket.getInetAddress().getHostAddress()+"��������...\n");
				SocketManager.getInstance().add(socket);
			}					
		}
	}
	
	/**
	 * �رշ���
	 */
	public void close() {
		startThread.stop();
		SocketManager.getInstance().clean();//�����������
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
