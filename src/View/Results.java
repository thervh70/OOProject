package View;

import View.ManagementCenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Results {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button back = new Button("Back to Management Center");
		
		Style.setButtonStyle(back, 45);
		back.setLayoutX(150);
		back.setLayoutY(870);
		
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
		
		root.getChildren().add(Style.setBackground("/View/Resources/background_results.png"));
		
		root.getChildren().addAll(back, tableResults);
		
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
