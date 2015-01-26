package View;

import java.util.ArrayList;

import Controller.saveGame;
import Model.Competition;
import Model.Match;
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
import javafx.stage.Stage;
import javafx.util.Callback;

	public class CompetitionMatches {
		
		private static ObservableList<Match> matchList = FXCollections.observableArrayList();
		
		/**This shows the CompetitionMatches screen, displaying a table containing all past results.
		 * @author D18.1
		 * 
		 * @param primaryStage - The GUI of Frits
		 * @param competitionTable - An ObservableList<Standing> used by CompetitionTable
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void start(Stage primaryStage) {

			Pane root = new Pane();			
			root.getChildren().add(Style.setBackground("/View/Resources/background_competition-table.png"));
			
			//Create a button to go back to the management center screen
			Button back = new Button("Back to Management Center");
			Style.setButtonStyle(back, 45);
			Style.setLocation(back,150,870);
			
			Button toRanking = new Button("To Ranking");
			Style.setButtonStyle(toRanking, 45);
			Style.setLocation(toRanking, 1550, 870);
			
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
				/**Gives functionality to the "Back" button which directs to management center on action.
				 * @author D18.1
				 * 
				 */
				@Override
				public void handle(ActionEvent e) {
					ManagementCenter.start(primaryStage);
				}
			}); 
			
			toRanking.setOnAction(new EventHandler<ActionEvent>() {
				/**Gives functionality to the "To Ranking" button which directs to the ranking table on action.
				 * @author D18.1
				 * 
				 */
				@Override
				public void handle(ActionEvent event) {
					CompetitionTable.start(primaryStage);				
				}				
			});
			
			root.getChildren().addAll(back,table,toRanking);
	
			primaryStage.getScene().setRoot(root);
			primaryStage.show();
		}
		
		/**
		 * 
		 * @param t
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private static void setColor(TableColumn t){
			t.setCellFactory(new Callback<TableColumn, TableCell>() {
				public TableCell call(TableColumn param) {
					return new TableCell<Team, String>() {

				        public void updateItem(String item, boolean empty) {
				        	super.updateItem(item, empty);
				            if (!isEmpty()) {
				            	this.setUnderline(false);
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
