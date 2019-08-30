package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class WriteThread extends Thread {

	private Socket socket = null;
	private OutputStream out = null;
	private OutputStreamWriter outputStreamWriter = null;
	
	public WriteThread(Socket socket) {
		this.socket = socket;
		// TODO Ð´Ïß³Ì
	}
	@Override
	public void run() {
		try {
			out = socket.getOutputStream();
			outputStreamWriter = new OutputStreamWriter(out, "UTF-8");
			
		} catch (IOException e) {
			e.printStackTrace();
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
