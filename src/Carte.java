import javafx.scene.image.*;


public class Carte {
	Values value;
	Colors color;
	Image img;
	ImageView card;
	
	Carte(Values v, Colors c) {
		this.value = v;
		this.color = c;
		img = new Image("file:./ressources/Tarot_nouveau_-_Grimaud_-_1898_-_"+color.toString()+"_-_"+value.toString()+".jpg");
		card = new ImageView();
		card.setImage(img);
	}
	
	
}
