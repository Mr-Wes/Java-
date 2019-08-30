package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.control.TextArea;

/**
 * 控制服务的启动，关闭
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
	 * 开启监听服务
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
	 * 监听Socket线程
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
				text_area.appendText(socket.getInetAddress().getHostAddress()+"请求连接...\n");
				SocketManager.getInstance().add(socket);
			}					
		}
	}
	
	/**
	 * 关闭服务
	 */
	public void close() {
		startThread.stop();
		SocketManager.getInstance().clean();//清空所有连接
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
