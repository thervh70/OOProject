package GUI;




import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class CompetitionTable {

	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		
		root.getChildren().add(Style.setBackground("/GUI/Resources/background_competition-table.png"));
		
		//Create a button to go back to the management center screen
		Button back = new Button("Back to Management Center");
		Style.setButtonStyle(back, 45);
		back.setLayoutX(150);
		back.setLayoutY(870);
		
		//Create a table with fixed columns
		TableView<String> table = new TableView();
		table.setEditable(false);
		table.setPrefSize(700, 540);
		table.setLayoutX(600);
		table.setLayoutY(225);
		TableColumn rank = new TableColumn("Rank");
		rank.setPrefWidth(50);
        TableColumn name = new TableColumn("Team Name");
		name.setPrefWidth(550);
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
