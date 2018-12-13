package DAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Student;

public class StudentDAO {
	private final String DBURL = "jdbc:sqlserver://localhost:1433;databaseName=ManageStudent";
	private final String USERNAME = "sa";
	private final String PASSWORD = "1234567890";
	
	//Create a new student from database
	private Student createStudent(ResultSet rs) {
		Student newstudent = new Student();
		try {
			newstudent.setID(rs.getString("ID"));
			newstudent.setFirstName(rs.getString("First Name"));
			newstudent.setLastName(rs.getString("Last Name"));
			newstudent.setClassName(rs.getString("Class Name"));
			
			Date tempbirthday = rs.getDate("Birthday");
			SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
			newstudent.setBirthday(ft.format(tempbirthday));
			
			newstudent.setAddress(rs.getString("Address"));
			newstudent.setGender(rs.getString("Gender"));
			newstudent.setNumberPhone(rs.getString("Number Phone"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return newstudent;
	}
	
	//Create a list student form database
	public List<Student> getList(){
		String sql = "SELECT * FROM Student ORDER BY ID";  	
		//Don't notice this: CONVERT(varchar,Birthday,103)
		
		List<Student> list = new ArrayList<Student>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
			Statement stt = conn.createStatement();
			ResultSet rs = stt.executeQuery(sql);
			
			//add students into list
			while(rs.next()) {
				Student newstudent = createStudent(rs);
				list.add(newstudent);
			}
			
			rs.close();
			conn.close();
		} catch(ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	//Delete a student from database by ID
	public int DeleteStudent(String id) {
		int check = 1;
		String sql = "DELETE FROM Student WHERE ID=?";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
			PreparedStatement stt = conn.prepareStatement(sql);
			
			stt.setString(1, id);
			
			//Perform deleting students and check how many students were deleted
			stt.executeUpdate();
			
			/*
			int rowsDeleted = stt.executeUpdate();
			if (0 <= rowsDeleted) {
			}
			*/
			conn.close();
		} catch(Exception ex) {
			check = 0;
			ex.printStackTrace();
		}
		
		return check;
	}
	
	//Insert a student into database
	public int InsertStudent(Student student) {
		int check = 1;
		String sql = "INSERT INTO Student VALUES(?,?,?,?,CONVERT(date,?,103),?,?,?)";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
			PreparedStatement stt = conn.prepareStatement(sql);
			
			stt.setString(1, student.getID());
			stt.setString(2, student.getFirstName());
			stt.setString(3, student.getLastName());
			stt.setString(4, student.getClassName());
			stt.setString(5, student.getBirthday());
			stt.setString(6, student.getAddress());
			stt.setString(7, student.getGender());
			stt.setString(8, student.getNumberPhone());
			
			//Perform inserting a student into database
			stt.executeUpdate();
			
			/*
			int rowsInserted = stt.executeUpdate();
			if (0 <= rowsInserted) {
			}
			*/
		} catch(Exception ex) {
			check = 0;
			ex.printStackTrace();
		}
		return check;
	}
	
	//Update Student for database
	public int UpdateStudent(Student student, String tempID) {
		int check = 1;
		String sql = "UPDATE Student SET ID = ?, [First Name] = ?, [Last Name] = ?, [Class Name] = ?,"
				+ " Birthday = CONVERT(date,?,103), Address = ?, Gender = ?, [Number Phone] = ?"
				+ " WHERE ID = ?";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
			PreparedStatement stt = conn.prepareStatement(sql);
			
			stt.setString(1, student.getID());
			stt.setString(2, student.getFirstName());
			stt.setString(3, student.getLastName());
			stt.setString(4, student.getClassName());
			stt.setString(5, student.getBirthday());
			stt.setString(6, student.getAddress());
			stt.setString(7, student.getGender());
			stt.setString(8, student.getNumberPhone());
			stt.setString(9, tempID);
			
			//Perform update student
			stt.executeUpdate();
			
			/*
			int rowsUpdate = stt.executeUpdate();
			if(0 <= rowsUpdate) {
			}
			*/
			
		} catch (Exception ex) {
			check = 0;
			ex.printStackTrace();
		}
		return check;
	}
}
