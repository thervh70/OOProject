package GUI;

import GUI.ManagementCenter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Results {

	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button back = new Button("Back to Management Center");
		Line line = new Line();
		
		line.setEndX(1770);
		line.setLayoutY(820);
		
		TableView<String> tableResults = new TableView();
		tableResults.setPrefSize(750, 500);
		tableResults.setLayoutX(200);
		tableResults.setLayoutY(150);
		tableResults.setEditable(false);
		
		TableColumn homeTeam = new TableColumn("Home");
		TableColumn column = new TableColumn("");
		TableColumn awayTeam = new TableColumn("Away");
		TableColumn column2 = new TableColumn("");
		TableColumn Score = new TableColumn("Score");
		
		homeTeam.setPrefWidth(200);
		awayTeam.setPrefWidth(200);
		column2.setPrefWidth(100);
		Score.setPrefWidth(150);
		
		homeTeam.setResizable(false);
		awayTeam.setResizable(false);
		column.setResizable(false);
		column2.setResizable(false);
		Score.setResizable(false);
		
		tableResults.getColumns().addAll(homeTeam, column, awayTeam, column2, Score);
		
		back.setFont(new Font("Arial", 25));
		back.setLayoutX(80);
		back.setLayoutY(870);
		
		root.getChildren().addAll(back, tableResults, line);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
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
