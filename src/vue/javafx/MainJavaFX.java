package vue.javafx;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainJavaFX extends Application {

	private ParallelTransition parallelTransition;
	private ScaleTransition scaleTransition;	
	private RotateTransition rotateTransition;	
	private TranslateTransition translateTransition;	

	
	public Transition flipCard(Card c) {
		RotateTransition ro = new RotateTransition(Duration.seconds(5),c);
		ro.setAxis(new Point3D(0,100,0));
		ro.setFromAngle(0);
		ro.setToAngle(90);
		c.setImage(c.img_back);
		RotateTransition ru = new RotateTransition(Duration.seconds(5),c);
		ru.setAxis(new Point3D(0,100,0));
		ru.setFromAngle(-90);
		ru.setToAngle(0);
		return new SequentialTransition(ro,ru);
	}
	@Override
	public void start(Stage fenetre) throws Exception {
		// objet graphique : carte 21
		Card normal = new Card(Values.ONE, Colors.TRUMPS,
				50,0);
		
		/****EXEMPLE****/
//		Image image21 = new Image("file:./ressources/Tarot_nouveau_-_Grimaud_-_1898_-_Trumps_-_21.jpg");
//		ImageView carte21 = new ImageView();
//		carte21.setImage(image21);
//		carte21.setX(-400);
//		carte21.setY(-1000);
//		carte21.setRotate(-10);
//		carte21.setCache(true);

		// scene graphique
		fenetre.setTitle("Let's play Tarot !");
		Group cartes = new Group();
		Scene plateau = new Scene(cartes,1024,768);
		plateau.setFill(Color.BLACK);
		//cartes.getChildren().add(trump);
		cartes.getChildren().add(normal);
		//cartes.getChildren().add(prout);
		fenetre.setScene(plateau); 
		fenetre.sizeToScene(); 
		fenetre.show(); 

		// animation 1 : zoom (toutes les cartes)
//		scaleTransition =
//				new ScaleTransition(Duration.seconds(4), cartes);
//		scaleTransition.setFromX(0.1);
//		scaleTransition.setFromY(0.1);
//		scaleTransition.setToX(0.1);
//		scaleTransition.setToY(0.1);
		//scaleTransition.setAutoReverse(true);
		//scaleTransition.setCy                                     cleCount(Timeline.INDEFINITE);
		// animation 2 : rotation (toutes les cartes)
		rotateTransition = new RotateTransition(Duration.seconds(5), normal);
		rotateTransition.setAxis(new Point3D(0,100,0));
		rotateTransition.setFromAngle(0);
		rotateTransition.setToAngle(90);
		//prout.setImage(prout.img_back);
		//rotateTransition.setToAngle(180);
		//rotateTransition.pause();
		//rotateTransition.setAutoReverse(true);
		//rotateTransition.setCycleCount(Timeline.INDEFINITE);
		// animation 3 : translation (carte Petit)
//		translateTransition = new TranslateTransition(Duration.seconds(1), prout);
//		translateTransition.setFromZ(200);
//		translateTransition.setToZ(-200);
//		translateTransition.setAutoReverse(true);
//		translateTransition.setCycleCount(Timeline.INDEFINITE);
		
		// animation globale: toutes en //
		parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(
				//rotateTransition
//				scaleTransition
//				translateTransition
				//flipCard(normal)
				); 
		//parallelTransition.setCycleCount(Timeline.INDEFINITE);
//
//		// go !
		parallelTransition.play();
	}     

	public static void main(String[] args) {
		launch(args);
	}

}
