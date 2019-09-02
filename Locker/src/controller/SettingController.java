package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Setting;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SettingController implements Initializable {
	
	Setting application = null;
	@FXML private Button bt_exit;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	public void setApplication(Setting application) {
		this.application = application;
	}
	
	@FXML
	public void EXIT(MouseEvent event) {
		application.close();
	}

}
