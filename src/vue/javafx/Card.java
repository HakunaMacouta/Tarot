package vue.javafx;
import javafx.scene.image.*;

/**
 * 
 * @author Thomas
 *
 */
public class Card extends ImageView {
	public Image img_back = new Image("file:./ressources/Tarot_back");
	private Image img_front;
	private Values value;
	private Colors color;
		
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
		img_front = new Image("file:./ressources/"+color.toString()+"_"+value.toString()+".png");
		setImage(img_front);
		setCache(true);
	}
	
	void setPosition(double x, double y) {
		setX(x);
		setY(y);
	}
}
