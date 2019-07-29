package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Sign;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import dao.DataHandle;

public class SignController implements Initializable {

	private Sign application;
	private int state_falg=0;
	private final int FLAG_LOGIN=0;
	private final int FLAG_REGISTE=1;
	@FXML private Label lb_back;
	@FXML private Label lb_registe;
	@FXML private Label lb_error_message;
	@FXML protected Label lb_user_name;
	@FXML private Label lb_user_password;
	@FXML private Label lb_user_position;
	@FXML public TextField tf_user_name;
	@FXML public TextField tf_user_password;
	@FXML public ChoiceBox<String> cb_user_position;
	@FXML private Button bt_sign;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	public void setApplication(Sign application) {
		this.application=application;
	}
	
	@FXML
	public void REGISTE(MouseEvent event) {
		lb_user_name.setLayoutY(48.00);
		lb_user_password.setLayoutY(90.00);
		tf_user_name.setLayoutY(44.00);
		tf_user_password.setLayoutY(86.00);
		lb_back.setVisible(true);
		lb_back.setManaged(true);
		lb_registe.setVisible(false);
		lb_registe.setManaged(false);
		lb_user_position.setVisible(true);
		lb_user_position.setManaged(true);
		cb_user_position.setVisible(true);
		cb_user_position.setManaged(true);
		lb_error_message.setText("");
		bt_sign.setText("ע��");		
		state_falg=FLAG_REGISTE;
	}
	@FXML
	public void BACK(MouseEvent event) {
		lb_user_name.setLayoutY(59.00);
		lb_user_password.setLayoutY(110.00);
		tf_user_name.setLayoutY(55.00);
		tf_user_password.setLayoutY(106.00);
		lb_back.setVisible(false);
		lb_back.setManaged(false);
		lb_registe.setVisible(true);
		lb_registe.setManaged(true);
		lb_user_position.setVisible(false);
		lb_user_position.setManaged(false);
		cb_user_position.setVisible(false);
		cb_user_position.setManaged(false);
		lb_error_message.setText("");
		bt_sign.setText("��¼");
		state_falg=FLAG_LOGIN;
	}
	@FXML
	public void ON_CLICK(ActionEvent event) {
		//1���жϵ�ǰ�����ǵ�¼���滹��ע�����
		if(state_falg==FLAG_LOGIN){//��¼����
			//2�����������Ƿ���ȷ
			if(testInput()) {
				String name = tf_user_name.getText();
				String password = tf_user_password.getText();
				int i = DataHandle.getInstance().testLogin(name, password);
				if (i==1) {
					lb_error_message.setText("�û���������");
				}else if (i==2) {
					lb_error_message.setText("�������벻��ȷ");
				}else {
					//��¼�ɹ�
					startMain(name, i-3);
				}
			}			
		}else {//ע�����
			//2���������������Ƿ���ȷ
			if(testInput()) {
				//3������ְλѡ���Ƿ���ȷ
				int p = cb_user_position.getSelectionModel().selectedIndexProperty().intValue();
				if(p!=-1) {//��ѡ��ְλ
					String name = tf_user_name.getText();
					String password = tf_user_password.getText();

					int i = DataHandle.getInstance().registe(name, password, p);
					if(i==2) {//ע��ɹ�
						//ģ��back����¼�
						lb_error_message.setText("ע��ɹ�");
					}
				}else {
					//ְλδѡ��
					lb_error_message.setText("������ְλ");
				}
			}
		}

	}
	
	public boolean testInput() {
		String name = tf_user_name.getText();
		String password = tf_user_password.getText();		
		if (name.equals("")||name==null) {
			lb_error_message.setText("�������û���");
			return false;//δ�����û���
		}else if (name.getBytes().length>10) {
			lb_error_message.setText("�û�������ӦС��5");
			return false;//�û�������ӦС��8
		}else if (password.equals("")||password==null) {
			lb_error_message.setText("������������");
			return false;//δ��������
		}else if (password.matches("[a-zA-Z0-9]+")&&password.length()!=6) {
			lb_error_message.setText("���볤��ӦΪ6ΪӢ�Ļ�����");
			return false;//���볤�Ȳ���ȷ
		}	
		return true;
	}
	public void startMain(String name, int position) {
		Platform.runLater(new Runnable() {
		    public void run() {             
		        new Main(name, position).start(new Stage());
		    }
		});
		application.primaryStage.close();
	}
}