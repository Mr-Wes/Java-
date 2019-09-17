package server;

import controller.SharedData;

/**
 * 负责接收数据的处理
 * 采用单例模式（内部类）
 * @author Administrator
 *
 */
public class MessageHandle {
	
	private static class Holder{
		private static final MessageHandle INSTANCE = new MessageHandle();
	}
	
	private MessageHandle() {
		
	}	
	
	public static MessageHandle getInstance() {
		return Holder.INSTANCE;
	}

	public String handle(String str) {
		String result = null;
		str = str.trim();
		String[] args = str.split(" ");
		switch(args[0]) {
		case "login_result":
			if(args.length==2) {
				//检测返回结果
				testLoginResult(args[1]);
				return args[1];
			}else {
				//参数不正确，主页面显示登录失败
				SharedData.getInstance().setErrorText("登录失败");
			}
			break;
		case "regist_result":
			break;
		default : break;
		}
		return result;
	}

	/**
	 * 检测登录返回结果，并执行相应操作
	 * @param string
	 */
	private void testLoginResult(String string) {
		int i = Integer.parseInt(string);
		if (i==0) {
			//与服务器连接失败
			SharedData.getInstance().setErrorText("服务器连接失败");
		}else if (i==-1) {
			SharedData.getInstance().setErrorText("用户名不存在");
		}else if (i==-2) {
			SharedData.getInstance().setErrorText("密码输入不正确");
		}else {
			//登录成功，启动新界面
			SharedData.getInstance().startMain(i);
		}
		
	}
}
