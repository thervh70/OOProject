package View;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Controller.saveGame;
import Model.Competition;
import Model.Match;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ManagementCenter {

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
		
		Text rank = new Text("Rank: ");
		Style.setTextStyle(rank, 60);
		
		VBox vbox2 = new VBox(10);
		vbox2.getChildren().addAll(day, opponent,space,budget,rank);
		Style.setLocation(vbox2, 1100, 240);
		vbox2.setAlignment(Pos.CENTER);
		
		manage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				TeamManagement.start(primaryStage);
			}
		});
		
		table.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				CompetitionTable.start(primaryStage);
			}
		});
		
		match.setOnAction(new EventHandler<ActionEvent>() {
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
			@Override
			public void handle(ActionEvent e) {
				try {
					TransferMarket.start(primaryStage);
				} catch (SAXException | IOException | ParserConfigurationException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				
				Popup save = new Popup();
				save.setHeight(200);
				save.setWidth(500);
				save.centerOnScreen();
				Rectangle rect = new Rectangle(500, 300, Color.WHITESMOKE);
				rect.setLayoutX(-62);
				rect.setLayoutY(110);
				rect.setArcHeight(30);
				rect.setArcWidth(30);
				Text overwrite = new Text("Saving the game will overwrite your previous save state");
				Text overwrite2 = new Text("Are you sure you want to overwrite?");
				Button yes = new Button("Yes");
				Button no = new Button("No");
				root.setDisable(true);
				//Not in new location format!!
				overwrite.setLayoutY(200);
				overwrite.setStyle("-fx-text-alignment: CENTER;");
				overwrite2.setLayoutY(250);
				overwrite2.setStyle("-fx-text-alignment: CENTER;");
				
				yes.setLayoutX(10);
				yes.setLayoutY(300);
				no.setLayoutX(310);
				no.setLayoutY(300);
				
				save.getContent().addAll(rect, overwrite, overwrite2, yes, no);
				save.show(primaryStage);
				
				yes.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						save.hide();
						root.setDisable(false);
					}
				});
				
				no.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						save.hide();
						root.setDisable(false);
					}
				});
				
			}
			
		});
		
		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				saveGame.setDefaults();
				StartupMenu.start(primaryStage);
			}
		});
		
		root.getChildren().addAll(vbox, vbox2, menu);
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
}
