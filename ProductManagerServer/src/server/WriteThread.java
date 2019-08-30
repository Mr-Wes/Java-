package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;

public class WriteThread extends Thread {

	private Socket socket = null;
	private OutputStream out = null;
	private OutputStreamWriter outputStreamWriter = null;
	private LinkedList<String> queue = new LinkedList<String>();
	
	public WriteThread(Socket socket) {
		this.socket = socket;
		// TODO Ð´Ïß³Ì
	}
	@Override
	public void run() {
		try {
			out = socket.getOutputStream();
			outputStreamWriter = new OutputStreamWriter(out, "UTF-8");
			String message = null;
			while(true) {
				if(!queue.isEmpty()) {
					message = queue.getFirst();
					outputStreamWriter.write(message);
					outputStreamWriter.flush();
					queue.removeFirst();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
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
