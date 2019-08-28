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
 * ���õ���ģʽ���ڲ��෽ʽ��
 * @author Administrator
 *
 */
public class DataHandle {

	private static class Holder{
		private static final DataHandle INSTANCE = new DataHandle();
	}
	private Socket socket;
	private DataHandle() {
		//������������
		Properties server_properties = new Properties();
		try {
			server_properties.load(new FileInputStream(new File("")));
			String ip = server_properties.getProperty("ip");
			int port = Integer.parseInt(server_properties.getProperty("port"));
			//���������������
			socket = new Socket(ip, port);
			
			//���ӳ�ʱ15�룬������ʾ�����ӳ�ʱ�������������ӡ�
			// TODO ���ӷ�����ʧ�ܵ���ʾ��
			
			// TODO �½��Զ����̣߳���������ӷ�������������
			
			
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
