package controller;

import java.net.Socket;

import dao.DataHandle;

public class MessageHandle {
	
	public void handle(Socket socket, String str) {
		str = str.trim();
		String[] args = str.split(" ");
		switch(args[0]) {
		case "login":
			if(args.length==3) {
				login(args[1], args[2]);
			}else {
				//TODO ���������Ϣ����������ȷ
				//TODO ��ҳ����ʾ��¼ʧ��
			}
			break;
		case "regist":
			break;
		default : break;
		}		
	}
	public void login(String name, String password) {
		int result = DataHandle.getInstance().testLogin(name, password);
	}
}
