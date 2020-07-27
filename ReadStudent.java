/**
 * This class read the ArrayList<Student> from student.out
 * Showing the detailed objects when user click button "Display Students"
 */
package application;

import java.io.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class ReadStudent extends Application{
	private static ArrayList<Student> stuList=null;

	public void start(Stage primaryStage) {

		// Set the layout of primary stage
		GridPane firstPane = new GridPane();
		firstPane.setPadding(new Insets(5,5,5,5));

		//A button to control showing students
		Button dspStu = new Button("Display Students");
		dspStu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {

				Stage subStage = new Stage();
				//using GridPane as layout
				GridPane pane = new GridPane();
				pane.setPadding(new Insets(5,5,5,5));

				//Loop the ArrayList to add the labels to scene, variable 'line' is the line number of first/last name
				int line = 0;
				for(Student stu:stuList) {
					//new label for text "First Name" and set to bold
					Label fn = new Label("First Name: ");
					fn.setStyle("-fx-font-weight:bold");
					//add to the pane and set the position
					pane.add(fn,0,line);
					//add the first name of object to pane and set position
					pane.add(new Label(stu.getFirstName()),1,line);

					Label ln = new Label("Last Name: ");
					ln.setStyle("-fx-font-weight:bold");
					pane.add(ln,2,line);
					pane.add(new Label(stu.getLastName()),3,line);

					Label sID = new Label("Student ID:");
					sID.setStyle("-fx-font-weight:bold");
					pane.add(sID,0,line+1);
					pane.add(new Label(" " + stu.getStdID()),1,line+1);

					Label sCrs = new Label("Courses:");
					sCrs.setStyle("-fx-font-weight:bold");
					pane.add(sCrs,0,line+2);
					pane.add(new Label(" " + stu.getCourses()),1,line+2);
					//space line between two students
					pane.add(new Label(""), 0, line+3);
					line+=4;		
				}


				Scene subScene = new Scene(pane, 400 ,300);
				subStage.setTitle("Display All Students");
				subStage.setScene(subScene);
				subStage.show();
			}
		});

		firstPane.add(dspStu, 1, 1);
		Scene scene = new Scene(firstPane, 100, 100);
		primaryStage.setTitle("Read File");
		primaryStage.setScene(scene);
		primaryStage.show();

	}


	public static void main(String args[]) {

		try {
			//read from students.out
			FileInputStream fileObj = new FileInputStream("students.out");
			ObjectInputStream stuObj = new ObjectInputStream(fileObj);

			//cast read object to ArrayList<Student>
			stuList = (ArrayList<Student>)stuObj.readObject();

			fileObj.close();
		} catch (Throwable e) {
			System.err.println(e);
		}

		launch(args);

	}


}

