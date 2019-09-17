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
 * ÿ�����Ӵ���һ��ʵ������������socket��readthread��writethread
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
		read.stop();//�ر��߳�
		write.stop();
		read.close();//�ر�������
		write.close();//�ر������
	}
	
	public String toString() {
		return null;
		
	}
}
class ReadThread extends Thread {

	private Socket socket = null;
	private WriteThread write = null;
	private InputStream in = null;
	private InputStreamReader inputStreamReader = null;//��һ���ֽ����е��ֽڽ�����ַ�
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
			//Ϊÿ������ʵ������һ��������
			MessageHandle handle = new MessageHandle();
			while((message = buff.readLine())!=null) {
				//sys
				System.out.println("������"+message);
				result = handle.handle(socket, message);
				if(result!=null&&!(result.equals(""))) {
					//��������Ϊ������£�д�������
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
				System.out.println(next+"д����");
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
