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
			if(args.length==3) {//格式：login name password
				int r = DataHandle.getInstance().testLogin(args[1], args[2]);
				if(r>0) {//登录成功
					// TODO 添加到用户列表中
					SharedData.getInstance().addList(socket, args[1], args[2]);
				}
				return "login_result "+r+"\n";
			}else {
				//输出错误信息：参数不正确
				//主页面显示登录失败
				SharedData.getInstance().TextAreaAppend("参数错误："+str);
			}
			break;
		case "regist":
			break;
		default : break;
		}
		return result;
	}
	
}
