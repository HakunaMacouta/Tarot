package vue.javafx;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.chart.Axis;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainJavaFX extends Application {

	private final Duration halfFlipDuration = Duration.seconds(1);
	@Override
	public void start(Stage fenetre) throws Exception {
		/*
		// objet graphique : carte 21
		Card normal = new Card(,
				50,0);
		ImageView back = new ImageView();
		back.setImage(normal.img_back);
		Group carte = new Group(normal,back);
		back.setVisible(false);
		normal.setRotationAxis(Rotate.Y_AXIS);
		
		
		/****EXEMPLE***
//		Image image21 = new Image("file:./ressources/Tarot_nouveau_-_Grimaud_-_1898_-_Trumps_-_21.jpg");
//		ImageView carte21 = new ImageView();
//		carte21.setImage(image21);
//		carte21.setX(-400);
//		carte21.setY(-1000);
//		carte21.setRotate(-10);
//		carte21.setCache(true);

		// scene graphique
		fenetre.setTitle("Let's play Tarot !");
		Group cartes = new Group();
		Scene plateau = new Scene(carte,1024,768);
		//plateau.setCamera(new PerspectiveCamera());
		plateau.setFill(Color.BLACK);
		//cartes.getChildren().add(trump);
		cartes.getChildren().add(normal);
		//cartes.getChildren().add(prout);
		fenetre.setScene(plateau); 
		fenetre.sizeToScene(); 
		fenetre.show(); 

		// animation 1 : zoom (toutes les cartes)
//		scaleTransition =
//				new ScaleTransition(Duration.seconds(4), cartes);
//		scaleTransition.setFromX(0.1);
//		scaleTransition.setFromY(0.1);
//		scaleTransition.setToX(0.1);
//		scaleTransition.setToY(0.1);
		//scaleTransition.setAutoReverse(true);
		//scaleTransition.setCy                                     cleCount(Timeline.INDEFINITE);
		// animation 2 : rotation (toutes les cartes)
		rotateTransition = new RotateTransition(Duration.seconds(5), normal);
		rotateTransition.setAxis(new Point3D(0,100,0));
		rotateTransition.setFromAngle(0);
		rotateTransition.setToAngle(90);
		//prout.setImage(prout.img_back);
		//rotateTransition.setToAngle(180);
		//rotateTransition.pause();
		//rotateTransition.setAutoReverse(true);
		//rotateTransition.setCycleCount(Timeline.INDEFINITE);
		// animation 3 : translation (carte Petit)
//		translateTransition = new TranslateTransition(Duration.seconds(1), prout);
//		translateTransition.setFromZ(200);
//		translateTransition.setToZ(-200);
//		translateTransition.setAutoReverse(true);
//		translateTransition.setCycleCount(Timeline.INDEFINITE);
		
		// animation globale: toutes en //
		parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(
				//rotateTransition
//				scaleTransition
//				translateTransition
				//flipCard(normal)
				); 
		//parallelTransition.setCycleCount(Timeline.INDEFINITE);
//
//		// go !
		parallelTransition.play();
		*/
		BackgroundImage background = new BackgroundImage(new Image("file:./ressources/Tarot_Background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background b = new Background(background);
		
		ArrayList<Card> cards = new ArrayList<Card>();
		double x = 0;
		
		for (Colors c : Colors.values()) {
			if(c == Colors.TRUMPS){
				for (Values v : Values.getValues(false)) {
					cards.add(new Card(v,c, x,50));
					x+=20;
				}
			}
			else {
				for(Values v : Values.getValues(true)) {
					cards.add(new Card(v,c, x,50));
					x+=20;	
				}
			}
			
		}
		Card card = new Card(Values.ONE, Colors.TRUMPS,200,200);
		
        final StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(card);
        stackPane.setBackground(b);
        stackPane.setAlignment(Pos.TOP_LEFT);
        final SubScene subScene = new SubScene(stackPane, 1600, 900, false, SceneAntialiasing.BALANCED); 
        subScene.setCamera(new PerspectiveCamera()); 
        final ToggleButton playButton = new ToggleButton("Play"); 
        StackPane.setAlignment(playButton, Pos.TOP_LEFT); 
        final ToolBar toolBar = new ToolBar(); 
        toolBar.getItems().addAll(playButton); 
        final BorderPane root = new BorderPane(); 
        root.setTop(toolBar); 
        root.setBottom(subScene); 
        
        
        
        final Scene scene = new Scene(root, 1600, 900); 
        fenetre.setTitle("Dishonored Tarot"); 
        fenetre.setScene(scene); 
        fenetre.show(); 
        SequentialTransition flips = new SequentialTransition();
        Animation a = card.halfRotateAnimation();
        for(Card c : cards) {
        	flips.getChildren().add(c.getRotateCard());
        }
        //animation.setCycleCount(SequentialTransition.INDEFINITE); 
        playButton.selectedProperty().addListener((observableValue, oldValue, newValue) -> { 
            if (newValue) { 
                //flips.play(); 
                a.play();
              
            } else { 
                //flips.pause();
                a.pause();
            } 
        }); 
	}     

	public static void main(String[] args) {
		launch(args);
	}

}
