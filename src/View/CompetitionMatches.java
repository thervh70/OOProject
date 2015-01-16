package View;

import java.util.ArrayList;

import Controller.saveGame;
import Model.Competition;
import Model.Match;
import Model.Result;
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
	
	public class CompetitionMatches {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void start(Stage primaryStage) {

			Pane root = new Pane();			
			root.getChildren().add(Style.setBackground("/View/Resources/background_competition-table.png"));
			
			//Create a button to go back to the management center screen
			Button back = new Button("Back to Management Center");
			Style.setButtonStyle(back, 45);
			Style.setLocation(back,150,870);
			
			Button toMatch = new Button("To Ranking");
			Style.setButtonStyle(toMatch, 45);
			Style.setLocation(toMatch, 1550, 870);
		
			ObservableList<Result> matchList = FXCollections.observableArrayList();
			
			TableView<Result> table = new TableView();
			table.setEditable(false);
			table.setPrefSize(Style.getNewSize(800), Style.getNewSize(540));
			Style.setLocation(table, 500, 250);
			
			TableColumn day = new TableColumn("Day");
			day.setPrefWidth(Style.getNewSize(50));
			day.setCellValueFactory(new PropertyValueFactory<Result, Integer>("day"));
	        TableColumn teamHome = new TableColumn("Team Home");
			teamHome.setPrefWidth(Style.getNewSize(200));
			teamHome.setCellValueFactory(new PropertyValueFactory<Result, String>("A"));
			TableColumn teamAway = new TableColumn("Team Away");
			teamAway.setPrefWidth(Style.getNewSize(200));
			teamAway.setCellValueFactory(new PropertyValueFactory<Result, String>("B"));
			TableColumn score = new TableColumn("Score");
			score.setPrefWidth(Style.getNewSize(50));
			score.setCellValueFactory(new PropertyValueFactory<Result, Integer>("score"));
			
			day.setResizable(false);
			teamHome.setResizable(false);
			teamAway.setResizable(false);
			score.setResizable(false);
			
			table.getColumns().addAll(day,teamHome,teamAway,score);
			
			Competition comp = saveGame.getCompetition();
			
			for(int i = 1; i < 35; i++){
				ArrayList<Match> matches = comp.getMatchesForDay(i);
				
				for(Match m : matches){
					matchList.add(m);
				}
			}
			
			table.setItems(matchList);
		
			back.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					ManagementCenter.start(primaryStage);
				}
			}); 
			
			toMatch.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					CompetitionTable.start(primaryStage);				
				}				
			});
			
			root.getChildren().addAll(back,table,toMatch);
	
			primaryStage.getScene().setRoot(root);
			primaryStage.show();
		}
}
