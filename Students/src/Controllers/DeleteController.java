package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import DAOs.ContactDAO;
import Models.ContactStudents;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeleteController implements Initializable{

	@FXML
	private TextField txtInputID;

	@FXML
	private Button btnDelete;
	
	@FXML
	private TableView<ContactStudents> ListTable;
	
	@FXML
	private TableColumn<ContactStudents, String> IDColumn;
	
	@FXML
	private TableColumn<ContactStudents, String> NameColumn;
	
	@FXML
	private TableColumn<ContactStudents, String> AgeColumn;
	
	@FXML
	private TableColumn<ContactStudents, String> AddressColumn;
	
	ContactDAO contact = new ContactDAO();
	public ObservableList<ContactStudents> getStudents(){
		List<ContactStudents> list = contact.getContacts();
		ObservableList<ContactStudents> students = FXCollections.observableArrayList(list);
		return students;
	}

	
	public void initialize(URL location, ResourceBundle resource) {
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		AgeColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
		AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
		ListTable.getItems().setAll(getStudents());
	}
	
	public void DeleteStudent(ActionEvent event) throws IOException {
		int ID = Integer.parseInt(txtInputID.getText());
		
		
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Xac nhan!");
		alert.setHeaderText("Ban co chac muon xoa sinh vien nay khong?");
		alert.setContentText("Chon co hoac khong!");
		
		ButtonType YES = new ButtonType("Co");
		ButtonType NO = new ButtonType("Khong");
		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(YES,NO);
		
		Optional<ButtonType> option = alert.showAndWait();
		
        if (option.get() == YES) {
        	ListController.deleteStage.close();
        	
        	Alert tb = new Alert(Alert.AlertType.INFORMATION);
        	tb.setTitle("Thong bao!");        	
        	if(contact.Delete(ID) == 1) {
        		tb.setHeaderText("Thanh cong!");
        		tb.setContentText("Xoa sinh vien thanh cong!");
        	}
        	else {
        		tb.setHeaderText("Khong thanh cong!");
        		tb.setContentText("Xoa sinh khong vien thanh cong!");
        	}
        	tb.show();
        }
        else if (option.get() == NO) {
        	ListController.deleteStage.close();
        	alert.close();
        }
	}
	
}
