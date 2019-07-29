package application;

import java.net.URL;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public MainController main;
	private Stage primaryStage;
	public final String user_name;
	public final int user_position;
	public Main(String user_name, int user_position) {
		this.user_name = user_name;
		this.user_position = user_position;
	}
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			URL location =getClass().getResource("MainLayout.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(location);
			Parent root = loader.load();
			main = loader.getController();
			main.setApplication(this);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("MainLayout.css").toExternalForm());
			primaryStage.setScene(scene);
			init();
			primaryStage.setTitle("禾嘉订单管理系统");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void init() {
		
	}

}
