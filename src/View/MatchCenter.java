package View;

import java.util.ArrayList;

import Controller.gameEngine;
import Controller.saveGame;
import Model.Competition;
import Model.Match;
import Model.Player;
import Model.Team;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MatchCenter {

	private static Timeline timeline;
	private static Label timerLabel = new Label();
	private static Integer timeSeconds = 0;
	 
	public static Integer attA,goalA = 0,attB,goalB = 0,yelA,yelB,redA,redB;
	
	private static Team alpha = null, beta = null;
	
	private static Text goalsA;
	private static Text goalsB; 
	    
	public static void start(Stage primaryStage) throws NullPointerException {
		Pane root = new Pane(); 
		
		root.getChildren().add(Style.setBackground("/View/Resources/background_match-center.png"));

		timerLabel.setText(timeSeconds.toString());
		Style.setLocation(timerLabel, 950, 920);
		Style.setLabelStyle(timerLabel, 60);
       
		Competition comp = saveGame.getCompetition();
		int day = saveGame.getDay();
		
		ArrayList<Match> gamesToday = comp.getMatchesForDay(day);
		for(Match game : gamesToday){
			Team home = game.getTeamHome();
			Team away = game.getTeamAway();
			
			if(home.getNm().equals(saveGame.getMyTeamName())){
				alpha = saveGame.getMyTeam();
				beta = away;
			}
			
			else if(away.getNm().equals(saveGame.getMyTeamName())){
				alpha = home;
				beta = saveGame.getMyTeam();
			}
		}
		
		gameEngine match = new gameEngine();
		
		VBox vboxLeft = new VBox(5);
		VBox vboxRight = new VBox(5);
		
		Text teamA = new Text(alpha.getNm());
		Style.setTextStyle(teamA, 80);
       
		goalA = 0;
		goalsA = new Text(goalA.toString());
		Style.setTextStyle(goalsA, 50);
       
		attA = 0;
		Text attemptsA = new Text(attA.toString());
		Style.setTextStyle(attemptsA, 50);
       
		yelA = 0;
		Text yellowA = new Text(yelA.toString());
		Style.setTextStyle(yellowA, 50);
		
		redA = 0;
		Text redsA = new Text(redA.toString());
		Style.setTextStyle(redsA, 50);
		
		vboxLeft.getChildren().addAll(teamA,goalsA,attemptsA,yellowA,redsA);
		vboxLeft.setAlignment(Pos.CENTER);
		Style.setLocation(vboxLeft, 320, 150);
       
		
		
		Text teamB = new Text(beta.getNm());
		Style.setTextStyle(teamB, 80);
       
		goalB = 0;
		goalsB = new Text(goalB.toString());
		Style.setTextStyle(goalsB, 50);
       
		attB = 0;
		Text attemptsB = new Text(attB.toString());
		Style.setTextStyle(attemptsB, 50);
		
		yelB = 0;
		Text yellowB = new Text(yelB.toString());
		Style.setTextStyle(yellowB, 50);
		
		redB = 0;
		Text redsB = new Text(redB.toString());
		Style.setTextStyle(redsB, 50);
       
		vboxRight.getChildren().addAll(teamB,goalsB,attemptsB,yellowB, redsB);
		vboxRight.setAlignment(Pos.CENTER);
		Style.setLocation(vboxRight, 1350, 150);
       
		
		
		Rectangle r = new Rectangle();
		r.setHeight(Style.getNewSize(50));
		Style.setRectangleStyle(r);
		Style.setLocation(r, 150, 780);
		
		
		
		Text goals= new Text("Goals");
		Style.setTextStyle(goals, 50);
		
		Text attempts = new Text("Attempts");
		Style.setTextStyle(attempts, 50);
		
		Text yellow = new Text("Yellow Cards");
		Style.setTextStyle(yellow, 50);

		Text red = new Text("Red Cards");
		Style.setTextStyle(red, 50);
		
		Text injury = new Text("Injuries");
		Style.setTextStyle(injury, 50);
		
		VBox vboxMiddle = new VBox(5);
		vboxMiddle.getChildren().addAll(goals,attempts,yellow,red,injury);
		vboxMiddle.setAlignment(Pos.CENTER);
		Style.setLocation(vboxMiddle, 890, 240);
		
		
		Text attemptAnimation = new Text("Attempt");
		Style.setTextStyle(attemptAnimation, 70);
		attemptAnimation.setVisible(false);
		
		Text missAnimation = new Text("Miss");
		Style.setTextStyle(missAnimation, 70);
		missAnimation.setVisible(false);
		
		Text goalAnimation = new Text("GOAL!!");
		Style.setTextStyle(goalAnimation, 70);
		goalAnimation.setVisible(false);
		
		Text cardAnimation = new Text("");
		Style.setTextStyle(cardAnimation, 70);
		cardAnimation.setVisible(false);
		
	
		Text won = new Text(alpha.getNm() + " won!");
		Style.setTextStyle(won, 90);
		Style.setLocation(won, 750, 700);
		won.setVisible(false);
		
		Text lost = new Text(beta.getNm() + " won!");
		Style.setTextStyle(lost, 90);
		Style.setLocation(lost, 750, 700);
		lost.setVisible(false);
		
		Text tie = new Text("It's a tie.");
		Style.setTextStyle(tie, 90);
		Style.setLocation(tie, 750, 700);
		tie.setVisible(false);		       
		
		
	    Button start = new Button("Start Match");
	    Style.setButtonStyle(start, 60);
	    Style.setLocation(start, 820, 600);
      
		Button results = new Button("Go to Results");
		Style.setButtonStyle(results, 45);
		Style.setLocation(results, 1500, 870);
		results.setDisable(true);
		results.setVisible(false);
		
		Button back = new Button("Back to Management Center");
		Style.setButtonStyle(back, 45);
		Style.setLocation(back, 150, 870);
       
		start.setOnAction(new EventHandler<ActionEvent>() {
       	
	        @Override
			public void handle(ActionEvent event) {
	        	
	        	
	        	if(!saveGame.getMyTeam().checkAvail()){
	        		Popup cardwarning = Warning.makeWarning("Selection contains \nunavailable players", root);
	        		cardwarning.show(primaryStage);
	        	}
	        	else{
		        	start.setDisable(true);
		        	start.setVisible(false);
		        	back.setDisable(true);
		        	back.setVisible(false);
		        	results.setDisable(true);
		        	results.setVisible(false);
		        	
		        	match.play(alpha, beta);
		        	
				    timerLabel.setText(timeSeconds.toString());
				    timeline = new Timeline();
				    timeline.setCycleCount(Timeline.INDEFINITE);
			        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.2),new EventHandler<ActionEvent>() {
			        	
				        public void handle(ActionEvent event) {
				        	timeSeconds++;
				        	r.setWidth(Style.getNewSize(timeSeconds*18));
				        	timerLabel.setText(timeSeconds.toString());
				        	
				            if (timeSeconds >=90) {
				            	timeline.stop();
				            	results.setVisible(true);
				            	results.setDisable(false);
				            	
				            	if(match.getToto() == 1){won.setVisible(true);}
				            	else if(match.getToto() == 2){lost.setVisible(true);}
				            	else if(match.getToto() == 0){tie.setVisible(true);}
				            			            	
				            }
				            
				            for(int i = 0; i < match.getGoalminutesA().length; i++){
				            	if(timeSeconds.intValue() == match.getGoalminutesA()[i]){
				            		attA++;
				            		attemptsA.setText(attA.toString());
				            		attemptAnimation(true,true,attemptAnimation,goalAnimation,missAnimation);
				            
				            		goalsA.setText(goalA.toString());
				            	}
				            }
				            
				            for(int j = 0; j < match.getAttemptminutesA().length; j++){
				            	if(timeSeconds.intValue() == match.getAttemptminutesA()[j]){
				            		attA++;
				            		attemptsA.setText(attA.toString());
				            		attemptAnimation(true,false,attemptAnimation,goalAnimation,missAnimation);
				            		
				            	}
				            }
				     
				            for(int k = 0; k < match.getGoalminutesB().length; k++){
				            	if(timeSeconds.intValue() == match.getGoalminutesB()[k]){
				            		attB++;
				            		attemptsB.setText(attB.toString());
				            		attemptAnimation(false,true,attemptAnimation,goalAnimation,missAnimation);
				            		
				            		goalsB.setText(goalB.toString());
				            		
				            	}
				            }
				            
				            for(int l = 0; l < match.getAttemptminutesB().length; l++){
				            	if(timeSeconds.intValue() == match.getAttemptminutesB()[l]){
				            		attB++;
				            		attemptsB.setText(attB.toString());
				            		attemptAnimation(false,false,attemptAnimation,goalAnimation,missAnimation);
				            	}
				            }
				            
				            for(int m = 0; m < match.getYellowcardminutesA().length; m++){
				            	if(timeSeconds.intValue() == match.getYellowcardminutesA()[m]){
				            		yelA++;
				            		yellowA.setText(yelA.toString());
				            		Player p = match.getYellowPlayerA().get(m);
				            		cardAnimation.setText(p.getName());
				            		cardAnimation(true,false,cardAnimation);
				            	}
				            }
				            
				            for(int n = 0; n < match.getYellowcardminutesB().length; n++){
				            	if(timeSeconds.intValue() == match.getYellowcardminutesB()[n]){
				            		yelB++;
				            		yellowB.setText(yelB.toString());
				            		Player p = match.getYellowPlayerB().get(n);
				            		cardAnimation.setText(p.getName());
				            		cardAnimation(false,false,cardAnimation);
				            	}
				            }
				            
				            for(int o = 0; o < match.getRedcardminutesA().length; o++){
				            	if(timeSeconds.intValue() == match.getRedcardminutesA()[o]){
				            		redA++;
				            		redsA.setText(redA.toString());
				            		Player p = match.getRedPlayerA().get(o);
				            		cardAnimation.setText(p.getName());
				            		cardAnimation(true,true,cardAnimation);
				            	}
				            }
				            
				            for(int q = 0; q < match.getRedcardminutesB().length; q++){
				            	if(timeSeconds.intValue() == match.getRedcardminutesB()[q]){
				            		redB++;
				            		redsB.setText(redB.toString());
				            		Player p = match.getRedPlayerB().get(q);
				            		cardAnimation.setText(p.getName());
				            		cardAnimation(false,true,cardAnimation);
				            	}
				            }
				        }
			        }));
				        
				    timeline.playFromStart();
	        	}
	        }
       });
       
       results.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				timeSeconds = 0;
				Results.start(primaryStage,match);
			}
		});
       
       back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
      
       root.getChildren().addAll(start,timerLabel,vboxLeft,vboxRight,results,back,r,vboxMiddle,attemptAnimation,goalAnimation,missAnimation,cardAnimation,won,lost,tie);
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
	
	public static void attemptAnimation(boolean user, boolean score, Text attempt,Text goal,Text miss){
		timeline.pause();
		attempt.setVisible(true);
		if(user){
			Style.setLocation(attempt, 320, 700);
		}
		else if(!user){
			Style.setLocation(attempt, 1350, 700);
		}
		
		ScaleTransition st = new ScaleTransition(Duration.millis(400), attempt);
		st.setByX(1);
	    st.setByY(1);
	    st.setCycleCount(2);
	    st.setAutoReverse(true);
	    st.play();
		
		FadeTransition ft = new FadeTransition(Duration.millis(400), attempt);
		ft.setAutoReverse(true);
		ft.setCycleCount(2);
		ft.setToValue(1);
		ft.setFromValue(0);
		ft.play();
		
		if(score){
			st.setOnFinished(new EventHandler<ActionEvent>(){
	
				@Override
				public void handle(ActionEvent event) {
					attempt.setVisible(false);
					goalAnimation(user,goal);
				}
				
			});		
		}
		
		else{
			st.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					attempt.setVisible(false);
					missAnimation(user,miss);
				}
				
			});
		}
	}
	
	public static void missAnimation(boolean user, Text text){
		text.setVisible(true);
		if(user){
			Style.setLocation(text, 320, 700);
		}
		else if(!user){
			Style.setLocation(text, 1350, 700);
		}
		
		ScaleTransition st = new ScaleTransition(Duration.millis(400), text);
		st.setByX(1);
	    st.setByY(1);
	    st.setCycleCount(2);
	    st.setAutoReverse(true);
	    st.play();
		
		FadeTransition ft = new FadeTransition(Duration.millis(400), text);
		ft.setAutoReverse(true);
		ft.setCycleCount(2);
		ft.setToValue(1);
		ft.setFromValue(0);
		ft.play();
		
		 st.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					text.setVisible(false);
					timeline.play();
				}
				
			});		
	}
	
	public static void goalAnimation(boolean user,Text text){
		text.setVisible(true);
		if(user){
			Style.setLocation(text, 320, 700);
		}
		else if(!user){
			Style.setLocation(text, 1350, 700);
		}
		
		ScaleTransition st = new ScaleTransition(Duration.millis(1000), text);
		st.setByX(1);
	    st.setByY(1);
	    st.setCycleCount(2);
	    st.setAutoReverse(true);
	    st.play();
	    
		FadeTransition ft = new FadeTransition(Duration.millis(1000), text);
		ft.setAutoReverse(true);
		ft.setCycleCount(2);
		ft.setToValue(1);
		ft.setFromValue(0);
		ft.play();
	    
	    RotateTransition rt = new RotateTransition(Duration.millis(1000), text);
	    rt.setByAngle(720);
	    rt.setCycleCount(2);
	    rt.setAutoReverse(true);
	    rt.play();
	    
	    st.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				text.setVisible(false);
				
				if(user){
					goalA++;
					goalsA.setText(goalA.toString());
				}
				else{
					goalB++;
					goalsB.setText(goalB.toString());
				}
				
				timeline.play();
			}
			
		});		
		
	}
	
	//color: false is yellow, true is red
	public static void cardAnimation(boolean loc, boolean color, Text name){
		timeline.pause();
		name.setVisible(true);
		if(loc){
			Style.setLocation(name, 320, 700);
		}
		else if(!loc){
			Style.setLocation(name, 1350, 700);
		}
		
		if(!color){
			name.setFill(Color.YELLOW);
		}
		else if(color){
			name.setFill(Color.RED);
		}
		
		ScaleTransition st = new ScaleTransition(Duration.millis(800), name);
		st.setByX(.8);
	    st.setByY(.8);
	    st.setCycleCount(2);
	    st.setAutoReverse(true);
	    st.play();
		
		FadeTransition ft = new FadeTransition(Duration.millis(800), name);
		ft.setAutoReverse(true);
		ft.setCycleCount(2);
		ft.setToValue(1);
		ft.setFromValue(0);
		ft.play();
		
		st.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				name.setVisible(false);
				timeline.play();
			}
			
		});		
	}
}
