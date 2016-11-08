package vue.javafx;
import javafx.scene.image.*;


public class Card {
	private static Image img_back = new Image("file:./ressources/back_card.jpg");
	private static ImageView card_back = new ImageView();
	private Values value;
	private Colors color;
	private Image img_front;
	private ImageView card_front;
	
	public static ImageView getCard_back() {
		return card_back;
	}

	public static void setCard_back(ImageView card_back) {
		Card.card_back = card_back;
	}

	public ImageView getCard_front() {
		return card_front;
	}

	public void setCard_front(ImageView card_front) {
		this.card_front = card_front;
	}

	Card(Values v, Colors c) {
		this.value = v;
		this.color = c;
		img_front = new Image("file:./ressources/Tarot_nouveau_-_Grimaud_-_1898_-_"+color.toString()+"_-_"+value.toString()+".jpg");
		card_front = new ImageView();
		card_front.setImage(img_front);
		card_back.setImage(img_back);
	}
	
	Card(int x) {
		
	}
	
	
}
