package com.student.jdbc;

public class Student {
	
	private int id;
	private String firstName;
	private String lastname;
	private String email;

	
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastname=" + lastname + ", email=" + email + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	



	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Student(String firstName, String lastname, String email) {
		this.firstName = firstName;
		this.lastname = lastname;
		this.email = email;
	}

	public Student(int id, String firstName, String lastname, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastname = lastname;
		this.email = email;
	}

	public Student() {
		// TODO Auto-generated constructor stub
	}

}
