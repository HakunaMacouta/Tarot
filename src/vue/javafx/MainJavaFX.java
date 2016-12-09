package vue.javafx;
import java.util.ArrayList;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Model;

public class MainJavaFX extends Application {

	Controller controler;
	boolean inMenu=true;
	
	public static ArrayList<Scene> scenes;
	
	public static int MAIN_MENU_INDEX=0;
	public static int GAME_INDEX=1;
	
	
	@Override
	public void start(Stage fenetre) throws Exception {
		
		scenes=new ArrayList<Scene>();
		fenetre.setResizable(false);
		controler = Controller.activeController;
		
		
		/* Menu Principal  */
		
		final MainMenu menuScene = new MainMenu(fenetre);
		scenes.add(menuScene);
		
		/* FIN MENU PRINCIPAL */
		
		final GameScene gameScene = new GameScene(fenetre);
		scenes.add(gameScene);
		
		
		fenetre.setTitle("Dishonored Tarot");
        fenetre.setScene(menuScene);
        fenetre.getIcons().add(new Image("file:./ressources/Tarot_faticon.png"));
        fenetre.show();
        
	}     

	public static void main(String[] args) {
		Model m = new Model();
		Controller c = new Controller(m);
		launch(args);
	}
}
