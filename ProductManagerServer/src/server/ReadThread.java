package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import controller.MessageHandle;

public class ReadThread extends Thread {

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
				new MessageHandle().handle(message);
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
