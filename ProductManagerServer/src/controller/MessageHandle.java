package controller;

import application.GetTextArea;
import dao.DataHandle;

public class MessageHandle {

	public String handle(String str) {
		String result = null;
		str = str.trim();
		String[] args = str.split(" ");
		switch(args[0]) {
		case "login":
			if(args.length==3) {//��ʽ��login name password
				return "loginresult "+DataHandle.getInstance().testLogin(args[1], args[2]);
			}else {
				//���������Ϣ����������ȷ
				//��ҳ����ʾ��¼ʧ��
				GetTextArea.getInstance().getTextArea().appendText("��������"+str);
			}
			break;
		case "regist":
			break;
		default : break;
		}
		return result;
	}
	
}
