package controller;

import application.Main;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * ���õ���ģʽ
 * @author Administrator
 *
 */
public class SharedData {
	
	private SignController sign;
	
	private static class Holder {
		private static final SharedData INSTANCE = new SharedData();
	}
	
	private SharedData() {
		
	}
	
	public static SharedData getInstance() {
		return Holder.INSTANCE;
	}
	
	public void setSign(SignController arg0) {
		this.sign = arg0;
	}

	/**
	 * �����½���
	 * @param name���û���
	 * @param position��ְλ����
	 */
	public void startMain(int position) {
		Platform.runLater(new Runnable() {
		    public void run() {             
		        new Main(sign.tf_user_name.getText(), position).start(new Stage());
		        sign.application.primaryStage.close();
		    }
		});
		
	}

	public void setErrorText(String string) {
		Platform.runLater(new Runnable() {
		    public void run() {             
		    	sign.lb_error_message.setText(string);	
		    }
		});	
	}
}
