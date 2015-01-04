package View;

import Controller.gameEngine;
import Model.DBmain;
import Model.Result;
import Model.Team;
import Model.XmlParser;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void start(Stage primaryStage, gameEngine match) {
		Pane root = new Pane(); 
		DBmain d = XmlParser.parseDB();
		
		Button back = new Button("Back to Management Center");
		
		Style.setButtonStyle(back, 45);
		Style.setLocation(back, 150, 870);
		
		ObservableList<Result> resultTable = FXCollections.observableArrayList();
		
		Result save = new Result(match.getTeamA(),match.getTeamB(),match.getGoalsA(),match.getGoalsB());
		resultTable.add(save);
		
		for(int i = 0; i < 18; i += 2){
			Team alpha = d.getTeam(i);
			Team beta = d.getTeam(i+1);
			gameEngine other = new gameEngine();
			other.play(alpha, beta);
			
			Result othermatch = new Result(alpha,beta,other.getGoalsA(),other.getGoalsB());
			
			resultTable.add(othermatch);
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
				ManagementCenter.start(primaryStage);
			}
		});
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}

}
