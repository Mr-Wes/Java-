package controller;

import dao.DataHandle;

public class MessageHandle {
	
	public String handle(String str) {
		String result = "ok\n";
		str = str.trim();
		String[] args = str.split(" ");
		switch(args[0]) {
		case "login":
			if(args.length==3) {
				return DataHandle.getInstance().testLogin(args[1], args[2])+"";
			}else {
				//TODO ���������Ϣ����������ȷ
				//TODO ��ҳ����ʾ��¼ʧ��
			}
			break;
		case "regist":
			break;
		default : break;
		}
		return result;
	}
}
