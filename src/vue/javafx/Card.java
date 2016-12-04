package vue.javafx;

import Enums.Colors;
import Enums.Values;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
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
public class Card extends Group {
	static Image img_back = new Image("file:./ressources/Tarot_back.png");
	private ImageView front_card;
	private ImageView back_card;
	private Values value;
	private Colors color;
	private Timeline rotateCard;
	private RotateTransition rotateZ;
	private double mouseX, mouseY;
	private final static Duration halfFlipDuration = Duration.millis(50);
	/**
	 * 
	 * @param v, a field from the enum Values 
	 * @param c, a field from the enum Colors
	 */
	
	Card(Values v, Colors c, double x, double y) {
		this.value = v;
		this.color = c;
		setLayoutX(x);
		setLayoutY(y);
		setScaleX(0.6);
		setScaleY(0.6);

		front_card = new ImageView();
		front_card.setImage(new Image("file:./ressources/Tarot_"+value.toString()+"_"+color.toString()+".png"));
		back_card = new ImageView();
		back_card.setImage(img_back);
		front_card.setVisible(false);
		front_card.setRotationAxis(Rotate.Y_AXIS);
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
		        setScaleX(0.8);
		        setScaleY(0.8);
			}
	    });
		
		this.setOnMouseExited(event -> {
			setScaleX(0.6);
			setScaleY(0.6);
		});
		
		this.setOnMousePressed(event -> {
            mouseX = event.getSceneX() ;
            mouseY = event.getSceneY() ;
            this.toFront();
        });
		
		setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mouseX ;
            double deltaY = event.getSceneY() - mouseY ;
            relocate(getLayoutX() + deltaX, getLayoutY() + deltaY);
            mouseX = event.getSceneX() ;
            mouseY = event.getSceneY() ;
         });
	}
	
	void setPosition(double x, double y) {
		setLayoutX(x);
		setLayoutY(y);
	}
	public Timeline halfRotateAnimation() {
		return new Timeline( 
                new KeyFrame(Duration.ZERO, new KeyValue(super.rotateProperty(), 0)), 
                new KeyFrame(halfFlipDuration.multiply(2), actionEvent -> { 
                    front_card.setVisible(!front_card.isVisible()); 
                    back_card.setVisible(!back_card.isVisible());
                }), 
//                new KeyFrame(halfFlipDuration.multiply(3), actionEvent -> { 
//                    front_card.setVisible(false); 
//                    back_card.setVisible(true); 
//                }), 
                new KeyFrame(halfFlipDuration.multiply(4), new KeyValue(super.rotateProperty(), 180)));
	}
	
	public Timeline fullRotateAnimation() {
		return new Timeline( 
                new KeyFrame(Duration.ZERO, new KeyValue(super.rotateProperty(), 0)), 
                new KeyFrame(halfFlipDuration.multiply(1), actionEvent -> { 
                    front_card.setVisible(true); 
                    back_card.setVisible(false);
                }), 
                new KeyFrame(halfFlipDuration.multiply(3), actionEvent -> { 
                    front_card.setVisible(false); 
                    back_card.setVisible(true); 
                }), 
                new KeyFrame(halfFlipDuration.multiply(4), new KeyValue(super.rotateProperty(), 180)));

	}

	public Timeline getRotateCard() {
		return rotateCard;
	}
	
	
}
