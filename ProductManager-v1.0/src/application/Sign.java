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

	SignController sign;
	public Stage primaryStage;

	@Override
	public void start(Stage arg0) {
		this.primaryStage = arg0;
		try {
			URL location = getClass().getResource("SignLayout.fxml");
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
			//窗口关闭事件
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
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