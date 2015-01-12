package View;

import Model.Standing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
		
		ObservableList<Standing> competitionTable = FXCollections.observableArrayList();
		competitionTable = Results.getCompetitionTable();
		
		//Create a table with fixed columns
		TableView<Standing> table = new TableView();
		table.setEditable(false);
		table.setPrefSize(Style.getNewSize(840), Style.getNewSize(540));
		Style.setLocation(table,550,225);
		
		table.setItems(competitionTable);
		
		TableColumn rank = new TableColumn("Rank");
		rank.setPrefWidth(Style.getNewSize(50));
        TableColumn name = new TableColumn("Team Name");
		name.setPrefWidth(Style.getNewSize(390));
		name.setCellValueFactory(new PropertyValueFactory<Standing, String>("teamName"));
		TableColumn points = new TableColumn("Points");
		points.setPrefWidth(Style.getNewSize(100));
		points.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("points"));
		TableColumn goalsFor = new TableColumn("GF");
		goalsFor.setPrefWidth(Style.getNewSize(100));
		goalsFor.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("goalsFor"));
		TableColumn goalsAgainst = new TableColumn("GA");
		goalsAgainst.setPrefWidth(Style.getNewSize(100));
		goalsAgainst.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("goalsAgainst"));
		TableColumn goalDifference = new TableColumn("GD");
		goalDifference.setPrefWidth(Style.getNewSize(100));
		goalDifference.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("goalDifference"));
		
        rank.setResizable(false);
        name.setResizable(false);
        points.setResizable(false);
        
        table.getColumns().addAll(rank,name,points,goalsFor,goalsAgainst,goalDifference);
        
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
