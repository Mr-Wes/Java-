package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.Initializable;

public class MainController implements Initializable {

	private Main application;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	public void setApplication(Main application) {
		this.application = application;
		
	}
}
