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
					text_area.appendText(socket.getInetAddress().getHostAddress()+"��������...\n");
					SocketManager.getInstance().add(socket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}					
		}
	}
	
	/**
	 * �رշ���
	 */
	public void close() {
		if(startThread!=null&&startThread.isAlive()) {
			startThread.stop();
		}		
		SocketManager.getInstance().clean();//�����������
		try {
			if(server!=null&&server.isBound()) {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
