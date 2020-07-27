/**
 * This class create a new stage to prompts user input student's ID and name, then click the "add course" button
 * will display a sub-stage to ask user input courses
 * Write the students info into students.out when user click "Finish Entering"
 * Display error stage when the input is invalid
 */

package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;


public class WriteStudent extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ArrayList<Student> students=new ArrayList<Student>();

			//Using GridPane as layout, set padding, column and row
			GridPane pane = new GridPane();
			pane.setPadding(new Insets(5,5,5,5));
			pane.setVgap(3);
			pane.setHgap(10);

			//TextFields tt let user enter student's name and ID
			TextField stuID = new TextField();
			TextField stuFName = new TextField();
			TextField stuLName = new TextField();

			//A button to create student object and display sub stage to prompt courses
			Button addBtn = new Button("Add Courses To Student");
			addBtn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {

					try {
						//create student instance and set ID and name
						Student oneStd = new Student(stuID.getText());
						oneStd.setFirstName(stuFName.getText());
						oneStd.setLastName(stuLName.getText());

						//new sub stage and using GridPane as layout
						Stage courseStage = new Stage();
						courseStage.setTitle("Add Courses");
						GridPane coursePane = new GridPane();
						coursePane.setPadding(new Insets(5,5,5,5));
						coursePane.setVgap(3);
						coursePane.setHgap(10);

						//TextField to let user enter course
						TextField course = new TextField();

						//A button to add the course to student object and then clear text field
						Button addCourse = new Button("Add Course");
						addCourse.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent arg0) {
								oneStd.setCourses(course.getText());
								course.clear();
							}
						});

						//A button to end entering, add this students to students arraylist, close the sub stage and then wait for next student  
						Button finishCourse = new Button("Finish");
						finishCourse.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent arg0) {
								courseStage.close();
								students.add(oneStd);
								stuID.clear();
								stuFName.clear();
								stuLName.clear();
							}
						});

						//Add taxt field and two buttons to sub stage
						coursePane.add(new Label("Course: "), 0, 0);
						coursePane.add(course,1,0);
						coursePane.add(addCourse, 0, 1);
						coursePane.add(finishCourse, 1, 1);

						Scene courseScene = new Scene(coursePane,300,100);
						courseStage.setScene(courseScene);
						courseStage.show();
					}catch(Exception e) {
						//A sub stage when the user input is invalid
						Stage errStage = new Stage();
						errStage.setTitle("Error!");
						errStage.setScene(new Scene(new Label(e.getMessage()),200,100));
						errStage.show();

					}

				}
			});

			//A button to finish entering all student, and then output the arrayList to students.out
			Button endStd = new Button("Finish Entering");
			endStd.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					try {

						//Write ArrayList students to file "students.out"
						FileOutputStream fileObj = new FileOutputStream("students.out");
						ObjectOutputStream stuObjs = new ObjectOutputStream(fileObj);
						stuObjs.writeObject(students);

						stuObjs.flush();
						fileObj.close();

					} catch(Throwable e) {
						System.err.println(e);
					}

					primaryStage.close();
				} 
			});


			//Add all labels, text fields and buttons to primary stage and set the position
			pane.add(new Label("Student ID: "),0,0);
			pane.add(stuID,1,0);
			pane.add(new Label("First Name: "),0,1);
			pane.add(stuFName,1,1);
			pane.add(new Label("Last Name: "),0,2);
			pane.add(stuLName,1,2);
			pane.add(addBtn,1,3);
			pane.add(endStd, 2, 3);

			//Add scene
			Scene scene = new Scene(pane, 400, 150);
			primaryStage.setTitle("Add a student");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}