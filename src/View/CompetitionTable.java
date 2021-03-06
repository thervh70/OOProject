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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CompetitionTable {
	
	/**This shows the CompetitionMatches screen, displaying a table showing the total ranking till now.
	 * @author D18.1
	 * 
	 * @param primaryStage - The GUI of Frits.
	 * @param competitionTable - ObservableList<Standing> used in the table to display ranking.
	 */
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
		
		//Create a table with fixed columns
		TableView<Standing> table = new TableView();
		table.setEditable(false);
		table.setPrefSize(Style.getNewSize(832), Style.getNewSize(554));
		Style.setLocation(table,550,250);
		
		List<Standing> standingList = new ArrayList<Standing>();
		for(int i = 0; i < 18; i++){
			standingList.add(saveGame.getDB().getTeam(i).getStanding());
		}
		Collections.sort(standingList, new Standing());
		
		ObservableList<Standing> competitionTable = FXCollections.observableArrayList();

		
		standingList.get(0).setRank(1);
		competitionTable.add(standingList.get(0));
		int i = 1;
		while(i < 18){
			if((standingList.get(i).getGoalDifference() == standingList.get(i-1).getGoalDifference()) && (standingList.get(i).getGoalsFor() == standingList.get(i-1).getGoalsFor())){
				standingList.get(i).setRank(standingList.get(i-1).getRank());
				competitionTable.add(standingList.get(i));
				i += 1;
			} else {
				standingList.get(i).setRank(i+1);
				competitionTable.add(standingList.get(i));
				i += 1;
			}
		}
		
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
        
        setUnderline(name);
               
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
	
	/** Underlines the entries which hold the name of the team the user plays as
	 * @author 	D18.1
	 * @param t	TableColumn to which this method needs to be applied
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
