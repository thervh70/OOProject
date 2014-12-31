package View;

import Controller.gameEngine;
import Model.DBmain;
import Model.Team;
import Model.XmlParser;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MatchCenter {

	private static Timeline timeline;
	private static Label timerLabel = new Label();
	private static Integer timeSeconds = 0;
	 
	public static Integer attA,goalA,attB,goalB;
	    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		
		root.getChildren().add(Style.setBackground("/View/Resources/background_match-center.png"));

		timerLabel.setText(timeSeconds.toString());
		Style.setLocation(timerLabel, 950, 400);
		Style.setLabelStyle(timerLabel, 60);
       
		DBmain d = XmlParser.parseDB();
		Team alpha = d.getTeam(1);
		Team player = d.getTeam(8);
		gameEngine match = new gameEngine();
		
		match.play(player, alpha);
		
		VBox vboxLeft = new VBox(5);
		VBox vboxRight = new VBox(5);
	    
		Text teamA = new Text(player.getNm());
		Style.setTextStyle(teamA, 80);
       
		goalA = 0;
		Text goalsA = new Text(goalA.toString());
		Style.setTextStyle(goalsA, 50);
       
		attA = 0;
		Text attemptsA = new Text(attA.toString());
		Style.setTextStyle(attemptsA, 50);
       
		vboxLeft.getChildren().addAll(teamA,goalsA,attemptsA);
		vboxLeft.setAlignment(Pos.CENTER);
		Style.setLocation(vboxLeft, 320, 250);
       
		
		Text teamB = new Text(alpha.getNm());
		Style.setTextStyle(teamB, 80);
       
		goalB = 0;
		Text goalsB = new Text(goalB.toString());
		Style.setTextStyle(goalsB, 50);
       
		attB = 0;
		Text attemptsB = new Text(attB.toString());
		Style.setTextStyle(attemptsB, 50);
       
		vboxRight.getChildren().addAll(teamB,goalsB,attemptsB);
		vboxRight.setAlignment(Pos.CENTER);
		Style.setLocation(vboxRight, 1350, 250);
       
       
	    Button start = new Button("Start Match");
	    Style.setButtonStyle(start, 60);
	    Style.setLocation(start, 820, 280);
       
		Button results = new Button("Go to Results");
		Style.setButtonStyle(results, 45);
		Style.setLocation(results, 1500, 870);
		results.setDisable(true);
		results.setVisible(false);
		
		Button back = new Button("Back to Management Center");
		Style.setButtonStyle(back, 45);
		Style.setLocation(back, 150, 870);
       
		start.setOnAction(new EventHandler() {
       	
	        @Override
			public void handle(Event event) {
	        	start.setDisable(true);
	        	start.setVisible(false);
	        	back.setDisable(true);
	        	back.setVisible(false);
	        	
	        	if (timeline != null) {
	        		timeline.stop();
		        }
			 
			    timerLabel.setText(timeSeconds.toString());
			    timeline = new Timeline();
			    timeline.setCycleCount(Timeline.INDEFINITE);
		        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.1),new EventHandler() {
		        	
			        public void handle(Event event) {
			        	timeSeconds++;
			        	timerLabel.setText(timeSeconds.toString());
			        	
			            if (timeSeconds >=90) {
			            	timeline.stop();
			            	results.setVisible(true);
			            	results.setDisable(false);
			            }
			            
			            for(int i = 0; i < match.getGoalminutesA().length; i++){
			            	if(timeSeconds.intValue() == match.getGoalminutesA()[i]){
			            		goalA++;
			            		goalsA.setText(goalA.toString());
			            		attA++;
			            		attemptsA.setText(attA.toString());
			            	}
			            }
			            
			            for(int j = 0; j < match.getAttemptminutesA().length; j++){
			            	if(timeSeconds.intValue() == match.getAttemptminutesA()[j]){
			            		attA++;
			            		attemptsA.setText(attA.toString());
			            	}
			            }
			     
			            for(int k = 0; k < match.getGoalminutesB().length; k++){
			            	if(timeSeconds.intValue() == match.getGoalminutesB()[k]){
			            		goalB++;
			            		goalsB.setText(goalB.toString());
			            		attB++;
			            		attemptsB.setText(attB.toString());
			            	}
			            }
			            
			            for(int l = 0; l < match.getAttemptminutesB().length; l++){
			            	if(timeSeconds.intValue() == match.getAttemptminutesB()[l]){
			            		attB++;
			            		attemptsB.setText(attB.toString());
			            	}
			            }
			        }
		        }));
			        
			    timeline.playFromStart();
					
	        }
       });
       
       results.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				timeSeconds = 0;
				Results.start(primaryStage);
			}
		});
       
       back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
      
       root.getChildren().addAll(start,timerLabel,vboxLeft,vboxRight,results,back);
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
}
