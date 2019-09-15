package controller;

import dao.DataHandle;

public class MessageHandle {

	public String handle(String str) {
		String result = null;
		str = str.trim();
		String[] args = str.split(" ");
		switch(args[0]) {
		case "login":
			if(args.length==3) {//格式：login name password
				return "loginresult "+DataHandle.getInstance().testLogin(args[1], args[2]);
			}else {
				//输出错误信息：参数不正确
				//主页面显示登录失败
				MainController.text_area.appendText("参数错误："+str);
			}
			break;
		case "regist":
			break;
		default : break;
		}
		return result;
	}
	
}
