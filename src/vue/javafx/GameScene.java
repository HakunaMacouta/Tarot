package vue.javafx;

import java.util.ArrayList;
import java.util.List;

import controler.Action;
import controler.Controler;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
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
	
	private ArrayList<CardFX> cards = new ArrayList<CardFX>();
	private ArrayList<CardFX> playerHand = new ArrayList<CardFX>();
	private ArrayList<CardFX> dogMaw = new ArrayList<CardFX>();
	
	private final StackPane stackPane = new StackPane();
	private final SubScene subScene = new SubScene(stackPane, 1600, 900, false, SceneAntialiasing.BALANCED); 
    private final TarotButton distribButton = new TarotButton("Commencer !");
    private final TarotButton backMenuButton = new TarotButton("Retour");
    private final TarotButton PButton = new TarotButton("Prise ?");
    private final TarotButton GButton = new TarotButton("Garde ?");
    private final TarotButton SCButton = new TarotButton("Garde sans chien ?");
    private final TarotButton CCButton = new TarotButton("Garde contre le chien ?");
    
    private final ToolBar toolBar = new ToolBar(); 
    private final static BorderPane root = new BorderPane(); 
    private SequentialTransition flip = new SequentialTransition();
    private SequentialTransition spread = new SequentialTransition();
    private SequentialTransition spread_n_flip = new SequentialTransition();
    private SequentialTransition distrib = new SequentialTransition();
    private SequentialTransition sort = new SequentialTransition();
    
    private CardFX movingCardFX;
	private boolean isDragging=false;
	private double sourceX;
	private double sourceY;
	private Object handToMaw;
	private boolean alreadyAddedToolBarButton=false;
	
    
	
	public GameScene(Stage fenetre){
		super(root, 1600, 900);
		this.fenetre = fenetre;
		
		initialize();
	}

	/**
	 * 
	 */
	private void initialize() {

		double x = 670;
		double y = 300;
		for (model.Card cm : Controler.activeControler.getModel().getDeck().getCards()) {
			cards.add(new CardFX(cm.getValue(),cm.getColor(),x,y));
			x -= 0.05;
			y -= 0.3;
		}

        stackPane.getChildren().addAll(cards);
        stackPane.setBackground(null);
        //Toolbar
        toolBar.getItems().addAll(distribButton);
        toolBar.getItems().addAll(backMenuButton);
        
        //BorderPane
        root.setTop(toolBar); 
        root.setCenter(subScene);
        root.setBackground(b);
        
        subScene.setCamera(new PerspectiveCamera());

        intializingEvents();
	}

	/**
	 * 
	 */
	private void intializingEvents() {
        distribButton.setOnAction(event ->{
        		if(Controler.activeControler.performAction(Action.START_DISTRUBUTION))
        			System.err.println("LE PETIT EST SEC");
        		else{
        		System.out.println("DISTRIBUTION COMMENCE");
        		//Commencer la distribution visuelle
        		toolBar.getItems().remove(distribButton);
        		initializeDistribution();
        		int u=0;
    			boolean b=true;
    	        for(CardFX c : cards) {
    	        	c.getShuffle(b,u).play();
    	        	if(cards.size()/2 < u+1 && b)
    	        		b=!b;
    	        	u++;
    	        }
    	        distrib.play();
        		}
        });
        
        backMenuButton.setOnAction(event ->{
        	boolean fc = fenetre.isFullScreen();
        	fenetre.setScene(MainJavaFX.scenes.get(MainJavaFX.MAIN_MENU_INDEX));
        	if(fc)
        		fenetre.setFullScreen(true);
        });
        
        this.setOnMouseClicked(event ->{
        	if(event.getSource() instanceof CardFX){
        		System.err.println("HAHA");
        		movingCardFX = (CardFX) event.getSource();
        		isDragging = true;
        		sourceX = event.getSceneX();
        		sourceY = event.getSceneY();
        		handToMaw = !validDogPosition(sourceX, sourceY);
        	}
        });
        
        this.setOnMouseDragReleased(event ->{
        	if(isDragging){
        		isDragging = false;
        		if(validDogPosition(event.getSceneX(), event.getSceneY())){
        			System.err.println("AJOUT AU CHIEN");
        			dogMaw.add(movingCardFX);
        			playerHand.remove(movingCardFX);
        		}
        	}
        });


        distrib.setOnFinished(event -> {
        	int k=0;
            for(CardFX c : playerHand){
            	spread_n_flip.getChildren().addAll(c.getMoveLeft(k),c.getRotateCard());
            	k++;
            }
            SortAnimations();
            //spread_n_flip.getChildren().addAll(spread,flip);
            spread_n_flip.getChildren().add(sort);
            spread_n_flip.play();
        });
        
        spread_n_flip.setOnFinished(event -> {
        	if(!alreadyAddedToolBarButton){
        	toolBar.getItems().addAll(PButton,GButton,SCButton,CCButton);
        	alreadyAddedToolBarButton = true;
        	for(CardFX c : playerHand){
        		c.setCanMove(true);
        	}
        	}
        });
        
        PButton.setOnAction(event -> {
        	int k = 0;
        	flip.getChildren().clear();
        	spread.getChildren().clear();
        	spread_n_flip.getChildren().clear();
        	for(CardFX c : dogMaw) {
        		spread.getChildren().add(c.getMoveLeft(k));
        		flip.getChildren().add(c.getRotateCard());
        		k++;
        	}
        	spread_n_flip.getChildren().addAll(spread,flip);
        	spread_n_flip.play();
        });
	}
	private void SortAnimations() {
		int k = 1;
		for(model.Card c : Controler.activeControler.getModel().getPlayer(0).getHand()) {
			for( CardFX fx : playerHand) {
				if(fx.getValue() == c.getValue() && fx.getColor() == c.getColor()) {
					sort.getChildren().add(fx.sortAnimation(k));
				}
			}
//			System.out.println("Model : "+c.getValue().toString()+" "+c.getColor().toString());
//			System.out.println("Vue   : "+playerHand.get(i).getValue().toString()+" "+playerHand.get(i).getColor().toString());
			k++;
		}
	}
	
	private boolean validDogPosition(double sourceX2, double sourceY2) {
		return sourceX2 >= 1000
				&& sourceY2<=300 
				&& sourceX2 <= 1550
				&& sourceY2 >= 50;
	}

	private void initializeDistribution() {
		List<Integer> l = Controler.activeControler.getModel().getDistributionOrder();
		for(int i : l) {
			CardFX c = cards.get(cards.size()-1);
			switch (i) {
			case -1:
				dogMaw.add(c);
				break;
			case 0:
				playerHand.add(c);
				//c.toFront();
				break;
			}
			//c.toFront();
			cards.remove(c);
			distrib.getChildren().add(c.getAnimationDistrib(i));
		}
	}
	public void reset(){
		initialize();
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
