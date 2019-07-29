package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {

	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;
	@FXML private Button button4;
	@FXML private Pane pane1;
	@FXML private Pane pane2;
	@FXML public TextArea text_area;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	public void BUTTON1_ON_CLICK(ActionEvent event) {
		text_area.setText("开启服务...\n");
	}
	
	@FXML
	public void BUTTON2_ON_CLICK(ActionEvent event) {
		//隐藏面板1，显示面板2
		pane1.setVisible(false);
		pane1.setManaged(false);
		pane2.setVisible(true);
		pane2.setManaged(true);
	}
	
	@FXML
	public void BUTTON3_ON_CLICK(ActionEvent event) {

	}
	
	@FXML
	public void BUTTON4_ON_CLICK(ActionEvent event) {

	}
}
