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
				//TODO 输出错误信息：参数不正确
				//TODO 主页面显示登录失败
			}
			break;
		case "regist":
			break;
		default : break;
		}
		return result;
	}
}
