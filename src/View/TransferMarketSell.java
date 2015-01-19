package View;

import Controller.Budget;
import Controller.saveGame;
import Model.Fieldplayer;
import Model.Goalkeeper;
import Model.Player;
import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TransferMarketSell {

	private static Player playerSelect = null;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		root.getChildren().add(Style.setBackground("/View/Resources/background_transfer-market.png"));
		
		//Reset select
		playerSelect = null;
		
		Button back = new Button("Back to Management Center");
		Button toBuy = new Button("Buy");
		
		Style.setButtonStyle(back, 45);
		Style.setLocation(back, 150, 870);
		
		Style.setButtonStyle(toBuy, 45);
		Style.setLocation(toBuy, 1650, 870);
		
		Text players = new Text("Players");
		Text keepers = new Text("Keepers");
		Text budget = new Text("Current Budget: " + saveGame.getMyTeam().getBdgt_vir());
		
		Style.setTextStyle(players, 45);
		Style.setLocation(players, 450, 230);
		
		Style.setTextStyle(keepers, 45);
		Style.setLocation(keepers, 450, 680);
		
		Style.setTextStyle(budget, 60);
		Style.setLocation(budget, 730, 940);
		
		Text selected = new Text("You have selected: ");
		Style.setTextStyle(selected, 60);
		
		Text pName = new Text(" ");
		Text pPrice = new Text(" ");

		Button confirm = new Button("Sell this player");
		Style.setButtonStyle(confirm, 45);
		
		VBox vbox = new VBox(15);
		Style.setLocation(vbox, 900, 200);
		vbox.setAlignment(Pos.CENTER);
		
		vbox.getChildren().addAll(selected,pName,pPrice,confirm);
		
		root.getChildren().addAll(back,toBuy,players,keepers,budget,vbox);
		
		//Create a table for the setup with fixed columns
		TableView<Fieldplayer> tableSelectionField = new TableView<Fieldplayer>();
		tableSelectionField.setEditable(false);
		tableSelectionField.setPrefSize(Style.getNewSize(700), Style.getNewSize(375));
		Style.setLocation(tableSelectionField, 150, 250);
				
		//Separate table for Keepers in Field
		TableView<Goalkeeper> tableSelectionKeeper = new TableView<Goalkeeper>();
		tableSelectionKeeper.setEditable(false);
		tableSelectionKeeper.setPrefSize(Style.getNewSize(700), Style.getNewSize(150));
		Style.setLocation(tableSelectionKeeper, 150, 700);
		
		//Columns for Selection Fieldplayers
		TableColumn nameS = new TableColumn("Name");
		nameS.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		TableColumn positionS = new TableColumn("Position");
		positionS.setCellValueFactory(new PropertyValueFactory<Player, String>("pos"));
		TableColumn ageS = new TableColumn("Age");
		ageS.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));
		TableColumn worthS = new TableColumn("Worth");
		worthS.setCellValueFactory(new PropertyValueFactory<Player, Integer>("pri"));
		TableColumn paceS = new TableColumn("PAC");
		paceS.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("pac"));
		TableColumn shootingS = new TableColumn("SHO");
		shootingS.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("sho"));
		TableColumn passingS = new TableColumn("PAS");
		passingS.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("pas"));
		TableColumn dribblingS = new TableColumn("DRI");
		dribblingS.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("dri"));
		TableColumn defendingS = new TableColumn("DEF");
		defendingS.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("def"));
		TableColumn physicalS = new TableColumn("PHY");
		physicalS.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("phy"));
		        
		nameS.setResizable(false);
		nameS.setPrefWidth(Style.getNewSize(197));
		positionS.setResizable(false);
		positionS.setPrefWidth(Style.getNewSize(100));
		ageS.setResizable(false);
		ageS.setPrefWidth(Style.getNewSize(50));
		worthS.setResizable(false);
		worthS.setPrefWidth(Style.getNewSize(100));
		shootingS.setResizable(false);
		shootingS.setPrefWidth(Style.getNewSize(50));
		passingS.setResizable(false);
		passingS.setPrefWidth(Style.getNewSize(50));
		dribblingS.setResizable(false);
		dribblingS.setPrefWidth(Style.getNewSize(50));
		defendingS.setResizable(false);
		defendingS.setPrefWidth(Style.getNewSize(50));
		physicalS.setResizable(false);
		physicalS.setPrefWidth(Style.getNewSize(50));
		
		//Columns for Selection Goalkeepers
		TableColumn nameKS = new TableColumn("Name");
		nameKS.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		TableColumn positionKS = new TableColumn("Position");
		positionKS.setCellValueFactory(new PropertyValueFactory<Player, String>("pos"));
		TableColumn ageKS = new TableColumn("Age");
		ageKS.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));
		TableColumn worthKS = new TableColumn("Worth");
		worthKS.setCellValueFactory(new PropertyValueFactory<Player, Integer>("pri"));
		TableColumn divingKS = new TableColumn("DIV");
		divingKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("div"));
		TableColumn handlingKS = new TableColumn("HAN");
		handlingKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("han"));
		TableColumn kickingKS = new TableColumn("KICK");
		kickingKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("kick"));
		TableColumn reflexKS = new TableColumn("REF");
		reflexKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("ref"));
		TableColumn speedKS = new TableColumn("SPD");
		speedKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("spd"));
		TableColumn posKS = new TableColumn("PING");
		posKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("ping"));
		TableColumn heightKS = new TableColumn("HEI");
		heightKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("hei"));		
		    
		nameKS.setResizable(false);
		nameKS.setPrefWidth(Style.getNewSize(202));
		positionKS.setResizable(false);
		positionKS.setPrefWidth(Style.getNewSize(100));
		ageKS.setResizable(false);
		ageKS.setPrefWidth(Style.getNewSize(50));
		worthKS.setResizable(false);
		worthKS.setPrefWidth(Style.getNewSize(100));
		divingKS.setResizable(false);
		divingKS.setPrefWidth(Style.getNewSize(35));
		handlingKS.setResizable(false);
		handlingKS.setPrefWidth(Style.getNewSize(35));
		kickingKS.setResizable(false);
		kickingKS.setPrefWidth(Style.getNewSize(35));
		reflexKS.setResizable(false);
		reflexKS.setPrefWidth(Style.getNewSize(35));
		speedKS.setResizable(false);
		speedKS.setPrefWidth(Style.getNewSize(35));
		posKS.setResizable(false);
		posKS.setPrefWidth(Style.getNewSize(35));
		heightKS.setResizable(false);
		heightKS.setPrefWidth(Style.getNewSize(35));
		
		setColor(nameS);
		setColor(nameKS);
		
		//Add columns to table
		tableSelectionField.getColumns().addAll(nameS,positionS,ageS,worthS,shootingS,passingS,dribblingS,defendingS,physicalS);
		tableSelectionKeeper.getColumns().addAll(nameKS,positionKS,ageKS,worthKS,divingKS,handlingKS,kickingKS,reflexKS,speedKS,posKS,heightKS);	
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		toBuy.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				TransferMarket.start(primaryStage);
			}
		});
		
		refreshPlayers(tableSelectionField, tableSelectionKeeper);
		
		tableSelectionField.setOnMouseClicked(new EventHandler <MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				Player p = (Player)tableSelectionField.getSelectionModel().getSelectedItem();
				pName.setText(p.getName() + ", " + p.getPos());
				Style.setTextStyle(pName, 45);
				
				pPrice.setText("\u20ac" + " " + p.getPri());
				Style.setTextStyle(pPrice, 45);
				
				playerSelect = p;
			}
		});
		
		tableSelectionKeeper.setOnMouseClicked(new EventHandler <MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				Player p = (Player)tableSelectionKeeper.getSelectionModel().getSelectedItem();
				pName.setText(p.getName() + ", " + p.getPos());
				Style.setTextStyle(pName, 45);
				
				pPrice.setText("\u20ac" + " " + p.getPri());
				Style.setTextStyle(pPrice, 45);
				
				playerSelect = p;
			}
		});
		
	confirm.setOnAction(new EventHandler <ActionEvent>(){

		@Override
		public void handle(ActionEvent event) {
			if(playerSelect == null){
				Popup warning = Warning.makeWarning("No player selected", root);
				warning.show(primaryStage);
			}
			else if(saveGame.getSellc()>=3){
				Popup three = Warning.makeWarning("You have already sold 3 players!", root);
				three.show(primaryStage);
			}
			else{
				if(playerSelect.getPlay()){
						EventHandler<MouseEvent> mouse = new EventHandler<MouseEvent>() {
							
							public void handle(MouseEvent y) {
								try{		
									String sold = Budget.tosell(playerSelect);
									if(!sold.equals(null)){
										Popup soldpopup = Warning.makeWarning("Your player was sold to: " + sold, root);
										soldpopup.show(primaryStage);										
									}
									else{
										Popup notsold = Warning.makeWarning("Your player was not sold.", root);
										notsold.show(primaryStage);
									}
									refreshPlayers(tableSelectionField, tableSelectionKeeper);
									setColor(nameS);
									setColor(nameKS);
									playerSelect = null;
									
								} catch (Exception e){
									Popup emptyTeam = Warning.makeWarning("Transfer not approved \nTeam would become too small", root);
									emptyTeam.show(primaryStage);
								}
							}
						};
						
						EventHandler<MouseEvent> mouse2 = new EventHandler<MouseEvent>() {
							
							public void handle(MouseEvent n) {
								;
							}
						};
						
						Popup confirm = Warning.makeWarning("Are you sure you \nwant to sell this player?", root, mouse,mouse2);
						confirm.show(primaryStage);
						
					}
				
					else{
						Popup availWarning = Warning.makeWarning("You cannot buy a player \nwith  a card or injury", root);
						availWarning.show(primaryStage);
					}
				}
			}
			
		});
		
		root.getChildren().addAll(tableSelectionKeeper,tableSelectionField);
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
	
	public static void refreshPlayers(TableView<Fieldplayer> tableSelectionField, TableView<Goalkeeper> tableSelectionKeeper){
		Team t = saveGame.getMyTeam();
		
		ObservableList<Fieldplayer> selectionField = FXCollections.observableArrayList();
		for (int j = 0; j < t.getSize(); j++) {
			Player p = t.getPlayer(j);
			if(p instanceof Fieldplayer)
				selectionField.add((Fieldplayer) p);
		}
				
		tableSelectionField.setItems(selectionField);
		tableSelectionField.getSelectionModel().clearSelection();
				
		ObservableList<Goalkeeper> selectionKeeper = FXCollections.observableArrayList();
		for(int k = 0; k < t.getSize(); k++){
			Player p = t.getPlayer(k);
			if(p instanceof Goalkeeper){
				selectionKeeper.add((Goalkeeper) p);
			}
		}
		tableSelectionKeeper.getSelectionModel().clearSelection();
		tableSelectionKeeper.setItems(selectionKeeper);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void setColor(TableColumn t){
		t.setCellFactory(new Callback<TableColumn, TableCell>() {
			public TableCell call(TableColumn param) {
				return new TableCell<Player, String>() {

			        public void updateItem(String item, boolean empty) {
			        	super.updateItem(item, empty);
			            if (!isEmpty()) {
			            	this.setTextFill(Color.BLACK);
			            	Player p = saveGame.getDB().lookForPlayer(this.getItem());
			            	if(p.checkRedCard()) {
			            		this.setTextFill(Color.RED);
			            	}
			            	else if(p.checkInjury()){
			            		this.setTextFill(Color.LIMEGREEN);
			            	}
			            	else if(p.checkYellowCard()){
			            		this.setTextFill(Color.GOLD);
			            	}
			            	
			            	setText(item);
			            }
			        }
				};
		     }
		 });
	}


}
