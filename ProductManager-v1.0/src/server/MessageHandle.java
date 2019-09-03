package server;

import java.lang.reflect.Method;

import application.Main;
import application.Sign;
import controller.MainController;
import controller.SignController;

/**
 * 负责接收数据的处理
 * 采用单例模式（内部类）
 * @author Administrator
 *
 */
public class MessageHandle {

	SignController sign;
	MainController main;
	
	private static class Holder{
		private static final MessageHandle INSTANCE = new MessageHandle();
	}
	
	private MessageHandle() {
		
	}	
	
	public static MessageHandle getInstance() {
		return Holder.INSTANCE;
	}
	
	public void setSign(SignController sign) {
		this.sign = sign;
	}
	
	public void setMain(MainController main) {
		this.main = main;
	}

	public String handle(String str) {
		String result = "hi\n";
		str = str.trim();
		String[] args = str.split(" ");
		switch(args[0]) {
		case "login_result":
			if(args.length==2) {
				loginResult(args[1]);
				return args[1];
			}else {
				//TODO 输出错误信息：参数不正确
				//TODO 主页面显示登录失败
			}
			break;
		case "regist_result":
			break;
		default : break;
		}
		return result;
	}

	private void loginResult(String string) {
		int i = Integer.parseInt(string);
		if (i==0) {
			//与服务器连接失败
		}else if (i==-1) {
			sign.lb_error_message.setText("用户名不存在");
		}else if (i==-2) {
			sign.lb_error_message.setText("密码输入不正确");
		}else {
			//登录成功，启动新界面
			sign.startMain(i);
		}
		
	}
}
