package GUI;

import GUI.ManagementCenter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Results {

	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button center = new Button("Back to Management Center");
		
		center.setFont(new Font("Arial", 25));
		center.setLayoutX(80);
		center.setLayoutY(870);
		
		root.getChildren().add(center);
		
		center.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		Scene scene = new Scene(root, 1770, 980);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
