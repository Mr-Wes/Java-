package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import controller.MessageHandle;

/**
 * 每个连接创建一个实例，包含属性socket，readthread，writethread
 * @author Administrator
 *
 */
public class SocketConnection {
	
	public String user_name;
	public String user_password;
	Socket socket;
	ReadThread read;
	WriteThread write;
	
	SocketConnection(Socket socket) throws IOException{
		this.socket = socket;
		write = new WriteThread(socket);
		read = new ReadThread(socket, write);

	}
	
	public void start() {
		read.start();
		write.start();
	}
	
	public void close() throws IOException {
		read.stop();//关闭线程
		write.stop();
		read.close();//关闭输入流
		write.close();//关闭输出流
	}
	
	public String toString() {
		return null;
		
	}
}
class ReadThread extends Thread {

	private Socket socket = null;
	private WriteThread write = null;
	private InputStream in = null;
	private InputStreamReader inputStreamReader = null;//将一个字节流中的字节解码成字符
	private BufferedReader buff = null;
	
	ReadThread(Socket s, WriteThread w) throws IOException {
		this.socket = s;
		this.write = w;
		in = socket.getInputStream();
		inputStreamReader = new InputStreamReader(in, "UTF-8");
		buff = new BufferedReader(inputStreamReader);
	}
	
	@Override
	public void run() {
		try {
			String message;
			String result;
			//为每个连接实例创建一个处理函数
			MessageHandle handle = new MessageHandle();
			while((message = buff.readLine())!=null) {
				//sys
				System.out.println("读到了"+message);
				result = handle.handle(socket, message);
				if(result!=null&&!(result.equals(""))) {
					//处理结果不为空情况下，写入输出流
					write.setMessage(result);
				}				
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
	
	public WriteThread(Socket socket) throws IOException {
		this.socket = socket;
		out = this.socket.getOutputStream();
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
			} catch (NoSuchElementException e) {
				continue;
			} catch (IOException e) {
				e.printStackTrace();
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
