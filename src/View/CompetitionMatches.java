package View;

import java.util.ArrayList;

import Controller.saveGame;
import Model.Competition;
import Model.Match;
import Model.Standing;
import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

	public class CompetitionMatches {
		
		private static ObservableList<Match> matchList = FXCollections.observableArrayList();
		
		/**This shows the CompetitionMatches screen, displaying a table containing all past results.
		 * 
		 * @param primaryStage - The GUI of Frits
		 * @param competitionTable - An ObservableList<Standing> used by CompetitionTable
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void start(Stage primaryStage, ObservableList<Standing> competitionTable) {

			Pane root = new Pane();			
			root.getChildren().add(Style.setBackground("/View/Resources/background_competition-table.png"));
			
			//Create a button to go back to the management center screen
			Button back = new Button("Back to Management Center");
			Style.setButtonStyle(back, 45);
			Style.setLocation(back,150,870);
			
			Button toMatch = new Button("To Ranking");
			Style.setButtonStyle(toMatch, 45);
			Style.setLocation(toMatch, 1550, 870);
			
			TableView<Match> table = new TableView();
			table.setEditable(false);
			table.setPrefSize(Style.getNewSize(840), Style.getNewSize(540));
			Style.setLocation(table,550,250);
			
			TableColumn day = new TableColumn("Day");
			day.setPrefWidth(Style.getNewSize(50));
			day.setCellValueFactory(new PropertyValueFactory<Match, Integer>("day"));
	        TableColumn teamHome = new TableColumn("Team Home");
			teamHome.setPrefWidth(Style.getNewSize(200));
			teamHome.setCellValueFactory(new PropertyValueFactory<Match, String>("teamHomeName"));
			TableColumn teamAway = new TableColumn("Team Away");
			teamAway.setPrefWidth(Style.getNewSize(200));
			teamAway.setCellValueFactory(new PropertyValueFactory<Match, String>("teamAwayName"));
			TableColumn homeScore = new TableColumn("");
			homeScore.setPrefWidth(Style.getNewSize(50));
			homeScore.setCellValueFactory(new PropertyValueFactory<Match, Integer>("goalsHomeS"));
			TableColumn awayScore = new TableColumn("");
			awayScore.setPrefWidth(Style.getNewSize(50));
			awayScore.setCellValueFactory(new PropertyValueFactory<Match, Integer>("goalsAwayS"));
			
			day.setResizable(false);
			teamHome.setResizable(false);
			teamAway.setResizable(false);
			homeScore.setResizable(false);
			awayScore.setResizable(false);
			
			setColor(teamAway);
			setColor(teamHome);
			
			table.getColumns().addAll(day,teamHome,homeScore,awayScore,teamAway);
			
			Competition comp = saveGame.getCompetition();
			matchList.clear();
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
					CompetitionTable.start(primaryStage, competitionTable);				
				}				
			});
			
			root.getChildren().addAll(back,table,toMatch);
	
			primaryStage.getScene().setRoot(root);
			primaryStage.show();
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private static void setColor(TableColumn t){
			t.setCellFactory(new Callback<TableColumn, TableCell>() {
				public TableCell call(TableColumn param) {
					return new TableCell<Team, String>() {

				        public void updateItem(String item, boolean empty) {
				        	super.updateItem(item, empty);
				            if (!isEmpty()) {
				            	this.setTextFill(Color.BLACK);
				            	Team t = saveGame.getDB().findTeam(this.getItem());
				            	if(t.getNm().equals(saveGame.getMyTeam().getNm())){
				            		this.setUnderline(true);
				            	}		            	
				            	setText(item);
				            }
				        }
					};
			     }
			 });
		}
}
