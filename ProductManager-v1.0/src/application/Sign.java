package application;

import java.net.URL;

import controller.SignController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import server.DataHandle;
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
			//TODO 加载客户端登录css
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("禾嘉订单管理系统");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					//窗口关闭事件
					DataHandle.getInstance().close();
					
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch();		
	}

}