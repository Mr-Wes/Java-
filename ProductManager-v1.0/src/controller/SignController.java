package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Sign;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import server.DataHandle;

public class SignController implements Initializable {

	public Sign application;
	private int state_falg=0;
	private final int FLAG_LOGIN=0;
	private final int FLAG_REGISTE=1;
	@FXML private Label lb_back;
	@FXML private Label lb_registe;
	@FXML public Label lb_error_message;
	@FXML protected Label lb_user_name;
	@FXML private Label lb_user_password;
	@FXML private Label lb_user_position;
	@FXML public TextField tf_user_name;
	@FXML public TextField tf_user_password;
	@FXML public ChoiceBox<String> cb_user_position;
	@FXML private Button bt_sign;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tf_user_name.setFocusTraversable(false);
		tf_user_password.setFocusTraversable(false);
		tf_user_name.setPromptText("请输入用户名");
		tf_user_password.setPromptText("请输入6位密码");		
		cb_user_position.getItems().addAll("订单员","产品经理","Bom工程师","仓管","采购","会计","其他");
		
		SharedData.getInstance().setSign(this);
	}
	
	/**
	 * 获取controller相应的application
	 * @param application
	 */
	public void setApplication(Sign application) {
		this.application = application;
	}
	
	/**
	 * 界面由主登录界面转换为注册界面
	 * @param event
	 */
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
		bt_sign.setText("注册");		
		state_falg=FLAG_REGISTE;
	}
	
	/**
	 * 界面由注册界面转换为登录界面
	 * @param event
	 */
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
		bt_sign.setText("登录");
		state_falg=FLAG_LOGIN;
	}
	
	/**
	 * 提交按钮点击事件
	 * @param event
	 */
	@FXML
	public void ON_CLICK(ActionEvent event) {
		//1：判断当前界面是登录界面还是注册界面
		if(state_falg==FLAG_LOGIN){//登录界面
			//1.1：测试输入是否正确
			if(testInput()) {
				String name = tf_user_name.getText();
				String password = tf_user_password.getText();
				//向服务器发送登录请
				DataHandle.getInstance().testLogin(name, password);
			}			
		}else {//注册界面
			//1.2：测试输入数据是否正确
			if(testInput()) {
				//3：测试职位选择是否正确
				int p = cb_user_position.getSelectionModel().selectedIndexProperty().intValue();
				if(p!=-1) {//已选择职位
					String name = tf_user_name.getText();
					String password = tf_user_password.getText();

					int i = DataHandle.getInstance().registe(name, password, p);
					if(i==1) {//注册成功
						//模拟back点击事件
						lb_error_message.setText("注册成功");
					}
				}else {
					//职位未选择
					lb_error_message.setText("请输入职位");
				}
			}
		}
	}
	
	/**
	 * 测试输入数据是否合法
	 * @return
	 */
	public boolean testInput() {
		String name = tf_user_name.getText();
		String password = tf_user_password.getText();
		if (name.equals("")||name==null) {
			lb_error_message.setText("请输入用户名");
			return false;//未输入用户名
		}else if (name.getBytes().length>10) {
			lb_error_message.setText("用户名长度应小于5");
			return false;//用户名长度应小于8
		}else if (name.indexOf(" ")!=-1) {
			lb_error_message.setText("用户名不能含空格");
			return false;
		}else if (password.equals("")||password==null) {
			lb_error_message.setText("请输入用密码");
			return false;//未输入密码
		}else if (password.matches("[a-zA-Z0-9]+")&&password.length()!=6) {
			lb_error_message.setText("密码长度应为6为英文或数字");
			return false;//密码字符不正确
		}	
		return true;
	}
}
