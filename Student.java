package application;

import java.util.ArrayList;
import java.io.Serializable;

public class Student implements java.io.Serializable{
	private int stdID;
	private String firstName;
	private String lastName;
	private ArrayList<String> courses;

	//constructor take the student ID string to create object, 
	//if this string cannot cast to integer, it throws NumberFormatException
	//if the ID is a negative number, throwing StudentException
	public Student(String stuID) throws StudentException {
		int sID;

		sID = Integer.parseInt(stuID);

		if(sID<=0) {
			throw new StudentException("ID is invalid!");

		}else {
			stdID=sID;
			courses=new ArrayList<String>();
		}

	}

	public int getStdID() {
		return stdID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public ArrayList<String> getCourses() {
		return courses;
	}

	public void setStdID(int stdID) throws StudentException {
		if(stdID<=0) {
			throw new StudentException("ID is invalid!");
		} else {
			this.stdID = stdID;	
		}

	}

	//check the firstName, 
	//if it's null or contains any character except uppercase, lowercase letter, throw studentException
	//then if no exception, set first name.
	public void setFirstName(String firstName) throws StudentException {
		if((firstName.length() == 0)||firstName.matches(".*[^a-zA-Z].*")){
			throw new StudentException("First name is invalid!");
		}else {	
			this.firstName = firstName;
		}
	}

	public void setLastName(String lastName) throws StudentException {
		if((lastName.length() == 0)||lastName.matches(".*[^a-zA-Z].*")){
			throw new StudentException("Last name is invalid!");
		}else {	
			this.lastName = lastName;
		}

	}

	//Set Courses
	public void setCourses(String course) {

		this.courses.add(course);

	}



}

