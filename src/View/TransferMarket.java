package View;

import java.util.Collections;

import Controller.Budget;
import Controller.saveGame;
import Model.DBmain;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TransferMarket {

	private static Player playerSelect = null;
	private static Team teamSelect = null;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void start(Stage primaryStage){
		Pane root = new Pane();
		
		//Reset select
		playerSelect = null;
		teamSelect = null;
		
		root.getChildren().add(Style.setBackground("/View/Resources/background_transfer-market.png"));
		
		Button back = new Button("Back to Management Center");
		Button toSell = new Button("Sell");
		Text players = new Text("Players");
		Text keepers = new Text("Keepers");
		Text budget = new Text("Current Budget: " + saveGame.getMyTeam().getBdgt_vir());
		
		Style.setButtonStyle(back, 45);
		Style.setLocation(back, 150, 870);
		
		Style.setButtonStyle(toSell, 45);
		Style.setLocation(toSell, 1650, 870);
		
		Style.setTextStyle(players, 45);
		Style.setLocation(players, 450, 230);
		
		Style.setTextStyle(keepers, 45);
		Style.setLocation(keepers, 450, 680);
		
		Style.setTextStyle(budget, 60);
		Style.setLocation(budget, 730, 940);
		
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
	    TableColumn injuryS = new TableColumn("Injury");
	    injuryS.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Injury"));
	    
	    nameS.setResizable(false);
	    nameS.setPrefWidth(Style.getNewSize(190));
	    positionS.setResizable(false);
	    positionS.setPrefWidth(Style.getNewSize(75));
	    ageS.setResizable(false);
	    ageS.setPrefWidth(Style.getNewSize(50));
	    worthS.setResizable(false);
	    worthS.setPrefWidth(Style.getNewSize(75));
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
	    injuryS.setResizable(false);
	    injuryS.setPrefWidth(Style.getNewSize(50));
		
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
        TableColumn injuryKS = new TableColumn("Injury");
        injuryKS.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Injury"));
        
        nameKS.setResizable(false);
		nameKS.setPrefWidth(Style.getNewSize(175));
		positionKS.setResizable(false);
		positionKS.setPrefWidth(Style.getNewSize(75));
		ageKS.setResizable(false);
		ageKS.setPrefWidth(Style.getNewSize(50));
		worthKS.setResizable(false);
		worthKS.setPrefWidth(Style.getNewSize(75));
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
		injuryKS.setResizable(false);
		injuryKS.setPrefWidth(Style.getNewSize(50));
		
		setColor(nameS);
		setColor(nameKS);
		
		//Add columns to table
		tableSelectionField.getColumns().addAll(nameS,positionS,ageS,worthS,shootingS,passingS,dribblingS,defendingS,physicalS,injuryS);
		tableSelectionKeeper.getColumns().addAll(nameKS,positionKS,ageKS,worthKS,divingKS,handlingKS,kickingKS,reflexKS,speedKS,posKS,heightKS,injuryKS);

		ComboBox comboBox = new ComboBox();
		DBmain DB = saveGame.getDB();

		ObservableList<String> teamList = FXCollections.observableArrayList();
		
		for(int i = 0; i < 18; i++){
			Team t = DB.getTeam(i);
			if(!t.getNm().equals(saveGame.getMyTeamName())){
				teamList.add(t.getNm());
			}
		}
		
		Collections.sort(teamList);
		comboBox.setItems(teamList);
		
		if(!saveGame.getMyTeamName().equals("ADO Den Haag")){
			comboBox.setValue("ADO Den Haag");
		}
		else {
			comboBox.setValue("Ajax");
		}
		
		refreshPlayers(comboBox.getValue().toString(),tableSelectionField,tableSelectionKeeper);
		Style.setLocation(comboBox, 700, 205);
		
		TextField input = new TextField();
		input.setMaxWidth(Style.getNewSize(400));
		input.setPromptText("Enter Bid");
		input.setAlignment(Pos.CENTER);
		input.setFont(Style.getFont(45));
		
		Button confirm = new Button("Place Bid");
		Style.setButtonStyle(confirm, 45);
		
		VBox vbox = new VBox(15);
		Style.setLocation(vbox, 900, 200);
		vbox.setAlignment(Pos.CENTER);
		
		Text selected = new Text("You have selected: ");
		Style.setTextStyle(selected, 60);
		
		Text pName = new Text(" ");
		Text pPrice = new Text(" ");
		
		vbox.getChildren().addAll(selected,pName,pPrice,input,confirm);
		
		VBox vbox2 = new VBox(15);
		Style.setLocation(vbox2, 1350, 200);
		vbox2.setAlignment(Pos.CENTER);
		
		Text bids = new Text("Pending bids: ");
		Style.setTextStyle(bids, 60);
		
		Text bid1 = new Text("");
		Text bid2 = new Text("");
		Text bid3 = new Text("");
		
		vbox2.getChildren().addAll(bids,bid1,bid2,bid3);
		
		root.getChildren().addAll(back,toSell, tableSelectionField, tableSelectionKeeper,players,keepers,comboBox,vbox,vbox2,budget);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		 toSell.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				TransferMarketSell.start(primaryStage);
			}
		});
		
		comboBox.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				playerSelect = null;
				teamSelect = null;
				pName.setText("");
				pPrice.setText("");
				setColor(nameS);
				setColor(nameKS);
				refreshPlayers(comboBox.getValue().toString(),tableSelectionField,tableSelectionKeeper);
			}
		});
		
		confirm.setOnAction(new EventHandler <ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				System.out.println(tableSelectionField.getSelectionModel().getSelectedItem().toString());System.out.println(saveGame.getBuyc());
				if(playerSelect == null){
					Popup warning = Warning.makeWarning("No player selected", root);
					warning.show(primaryStage);
				}
				else if(saveGame.getBuyc()>=3){
					Popup three = Warning.makeWarning("You have already bid three times!", root);
					three.show(primaryStage);
				}
				else{
					if(playerSelect.getPlay()){
						try{
							int bid = Integer.parseInt(input.getText());
							
							if(bid < 0){
								Popup negative = Warning.makeWarning("Please enter a positive number", root);
								negative.show(primaryStage);
							}
							
							else{
								EventHandler<MouseEvent> mouse = new EventHandler<MouseEvent>() {
									
									public void handle(MouseEvent y) {
										try{
											boolean b = Budget.bid(playerSelect, teamSelect, bid);
											
											if(b){
												Popup congratz = Warning.makeWarning("Congratulations! \nYou have bought " + playerSelect.getName(), root);
												congratz.show(primaryStage);
												setColor(nameS);
												setColor(nameKS);
												refreshPlayers(comboBox.getValue().toString(),tableSelectionField,tableSelectionKeeper);
												budget.setText("Current Budget: " + saveGame.getMyTeam().getBdgt_vir());
												pName.setText("");
												pPrice.setText("");
												
												
												if(bid1.getText().equals("")){
													Player p = playerSelect;
													bid1.setText(p.getName() + " " + bid);
													Style.setTextStyle(bid1, 40);
												}
												else if(bid2.getText().equals("")){
													Player p = playerSelect;
													bid2.setText(p.getName() + " " + bid);
													Style.setTextStyle(bid2, 40);
												}
												else if(bid3.getText().equals("")){
													Player p = playerSelect;
													bid3.setText(p.getName() + " " + bid);
													Style.setTextStyle(bid3, 40);
												}
												
												input.clear();
												playerSelect = null;
											}
											
											else if(!b){
												Popup toobad = Warning.makeWarning("Too bad... \n" + teamSelect.getNm() + " refused your offer.", root);
												toobad.show(primaryStage);
												setColor(nameS);
												setColor(nameKS);
												refreshPlayers(comboBox.getValue().toString(),tableSelectionField,tableSelectionKeeper);
												input.clear();
											}
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
								
								Popup confirm = Warning.makeWarning("Are you sure you \nwant to place this bid?", root, mouse,mouse2);
								confirm.show(primaryStage);
								
							}
							
						} catch( NumberFormatException e){
							Popup warning2 = Warning.makeWarning("Please insert a number", root);
							input.clear();
							warning2.show(primaryStage);
						}
					}
					else{
						Popup availWarning = Warning.makeWarning("You cannot buy a player \nwith  a card or injury", root);
						availWarning.show(primaryStage);
					}
				}
			}
			
		});
		
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
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
	
	public static void refreshPlayers(String s, TableView<Fieldplayer> tableSelectionField, TableView<Goalkeeper> tableSelectionKeeper){
		
		for(int i = 0; i < 18; i++){
			Team t = saveGame.getDB().getTeam(i);
			if(s.equals(t.getNm())){
				
				teamSelect = t;
				
				ObservableList<Fieldplayer> selectionField = FXCollections.observableArrayList();
				selectionField.removeAll(selectionField);
				
				for (int j = 0; j < t.getSize(); j++) {
					Player p = t.getPlayer(j);
					if(p instanceof Fieldplayer)
						selectionField.add((Fieldplayer) p);
				}
				
				tableSelectionField.setItems(selectionField);
				tableSelectionField.getSelectionModel().select(0);
				
				ObservableList<Goalkeeper> selectionKeeper = FXCollections.observableArrayList();
				selectionKeeper.removeAll(selectionKeeper);
				
				for(int k = 0; k < t.getSize(); k++){
					Player p = t.getPlayer(k);
					if(p instanceof Goalkeeper){
						selectionKeeper.add((Goalkeeper) p);
					}
				}
				
				tableSelectionKeeper.setItems(selectionKeeper);
				tableSelectionKeeper.getSelectionModel().select(0);
			}
		}
		
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
