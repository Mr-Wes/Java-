package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

/**
 * 采用单例模式（内部类方式）
 * @author Administrator
 *
 */
public class DataHandle {

	private static class Holder{
		private static final DataHandle INSTANCE = new DataHandle();
	}
	private Socket socket;
	private DataHandle() {
		//开启监听服务
		Properties server_properties = new Properties();
		try {
			server_properties.load(new FileInputStream(new File("")));
			String ip = server_properties.getProperty("ip");
			int port = Integer.parseInt(server_properties.getProperty("port"));
			//与服务器建立连接
			socket = new Socket(ip, port);
			
			//连接超时15秒，弹出提示框“连接超时，请检查网络连接”
			// TODO 连接服务器失败的提示框
			
			// TODO 新建自定义线程，负责监听从服务器来的数据
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}	
	public static final DataHandle getInstance() {
		return Holder.INSTANCE;
	}
	
	/**
	 * 向服务器发送登录指令
	 * @param user_name
	 * @param user_password
	 * @return：0-连接失败；-1-用户不存在；-2-登录密码错误；其他-登录成功
	 */
	public int testLogin(String user_name, String user_password) {
		if(socket==null) {
			return 0;
		}else {
			try {
				OutputStream out = socket.getOutputStream();
				String message = "login "+user_name+" "+user_password;
				out.write(message.getBytes());
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	/**
	 * 
	 * @param user_name
	 * @param user_password
	 * @param position
	 * @return
	 */
	public int registe(String user_name, String user_password, int position) {
		
		return 0;
	}

}
