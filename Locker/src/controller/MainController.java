package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Setting;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController implements Initializable {

	@FXML private Button bt_setting;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image image = new Image(getClass().getResourceAsStream("/resources/setting.png"));
		bt_setting.setGraphic(new ImageView(image));
	}

	@FXML
	public void SETTING(MouseEvent event) {
		Platform.runLater(new Runnable() {
		    public void run() {
		        new Setting().start(new Stage());
		    }
		});
	}
}
