package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;

import controller.MessageHandle;

public class SocketConnection {
	Socket socket;
	ReadThread read;
	WriteThread write;
	SocketConnection(Socket socket){
		this.socket = socket;
		read = new ReadThread(socket);
		write = new WriteFunction(socket);
	}
	
	public void start() {
		read.start();
		write.start();
	}
	
	public void close() throws IOException {
		read.close();
		write.close();
	}
}
class ReadThread extends Thread {

	private Socket socket = null;
	private InputStream in = null;
	private InputStreamReader inputStreamReader = null;//将一个字节流中的字节解码成字符
	private BufferedReader buff = null;
	
	ReadThread(Socket socket) {
		this.socket = socket;
		// TODO 读线程
	}
	@Override
	public void run() {
		try {
			in = socket.getInputStream();
			inputStreamReader = new InputStreamReader(in, "UTF-8");
			buff = new BufferedReader(inputStreamReader);
			String message;
			while((message = buff.readLine())!=null) {
				new MessageHandle().handle(socket, message);
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
class WriteFunction {

	private Socket socket = null;
	private OutputStream out = null;
	private OutputStreamWriter outputStreamWriter = null;
	private LinkedList<String> queue = new LinkedList<String>();
	
	public WriteFunction(Socket socket) {
		this.socket = socket;
		// TODO 写线程
	}
	public synchronized void setMessage(String message) {
		queue.addLast(message);
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