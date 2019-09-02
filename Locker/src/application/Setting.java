package application;

import java.net.URL;

import controller.SettingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Setting extends Application {

	SettingController controller = null;
	
	@Override
	public void start(Stage primaryStage){
		try {
			URL location = getClass().getResource("Setting.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(location);
			Parent root = loader.load();
			controller = loader.getController();
			controller.setApplication(this);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("…Ë÷√");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		System.exit(0);
	}
}
