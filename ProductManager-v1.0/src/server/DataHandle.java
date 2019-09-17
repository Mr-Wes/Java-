package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * 采用单例模式（内部类方式）
 * 负责与服务器间的socket连接，数据的发送与接收
 * @author wjb
 *
 */
public class DataHandle {

	private Socket socket;
	private ReadThread read;
	private WriteThread write;
	
	private static class Holder{
		private static final DataHandle INSTANCE = new DataHandle();
	}
	
	private DataHandle() {
		//开启监听服务
		Properties server_properties = new Properties();
		try {
			server_properties.load(new FileInputStream(new File("./src/server/server.properties")));
			String ip = server_properties.getProperty("ip");
			int port = Integer.parseInt(server_properties.getProperty("port"));
			//与服务器建立连接
			socket = new Socket(ip, port);
			//连接超时15秒，弹出提示框“连接超时，请检查网络连接”
			// TODO 连接服务器失败的提示框
			
			//新建自定义线程，负责监听从服务器来的数据
			if(socket!=null) {
				write = new WriteThread(socket);
				read = new ReadThread(socket);
				write.start();
				read.start();
			} else {
				//TODO "客户端socket连接失败"
			}
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
	public void testLogin(String user_name, String user_password) {
		if(socket==null) {
			return;
		}else {
			String message = "login "+user_name+" "+user_password+"\n";
			write.setMessage(message);
		}
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
	
	public void close() {
		if(read.isAlive()) {
			try {
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			read.stop();
		}
		if(write.isAlive()) {
			try {
				write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			write.stop();
		}
	}
}
class ReadThread extends Thread {

	private Socket socket = null;
	private InputStream in = null;
	private InputStreamReader inputStreamReader = null;//将一个字节流中的字节解码成字符
	private BufferedReader buff = null;
	
	ReadThread(Socket s) throws IOException {
		this.socket = s;
		in = socket.getInputStream();
		inputStreamReader = new InputStreamReader(in, "UTF-8");
		buff = new BufferedReader(inputStreamReader);
	}
	
	@Override
	public void run() {
		try {
			String message;
			while((message = buff.readLine())!=null) {
				//读取接收到的信息，并处理
				//sys
				System.out.println("读到了"+message);
				MessageHandle.getInstance().handle(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void close() throws IOException {
		if(in!=null) {
			in.close();
		}
		if(inputStreamReader!=null) {
			inputStreamReader.close();
		}
		if(buff!=null) {
			buff.close();
		}
	}
}
class WriteThread extends Thread {

	private Socket socket = null;
	private OutputStream out = null;
	private OutputStreamWriter outputStreamWriter = null;
	private LinkedList<String> queue = new LinkedList<String>();
	
	WriteThread(Socket s) throws IOException {
		this.socket = s;
		out = socket.getOutputStream();
		outputStreamWriter = new OutputStreamWriter(out);
	}

	public synchronized void setMessage(String message) {
		queue.addLast(message);
	}
	
	@Override
	public void run() {
		String next = null;
		while(true) {
			try {
				next = queue.getFirst();
				outputStreamWriter.write(next);
				outputStreamWriter.flush();				
				queue.removeFirst();
				//sys
				System.out.println(next+"写完了");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchElementException e) {
				continue;
			}
		}
	}
	public void close() throws IOException {
		if(out!=null) {
			out.close();
		}
		if(outputStreamWriter!=null) {
			outputStreamWriter.close();
		}
	}
}
