package vue.javafx;

import java.util.ArrayList;
import java.util.List;

import controller.Action;
import controller.Controller;
import javafx.animation.SequentialTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
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
	private ArrayList<CardFX> Ecart = new ArrayList<CardFX>();
	
	private Rectangle DraggableZone;
	
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
		
		initializeDragRect();
		initialize();
	}

	/**
	 * 
	 */
	private void initialize() {

		double x = 670;
		double y = 300;
		for (model.Card cm : Controller.activeController.getModel().getDeck().getCards()) {
			cards.add(new CardFX(cm.getValue(),cm.getColor(),x,y));
			x -= 0.05;
			y -= 0.3;
		}
		
		GButton.setDisable(true);
		SCButton.setDisable(true);
		CCButton.setDisable(true);
		
		
		
		
        stackPane.getChildren().addAll(cards);
        stackPane.getChildren().add(DraggableZone);
        stackPane.setBackground(null);
        //Toolbar
        toolBar.getItems().addAll(distribButton);
        toolBar.getItems().addAll(backMenuButton);
        
        //BorderPane
        root.setTop(toolBar); 
        root.setCenter(subScene);
        root.setBackground(b);
        
        subScene.setCamera(new PerspectiveCamera());
        initializeDragRect();
        intializingEvents();
	}
	
	private void initializeDragRect() {
		DraggableZone = new Rectangle(200,400,Color.TRANSPARENT);
		DraggableZone.setLayoutX(10);
		DraggableZone.setLayoutY(10);
		DraggableZone.setManaged(false);
		DraggableZone.setStrokeLineJoin(StrokeLineJoin.ROUND);
		DraggableZone.setStroke(Color.BLACK);
		DraggableZone.setStrokeWidth(4);
		DraggableZone.setVisible(false);
		
		DraggableZone.setOnMouseExited(event -> {
			DraggableZone.setStroke(Color.BLACK);
		});
	}

	/**
	 * 
	 */
	private void intializingEvents() {
        distribButton.setOnAction(event ->{
        		if(Controller.activeController.performAction(Action.START_DISTRUBUTION))
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
        	((MainMenu) MainJavaFX.scenes.get(MainJavaFX.MAIN_MENU_INDEX)).reset();
        	fenetre.setScene(MainJavaFX.scenes.get(MainJavaFX.MAIN_MENU_INDEX));
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
        		if(DraggableZone.isHover()){
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
        	DraggableZone.setVisible(true);
        	for(CardFX c : playerHand) {
        		c.setOnDragDetected(mouseEvent -> {
        			System.out.println("DnD");
        			Dragboard dragBoard = c.startDragAndDrop(TransferMode.MOVE);
        			ClipboardContent content = new ClipboardContent();
        			dragBoard.setContent(content);
        			
        			
        		});
        	}
        	
        });
        DraggableZone.setOnDragDropped(dragEvent -> {
        	CardFX c = (CardFX) dragEvent.getGestureSource();
    		Ecart.add(c);
    		playerHand.remove(c);
    		System.out.println("Dragged");
    	});
	}
	private void SortAnimations() {
		int k = 1;
		for(model.Card c : Controller.activeController.getModel().getPlayer(0).getHand()) {
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
		List<Integer> l = Controller.activeController.getModel().getDistributionOrder();
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
	public void reset() {
		initialize();
	}
}
