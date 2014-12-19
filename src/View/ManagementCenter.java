package View;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
		
		Style.setButtonStyle(manage, 50);
		Style.setButtonStyle(table, 50);
		Style.setButtonStyle(match, 50);
		Style.setButtonStyle(market, 50);
		Style.setButtonStyle(save, 50);
		
		Style.setButtonStyle(menu, 45);

		root.getChildren().add(Style.setBackground("/View/Resources/background_managementcenter.png"));

		menu.setLayoutX(150);
		menu.setLayoutY(870);
		
		VBox vbox = new VBox(15);
		vbox.getChildren().addAll(manage, table, match, market, save);
		vbox.setLayoutX(775);
		vbox.setLayoutY(220);
		vbox.setAlignment(Pos.CENTER);
		
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
				MatchCenter.start(primaryStage);
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
				
				Text overwrite = new Text("Saving the game will overwrite your previous save state");
				Text overwrite2 = new Text("Are you sure you want to overwrite?");
				Button yes = new Button("Yes");
				Button no = new Button("No");
				
				overwrite.setLayoutY(200);
				overwrite2.setLayoutY(250);
				yes.setLayoutX(10);
				yes.setLayoutY(300);
				no.setLayoutX(310);
				no.setLayoutY(300);
				
				save.getContent().addAll(overwrite, overwrite2, yes, no);
				root.setVisible(false);
				
				save.show(primaryStage);
				
				yes.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						save.hide();
						root.setVisible(true);
					}
				});
				
				no.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						save.hide();
						root.setVisible(true);
					}
				});
				
			}
			
		});
		
		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				StartupMenu.start(primaryStage);
			}
		});
		
		root.getChildren().addAll(vbox, menu);
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
}
