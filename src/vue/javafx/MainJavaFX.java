package vue.javafx;
import java.io.File;
import java.util.ArrayList;

import Enums.Colors;
import Enums.Values;
import controler.Action;
import controler.Controler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.chart.Axis;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Model;

public class MainJavaFX extends Application {

	Controler controler;
	boolean inMenu=true;
	
	public static ArrayList<Scene> scenes;
	
	public static int MAIN_MENU_INDEX=0;
	public static int GAME_INDEX=1;
	
	
	@Override
	public void start(Stage fenetre) throws Exception {
		
		scenes=new ArrayList<Scene>();
		fenetre.setFullScreen(true);
		fenetre.setResizable(false);
		controler = Controler.activeControler;
		
		
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
		Controler c = new Controler(m);
		launch(args);
	}
}
