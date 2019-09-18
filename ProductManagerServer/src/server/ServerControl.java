package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controller.SharedData;

/**
 * 控制Socket监听服务的启动，关闭
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
	 * 新建线程，开启监听服务
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
					//检测到socket后，控制台输出日志，添加到SocketManager中
					SharedData.getInstance().TextAreaAppend(socket.getInetAddress().getHostAddress()+"请求连接...\n");
					SocketManager.getInstance().add(socket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}					
		}
	}
	
	/**
	 * 关闭服务
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
