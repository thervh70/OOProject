package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TeamManagement {

	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button back = new Button("Back to Management Center");
		
		Image background = new Image("/GUI/Resources/background_team-management.png");
		ImageView imgView = new ImageView(background);
		root.getChildren().add(imgView);
		
		Text t1 = new Text(400,50,"Set-up");
		Text t2 = new Text(400,500, "Bench");
		
		//Create a table for the setup with fixed columns
		TableView<String> tableSetup = new TableView();
		tableSetup.setEditable(false);
		tableSetup.setPrefSize(700, 540);
		tableSetup.setLayoutX(125);
		tableSetup.setLayoutY(200);
		tableSetup.setEditable(false);

		//Create a table for the bench with fixed columns
		TableView<String> tableBench = new TableView();
		tableBench.setEditable(false);
		tableBench.setPrefSize(700, 540);
		tableBench.setLayoutX(1000);
		tableBench.setLayoutY(200);
		tableBench.setEditable(false);
		
		//Creat columns for both tables
		TableColumn name = new TableColumn("Name");
        TableColumn position = new TableColumn("Position");
		TableColumn age = new TableColumn("Age");
		TableColumn worth = new TableColumn("Worth");
		TableColumn pace = new TableColumn("PAC");
		TableColumn shooting = new TableColumn("SHO");
		TableColumn passing = new TableColumn("PAS");
		TableColumn dribbling = new TableColumn("DRI");
		TableColumn defending = new TableColumn("DEF");
		TableColumn physical = new TableColumn("PHY");
		name.setResizable(false);
		name.setPrefWidth(200);
		position.setResizable(false);
		position.setPrefWidth(100);
		age.setResizable(false);
		age.setPrefWidth(50);
		worth.setResizable(false);
		worth.setPrefWidth(100);
		shooting.setResizable(false);
		shooting.setPrefWidth(50);
		passing.setResizable(false);
		passing.setPrefWidth(50);
		dribbling.setResizable(false);
		dribbling.setPrefWidth(50);
		defending.setResizable(false);
		defending.setPrefWidth(50);
		physical.setResizable(false);

		name.setEditable(false);

		physical.setPrefWidth(50);

		
		//Add columns to table
		tableSetup.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical);
		tableBench.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical);
		
		
		
		back.setFont(new Font("Arial", 25));
		back.setLayoutX(80);
		back.setLayoutY(870);
		
		//Add elements to the canvas
		root.getChildren().addAll(back, tableSetup, tableBench);
		
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
