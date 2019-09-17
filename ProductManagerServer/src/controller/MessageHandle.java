package controller;

import java.net.Socket;

import dao.DataHandle;

public class MessageHandle {

	public String handle(Socket socket, String str) {
		String result = null;
		str = str.trim();
		String[] args = str.split(" ");
		switch(args[0]) {
		case "login":
			if(args.length==3) {//��ʽ��login name password
				int r = DataHandle.getInstance().testLogin(args[1], args[2]);
				if(r>0) {//��¼�ɹ�
					// TODO ��ӵ��û��б���
					SharedData.getInstance().addList(socket, args[1], args[2]);
				}
				return "login_result "+r+"\n";
			}else {
				//���������Ϣ����������ȷ
				//��ҳ����ʾ��¼ʧ��
				SharedData.getInstance().TextAreaAppend("��������"+str);
			}
			break;
		case "regist":
			break;
		default : break;
		}
		return result;
	}
	
}
