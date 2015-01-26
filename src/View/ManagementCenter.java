package View;

import java.util.ArrayList;

import Controller.saveGame;
import Model.Competition;
import Model.Match;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ManagementCenter {

	/**Shows the ManagementCenter screen, where the user can pick to manage his team, check the results, go the the match center, etc.
	 * @author D18.1
	 * 
	 * @param primaryStage - The window for the application
	 */
	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button manage = new Button("Manage Team");
		Button table = new Button("Competition Table");
		Button match = new Button("Match Center");
		Button market = new Button("Transfer Market");
		Button save = new Button("Save Game");
		Button menu = new Button("Back to Main Menu");
		
		Style.setButtonStyle(manage, 55);
		Style.setButtonStyle(table, 55);
		Style.setButtonStyle(match, 55);
		Style.setButtonStyle(market, 55);
		Style.setButtonStyle(save, 55);
		
		Style.setButtonStyle(menu, 45);

		root.getChildren().add(Style.setBackground("/View/Resources/background_managementcenter.png"));

		Style.setLocation(menu, 150, 870);
		
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(manage, table, match, market, save);
		Style.setLocation(vbox, 350, 220);
		vbox.setAlignment(Pos.CENTER);
		
		Text day = new Text("Day: " + saveGame.getDay());
		Style.setTextStyle(day, 90);
		
		Competition comp = saveGame.getCompetition();
		String opp = null;
		
		ArrayList<Match> gamesToday = comp.getMatchesForDay(saveGame.getDay());
		for(Match game : gamesToday){
			Team home = game.getTeamHome();
			Team away = game.getTeamAway();
			
			if(home.getNm().equals(saveGame.getMyTeamName())){
				opp = away.getNm();
			}
			
			else if(away.getNm().equals(saveGame.getMyTeamName())){
				opp = home.getNm();
			}
		}
		
		Text opponent = new Text("Next opponent: " + opp);
		Style.setTextStyle(opponent, 60);
		
		Text space = new Text("");
		
		Text budget = new Text("Budget: " + saveGame.getMyTeam().getBdgt_vir());
		Style.setTextStyle(budget, 60);
		
		//Create local team to show ranking
		Team t = null;
		for(int i = 0; i<18; i++){
			if(saveGame.getMyTeam().getNm().equals(saveGame.getDB().getTeam(i).getNm())){
				t = saveGame.getDB().getTeam(i);
				if(t.getPoints() == 0 && t.getGoalsFor() == 0 && t.getGoalsAgainst() == 0){
					t.getStanding().setRank(i+1);
				}
			}
		}
		Text rank = new Text("Rank: " + t.getStanding().getRank());
		Style.setTextStyle(rank, 60);
		
		VBox vbox2 = new VBox(10);
		vbox2.getChildren().addAll(day, opponent,space,budget,rank);
		Style.setLocation(vbox2, 1100, 240);
		vbox2.setAlignment(Pos.CENTER);
		
		manage.setOnAction(new EventHandler<ActionEvent>() {
			/**Gives functionality to the "Manage Team" button and goes to the team management screen on click.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(ActionEvent e) {
				TeamManagement.start(primaryStage);
			}
		});
		
		table.setOnAction(new EventHandler<ActionEvent>() {
			/**Gives functionality to the "Competition table" button which directs to the ranking table screen on action.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(ActionEvent e) {
				CompetitionTable.start(primaryStage);
			}
		});
		
		match.setOnAction(new EventHandler<ActionEvent>() {
			/**Gives functionality to the "Match Center" button which directs to the match center screen on action.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(ActionEvent e) {
				
				try{
					MatchCenter.start(primaryStage);
				} catch(NullPointerException e1){
					Popup warning = Warning.makeWarning("Competition cannot be loaded", root);
					warning.show(primaryStage);
				}
			}
		});
		
		market.setOnAction(new EventHandler<ActionEvent>() {
			/**Gives functionality to the "Transfer Market" button which directs to the buying screen of the transfer market on action.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(ActionEvent e) {
				TransferMarket.start(primaryStage);				
			}
		});
		
		
		save.setOnAction(new EventHandler<ActionEvent>() {
			/**Gives functionality to the "Save Game" button which prompts the user for a filename if it is a new file or asks
			 * if the user wants to overwrite the existing file on action.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(ActionEvent event) {
				
				String file = saveGame.getFile();
				if(file.equals("")) {
					
					EventHandler<MouseEvent> save = new EventHandler<MouseEvent>() {
						
						public void handle(MouseEvent e) {
							String newFile = saveGame.getFile();
							saveGame.write(newFile);
							root.setDisable(false);
						}
					};
					
					EventHandler<MouseEvent> back = new EventHandler<MouseEvent>() {
						
						public void handle(MouseEvent e) {
							;
						}
					};
					
					Popup getText = Warning.getInput("Set File Name", root, save, back);
					getText.show(primaryStage);
					
				}
				else{
					EventHandler<MouseEvent> yes = new EventHandler<MouseEvent>() {
						
						public void handle(MouseEvent e) {
							root.setDisable(false);
							saveGame.write(file);
						}
					};
					
					EventHandler<MouseEvent> no = new EventHandler<MouseEvent>() {
						
						public void handle(MouseEvent e) {
							;
						}
					};
					
					Popup confirm = Warning.makeWarning("Do you want to overwrite \nthe previous savegame?", root, yes, no);
					confirm.show(primaryStage);	
				}			
			}
		});
		
		menu.setOnAction(new EventHandler<ActionEvent>() {
			/**Gives functionality to the "Back to Main Menu" button which asks the user if he/she really wants to exit.
			 * Confirming will direct to the startup menu on action.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(ActionEvent e) {
				EventHandler<MouseEvent> yes = new EventHandler<MouseEvent>() {
					
					public void handle(MouseEvent e) {
						root.setDisable(false);
						saveGame.setDefaults();
						StartupMenu.start(primaryStage);
					}
				};
				
				EventHandler<MouseEvent> no = new EventHandler<MouseEvent>() {
					
					public void handle(MouseEvent e) {
						;
					}
				};
				
				Popup confirm = Warning.makeWarning("Do you really want to quit?", root, yes, no);
				confirm.show(primaryStage);	
			}
		});
		
		
		
		root.getChildren().addAll(vbox, vbox2, menu);
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
}
