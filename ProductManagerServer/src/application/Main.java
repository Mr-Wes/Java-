package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public Stage primaryStage;
	
	@Override
	public void start(Stage arg0) {
		this.primaryStage = arg0;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/MainLayout.fxml"));
			primaryStage.setTitle("禾嘉订单管理系统服务器");
			primaryStage.setResizable(false);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	public static void main(String[] args) {
		launch();
	}
	// TODO 重写关闭方法，关闭时要断开连接，关闭进程，清空socket列表
}
