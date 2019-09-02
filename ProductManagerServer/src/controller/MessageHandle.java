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
				//TODO 输出错误信息：参数不正确
				//TODO 主页面显示登录失败
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
