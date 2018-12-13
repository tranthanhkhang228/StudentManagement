package Controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Optional;

import DAOs.ContactDAO;
import application.Main;


public class InsertController {
	@FXML
	private Button btnThemSV;
	
	@FXML
	private TextField txtID;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtAge;
	
	@FXML
	private TextField txtAddress;
	
	public void ThemSinhVien(ActionEvent event) throws IOException {
		
		String textID = txtID.getText();
		String textName = txtName.getText();
		String textAge = txtAge.getText();
		String textAddress = txtAddress.getText();
		
		ContactDAO contact = new ContactDAO();
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Thong bao");
		ButtonType Them = new ButtonType("Them sinh vien");
		ButtonType Xem = new ButtonType("Xem danh sach");
		ButtonType Cancel = new ButtonType("Huy");
		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(Them,Xem,Cancel);
		
		if(contact.Insert(Integer.parseInt(textID), textName, Integer.parseInt(textAge), textAddress) != 1) {
			alert.setHeaderText("Them sinh vien khong thanh cong!");
			alert.setContentText("Ban co muon lam gi nua khong?");
			ListController.insertStage.close();
		}
		else {
			alert.setHeaderText("Them sinh vien thanh cong!");
			alert.setContentText("Ban co muon lam gi nua khong?");
			ListController.insertStage.close();
		}
		Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == Them) {
        	alert.close();
            ListController.insertStage.show();
        } else if (option.get() == Xem) {
        	alert.close();
        	
    		Parent ListScenefxml = FXMLLoader.load(getClass().getClassLoader().getResource("Views/ListScene.fxml"));
    		Scene ListScene = new Scene(ListScenefxml);
    		Stage ListStage = new Stage();
    		ListStage.setScene(ListScene);
    		ListStage.setTitle("Danh sách");
    		
    		ListStage.initModality(Modality.WINDOW_MODAL);
    		ListStage.initOwner(Main.primaryStage);
    		
    		ListStage.show();
        } else if (option.get() == Cancel) {
            alert.close();
        }
	}
}
