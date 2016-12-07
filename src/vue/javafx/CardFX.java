package vue.javafx;

import Enums.Colors;
import Enums.Values;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point3D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * 
 * @author Thomas
 *
 */
public class CardFX extends Group {
	static Image img_back = new Image("file:./ressources/Tarot_back.png");
	private ImageView front_card;
	private ImageView back_card;
	private Values value;
	private Colors color;
	private Timeline rotateCard, moveRight;
	private Rotate rotateAxisX, rotateAxisY, rotateAxisZ;
	private RotateTransition rotateZ;
	private double mouseX, mouseY;
	private final static Duration halfFlipDuration = Duration.millis(25);
	private final static Duration moveSemiFlipFromDeckDuration = Duration.millis(100);
	
	private double baseScaleY=0.35, baseScaleX=0.35;
	private double scaleY=0.35, scaleX=0.35;
	private double powerScaleY=0.0005, powerScaleX=0.00008;
	/**
	 * 
	 * @param v, a field from the enum Values 
	 * @param c, a field from the enum Colors
	 */
	
	CardFX(Values v, Colors c, double x, double y) {
		this.value = v;
		this.color = c;
		scaleY = baseScaleY + (y*powerScaleY);
		scaleX =scaleY-Math.abs(x-700)*powerScaleX;
		setLayoutX(x);
		setLayoutY(y);
		setScaleX(scaleX);
		setScaleY(scaleY);
		setManaged(false);
		rotateAxisX = new Rotate(120,Rotate.X_AXIS);
		getTransforms().add(rotateAxisX);
		rotateAxisY = new Rotate(0,100,100,0,Rotate.Y_AXIS);
		getTransforms().add(rotateAxisY);
		rotateAxisZ = new Rotate(90,Rotate.Z_AXIS);
		
		

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
//		rotateZ = new RotateTransition(Duration.ONE, this);
//		rotateZ.setAxis(new Point3D(-50, 0, 0));
//		rotateZ.setFromAngle(0);
//		rotateZ.setToAngle(-50);
//		rotateZ.play();
		this.setOnMouseEntered(event -> {
			if(front_card.isVisible()) {
		        this.setCursor(Cursor.OPEN_HAND);
		        setScaleX(scaleX+0.2);
		        setScaleY(scaleY+0.2);
			}
	    });
		
		this.setOnMouseExited(event -> {
			setScaleX(scaleX);
			setScaleY(scaleY);
		});
		
		this.setOnMousePressed(event -> {
			System.err.println(event.getSceneX());
            mouseX = event.getSceneX() ;
            mouseY = event.getSceneY() ;
            this.toFront();
        });
		
		this.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mouseX ;
            double deltaY = event.getSceneY() - mouseY ;
            
            scaleY+=deltaY*powerScaleY;
            scaleX=scaleY-Math.abs((getLayoutX() + deltaX)-700)*powerScaleX;
            setScaleX(scaleX);
            setScaleY(scaleY);
            
            relocate(getLayoutX() + deltaX, getLayoutY() + deltaY);
            mouseX = event.getSceneX() ;
            mouseY = event.getSceneY() ;
         });
	}
	
	void setPosition(double x, double y) {
		setLayoutX(x);
		setLayoutY(y);
	}
	
	private Timeline moveSemiFlipFromDeckAnimation(int k){
		return new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(this.layoutXProperty(), getLayoutX())),
				new KeyFrame(moveSemiFlipFromDeckDuration,new KeyValue(this.layoutXProperty(), getLayoutX()-1500+(120*k*scaleX)))
				);
	}
	
	public Timeline getMoveLeft(int k){
		return moveSemiFlipFromDeckAnimation(k);
	}
	
	private Timeline shuffleCardAnimation(boolean b,int k){
		if(!b)
			return new Timeline(
					new KeyFrame(Duration.ZERO/*, new KeyValue(this.layoutXProperty(), getLayoutX())*/,
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
	public Timeline getAnimationDistrib(int i) {
		System.out.println("i: "+i);
		DoubleProperty x  = super.translateXProperty();
        DoubleProperty y  = super.translateYProperty();
        Timeline t = new Timeline();
        switch (i) {
        //Cas Chien
        case -1:
        	t = new Timeline(
    				new KeyFrame(Duration.ZERO,new KeyValue(x,0)),
    				new KeyFrame(Duration.seconds(0.5),new KeyValue(x,200)),
    				new KeyFrame(Duration.seconds(0.501),  actionEvent -> {
    					scaleY=baseScaleY + (getLayoutY()*powerScaleY);
    		            scaleX=scaleY-Math.abs(200-700)*powerScaleX;
    		            setScaleX(scaleX);
    		            setScaleY(scaleY);
    				})
    		);
        	break;
        //Cas Joueur
        case 0:
        	t = new Timeline(
    				new KeyFrame(Duration.ZERO,new KeyValue(y,0)),
    				new KeyFrame(Duration.seconds(0.5), new KeyValue(y,450)),
    				new KeyFrame(Duration.seconds(0.501),  actionEvent -> {
    					scaleY=baseScaleY + (450*powerScaleY);
    		            scaleX=scaleY-Math.abs(getLayoutX()-700)*powerScaleX;
    		            setScaleX(scaleX);
    		            setScaleY(scaleY);
    				})
    		);
        	break;
		//Cas IA Gauche
        case 1:
        	t = new Timeline(
    				new KeyFrame(Duration.ZERO,new KeyValue(x,0),new KeyValue(y,0)),
    				new KeyFrame(Duration.seconds(0.5),new KeyValue(x,-600),new KeyValue(y,100)),
    				new KeyFrame(Duration.seconds(0.5),  actionEvent -> {
    					scaleY=baseScaleY + (100*powerScaleY);
    		            scaleX=scaleY-Math.abs(-600-700)*powerScaleX;
    		            setScaleX(scaleX);
    		            setScaleY(scaleY);
    				})
    		);
        	break;
        //Cas IA Droite
        case 2:
        	t = new Timeline(
    				new KeyFrame(Duration.ZERO,new KeyValue(x,0),new KeyValue(y,0)),
    				new KeyFrame(Duration.seconds(0.5),new KeyValue(x,600),new KeyValue(y,100)),
    				new KeyFrame(Duration.seconds(0.5),  actionEvent -> {
    					scaleY=baseScaleY + (100*powerScaleY);
    		            scaleX=scaleY-Math.abs(600-700)*powerScaleX;
    		            setScaleX(scaleX);
    		            setScaleY(scaleY);
    				})
    		);
        	break;
        //Cas IA Fond
        case 3:
        	t = new Timeline(
    				new KeyFrame(Duration.ZERO,new KeyValue(y,0)),
    				new KeyFrame(Duration.seconds(0.5),new KeyValue(y,100)),
    				new KeyFrame(Duration.seconds(0.5),  actionEvent -> {
    					scaleY=baseScaleY + (100*powerScaleY);
    		            scaleX=scaleY-Math.abs(getLayoutX()-700)*powerScaleX;
    		            setScaleX(scaleX);
    		            setScaleY(scaleY);
    				})
    		);
        	break;
        }
		return t;	
	}
	public Timeline getShuffle(boolean b, int k){
		return shuffleCardAnimation(b,k);
	}
	
	private Timeline halfRotateAnimation() {
		return new Timeline( 
                new KeyFrame(Duration.ZERO, new KeyValue(rotateAxisY.angleProperty(), 0)), 
                new KeyFrame(halfFlipDuration.multiply(2), actionEvent -> { 
                    front_card.setVisible(!front_card.isVisible()); 
                    back_card.setVisible(!back_card.isVisible());
                }), 
//                new KeyFrame(halfFlipDuration.multiply(3), actionEvent -> { 
//                    front_card.setVisible(false); 
//                    back_card.setVisible(true); 
//                }), 
                new KeyFrame(halfFlipDuration.multiply(4), new KeyValue(rotateAxisY.angleProperty(), 180)));
	}
	
	public Timeline getRotateCard() {
		return rotateCard;
	}
	
	public void setHorizontal()
	{
		getTransforms().add(rotateAxisZ);
	}
	
}
