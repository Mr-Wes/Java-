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
 * ���õ���ģʽ���ڲ��෽ʽ��
 * ��������������socket���ӣ����ݵķ��������
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
		//������������
		Properties server_properties = new Properties();
		try {
			server_properties.load(new FileInputStream(new File("./src/server/server.properties")));
			String ip = server_properties.getProperty("ip");
			int port = Integer.parseInt(server_properties.getProperty("port"));
			//���������������
			socket = new Socket(ip, port);
			//���ӳ�ʱ15�룬������ʾ�����ӳ�ʱ�������������ӡ�
			// TODO ���ӷ�����ʧ�ܵ���ʾ��
			
			//�½��Զ����̣߳���������ӷ�������������
			if(socket!=null) {
				write = new WriteThread(socket);
				read = new ReadThread(socket);
				write.start();
				read.start();
			} else {
				//TODO "�ͻ���socket����ʧ��"
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
	 * ����������͵�¼ָ��
	 * @param user_name
	 * @param user_password
	 * @return��0-����ʧ�ܣ�-1-�û������ڣ�-2-��¼�����������-��¼�ɹ�
	 */
	public void testLogin(String user_name, String user_password) {
		if(socket==null) {
			return;
		}else {
			String message = "login "+user_name+" "+user_password+"\n";
			write.setMessage(message);
		}
	}
	
	public void test() {
		write.setMessage("hi\n");
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
	private InputStreamReader inputStreamReader = null;//��һ���ֽ����е��ֽڽ�����ַ�
	private BufferedReader buff = null;
	
	ReadThread(Socket socket) throws IOException {
		this.socket = socket;
		in = socket.getInputStream();
		inputStreamReader = new InputStreamReader(in, "UTF-8");
		buff = new BufferedReader(inputStreamReader);
	}
	
	@Override
	public void run() {
		try {
			String message;
			while((message = buff.readLine())!=null) {
				//��ȡ���յ�����Ϣ��������
				ifMessageHandle.getInstance().handle(message);
				System.out.println(message+"������");
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
	
	WriteThread(Socket socket) throws IOException {
		this.socket = socket;
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
				System.out.println(next+"д����");
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
