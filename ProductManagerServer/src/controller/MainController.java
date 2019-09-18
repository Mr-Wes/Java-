package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import server.ServerControl;

public class MainController implements Initializable {

	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;
	@FXML private Button button4;
	@FXML private Pane pane1;
	@FXML private Pane pane2;
	@FXML private Pane pane3;
	@FXML private Pane pane4;
	@FXML private TextArea text_area = new TextArea();
	@FXML private ListView<String> user_list;
	private int FLAG_PANE = 1;
	private int FLAG_START = 0;//0-��ʾ����رգ�1-��ʾ������
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> olist = FXCollections.observableArrayList();
		user_list = new ListView<String>(olist);
		user_list.setItems(null);
		user_list.setItems(olist);
		
		SharedData.getInstance().setTextArea(text_area);
		SharedData.getInstance().setList(user_list);
	}

	/**
	 * ��������/�رշ���ĵ���¼�
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void BUTTON1_ON_CLICK(ActionEvent event) throws IOException {
		if(FLAG_PANE==1) {//��ǰ��ʾΪ���1
			if(FLAG_START==0) {
				text_area.appendText("��������...\n");
				if(ServerControl.getInstance().startServer()) {
					button1.setText("�رշ���");
					FLAG_START = 1;
				}else {
					text_area.appendText("��������ʧ��");
				}				
			}else {
				text_area.appendText("�رշ���\n");
				ServerControl.getInstance().close();
				button1.setText("��������");
				FLAG_START = 0;
			}
	
		}else {
			//����������壬��ʾ���1
			switch(FLAG_PANE) {
			case 2:
				pane2.setVisible(false);
				pane2.setManaged(false);
				break;
			case 3:
				pane3.setVisible(false);
				pane3.setManaged(false);
				break;
			case 4:
				pane4.setVisible(false);
				pane4.setManaged(false);
				break;
			default : break;
			}
			pane1.setVisible(true);
			pane1.setManaged(true);
			FLAG_PANE = 1;
		}
	}
	
	@FXML
	public void BUTTON2_ON_CLICK(ActionEvent event) {
		if(FLAG_PANE==2) {
			
		}else {
			//�������?����ʾ���2
			switch(FLAG_PANE) {
				case 1:
					pane1.setVisible(false);
					pane1.setManaged(false);
					break;
				case 3:
					pane3.setVisible(false);
					pane3.setManaged(false);
					break;
				case 4:
					pane4.setVisible(false);
					pane4.setManaged(false);
					break;
				default : break;
			}
			pane2.setVisible(true);
			pane2.setManaged(true);
			FLAG_PANE = 2;	
		}
	}
	
	@FXML
	public void BUTTON3_ON_CLICK(ActionEvent event) {
		if(FLAG_PANE==3) {
			
		}else {
			//�������?����ʾ���2	
			switch(FLAG_PANE) {
				case 1:
					pane1.setVisible(false);
					pane1.setManaged(false);
					break;
				case 2:
					pane2.setVisible(false);
					pane2.setManaged(false);
					break;
				case 4:
					pane4.setVisible(false);
					pane4.setManaged(false);
					break;
				default : break;
			}
			pane3.setVisible(true);
			pane3.setManaged(true);
			FLAG_PANE = 3;
		}
	}
	
	@FXML
	public void BUTTON4_ON_CLICK(ActionEvent event) {
		if(FLAG_PANE==4) {
			
		}else {
			//�������?����ʾ���2
			switch(FLAG_PANE) {
				case 1:
					pane1.setVisible(false);
					pane1.setManaged(false);
					break;
				case 2:
					pane2.setVisible(false);
					pane2.setManaged(false);
					break;
				case 3:
					pane3.setVisible(false);
					pane3.setManaged(false);
					break;
				default : break;
			}
			pane4.setVisible(true);
			pane4.setManaged(true);
			FLAG_PANE = 4;	
		}
	}
}
