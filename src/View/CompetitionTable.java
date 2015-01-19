package View;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Controller.saveGame;
import Model.Standing;
import Model.Team;
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
		
		Button toMatch = new Button("To Matches");
		Style.setButtonStyle(toMatch, 45);
		Style.setLocation(toMatch, 1550, 870);
		
		List<Standing> standingList = new ArrayList<Standing>();
		for(int i = 0; i < 18; i++){
			standingList.add(saveGame.getDB().getTeam(i).getStanding());
		}
		Collections.sort(standingList, new Standing());
		
		ObservableList<Standing> competitionTable = FXCollections.observableArrayList();

		for(int i = 0; i < 18; i++) {
			standingList.get(i).setRank(i+1);
			competitionTable.add(standingList.get(i));
		}
		
		//Create a table with fixed columns
		TableView<Standing> table = new TableView();
		table.setEditable(false);
		table.setPrefSize(Style.getNewSize(832), Style.getNewSize(554));
		Style.setLocation(table,550,250);
		
		table.setItems(competitionTable);
		
		TableColumn rank = new TableColumn("Rank");
		rank.setPrefWidth(Style.getNewSize(50));
		rank.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("rank"));
        TableColumn name = new TableColumn("Team Name");
		name.setPrefWidth(Style.getNewSize(380));
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
        goalsAgainst.setResizable(false);
        goalsFor.setResizable(false);
        goalDifference.setResizable(false);
               
        table.getColumns().addAll(rank,name,points,goalsFor,goalsAgainst,goalDifference);
        
		root.getChildren().addAll(back, table,toMatch);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		}); 
		
		toMatch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				CompetitionMatches.start(primaryStage);				
			}
			
		});
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
}
