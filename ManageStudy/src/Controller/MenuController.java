package Controller;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;

import DAO.StudentDAO;
import Model.Student;
import Model.StudentListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable {
	private Stage MenuStage;
	
	private StudentDAO newsDAO = new StudentDAO();
	
	private ObservableList<Student> ListStudentFromDatabase = FXCollections.observableArrayList(newsDAO.getList());
	
	private ObservableList<Student> ListStudentFromXML = FXCollections.observableArrayList();
	
	//Database or XML. Default
	private String WorkingStatus ="";
	
	@FXML 
	private Label txtNotice;
	
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
	
	public void setMenuStage(Stage MenuStage) {
		this.MenuStage = MenuStage;
	}
	
	public Stage getMenuStage() {
		return MenuStage;
	}
	
	public ObservableList<Student> getListStudentFromDatabase(){
		return ListStudentFromDatabase;
	}
	
	public ObservableList<Student> getListStudentFromXML(){
		return ListStudentFromXML;
	}
	
	//Check Working Status
	public boolean checkWorkingStatus() {
		if(WorkingStatus.isEmpty()) {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Notice!");
	        alert.setHeaderText("No MODE selected!");
	        alert.setContentText("Please select a MODE at Edit !");
	        alert.showAndWait();
	        return false;
		}
		else {
			return true;
		}
	}
	
	//Set Items for Student Table
	public void setStudentTable(ObservableList<Student> liststudent) {
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	//Delete a student
	public void DeleteStudent(ActionEvent event) {
		//Alert confirm deleting
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

		//Alert warn no student selected
		Alert alertwarning = new Alert(AlertType.WARNING);
		alertwarning.initOwner(MenuStage);
		alertwarning.initModality(Modality.WINDOW_MODAL);
		alertwarning.setTitle("Warning!");
		alertwarning.setHeaderText("No Student selected!");
		alertwarning.setContentText("Please select a student in table to delete!");
		
		//Check if Working Status is empty or not.
		if(checkWorkingStatus()) {
			//Check Working Status
			if(WorkingStatus.equals("Database")) {
				int selectedIndex = StudentTable.getSelectionModel().getSelectedIndex();
				if(selectedIndex >= 0) {
					Optional<ButtonType> option = alert.showAndWait();
					if(option.get() == yes) {
						
						//Take Student's ID
						String StudentID = StudentTable.getSelectionModel().getSelectedItem().getID();
						//Delete student from database
						if(newsDAO.DeleteStudent(StudentID) == 1) {
							ListStudentFromDatabase.remove(StudentTable.getSelectionModel().getSelectedIndex());
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
					alertwarning.showAndWait();
				}
			} else {
				int selectedIndex = StudentTable.getSelectionModel().getSelectedIndex();
				if(selectedIndex >= 0) {
					Optional<ButtonType> option = alert.showAndWait();
					if(option.get() == yes) {
						ListStudentFromXML.remove(StudentTable.getSelectionModel().getSelectedIndex());
					}
					else {
						if(option.get() == no) {
							alert.close();
						}
					}
				} 
				else {
					alertwarning.showAndWait();
				}
			}
		}
	}
	
	//Call insert stage
	public void InsertStudent(ActionEvent event) {
		//Check if Working Status is empty or not.
		if(checkWorkingStatus()) {
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
				controller.setMenuController(this);
				controller.setInsertStage(InsertStage);
				controller.setWorkingStatus(WorkingStatus);
				
				InsertStage.showAndWait();;
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	//Call update stage
	public void UpdateStudent(ActionEvent event) {
		//Check if Working Status is empty or not.
		if(checkWorkingStatus()) {
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
					controller.setWorkingStatus(WorkingStatus);
					
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
	
	//Reading and Writing XML
	
	//Return the student file preference. the file that was last opened.
	public File getStudentFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(getClass());
		String filePath = prefs.get("filePath", null);
		if(filePath != null) {
			return new File(filePath);
		}
		else {
			return null;
		}
	}
	
	//Set the file path of the currently loaded file.
	public void setStudentFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(getClass());
		if(file != null) {
			prefs.put("filePath", file.getPath());
			MenuStage.setTitle("Manage Student: " + file.getName());
		}
		else {
			prefs.remove("filePath");
			MenuStage.setTitle("Manage Student");
		}
	}
	
	//Load student data from specified file. The current person data will be replaced.
	public void loadStudentDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(StudentListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			
			//Reading XML from the file and unmarshalling
			StudentListWrapper wrapper = (StudentListWrapper) um.unmarshal(file);
			
			ListStudentFromXML.clear();
			ListStudentFromXML.addAll(wrapper.getStudents());
			
			setStudentTable(ListStudentFromXML);
		} catch(Exception e) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not load data");
	        alert.setContentText("Could not load data from file:\n" + file.getPath());

	        alert.showAndWait();
		}
	}
	
	//Saves current person data to the specified file.
	public void saveStudentDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(StudentListWrapper.class);
			Marshaller ma = context.createMarshaller();
			
			ma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			//Wrapping our student data.
			StudentListWrapper wrapper = new StudentListWrapper();
			wrapper.setStudents(ListStudentFromXML);
			
			//Marshalling and saving XML to the file.
			ma.marshal(wrapper, file);
			
			//Save the file path to the registry.
			setStudentFilePath(file);
			
		} catch(Exception e) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());

	        alert.showAndWait();
		}
	}
	
	//Handle Menu
	//File
	public void LoadDatabase(ActionEvent event) {
		if(checkWorkingStatus()) {
			if(WorkingStatus.equals("Database")) {
				ObservableList<Student> listtemp = ListStudentFromDatabase;
				setStudentTable(listtemp);}
			else {
		        Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Notice!");
		        alert.setHeaderText("You are working with XML!");
		        alert.setContentText("Please select a Database MODE at Edit !");
		        alert.showAndWait();
			}
		}
	}
	
	public void NewXML(ActionEvent event) {
		if(checkWorkingStatus()) {
			if(WorkingStatus.equals("XML")) {
				ListStudentFromXML.clear();
				setStudentTable(ListStudentFromXML);
				setStudentFilePath(null);
			}
			else {
		        Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Notice!");
		        alert.setHeaderText("You are working with Database!");
		        alert.setContentText("Please select a XML MODE at Edit !");
		        alert.showAndWait();
			}
		}
	}
	
	public void OpenXML(ActionEvent event) {
		if(checkWorkingStatus()) {
			if(WorkingStatus.equals("XML")) {
				FileChooser fileChooser = new FileChooser();
				
				//Set extensionfilter
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
				fileChooser.getExtensionFilters().add(extFilter);
				
				File file = fileChooser.showOpenDialog(MenuStage);
				
				if(file != null) {
					loadStudentDataFromFile(file);
				}
			}
			else {
		        Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Notice!");
		        alert.setHeaderText("You are working with Database!");
		        alert.setContentText("Please select a XML MODE at Edit !");
		        alert.showAndWait();
			}
		}
	}
	
	public void SaveXML(ActionEvent event) {
		if(checkWorkingStatus()) {
			if(WorkingStatus.equals("XML")) {
				File studentFile = getStudentFilePath();
				if(studentFile != null) {
					saveStudentDataToFile(studentFile);
				} 
				else {
					SaveAsXML(event);
				}
			}
			else {
		        Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Notice!");
		        alert.setHeaderText("You are working with Database!");
		        alert.setContentText("Please select a XML MODE at Edit !");
		        alert.showAndWait();
			}
		}
	}
	
	public void SaveAsXML(ActionEvent event) {
		if(checkWorkingStatus()) {
			if(WorkingStatus.equals("XML")) {
				FileChooser fileChooser = new FileChooser();
				
				//Set extension filter
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)","*.xml");
				
				fileChooser.getExtensionFilters().add(extFilter);
				
				//Show save file dialog
				File file = fileChooser.showSaveDialog(MenuStage);
				
				if(file != null) {
					//Make sure it has the correct extension
					if(!file.getPath().endsWith(".xml")) {
						file = new File(file.getPath() + ".xml");
					}
					saveStudentDataToFile(file);
				}
			}
			else {
		        Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Notice!");
		        alert.setHeaderText("You are working with Database!");
		        alert.setContentText("Please select a XML MODE at Edit !");
		        alert.showAndWait();
			}
		}
	}
	
	public void MenuExit(ActionEvent event) {
		System.exit(0);
	}
	
	//Edit
	public void WorkWithDatabase(ActionEvent event) {
		if (WorkingStatus.isEmpty()) {
				setStudentTable(null);
				btninsert.setText("Insert (Database)");
				btnupdate.setText("Update (Database)");
				btndelete.setText("Delete (Database)");
				txtNotice.setText("Notice: You are working with database!");
				WorkingStatus = "Database";
		}
		else {
			if(WorkingStatus.equals("Database")) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initOwner(MenuStage);
				alert.initModality(Modality.WINDOW_MODAL);
				alert.setTitle("Information!");
				alert.setHeaderText("The Current MODE is Database!");
				alert.show();
			}	
			else {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.initOwner(MenuStage);
				alert.initModality(Modality.WINDOW_MODAL);
				alert.setTitle("Warning!");
				alert.setHeaderText("You will change to Database Mode. Have you saved your data?");
				alert.setContentText("Do you want to save your data?");
				alert.getButtonTypes().clear();
				ButtonType save = new ButtonType("Save");
				ButtonType close = new ButtonType("Stop changing");
				ButtonType changemode = new ButtonType("Change to Database Mode");
				alert.getButtonTypes().addAll(save,close, changemode);
				Optional<ButtonType> option = alert.showAndWait();
				if(option.get() == save) {
					SaveXML(event);
				}
				else {
					if(option.get() == close) {
						alert.close();
					}
					else {
						setStudentTable(null);
						btninsert.setText("Insert (Database)");
						btnupdate.setText("Update (Database)");
						btndelete.setText("Delete (Database)");
						txtNotice.setText("Notice: You are working with database!");
						WorkingStatus = "Database";
					}
				}
			}
		}
	}
	
	public void WorkWithXML(ActionEvent event) {
		if(WorkingStatus.isEmpty()) {
			setStudentTable(null);
			
			btninsert.setText("Insert (XML)");
			btnupdate.setText("Update (XML)");
			btndelete.setText("Delete (XML)");
			txtNotice.setText("Notice: You are working with XML!");
			WorkingStatus = "XML";
		}
		else {
			if(WorkingStatus.equals("Database")) {
				setStudentTable(null);
				
				btninsert.setText("Insert (XML)");
				btnupdate.setText("Update (XML)");
				btndelete.setText("Delete (XML)");
				txtNotice.setText("Notice: You are working with XML!");
				WorkingStatus = "XML";
			}
			else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initOwner(MenuStage);
				alert.initModality(Modality.WINDOW_MODAL);
				alert.setTitle("Information!");
				alert.setHeaderText("The Current MODE is XML!");
				alert.show();
			}
		}
	}
	
	//About
	public void ShowAbout(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Manage Student");
        alert.setHeaderText("About");
        alert.setContentText("Author: Tran Thanh Khang");

        alert.showAndWait();
	}

}
