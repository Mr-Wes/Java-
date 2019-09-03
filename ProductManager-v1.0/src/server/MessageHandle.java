package server;

import java.lang.reflect.Method;

import application.Main;
import application.Sign;
import controller.MainController;
import controller.SignController;

/**
 * ����������ݵĴ���
 * ���õ���ģʽ���ڲ��ࣩ
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
				//TODO ���������Ϣ����������ȷ
				//TODO ��ҳ����ʾ��¼ʧ��
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
			//�����������ʧ��
		}else if (i==-1) {
			sign.lb_error_message.setText("�û���������");
		}else if (i==-2) {
			sign.lb_error_message.setText("�������벻��ȷ");
		}else {
			//��¼�ɹ��������½���
			sign.startMain(i);
		}
		
	}
}
