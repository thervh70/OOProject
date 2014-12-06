package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MatchCenter {

	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button results = new Button("Results");
		
		results.setFont(new Font("Arial", 30));
		results.setLayoutX(150);
		results.setLayoutY(800);
		
		root.getChildren().add(results);
		
		results.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Results.start(primaryStage);
			}
		});
		
		Scene scene = new Scene(root, 1770, 980);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
