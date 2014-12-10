package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		
		Style.setButtonStyle(results, 50);
		Style.setButtonStyle(start, 60);
		Style.setButtonStyle(back, 45);

	//	Image background = new Image("/GUI/Resources/background_matchcenter.png");
	//	ImageView imgView = new ImageView(background);
	//	root.getChildren().add(imgView);

		back.setLayoutX(150);
		back.setLayoutY(870);
		results.setLayoutX(150);
		results.setLayoutY(200);
		start.setLayoutX(150);
		start.setLayoutY(300);
		
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
		
		root.getChildren().addAll(back, results, start);
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}

}
