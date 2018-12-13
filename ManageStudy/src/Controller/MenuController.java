package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.StudentDAO;
import Model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable {
	private Stage MenuStage;
	
	@FXML
	private TableView<Student> StudentTable;
	
	@FXML
	private TableColumn<Student, String> IDCol;
	
	@FXML
	private TableColumn<Student, String> FirstNameCol;
	
	@FXML
	private TableColumn<Student, String> LastNameCol;
	
	@FXML
	private TableColumn<Student, String> ClassNameCol;
	
	@FXML
	private TableColumn<Student, String> BirthdayCol;
	
	@FXML
	private TableColumn<Student, String> AddressCol;
	
	@FXML
	private TableColumn<Student, String> GenderCol;
	
	@FXML
	private TableColumn<Student, String> NumberPhoneCol;

	@FXML 
	private Button btndelete;
	
	@FXML
	private Button btninsert;
	
	@FXML
	private Button btnupdate;
	
	private StudentDAO newsDAO = new StudentDAO();
	
	private ObservableList<Student> liststudent = FXCollections.observableArrayList(newsDAO.getList());
	
	public void setMenuStage(Stage MenuStage) {
		this.MenuStage = MenuStage;
	}
	
	public Stage getMenuStage() {
		return MenuStage;
	}
	
	public ObservableList<Student> getListStudent(){
		return liststudent;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Remember to add asOject() after Property if they are IntergerProperty, DoubleProperty... 
		IDCol.setCellValueFactory(cellData -> cellData.getValue().IDProperty());
		FirstNameCol.setCellValueFactory(cellData -> cellData.getValue().FirstNameProperty());
		LastNameCol.setCellValueFactory(cellData -> cellData.getValue().LastNameProperty());
		ClassNameCol.setCellValueFactory(cellData -> cellData.getValue().ClassNameProperty());
		BirthdayCol.setCellValueFactory(cellData -> cellData.getValue().BirthdayProperty());
		AddressCol.setCellValueFactory(cellData -> cellData.getValue().AddressProperty());
		GenderCol.setCellValueFactory(cellData -> cellData.getValue().GenderProperty());
		NumberPhoneCol.setCellValueFactory(cellData -> cellData.getValue().NumberPhoneProperty());
		StudentTable.setItems(liststudent);
	}
	
	//Delete a student
	public void DeleteStudent(ActionEvent event) {
		int selectedIndex = StudentTable.getSelectionModel().getSelectedIndex();
		
		if(selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(MenuStage);
			alert.initModality(Modality.WINDOW_MODAL);
			
			alert.setTitle("Confirmation!");
			alert.setHeaderText("Do you really want to delete this student?");
			alert.setContentText("Please select YES or NO!");
			
			//Delete Default ButtonTypes
			alert.getButtonTypes().clear();
			
			ButtonType yes = new ButtonType("Yes");
			ButtonType no = new ButtonType("No");
			alert.getButtonTypes().addAll(yes,no);
			
			Optional<ButtonType> option = alert.showAndWait();
			if(option.get() == yes) {
				
				//Take Student's ID
				String StudentID = StudentTable.getSelectionModel().getSelectedItem().getID();
				//Delete student
				if(newsDAO.DeleteStudent(StudentID) == 1) {
					liststudent.remove(StudentTable.getSelectionModel().getSelectedIndex());
					//StudentTable.getItems().remove(StudentID);
				}
			}
			else {
				if(option.get() == no) {
					alert.close();
				}
			}
		} 
		//Nothing selected
		else {
			Alert alert = new Alert(AlertType.WARNING);
			
			//Set MenuStage is owner
			alert.initOwner(MenuStage);
			alert.initModality(Modality.WINDOW_MODAL);
			
			alert.setTitle("Warning!");
			alert.setHeaderText("No Student selected!");
			alert.setContentText("Please select a student in table to delete!");
			
			alert.showAndWait();
		}
	}
	
	//Call insert stage
	public void InsertStudent(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("View/InsertView.fxml"));
			
			AnchorPane InsertLayout = (AnchorPane) loader.load();
			
			Scene InsertScene = new Scene(InsertLayout);
			
			Stage InsertStage = new Stage();
			InsertStage.setScene(InsertScene);
			InsertStage.setTitle("Insert");
	
			InsertStage.initModality(Modality.WINDOW_MODAL);
			InsertStage.initOwner(MenuStage);
	
			InsertController controller = loader.getController();
			controller.setInsertStage(InsertStage);
			controller.setMenuController(this);
			
			InsertStage.showAndWait();;
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	//Call update stage
	public void UpdateStudent(ActionEvent event) {
		int selectedIndex = StudentTable.getSelectionModel().getSelectedIndex();
		Student student = StudentTable.getSelectionModel().getSelectedItem();
		
		if(selectedIndex >= 0) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getClassLoader().getResource("View/UpdateView.fxml"));
				
				AnchorPane UpdateLayout = loader.load();
				
				Scene UpdateScene = new Scene(UpdateLayout);
				
				Stage UpdateStage = new Stage();
				UpdateStage.setScene(UpdateScene);
				UpdateStage.setTitle("Update");
				
				UpdateStage.initOwner(MenuStage);
				UpdateStage.initModality(Modality.WINDOW_MODAL);
				
				UpdateController controller = loader.getController();
				
				controller.setMenuController(this);
				controller.setUpdateStage(UpdateStage);
				controller.setStudent(student);			
				
				UpdateStage.showAndWait();
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			
			//Set MenuStage is owner
			alert.initOwner(MenuStage);
			alert.initModality(Modality.WINDOW_MODAL);
			
			alert.setTitle("Warning!");
			alert.setHeaderText("No Student selected!");
			alert.setContentText("Please select a student in table to update!");
			
			alert.showAndWait();
		}
	}
}
