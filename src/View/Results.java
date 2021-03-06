package View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Controller.CreateSelection;
import Controller.gameEngine;
import Controller.saveGame;
import Model.Competition;
import Model.Match;
import Model.Standing;
import Model.Team;
import View.ManagementCenter;
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

public class Results {
	
	/**Shows the Results screen, which contains the results of the matches of the current day.
	 * @author D18.1
	 * 
	 * @param primaryStage - The window
	 * @param match - 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void start(Stage primaryStage, gameEngine match) {
		Pane root = new Pane(); 
		
		Button back = new Button("Back to Management Center");
		
		Style.setButtonStyle(back, 45);
		Style.setLocation(back, 150, 870);
		
		ObservableList<Match> resultTable = FXCollections.observableArrayList();
		
		Team TeamA1 = match.getTeamA();
		Team TeamB1 = match.getTeamB();
		Match save = new Match(saveGame.getDay(),TeamA1,TeamB1,match.getGoalsA(),match.getGoalsB());
		saveGame.setMatchResult(save);
		resultTable.add(save);

		if(match.getToto() == 1){
			TeamA1.addPoints(3, match.getGoalsA(), match.getGoalsB());
			TeamB1.addPoints(0, match.getGoalsB(), match.getGoalsA());
		} else if(match.getToto() == 2){
			TeamA1.addPoints(0, match.getGoalsA(), match.getGoalsB());
			TeamB1.addPoints(3, match.getGoalsB(), match.getGoalsA());
		} else if(match.getToto() == 0){
			TeamA1.addPoints(1, match.getGoalsA(), match.getGoalsB());
			TeamB1.addPoints(1, match.getGoalsB(), match.getGoalsA());
		}
		
		Competition comp = saveGame.getCompetition();
		int day = saveGame.getDay();
		
		ArrayList<Match> gamesToday = comp.getMatchesForDay(day);
		
		for(Match game : gamesToday){
			
			if(match.getTeamA().getNm().equals(game.getTeamHome().getNm())){
				;
			}
			
			else {
				Team alpha = game.getTeamHome();
				CreateSelection.create(alpha);
				Team beta = game.getTeamAway();
				CreateSelection.create(beta);
				
				gameEngine other = new gameEngine();
				
				other.play(alpha, beta);
				
				Match othermatch = new Match(saveGame.getDay(),alpha,beta,other.getGoalsA(),other.getGoalsB());
				
				saveGame.setMatchResult(othermatch);
				
				resultTable.add(othermatch);
				Team TeamA2 = other.getTeamA();
				Team TeamB2 = other.getTeamB();
				if(other.getToto() == 1){
					TeamA2.addPoints(3, other.getGoalsA(), other.getGoalsB());
					TeamB2.addPoints(0, other.getGoalsB(), other.getGoalsA());
				} else if(other.getToto() == 2){
					TeamA2.addPoints(0, other.getGoalsA(), other.getGoalsB());
					TeamB2.addPoints(3, other.getGoalsB(), other.getGoalsA());
				} else if(other.getToto() == 0){
					TeamA2.addPoints(1, other.getGoalsA(), other.getGoalsB());
					TeamB2.addPoints(1, other.getGoalsB(), other.getGoalsA());
				}
			}
		}
		List<Standing> standingList = new ArrayList<Standing>();
		for(int i = 0; i < 18; i++){
			standingList.add(saveGame.getDB().getTeam(i).getStanding());
		}
		Collections.sort(standingList, new Standing());

		for(int i = 0; i < 18; i++) {
			standingList.get(i).setRank(i+1);
		}
	
		TableView<Match> tableResults = new TableView();
		tableResults.setPrefSize(Style.getNewSize(750), Style.getNewSize(500)); 
		Style.setLocation(tableResults, 575, 200);
		tableResults.setEditable(false);
		
		tableResults.setItems(resultTable);
		
		TableColumn homeTeam = new TableColumn("Home");
		homeTeam.setCellValueFactory(new PropertyValueFactory<Match, String>("teamHomeName"));
		TableColumn column = new TableColumn("");
		TableColumn awayTeam = new TableColumn("Away");
		awayTeam.setCellValueFactory(new PropertyValueFactory<Match, String>("teamAwayName"));
		TableColumn column2 = new TableColumn("");
		TableColumn Score = new TableColumn("Score");
		Score.setCellValueFactory(new PropertyValueFactory<Match, String>("score"));
		
		homeTeam.setPrefWidth(Style.getNewSize(200));
		awayTeam.setPrefWidth(Style.getNewSize(200));
		column2.setPrefWidth(Style.getNewSize(100));
		Score.setPrefWidth(Style.getNewSize(150));
		
		homeTeam.setResizable(false);
		awayTeam.setResizable(false);
		column.setResizable(false);
		column2.setResizable(false);
		Score.setResizable(false);
		
		setUnderline(homeTeam);
		setUnderline(awayTeam);
		
		tableResults.getColumns().addAll(homeTeam, column, awayTeam, column2, Score);
		
		root.getChildren().add(Style.setBackground("/View/Resources/background_results.png"));
		
		root.getChildren().addAll(back, tableResults);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				saveGame.nextDay();
				ManagementCenter.start(primaryStage);
			}
		});
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
	
	/**	Underlines the name of the team the user plays as
	 * @author D18.1
	 * @param t	Table to which this method needs to be applied
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void setUnderline(TableColumn t){
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
