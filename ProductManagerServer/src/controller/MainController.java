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
	@FXML private Pane pane3;
	@FXML private Pane pane4;
	@FXML public TextArea text_area;
	private int FLAG_PANE = 1;
	private int FLAG_START = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	public void BUTTON1_ON_CLICK(ActionEvent event) {
		if(FLAG_PANE==1) {
			if(FLAG_START==0) {
				text_area.setText("开启服务...\n");
				FLAG_START = 1;
				button1.setText("关闭服务");
			}else {
				text_area.appendText("关闭服务");
				FLAG_START = 0;
				button1.setText("开启服务");
			}
	
		}else {
			//隐藏其他面板，显示面板1
			pane1.setVisible(true);
			pane1.setManaged(true);
			switch(FLAG_PANE) {
			case 2: {pane2.setVisible(true);pane2.setManaged(true);}break;
			case 3: {pane3.setVisible(true);pane3.setManaged(true);} break;
			case 4: {pane4.setVisible(true);pane4.setManaged(true);} break;
			default :break;
			}
		}
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
