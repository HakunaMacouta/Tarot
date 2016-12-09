package vue.javafx;

import java.io.File;
import java.util.HashMap;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class MainMenu extends Scene {
	
	private String eestr = new String();
	
	private HashMap<String, MediaPlayer> easterEggs = new HashMap<String, MediaPlayer>();
	
	private Stage fenetre;
	
	private final static Pane menuPane = new Pane();
	private final TarotButton playButton = new TarotButton("Jouer", true);
	private final TarotButton quitButton = new TarotButton("Quitter", true);
	
	private Media sound = new Media(new File("./ressources/song.mp3").toURI().toString());
	private MediaPlayer mediaPlayer = new MediaPlayer(sound);
	
	/* EASTER EGGS SOUNDS */
	private Media ee_ce = new Media(new File("./ressources/ee_ce.mp3").toURI().toString());
	private MediaPlayer ce_ee_mp = new MediaPlayer(ee_ce);
	private Media ee_loup = new Media(new File("./ressources/ee_loup.mp3").toURI().toString());
	private MediaPlayer loup_ee_mp = new MediaPlayer(ee_loup);
	private Media ee_s1b = new Media(new File("./ressources/ee_s1b.mp3").toURI().toString());
	private MediaPlayer s1b_ee_mp = new MediaPlayer(ee_s1b);
	private Media ee_antho = new Media(new File("./ressources/ee_anthony.mp3").toURI().toString());
	private MediaPlayer antho_ee_mp = new MediaPlayer(ee_antho);
	
	/* LOGO AND TEXT */
	private static Image logo_img = new Image("file:./ressources/Dishonored_Logo.png");
	private ImageView logo = new ImageView(logo_img);
	private static Image sbttl_img = new Image("file:./ressources/sstitre.png");
	private ImageView sbttl = new ImageView(sbttl_img);
	
	private static BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
	private static BackgroundImage biBG1 = new BackgroundImage(new Image("file:./ressources/bg2.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	private static BackgroundImage biBG2 = new BackgroundImage(new Image("file:./ressources/bg3.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	private Background bg = new Background(biBG1);
	
	public MainMenu(Stage fenetre){
		super(menuPane,1600,900);
		this.fenetre = fenetre;
		initialize();		
	}

	/**
	 * 
	 */
	private void initialize() {
		logo.setLayoutY(25);
		logo.setLayoutX(40);
		sbttl.setScaleX(0.5);
		sbttl.setScaleY(0.5);
		sbttl.setLayoutX(-80);
		sbttl.setLayoutY(75);
		menuPane.setBackground(bg);
		playButton.setLayoutY(300);
		playButton.setLayoutX(150);
		playButton.setScaleY(2);
		playButton.setScaleX(2);
		quitButton.setLayoutY(300);
		quitButton.setLayoutX(350);
		quitButton.setScaleY(2);
		quitButton.setScaleX(2);
		menuPane.getChildren().addAll(playButton, quitButton, logo, sbttl);
		mediaPlayer.setVolume(0.3);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();
		
		intializingEvents();
		initializeEE();
	}

	private void initializeEE() {
		easterEggs.put("ALICIA", ce_ee_mp);
		easterEggs.put("LOUP", loup_ee_mp);
		easterEggs.put("SNUMPAD1B", s1b_ee_mp);
		easterEggs.put("SDIGIT1B", s1b_ee_mp);
		easterEggs.put("ANTHONY", antho_ee_mp);
	}
	/**
	 * 
	 */
	private void intializingEvents() {
		//ON CLICK EVENT PLAY BUTTON
		playButton.setOnAction(event -> {
        	boolean fc = fenetre.isFullScreen();
			//playButton.playClickSound();
        	fenetre.setScene(MainJavaFX.scenes.get(MainJavaFX.GAME_INDEX));
        	if(fc)
        			fenetre.setFullScreen(true);
        	fenetre.show();
        	mediaPlayer.stop();
			});

        //ON CLICK EVENT QUIT BUTTON
        quitButton.setOnAction(event -> {
        	fenetre.close();
        });
        
        this.setOnKeyPressed(event -> {
        	if(event.getCode()==KeyCode.ENTER && !eestr.isEmpty()){
        		if(easterEggs.containsKey(eestr)){
        			easterEggs.get(eestr).stop();
        			easterEggs.get(eestr).play();
        		}
        		eestr = new String();
        	}
        	else{
        		eestr += event.getCode().toString();
        		System.err.println(eestr);
        	}
        });
	}
	
	public void reset() {
		initialize();
	}

}
