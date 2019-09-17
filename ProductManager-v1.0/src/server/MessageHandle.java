package server;

import controller.SharedData;

/**
 * ����������ݵĴ���
 * ���õ���ģʽ���ڲ��ࣩ
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
				//��ⷵ�ؽ��
				testLoginResult(args[1]);
				return args[1];
			}else {
				//��������ȷ����ҳ����ʾ��¼ʧ��
				SharedData.getInstance().setErrorText("��¼ʧ��");
			}
			break;
		case "regist_result":
			break;
		default : break;
		}
		return result;
	}

	/**
	 * ����¼���ؽ������ִ����Ӧ����
	 * @param string
	 */
	private void testLoginResult(String string) {
		int i = Integer.parseInt(string);
		if (i==0) {
			//�����������ʧ��
			SharedData.getInstance().setErrorText("����������ʧ��");
		}else if (i==-1) {
			SharedData.getInstance().setErrorText("�û���������");
		}else if (i==-2) {
			SharedData.getInstance().setErrorText("�������벻��ȷ");
		}else {
			//��¼�ɹ��������½���
			SharedData.getInstance().startMain(i);
		}
		
	}
}
