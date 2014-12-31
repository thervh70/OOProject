package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CompetitionTable {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		
		root.getChildren().add(Style.setBackground("/View/Resources/background_competition-table.png"));
		
		//Create a button to go back to the management center screen
		Button back = new Button("Back to Management Center");
		Style.setButtonStyle(back, 45);
		Style.setLocation(back,150,870);
		
		//Create a table with fixed columns
		TableView<String> table = new TableView();
		table.setEditable(false);
		table.setPrefSize(Style.getNewSize(700), Style.getNewSize(540));
		Style.setLocation(table,600,225);
		
		TableColumn rank = new TableColumn("Rank");
		rank.setPrefWidth(Style.getNewSize(50));
        TableColumn name = new TableColumn("Team Name");
		name.setPrefWidth(Style.getNewSize(550));
		TableColumn points = new TableColumn("Points");
		points.setPrefWidth(100);
        rank.setResizable(false);
        name.setResizable(false);
        points.setResizable(false);
        table.getColumns().addAll(rank,name,points);
        
        
		root.getChildren().addAll(back, table);
		
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
