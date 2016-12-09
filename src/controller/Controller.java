package controller;

import model.Model;

/**
 * Class Controller de l'architecture MCD.
 * Permet de lancer les actions utilisateurs.
 * @author Thomas
 *
 */
public class Controller {
	private Model model;
	public static Controller activeController;
	private static boolean isActive=false;
	
	/**
	 * Constructeur du controller. 
	 * @param m
	 * Modele, permet d'appliquer des méthodes de celui ci.
	 * 
	 */
	public Controller(Model m){
		if(isActive){
			try {
				this.finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} else
		{
			this.model = m;
			isActive= true;
			activeController = this;
		}
	}
	/**
	 * 
	 * @return le modele. 
	 */
	public Model getModel(){
		return model;
	}
	
	/**
	 * 
	 * @param ac
	 * Enum Action, permet les différentes actions utilisateur.
	 * @return 
	 * booleen, vrai si le petit est sec, faux sinon.
	 */
	public boolean performAction(Action ac){
		boolean returned=true;
		switch(ac){
		case SHUFFLE_DECK:
			model.getDeck().shuffle();
			break;
		case SORT_PLAYER_HAND:
			model.getPlayer(Model.INDEX_OF_PLAYER).sortHand();
			break;
		case START_DISTRUBUTION:
			model.distribution();
			returned = model.isSmallDry();
			break;
		case PRISE_OU_GARDE:
			model.addMawToPlayerHand();
		    break;
		default:
			break;
		}
		return returned;
	}
}
