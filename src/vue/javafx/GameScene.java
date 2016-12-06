package vue.javafx;

import java.util.ArrayList;

import Enums.Colors;
import Enums.Values;
import controler.Action;
import controler.Controler;
import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class GameScene extends Scene {
	
	private Stage fenetre;
	private BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
	private final BackgroundImage bi = new BackgroundImage(new Image("file:./ressources/Tarot_Background.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	private final Background b = new Background(bi);
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	private ArrayList<Card> playerHand = new ArrayList<Card>();
	private ArrayList<Card> dogMaw = new ArrayList<Card>();
	
	private final StackPane stackPane = new StackPane();
	private final SubScene subScene = new SubScene(stackPane, 1920, 1080, false, SceneAntialiasing.BALANCED); 
    private final Button playAnimButton = new Button("Play");
    private final Button distribButton = new Button("Distribuer"); 
    private final ToolBar toolBar = new ToolBar(); 
    private final static BorderPane root = new BorderPane(); 
    private SequentialTransition flips = new SequentialTransition();
	private Button backMenuButton = new Button("Retourner au Menu");
    
	
	public GameScene(Stage fenetre){
		super(root, 1600, 900);
		this.fenetre = fenetre;
		
		initialize();
	}

	/**
	 * 
	 */
	private void initialize() {
double x = 0;
		
		for (Colors c : Colors.values()) {
			System.out.println(c.toString());
			if(c == Colors.TRUMPS){
				for (Values v : Values.getValues(false)) {
					System.out.println(v.toString());
					Card c1 = new Card (v,c, x,100);
					c1.setManaged(false);
					cards.add(c1);
					x+=40;
				}
			}
			else {
				for(Values v : Values.getValues(true)) {
					System.out.println(v.toString());
					Card c1 = new Card (v,c, x,100);
					c1.setManaged(false);
					cards.add(c1);
					x+=40;
				}
			}
		}
		
        stackPane.getChildren().addAll(cards);
        //stackPane.setAlignment(Pos.BASELINE_CENTER);
        //stackPane.getChildren().addAll(ptGroup);
        stackPane.setBackground(null);
        //setCamera(new PerspectiveCamera());
        playAnimButton.getStyleClass().add("button");
        StackPane.setAlignment(playAnimButton, Pos.TOP_LEFT);
        
        //Bouton de distribution
        playAnimButton.getStyleClass().add("button");
        StackPane.setAlignment(distribButton, Pos.TOP_LEFT); 
        
        playAnimButton.getStyleClass().add("button");
        StackPane.setAlignment(backMenuButton, Pos.TOP_RIGHT);
        //Toolbar
        toolBar.getItems().addAll(playAnimButton); 
        toolBar.getItems().addAll(distribButton);
        toolBar.getItems().addAll(backMenuButton);
        
        //BorderPane
        root.setTop(toolBar); 
        root.setCenter(subScene);
        root.setBackground(b);
        
        subScene.setCamera(new PerspectiveCamera());
        //scene.getStylesheets().add("https://github.com/JFXtras/jfxtras-styles/blob/master/src/jmetro/JMetroDarkTheme.css");

        for(Card c : cards) {
        	flips.getChildren().add(c.getRotateCard());
        }
        //animation.setCycleCount(SequentialTransition.INDEFINITE); 
        intializingEvents();
	}

	/**
	 * 
	 */
	private void intializingEvents() {
        
		playAnimButton.setOnAction(event ->{
			flips.play(); 
		});
		
        distribButton.setOnAction(event ->{
        		Controler.activeControler.performAction(Action.START_DISTRUBUTION);
        		System.out.println("DISTRIBUTION COMMENCE");
        		//Commencer la distribution visuelle
        		toolBar.getItems().remove(distribButton);
        });
        
        backMenuButton.setOnAction(event ->{
        	boolean fc = fenetre.isFullScreen();
        	fenetre.setScene(MainJavaFX.scenes.get(MainJavaFX.MAIN_MENU_INDEX));
        	if(fc)
        		fenetre.setFullScreen(true);
        });
	}

	public GameScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	public GameScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public GameScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public GameScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public GameScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public GameScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

}
