package View;

import java.util.ArrayList;

import Controller.CreateSelection;
import Controller.gameEngine;
import Controller.saveGame;
import Model.Competition;
import Model.Match;
import Model.Result;
import Model.Standing;
import Model.Team;
import View.ManagementCenter;
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

public class Results {
	public static ObservableList<Standing> competitionTable = FXCollections.observableArrayList();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void start(Stage primaryStage, gameEngine match) {
		Pane root = new Pane(); 
		
		Button back = new Button("Back to Management Center");
		
		Style.setButtonStyle(back, 45);
		Style.setLocation(back, 150, 870);
		
		ObservableList<Result> resultTable = FXCollections.observableArrayList();
		
		Team TeamA1 = match.getTeamA();
		Team TeamB1 = match.getTeamB();
		Result save = new Result(TeamA1,TeamB1,match.getGoalsA(),match.getGoalsB());
		resultTable.add(save);
		
		if(match.getToto() == 1){
			TeamA1.addPoints(3, match.getGoalsA(), match.getGoalsB());
			competitionTable.add(TeamA1.getStanding());
			TeamB1.addPoints(0, match.getGoalsB(), match.getGoalsA());
			competitionTable.add(TeamB1.getStanding());
		} else if(match.getToto() == 2){
			TeamA1.addPoints(0, match.getGoalsA(), match.getGoalsB());
			competitionTable.add(TeamA1.getStanding());
			TeamB1.addPoints(3, match.getGoalsB(), match.getGoalsA());
			competitionTable.add(TeamB1.getStanding());
		} else if(match.getToto() == 0){
			TeamA1.addPoints(1, match.getGoalsA(), match.getGoalsB());
			competitionTable.add(TeamA1.getStanding());
			TeamB1.addPoints(1, match.getGoalsB(), match.getGoalsA());
			competitionTable.add(TeamB1.getStanding());
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
				
				Result othermatch = new Result(alpha,beta,other.getGoalsA(),other.getGoalsB());
				
				resultTable.add(othermatch);
				Team TeamA2 = other.getTeamA();
				Team TeamB2 = other.getTeamB();
				if(other.getToto() == 1){
					TeamA2.addPoints(3, other.getGoalsA(), other.getGoalsB());
					Standing standingA = new Standing(TeamA2.getPoints(), TeamA2, TeamA2.getGoalsFor(), TeamA2.getGoalsAgainst(), TeamA2.getGoalDifference());
					competitionTable.add(standingA);
					TeamB2.addPoints(0, other.getGoalsB(), other.getGoalsA());
					Standing standingB = new Standing(TeamB2.getPoints(), TeamB2, TeamB2.getGoalsFor(), TeamB2.getGoalsAgainst(), TeamB2.getGoalDifference());
					competitionTable.add(standingB);
				} else if(other.getToto() == 2){
					TeamA2.addPoints(0, other.getGoalsA(), other.getGoalsB());
					Standing standingA = new Standing(TeamA2.getPoints(), TeamA2, TeamA2.getGoalsFor(), TeamA2.getGoalsAgainst(), TeamA2.getGoalDifference());
					competitionTable.add(standingA);
					TeamB2.addPoints(3, other.getGoalsB(), other.getGoalsA());
					Standing standingB = new Standing(TeamB2.getPoints(), TeamB2, TeamB2.getGoalsFor(), TeamB2.getGoalsAgainst(), TeamB2.getGoalDifference());
					competitionTable.add(standingB);
				} else if(other.getToto() == 0){
					TeamA2.addPoints(1, other.getGoalsA(), other.getGoalsB());
					Standing standingA = new Standing(TeamA2.getPoints(), TeamA2, TeamA2.getGoalsFor(), TeamA2.getGoalsAgainst(), TeamA2.getGoalDifference());
					competitionTable.add(standingA);
					TeamB2.addPoints(1, other.getGoalsB(), other.getGoalsA());
					Standing standingB = new Standing(TeamB2.getPoints(), TeamB2, TeamB2.getGoalsFor(), TeamB2.getGoalsAgainst(), TeamB2.getGoalDifference());
					competitionTable.add(standingB);
				}
			}
		}
	
		TableView<Result> tableResults = new TableView();
		tableResults.setPrefSize(Style.getNewSize(750), Style.getNewSize(500)); 
		Style.setLocation(tableResults, 200, 150);
		tableResults.setEditable(false);
		
		tableResults.setItems(resultTable);
		
		TableColumn homeTeam = new TableColumn("Home");
		homeTeam.setCellValueFactory(new PropertyValueFactory<Result, String>("A"));
		TableColumn column = new TableColumn("");
		TableColumn awayTeam = new TableColumn("Away");
		awayTeam.setCellValueFactory(new PropertyValueFactory<Result, String>("B"));
		TableColumn column2 = new TableColumn("");
		TableColumn Score = new TableColumn("Score");
		Score.setCellValueFactory(new PropertyValueFactory<Result, String>("score"));
		
		homeTeam.setPrefWidth(Style.getNewSize(200));
		awayTeam.setPrefWidth(Style.getNewSize(200));
		column2.setPrefWidth(Style.getNewSize(100));
		Score.setPrefWidth(Style.getNewSize(150));
		
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
				saveGame.nextDay();
				saveGame.clearDBcards();
				ManagementCenter.start(primaryStage);
			}
		});
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
	
	public static ObservableList<Standing> getCompetitionTable(){
		return competitionTable;
	}

}
