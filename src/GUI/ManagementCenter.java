package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		Button manage = new Button("Manage Team");
		Button table = new Button("Competition Table");
		Button match = new Button("Match Center");
		Button market = new Button("Transfer Market");
		Button save = new Button("Save Game");
		Button menu = new Button("Back to main menu");
		
		manage.setFont(new Font("Arial", 35));
		table.setFont(new Font("Arial", 35));
		match.setFont(new Font("Arial", 35));
		market.setFont(new Font("Arial", 35));
		save.setFont(new Font("Arial", 35));
		
		Image background = new Image("/GUI/Resources/background_managementcenter.png");
		ImageView imgView = new ImageView(background);
		root.getChildren().add(imgView);
		
		menu.setLayoutX(80);
		menu.setLayoutY(870);
		menu.setFont(new Font("Arial", 25));
		
		VBox vbox = new VBox(20);
		vbox.getChildren().addAll(manage, table, match, market, save);
		vbox.setLayoutX(650);
		vbox.setLayoutY(300);
		
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
				TransferMarket.start(primaryStage);
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
