package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MatchCenter {

	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button results = new Button("Results");
		Button start = new Button("Start match");
		Button back = new Button("Back to Management Center");
		Line line = new Line();
		
		line.setEndX(1770);
		line.setLayoutY(820);
		
		back.setFont(new Font("Arial", 25));
		results.setFont(new Font("Arial", 25));
		start.setFont(new Font("Arial", 30));
		back.setLayoutX(80);
		back.setLayoutY(870);
		results.setLayoutX(100);
		results.setLayoutY(200);
		start.setLayoutX(100);
		start.setLayoutY(270);
		
	
		
		results.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Results.start(primaryStage);
			}
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		root.getChildren().addAll(back, results, start, line);
		Scene scene = new Scene(root, 1770, 980);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
