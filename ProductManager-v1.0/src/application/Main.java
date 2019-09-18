package application;

import java.net.URL;

import controller.MainController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import server.DataHandle;

public class Main extends Application {

	public MainController main;
	public final String user_name;
	public final int user_position;
	public Main(String user_name, int user_position) {
		this.user_name = user_name;
		this.user_position = user_position;
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			URL location =getClass().getResource("MainLayout.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(location);
			Parent root = loader.load();
			main = loader.getController();
			Scene scene = new Scene(root);
			// TODO ������ҳ��css
			//scene.getStylesheets().add(getClass().getResource("MainLayout.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("�̼ζ�������ϵͳ");
			primaryStage.show();
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
}
