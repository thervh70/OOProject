package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TeamManagement {

	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button back = new Button("Back to Management Center");
		Line line = new Line();
		
		line.setEndX(1770);
		line.setLayoutY(820);
		
		//Create a table for the setup with fixed columns
		TableView<String> tableSetup = new TableView();
		tableSetup.setEditable(false);
		tableSetup.setPrefSize(700, 540);
		tableSetup.setLayoutX(100);
		tableSetup.setLayoutY(100);
		tableSetup.setEditable(false);
		
		//Create a table for the bench with fixed columns
		TableView<String> tableBench = new TableView();
		tableBench.setEditable(false);
		tableBench.setPrefSize(700, 540);
		tableBench.setLayoutX(100);
		tableBench.setLayoutY(550);
		tableBench.setEditable(false);
		
		//Creat columns for both tables
		TableColumn name = new TableColumn("Name");
        TableColumn position = new TableColumn("Position");
		TableColumn age = new TableColumn("Age");
		TableColumn worth = new TableColumn("Worth");
		TableColumn shooting = new TableColumn("Shooting");
		TableColumn passing = new TableColumn("Passing");
		TableColumn dribbling = new TableColumn("Dribbling");
		TableColumn defending = new TableColumn("Defending");
		TableColumn physical = new TableColumn("Physical");
		name.setResizable(false);
		position.setResizable(false);
		age.setResizable(false);
		worth.setResizable(false);
		shooting.setResizable(false);
		passing.setResizable(false);
		dribbling.setResizable(false);
		defending.setResizable(false);
		physical.setResizable(false);
		name.setEditable(false);
		
		//Add columns to table
		tableSetup.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical);
		tableBench.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical);
		
		
		
		back.setFont(new Font("Arial", 25));
		back.setLayoutX(80);
		back.setLayoutY(870);
		
		//Add elements to the canvas
		root.getChildren().addAll(back, tableSetup, tableBench, line);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
}
