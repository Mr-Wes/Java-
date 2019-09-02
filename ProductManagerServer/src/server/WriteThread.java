package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;

public class WriteThread extends Thread {

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
	
	
}
