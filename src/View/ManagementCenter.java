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
		
		Style.setButtonStyle(manage, 50);
		Style.setButtonStyle(table, 50);
		Style.setButtonStyle(match, 50);
		Style.setButtonStyle(market, 50);
		Style.setButtonStyle(save, 50);
		
		Style.setButtonStyle(menu, 45);

		root.getChildren().add(Style.setBackground("/View/Resources/background_managementcenter.png"));

		Style.setLocation(menu, 150, 870);
		
		VBox vbox = new VBox(15);
		vbox.getChildren().addAll(manage, table, match, market, save);
		Style.setLocation(vbox, 775, 220);
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
				Popup warning = Warning.makeWarning("Hoi", root);
				warning.show(primaryStage);
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
				StartupMenu.start(primaryStage);
			}
		});
		
		root.getChildren().addAll(vbox, menu);
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
}
