package DAOs;

import java.sql.*;
import java.util.*;
import Models.ContactStudents;

public class ContactDAO implements DAO {
	private ContactStudents createContactStudents(ResultSet rs) {
		ContactStudents student = new ContactStudents();
		try {
			student.setID(rs.getInt("ID"));
			student.setName(rs.getString("Name"));
			student.setAge(rs.getInt("Age"));
			student.setAddress(rs.getString("Address"));
		} catch (SQLException ex) {
			System.out.println("Ket noi that bai");
		}
		return student;
	}

	public List<ContactStudents> getContacts() {
		String sql = "SELECT * FROM Students ORDER BY ID";
		List<ContactStudents> list = new ArrayList<>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
			Statement stt = conn.createStatement();
			ResultSet rs = stt.executeQuery(sql);
			while (rs.next()) {
				ContactStudents student = createContactStudents(rs);
				list.add(student);
			}
			rs.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("Ket noi that bai");
		}
		return list;
	}
	
	public static int Insert(int ID, String Name, int Age, String Address) {
		int check=1;
		ContactStudents student = new ContactStudents();
		student.setID(ID);
		student.setName(Name);
		student.setAge(Age);
		student.setAddress(Address);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
			String sql = "INSERT INTO Students VALUES (?,?,?,?)";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setInt(1, student.getID());
			state.setString(2, student.getName());
			state.setInt(3, student.getAge());
			state.setString(4, student.getAddress());

			int rowsInserted = state.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Ket noi thanh cong");
			}
			conn.close();
			
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("Ket noi that bai");
			check=0;
		}
		return check;
	}
	
	public static int Delete(int ID) {
		int check = 1;
		ContactStudents student = new ContactStudents();
		student.setID(ID);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
			String sql = "DELETE FROM Students WHERE ID=?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, student.getID());

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Ket noi thanh cong");
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println("Ket noi that bai");
			check = 0;
		}
		return check;
	}
	
	public static ContactStudents Search(int ID) {
		ContactStudents student = new ContactStudents();
		student.setID(ID);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Students WHERE ID =" + student.getID());

			if (rs.next()) {
				student.setID(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setAge(rs.getInt(3));
				student.setAddress(rs.getString(4));
			} else {
				System.out.println("Data is not found!!!");
			}
			conn.close();
		} catch (Exception ex) {
		}
		return student;
	}
	
	public static int Update(int ID, String Name, int Age, String Address) {
		int check = 1;
		ContactStudents student = new ContactStudents();
		student.setID(ID);
		student.setName(Name);
		student.setAge(Age);
		student.setAddress(Address);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
			String sql = "UPDATE Student SET ID =?, Name=?, Age=?, Address=? WHERE ID=?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, student.getID());
			statement.setString(2, student.getName());
			statement.setInt(3, student.getAge());
			statement.setString(4, student.getAddress());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Ket noi thanh cong");
				check = 1;
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println("Ket noi that bai");
			check = 0;
		}
		return check;
	}
}

