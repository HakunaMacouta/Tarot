/* VAIN BLANC S3C */
package vue.javafx;

import java.io.File;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Main class for the tarot's button.
 * This buttons has special apparence for the game and some sounds effects on hover's and click's events
 * 
 * @author Florent Vain
 * @version 1.0
 * @see #javafx.scene.control.Button
 *
 */
public class TarotButton extends Button {
	
	/**
	 * On click sound effect media
	 */
	private static final Media soundButtonClick = new Media(new File("./ressources/buttonClic.mp3").toURI().toString());
	
	/**
	 * The media player associated to the On click sound effect media
	 */
	public static MediaPlayer mediaPlayerButtonClick = new MediaPlayer(soundButtonClick);
	
	/**
	 * On hover sound effect media
	 */
	private static final Media soundButtonHover = new Media(new File("./ressources/buttonHover.mp3").toURI().toString());
	
	/**
	 * The media player associated to the On hover sound effect media
	 */
	public static MediaPlayer mediaPlayerButtonHover = new MediaPlayer(soundButtonHover);
	
	
	// CSS FOR THE HOVERING AND NEW STYLE
	private static final String buttonMenuStyle = "-fx-padding: 5 22 5 22;-fx-border-color: #101010;-fx-border-width: 1;-fx-background-radius: 0;   -fx-background-color: #1d1d1d;       -fx-font-family: \"Calibri\", Helvetica, Arial, sans-serif;    -fx-font-size: 13pt;    -fx-text-fill: #d8d8d8;";
	private static final String buttonMenuStyle_Hover = "-fx-padding: 5 22 5 22;-fx-border-color: #101010;-fx-border-width: 1;-fx-background-radius: 0;   -fx-background-color: #454545;       -fx-font-family: \"Calibri\", Helvetica, Arial, sans-serif;    -fx-font-size: 13pt;    -fx-text-fill: #d8d8d8;";
	
	private boolean isMenuButton=false;

	/**
	 * Main constructor
	 */
	public TarotButton() {
		super();
		initializing();
	}

	/**
	 * 
	 */
	private void initializing() {
		mediaPlayerButtonClick.setVolume(0.8);
		mediaPlayerButtonHover.setVolume(0.2);
    	setStyle(buttonMenuStyle);
    	
    	initilizeEvents();
	}

	/**
	 * 
	 */
	private void initilizeEvents() {
		
		setOnMouseEntered(event -> {
        	setStyle(buttonMenuStyle_Hover);
        	playHoverSound();
        });

        setOnMouseExited(event -> {
        	setStyle(buttonMenuStyle);
        });
        setOnMouseClicked(event -> {
        	playClickSound();
        });
	}

	/**
	 * Button constructor 
	 * @param arg0 Text on the button
	 */
	public TarotButton(String arg0) {
		super(arg0);

		initializing();
	}

	public TarotButton(String arg0, Node arg1) {
		super(arg0, arg1);
		
		initializing();
	}

	public TarotButton(String string, boolean b) {
		super(string);
		isMenuButton=b;
		initializing();
		
	}

	
	public void playClickSound() {
		if(isMenuButton)
			mediaPlayerButtonClick.play();		
	}
	
	public void playHoverSound(){
		mediaPlayerButtonHover.stop();
		mediaPlayerButtonHover.play();
	}
	

}
