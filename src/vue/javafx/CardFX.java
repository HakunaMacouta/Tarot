/* VAIN BLANC S3C */
package vue.javafx;

import Enums.Colors;
import Enums.Values;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * <b>CardFX Class</b>
 * 
 * The view's card class. More or less linked to the model's Card class.
 * Herited from javafx.scene.Group.
 * 
 * @author Thomas
 *
 */
public class CardFX extends Group {
	
	static Image img_back = new Image("file:./ressources/Tarot_back.png"); 
	private ImageView front_card;
	private ImageView back_card;
	private Values value;
	
	public Values getValue() {
		return value;
	}
	private Colors color;
	public Colors getColor() {
		return color;
	}

	
	private Timeline rotateCard, moveRight;
	private Rotate rotateAxisX, rotateAxisY, rotateAxisZ;
	private RotateTransition rotateZ;
	private double mouseX, mouseY;
	private final static Duration halfFlipDuration = Duration.millis(50);
	private final static Duration distribDuration = Duration.millis(50);
	private final static Duration moveSemiFlipFromDeckDuration = Duration.millis(100);
	
	private final double zoomScale = 0.2;
	private double baseScaleY=0.5, baseScaleX=0.35;
	private double scaleY=0.5, scaleX=0.5;
	private double oldX, oldY;
	private double powerScaleY=0.0002, powerScaleX=-0.0001;
	private boolean soulevageDoing = false;
	private boolean desoulevageDoing = false;
	private boolean inSoulevage;
	private boolean mouseHasExited;
	private boolean canMove;
	private boolean fromDog = false;
	/**
	 * <b> Card class construtor </b>
	 * 
	 * Main constructor of the class.
	 * Flip the card 3D style and set the right ImageView to it.
	 * 
	 * @param v Card's value you want to build
	 * @param c Card's color you want to build
	 * @param x Horizontal position of the card in the layout.
	 * @param y Vertical position of the card in the layout.
	 */
	CardFX(Values v, Colors c, double x, double y) {
		this.value = v;
		this.color = c;
		setLayoutX(x);
		setLayoutY(y);
		setScaleX(calculateScaleX());
		setScaleY(calculateScaleY());
		setManaged(false);
		rotateAxisX = new Rotate(120,100,100,0,Rotate.X_AXIS);
		getTransforms().add(rotateAxisX);
		rotateAxisY = new Rotate(0,100,100,0,Rotate.Y_AXIS);
		getTransforms().add(rotateAxisY);
		rotateAxisZ = new Rotate(0,100,100,0,Rotate.Z_AXIS);
		getTransforms().add(rotateAxisZ);
		
		// Images initializtion
		front_card = new ImageView();
		front_card.setImage(new Image("file:./ressources/Tarot_"+value.toString()+"_"+color.toString()+".png"));
		back_card = new ImageView();
		back_card.setImage(img_back);
		front_card.setVisible(false);
		front_card.setRotationAxis(Rotate.Y_AXIS);
		front_card.setRotate(180);
		front_card.setRotationAxis(Rotate.Z_AXIS);
		front_card.setRotate(180);
		super.getChildren().add(front_card);
		super.getChildren().add(back_card);	
		super.setRotationAxis(Rotate.Y_AXIS);
		rotateCard = halfRotateAnimation();
		
		intializeEvents();
	}

	/**
	 * Intialize all event linked to cards in general
	 */
	private void intializeEvents() {
		/**
		 * When the mouse enter the card zone
		 * 
		 * start the focusing animation and save the old coordinate of the card only if the card showing its front and not already in one of the focused state
		 * 
		 */
		this.setOnMouseEntered(event -> {
			if(front_card.isVisible() && !desoulevageDoing && !soulevageDoing && !inSoulevage) {
				this.toFront();
		        this.setCursor(Cursor.OPEN_HAND);
		        
		        scaleY = getScaleY();
		        scaleX = getScaleX();
		        oldX = getLayoutX();
		        oldY = getLayoutY();
		        soulevageProjetTarot().play();
			}
	    });
		
		
		/**
		 * When the mouse exit
		 */
		this.setOnMouseExited(event -> {
			if(front_card.isVisible()){
				if(!soulevageDoing && inSoulevage && !desoulevageDoing)
					desoulevageProjetTarot().play();
				else
					mouseHasExited = true;
			}
		});
		
		/**
		 * 
		 */
		this.setOnMousePressed(event -> {			
            mouseX = event.getSceneX() ;
            mouseY = event.getSceneY() ;
            this.toFront();
        });
		
		
		this.setOnMouseReleased(enter ->{
		});
		
		/**
		 * 
		 */
		this.setOnMouseDragged(event -> {
			if(front_card.isVisible() && canMove) {
	            double deltaX = event.getSceneX() - mouseX ;
	            double deltaY = event.getSceneY() - mouseY ;
	            
	            relocate(getLayoutX() + deltaX, getLayoutY() + deltaY);
	            mouseX = event.getSceneX() ;
	            mouseY = event.getSceneY() ;
	            
	            calculateScaleX();
	            calculateScaleY();
            }
         });
		
		this.setOnMouseDragReleased(event -> {
			
		});
	}
	
	/**
	 * <b> Setter </b>
	 * 
	 * Allow to set canMove
	 * 
	 * @param b boolean
	 */
	public void setCanMove(boolean b){
		canMove = b;
	}
	
	/**
	 * <b> Getter </b>
	 * 
	 * Allow to get canMove
	 * 
	 * @return boolean canMove.
	 */
	public boolean getCanMove(){
		return canMove;
	}
	

	/**
	 * 
	 * Calculate the new Y scale of the card based on the card's position on the layout of the scene.
	 * 
	 * It's used to perform the 2.5D perpective effect on the side of the tables
	 * 
	 * @return The new Y scale linked to the card'es coordinates
	 */
	private double calculateScaleY(){
		double x = (Math.abs(getLayoutX()-700)*powerScaleX*0.5);
		
		double value = ((Math.abs(getLayoutY()-180)*powerScaleY)+baseScaleY)+x;
		//System.err.println(" Y : " + value);
		return value;
	}

	/**
	 * 
	 * Calculate the new X scale of the card based on the card's position on the layout of the scene.
	 * 
	 * It's used to perform the 2.5D perpective effect on the side of the tables
	 * 
	 * @return The new X scale linked to the card'es coordinates
	 */
	private double calculateScaleX(){
		double y = (Math.abs(getLayoutY()-180)*powerScaleX*4.3);
		
		double value = ((Math.abs(getLayoutX()-700)*powerScaleX)+baseScaleX)-y;
		//System.err.println(" Z : " + value);
		return value<=1?value:-1;
	}
	/**
	 * 
	 * Calculate the new Y scale of the card based on a given position on the layout of the scene.
	 * 
	 * It's used to perform the 2.5D perpective effect on the side of the tables
	 * 
	 * @param d X position on the layout
	 * @param e Y Position on th elayout
	 * @return The new Y scale linked to those coordinate
	 */
	private double calculateScaleY(double posx, double posy){
		double x = (Math.abs(posx-700)*powerScaleX*0.12);
		
		double value = ((Math.abs(posy-180)*powerScaleY)+baseScaleY)+x;
		//System.err.println(" Y : " + value);
		return value;
	}
	/**
	 * 
	 * Calculate the new X scale of the card based on a given position on the layout of the scene.
	 * 
	 * It's used to perform the 2.5D perpective effect on the side of the tables
	 * 
	 * @param d X position on the layout
	 * @param e Y Position on th elayout
	 * @return The new X scale linked to those coordinate
	 */
	private double calculateScaleX(double d, double e){
		double y = (Math.abs(e-180)*powerScaleX*4.3);
		
		double value = ((Math.abs(d-700)*powerScaleX)+baseScaleX)-y;
		//System.err.println(" Z : " + value);
		return value<=1?value:-1;
	}
	
	/**
	 * Setter of the position of the cards inside the layout
	 * 
	 * @param x new X position of the card inside the layout
	 * @param y new y position of the card inside the layout
	 */
	void setPosition(double x, double y) {
		setLayoutX(x);
		setLayoutY(y);
	}
	
	/**
	 * 
	 * @param k
	 * @return
	 */
	private Timeline moveSemiFlipFromDeckAnimation(int k){
		if(!fromDog){
		return new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(this.layoutXProperty(), getLayoutX())),
				new KeyFrame(moveSemiFlipFromDeckDuration,new KeyValue(this.layoutXProperty(), 100+(150*k*scaleX)))
				);
		}
		else{
			return new Timeline(
					new KeyFrame(Duration.ZERO, new KeyValue(this.layoutXProperty(), getLayoutX())),
					new KeyFrame(moveSemiFlipFromDeckDuration,new KeyValue(this.layoutXProperty(), 100+(150*k*scaleX)),
							new KeyValue(this.layoutYProperty(),300))
					);
		}
	}
	
	/**
	 * 
	 * @param k
	 * @return
	 */
	public Timeline getMoveLeft(int k){
		return moveSemiFlipFromDeckAnimation(k);
	}
	
	/**
	 * <b>Card shuffling animation timeline</b>
	 * 
	 * This method create two timeline animations depending on the card order in the deck.
	 * The first half (defined by a false boolean b) gets lift upon the air and the second half move alongside the horizontal axis.
	 * Then card are reunited from a common height to shuffle them.
	 * 
	 * @author Florent Vain
	 * @author Thomas Blanc
	 * @param b Boolean telling if the card is in the first half of the deck or not
	 * @param k Integer giving the order of the card in the deck
	 * @return A timeline animation moving the card ina shuffle animation
	 * 
	 * @see #javafx.animation.Timeline
	 */
	private Timeline shuffleCardAnimation(boolean b,int k){
		if(!b)
			return new Timeline(
					new KeyFrame(Duration.ZERO, new KeyValue(this.layoutXProperty(), getLayoutX()),
							new KeyValue(this.layoutYProperty(),getLayoutY())),
					new KeyFrame(Duration.millis(500), new KeyValue(this.layoutYProperty(),getLayoutY()-100),new KeyValue(this.layoutXProperty(), getLayoutX())),
					new KeyFrame(Duration.millis(1200), new KeyValue(this.layoutYProperty(),getLayoutY()+30), new KeyValue(this.layoutXProperty(), getLayoutX())),
					new KeyFrame(Duration.millis(1800),new KeyValue(this.layoutXProperty(), getLayoutX()+125),new KeyValue(this.layoutYProperty(),getLayoutY()+78-k+1)),
					new KeyFrame(Duration.millis(2100),new KeyValue(this.layoutXProperty(), getLayoutX()), new KeyValue(this.layoutYProperty(),getLayoutY()-(k*2))),
					new KeyFrame(Duration.millis(2400),new KeyValue(this.layoutXProperty(), getLayoutX()), new KeyValue(this.layoutYProperty(),getLayoutY()))
					);
		else
			return new Timeline(
					new KeyFrame(Duration.millis(200), new KeyValue(this.layoutXProperty(), getLayoutX()),
							new KeyValue(this.layoutYProperty(),getLayoutY())),
					new KeyFrame(Duration.millis(800),new KeyValue(this.layoutXProperty(), getLayoutX()+200),
							new KeyValue(this.layoutYProperty(),getLayoutY())),
					new KeyFrame(Duration.millis(1200),new KeyValue(this.layoutXProperty(), getLayoutX()+200),
							new KeyValue(this.layoutYProperty(),getLayoutY())),
					new KeyFrame(Duration.millis(1800),new KeyValue(this.layoutXProperty(), getLayoutX()+125),
							new KeyValue(this.layoutYProperty(),getLayoutY()-k)),
					new KeyFrame(Duration.millis(1801),new KeyValue(this.layoutXProperty(), getLayoutX()+125),
							new KeyValue(this.layoutYProperty(),getLayoutY()-k)),
					new KeyFrame(Duration.millis(2100),new KeyValue(this.layoutXProperty(), getLayoutX()),
							new KeyValue(this.layoutYProperty(),getLayoutY()-(k*2))),
					new KeyFrame(Duration.millis(2400),new KeyValue(this.layoutXProperty(), getLayoutX()),
							new KeyValue(this.layoutYProperty(),getLayoutY())
					));
	}
	
	/**
	 * <b>Card Distribution animation timeline.</b>
	 * 
	 * 
	 * 
	 * @author Florent Vain
	 * @author Thomas Blanc
	 * @param i Player's number (-1 for the dog)
 	 * @return A timeline animation moving the card from the main deck to the player's one
	 */
	public Timeline getAnimationDistrib(int i) {
		System.out.println("i: "+i);
		DoubleProperty x  = super.translateXProperty();
        DoubleProperty y  = super.translateYProperty();
        this.scaleXProperty();
        Timeline t = new Timeline();
        switch (i) {
        //Cas Chien
        case -1:
        	t = new Timeline(
    				new KeyFrame(Duration.ZERO,new KeyValue(x,0),new KeyValue(y,0),
    						new KeyValue(this.scaleXProperty(), getScaleX()),
    						new KeyValue(this.scaleYProperty(), getScaleY())
    						),
    				new KeyFrame(distribDuration,
    						new KeyValue(x,200), new KeyValue(y,50), 
    						new KeyValue(this.scaleXProperty(),
    								calculateScaleX(getLayoutX()+200,getLayoutY()+50)), 
    						new KeyValue(this.scaleYProperty(),
    								calculateScaleY(getLayoutX()+200, getLayoutY()+50))
    						)
    		);
        	break;
        //Cas Joueur
        case 0:
        	t = new Timeline(
    				new KeyFrame(Duration.ZERO,new KeyValue(y,0),
    						new KeyValue(this.scaleXProperty(), getScaleX()),
    						new KeyValue(this.scaleYProperty(), getScaleY())
    						),
    				new KeyFrame(distribDuration, new KeyValue(y,450), 
    						new KeyValue(this.scaleXProperty(),
    								calculateScaleX(getLayoutX(),getLayoutY()+450)), 
    						new KeyValue(this.scaleYProperty(),
    								calculateScaleY(getLayoutX(),getLayoutY()+450))
    						)
    		);
        	break;
		//Cas IA Gauche
        case 1:
        	t = new Timeline(
    				new KeyFrame(Duration.ZERO,new KeyValue(x,0),new KeyValue(y,0),
    						new KeyValue(rotateAxisZ.angleProperty(),0),
    						new KeyValue(this.scaleYProperty(), getScaleY()),
    						new KeyValue(this.scaleXProperty(), getScaleX())
    						),
    				new KeyFrame(distribDuration,new KeyValue(x,-600),new KeyValue(y,-100),
    						new KeyValue(rotateAxisZ.angleProperty(),-90), 
    						new KeyValue(this.scaleXProperty(),
    								calculateScaleX(getLayoutX()-600,getLayoutY()-100)), 
    						new KeyValue(this.scaleYProperty(),
    								calculateScaleY(getLayoutX()-600,getLayoutY()-100))
    						)
    		);
        	break;
        //Cas IA Droite
        case 2:
        	t = new Timeline(
    				new KeyFrame(Duration.ZERO,new KeyValue(x,0),new KeyValue(y,0),
    						new KeyValue(rotateAxisZ.angleProperty(),0),
    						new KeyValue(this.scaleXProperty(), getScaleX()),
    						new KeyValue(this.scaleYProperty(), getScaleY())
    						),
    				new KeyFrame(distribDuration,
    						new KeyValue(x,600),new KeyValue(y,-100),
    						new KeyValue(rotateAxisZ.angleProperty(),90),
    						new KeyValue(this.scaleXProperty(),
    								calculateScaleX(getLayoutX()+600,getLayoutY()-100)), 
    						new KeyValue(this.scaleYProperty(),
    								calculateScaleY(getLayoutX()+600,getLayoutY()-100))
    						)
    				);
        	break;
        //Cas IA Fond
        case 3:
        	t = new Timeline(
    				new KeyFrame(Duration.ZERO,new KeyValue(y,0),
    						new KeyValue(this.scaleYProperty(), getScaleY()),
    						new KeyValue(this.scaleXProperty(), getScaleX())
    						),
    				new KeyFrame(distribDuration,new KeyValue(y,-100), 
    						new KeyValue(this.scaleXProperty(),
    								calculateScaleX(getLayoutX(),getLayoutY()-100)), 
    						new KeyValue(this.scaleYProperty(),
    								calculateScaleY(getLayoutX(),getLayoutY()-100))
    						)
    		);
        	break;
        }
		return t;	
	}
	
	/**
	 * 
	 * Shuffle Animation getter
	 * 
	 * @param b Boolean telling if the card is in the first half of the deck or not
	 * @param k Integer giving the order of the card in the deck
	 * @return The timeline animation
	 */
	public Timeline getShuffle(boolean b, int k){
		return shuffleCardAnimation(b,k);
	}

	
	/**
	 * Card flipping animation
	 * Flip the card on the Y axis.
	 * @return Timeline animation whic rotate the card on the Y axis on 180 degrees
	 */
	private Timeline halfRotateAnimation() {
		return new Timeline( 
                new KeyFrame(Duration.ZERO, new KeyValue(rotateAxisY.angleProperty(), 0)), 
                new KeyFrame(halfFlipDuration.multiply(2), actionEvent -> { 
                    front_card.setVisible(!front_card.isVisible()); 
                    back_card.setVisible(!back_card.isVisible());
                }), 
                new KeyFrame(halfFlipDuration.multiply(4), new KeyValue(rotateAxisY.angleProperty(), 180)));
	}
	
	/**
	 * 
	 * Animation timeline doing the animation between the unfocused state and the focused state of a card.
	 * 
	 *  The unfocused state is when the card is not hovered.
	 *  The focused state is when the card is hovered.
	 *  
	 *  This animation makes the card go from perspective 2.5D to front 2D view by changing the X rotation value and the scale of the card.
	 *  It also set a set of boolean values to safe the animation integrity. 
	 * 
	 * @author Florent Vain
	 * @autor Thomas Blanc
	 * @return timeline animation between unfocused and focused mode for cards
	 * @see vue.javafx.CardFX#desoulevageProjetTarot()
	 */
	private Timeline soulevageProjetTarot(){
		soulevageDoing = true;
		inSoulevage = true;
		return new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(rotateAxisX.angleProperty(),120),
						new KeyValue(this.scaleXProperty(), scaleX),
						new KeyValue(this.scaleYProperty(), scaleY)
						),
				new KeyFrame(Duration.millis(400), new KeyValue(rotateAxisX.angleProperty(), 180),
						new KeyValue(this.scaleXProperty(), scaleX+zoomScale),
						new KeyValue(this.scaleYProperty(), scaleY+zoomScale)
						),
				new KeyFrame(Duration.millis(401), actionEvent -> {
							soulevageDoing = false;
							if(mouseHasExited)
								desoulevageProjetTarot().play();
						})
				);
	}
	
	
	/**
     * Animation timeline doing the animation between the focused state and the unfocused state of a card.
	 * 
	 *  The unfocused state is when the card is not hovered.
	 *  The focused state is when the card is hovered.
	 *  
	 *  This animation makes the card go from front 2D vew to perspective 2.5D view by changing the X rotation value and the scale of the card.
	 *  It also set a set of boolean values to safe the animation integrity. 
	 * 
	 * @author Florent Vain
	 * @author Thomas Blanc
	 * @return timeline animation between unfocused and focused mode for cards
	 * @see vue.javafx.CardFX#soulevageProjetTarot()
	 */
	private Timeline desoulevageProjetTarot(){
		desoulevageDoing = true;
		double x=getLayoutX(), y = getLayoutY();
		return new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(rotateAxisX.angleProperty(),180),
						new KeyValue(this.scaleXProperty(),getScaleX()),
						new KeyValue(this.scaleYProperty(),getScaleY())),
				new KeyFrame(Duration.millis(400), new KeyValue(rotateAxisX.angleProperty(), 120),
						new KeyValue(this.scaleXProperty(),getScaleX()-zoomScale),
						new KeyValue(this.scaleYProperty(),getScaleY()-zoomScale)),
				new KeyFrame(Duration.millis(401), actionEvent ->{
					desoulevageDoing = false;
					inSoulevage = false;
					mouseHasExited = false;
				})
				);
	}
	
	public Timeline getRotateCard() {
		return rotateCard;
	}

	
	
	public Timeline sortAnimation(int k, int nbCarte) {
		this.toFront();
		if(!fromDog){
		return new Timeline(
				new KeyFrame(Duration.ZERO,
						new KeyValue(layoutXProperty(),getLayoutX()),
						new KeyValue(layoutYProperty(), getLayoutY())),
				new KeyFrame(Duration.millis(250),
						new KeyValue(layoutXProperty(),-150+((1600/nbCarte)*k)),
						new KeyValue(layoutYProperty(), 100))
				);
		}
		else
		{
			return new Timeline(
					new KeyFrame(Duration.ZERO,
							new KeyValue(layoutXProperty(),getLayoutX()),
							new KeyValue(layoutYProperty(), getLayoutY())),
					new KeyFrame(Duration.millis(250),
							new KeyValue(layoutXProperty(),-360+((1600/nbCarte)*k)),
							new KeyValue(layoutYProperty(), 500))
					);
		}
	}
	
	public void setUltraScale(double x, double y){
		setScaleX(x);
		setScaleY(y);
	}
	

	public void isFromDog(){
		fromDog  = true;
	}
	

	public Timeline reuniteAnimation() {
		this.toFront();
		return new Timeline(
				new KeyFrame(Duration.ZERO,
						new KeyValue(layoutXProperty(),getLayoutX()),
						new KeyValue(layoutYProperty(), getLayoutY())),
				new KeyFrame(Duration.millis(500),
						new KeyValue(layoutXProperty(), 768),
						new KeyValue(layoutYProperty(), 300))
				);
		}
	
	public Timeline reuniteChienAnimation(){
		this.toBack();
		return new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(layoutXProperty(),getLayoutX()),
						new KeyValue(layoutYProperty(), getLayoutY())),
				new KeyFrame(Duration.millis(500),
						new KeyValue(layoutXProperty(), 570),
						new KeyValue(layoutYProperty(), 680))
				);
	}
	
}
