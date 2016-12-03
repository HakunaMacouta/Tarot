package controler;

import model.Model;

public class Controler {
	private Model model;
	
	
	public Controler(Model m){
		this.model = m;
	}
	
	public Model getModel(){
		return model;
	}
	
	public void performAction(Action ac){
		switch(ac){
		case SHUFFLE_DECK:
			model.getDeck().shuffle();
			break;
		case SORT_PLAYER_HAND:
			model.getPlayer(Model.INDEX_OF_PLAYER).sortHand();
			break;
		case START_DISTRUBUTION:
			model.distribution();
			break;
		default:
			break;
		}
	}
}
