package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import server.ServerControl;
import server.SocketManager;

public class Main extends Application {
	
	public Stage primaryStage;
	
	@Override
	public void start(Stage arg0) {
		this.primaryStage = arg0;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/MainLayout.fxml"));
			primaryStage.setTitle("�̼ζ�������ϵͳ������");
			primaryStage.setResizable(false);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					ServerControl.getInstance().close();//�رշ���
					SocketManager.getInstance().clean();//���socket�б�
				}
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	public static void main(String[] args) {
		launch();
	}
}
