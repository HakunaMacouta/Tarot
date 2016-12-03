package vue.javafx;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
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
	
	private final static Duration halfFlipDuration = Duration.seconds(0.1);
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
		setScaleX(0.4);
		setScaleY(0.4);

		front_card = new ImageView();
		front_card.setImage(new Image("file:./ressources/Tarot_"+color.toString()+"_"+value.toString()+".png"));
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
                new KeyFrame(Duration.ZERO, new KeyValue(super.rotateProperty(), -20)), 
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
