package application;

import java.net.URL;

import controller.SignController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Sign extends Application {

	public Stage primaryStage;
	SignController sign;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage=primaryStage;
		try {
			URL location =getClass().getResource("SignLayout.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(location);
			Parent root = loader.load();
			sign = loader.getController();
			sign.setApplication(this);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			init();
			primaryStage.setTitle("禾嘉订单管理系统");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
		sign.tf_user_name.setFocusTraversable(false);
		sign.tf_user_password.setFocusTraversable(false);
		sign.tf_user_name.setPromptText("请输入用户名");
		sign.tf_user_password.setPromptText("请输入6位密码");		
		sign.cb_user_position.getItems().addAll("订单员","产品经理","Bom工程师","仓库","采购","会计","其他");
	}
	public static void main(String[] args) {
		launch();		
	}

}