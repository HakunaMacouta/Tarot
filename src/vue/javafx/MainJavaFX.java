package vue.javafx;
import java.util.ArrayList;

import Enums.Colors;
import Enums.Values;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainJavaFX extends Application {

	@Override
	public void start(Stage fenetre) throws Exception {
		
		BackgroundImage bi = new BackgroundImage(new Image("file:./ressources/Tarot_Background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background b = new Background(bi);
		
		ArrayList<Card> cards = new ArrayList<Card>();
		double x = 0;
		
//		for (Colors c : Colors.values()) {
//			System.out.println(c.toString());
//			if(c == Colors.TRUMPS){
//				for (Values v : Values.getValues(false)) {
//					System.out.println(v.toString());
//					cards.add(new Card(v,c, x,-100));
//					x+=20;
//				}
//			}
//			else {
				for(Values v : Values.getValues(true)) {
					System.out.println(v.toString());
					Card c = new Card (v,Colors.HEARTS, x,-100);
					c.setManaged(false);
					cards.add(c);
					x+=40;	
				}
//			}
//			
//		}
		
		Card card = new Card(Values.ONE, Colors.TRUMPS,200,200);
		
        final StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(cards);
        stackPane.setBackground(b);
        final SubScene subScene = new SubScene(stackPane, 1600, 900, false, SceneAntialiasing.BALANCED); 
        subScene.setCamera(new PerspectiveCamera()); 
        final ToggleButton playButton = new ToggleButton("Play"); 
        playButton.getStyleClass().add("button");
        StackPane.setAlignment(playButton, Pos.TOP_LEFT); 
        final ToolBar toolBar = new ToolBar(); 
        toolBar.getItems().addAll(playButton); 
        final BorderPane root = new BorderPane(); 
        root.setTop(toolBar); 
        root.setBottom(subScene); 
        final Scene scene = new Scene(root, 1600, 900); 
        //scene.getStylesheets().add("https://github.com/JFXtras/jfxtras-styles/blob/master/src/jmetro/JMetroDarkTheme.css");
        fenetre.setTitle("Dishonored Tarot"); 
        fenetre.setScene(scene); 
        fenetre.getIcons().add(new Image("file:./ressources/Tarot_faticon.png"));
        fenetre.show(); 
        SequentialTransition flips = new SequentialTransition();
        Animation a = card.halfRotateAnimation();
        for(Card c : cards) {
        	flips.getChildren().add(c.getRotateCard());
        }
        //animation.setCycleCount(SequentialTransition.INDEFINITE); 
        playButton.selectedProperty().addListener((observableValue, oldValue, newValue) -> { 
            if (newValue) { 
                flips.play(); 
                //a.play();
                //card.getRotateZ().play();
              
            } else { 
                //flips.pause();
                flips.pause();
            } 
        }); 
	}     

	public static void main(String[] args) {
		launch(args);
	}

}
