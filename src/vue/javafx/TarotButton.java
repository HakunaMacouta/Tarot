package vue.javafx;

import java.io.File;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TarotButton extends Button {
	
	private static final Media soundButtonClick = new Media(new File("./ressources/buttonClic.mp3").toURI().toString());
	public static MediaPlayer mediaPlayerButtonClick = new MediaPlayer(soundButtonClick);
	
	private static final Media soundButtonHover = new Media(new File("./ressources/buttonHover.mp3").toURI().toString());
	public static MediaPlayer mediaPlayerButtonHover = new MediaPlayer(soundButtonHover);
	
	
	private static final String buttonMenuStyle = "-fx-padding: 5 22 5 22;-fx-border-color: #101010;-fx-border-width: 1;-fx-background-radius: 0;   -fx-background-color: #1d1d1d;       -fx-font-family: \"Calibri\", Helvetica, Arial, sans-serif;    -fx-font-size: 13pt;    -fx-text-fill: #d8d8d8;";
	private static final String buttonMenuStyle_Hover = "-fx-padding: 5 22 5 22;-fx-border-color: #101010;-fx-border-width: 1;-fx-background-radius: 0;   -fx-background-color: #454545;       -fx-font-family: \"Calibri\", Helvetica, Arial, sans-serif;    -fx-font-size: 13pt;    -fx-text-fill: #d8d8d8;";
	
	private boolean isMenuButton=false;

	public TarotButton() {
		super();
		mediaPlayerButtonClick.setVolume(0.8);
		mediaPlayerButtonHover.setVolume(0.2);
    	setStyle(buttonMenuStyle);
    	mediaPlayerButtonHover.setAutoPlay(true);
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

	public TarotButton(String arg0) {
		super(arg0);

		mediaPlayerButtonClick.setVolume(0.8);
		mediaPlayerButtonHover.setVolume(0.2);
    	setStyle(buttonMenuStyle);
		
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

	public TarotButton(String arg0, Node arg1) {
		super(arg0, arg1);
		
		mediaPlayerButtonClick.setVolume(0.8);
		mediaPlayerButtonHover.setVolume(0.2);
    	setStyle(buttonMenuStyle);
		
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

	public TarotButton(String string, boolean b) {
		super(string);
		isMenuButton=b;
		mediaPlayerButtonClick.setVolume(0.8);
		mediaPlayerButtonHover.setVolume(0.2);
    	setStyle(buttonMenuStyle);
		
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

	public void playClickSound() {
		if(isMenuButton)
			mediaPlayerButtonClick.play();		
	}
	
	public void playHoverSound(){
		mediaPlayerButtonHover.stop();
		mediaPlayerButtonHover.play();
	}
	

}
