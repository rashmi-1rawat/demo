package com.student.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class StudentDbUtil {

	private DataSource dataSource;
	
	

	public StudentDbUtil(DataSource theDatasource) {
		dataSource= theDatasource;
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("null")
	public List<Student> getStudents() throws SQLException{
		List<Student> students = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from student order by last_name";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);

	         while(myRs.next()) {
	        	 int id= myRs.getInt("id");
	        	 String firstName =myRs.getString("first_name");
	        	 String lastName= myRs.getString("last_name");
	        	 String email = myRs.getString("email");
	        	 Student tempStudent = new Student(id,firstName,lastName,email);
	        	 students.add(tempStudent);
	         }
			 return students; 
		}
		finally {
close(myConn,myStmt,myRs);
		}
		
	}

	private void close(Connection myConn, java.sql.Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
	try {
		if(myRs !=null) {
			myRs.close();
		}
		if(myStmt != null) {
			myStmt.close();
		}
		if(myConn!=null) {
			myConn.close();
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}

	public void addStudent(Student theStudent) throws SQLException {
		// TODO Auto-generated method stub
		java.sql.Connection myconn = null;
		java.sql.PreparedStatement myStmt = null;
		
		try {
			myconn =dataSource.getConnection();
			String sql="insert into student"
					+"(first_name,last_name,email)"
					+"values(?,?,?)";
			
			myStmt=myconn.prepareStatement(sql);
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastname());
			myStmt.setString(3, theStudent.getEmail());
			
			
			myStmt.execute();
		}
		finally {
			close(myconn,myStmt,null);
		}
	}

	public Student getStudent(String theStudentId) throws Exception {

		Student theStudent = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from student where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				// use the studentId during construction
				theStudent = new Student(studentId, firstName, lastName, email);
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}				
			
			return theStudent;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}


	public void updateStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update student "
						+ "set first_name=?, last_name=?, email=? "
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastname());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteStudent(String theStudentId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from student where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}

}
