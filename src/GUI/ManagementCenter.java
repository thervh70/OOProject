package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

public class ManagementCenter {

	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button change = new Button("Change Team");
		Button table = new Button("Competition Table");
		Button match = new Button("Match Center");
		Button market = new Button("Transfer Market");
		Button save = new Button("Save Game");
		Button menu = new Button("Back to main menu");
		
		change.setFont(new Font("Arial", 35));
		table.setFont(new Font("Arial", 35));
		match.setFont(new Font("Arial", 35));
		market.setFont(new Font("Arial", 35));
		save.setFont(new Font("Arial", 35));
		
		menu.setLayoutX(80);
		menu.setLayoutY(870);
		menu.setFont(new Font("Arial", 25));
		
		VBox vbox = new VBox(20);
		vbox.getChildren().addAll(change, table, match, market, save);
		vbox.setLayoutX(650);
		vbox.setLayoutY(300);
		
		root.getChildren().addAll(vbox, menu);
		
		match.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				MatchCenter.start(primaryStage);
			}
		});
		
		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				StartupMenu.back(primaryStage);
			}
		});
		
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Popup save = new Popup();
				save.setHeight(200);
				save.setWidth(500);
				
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
		
		Scene scene = new Scene(root, 1770, 980);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
