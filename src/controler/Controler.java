package controler;

import model.Model;

public class Controler {
	private Model model;
	public static Controler activeControler;
	private static boolean isActive=false;
	
	
	public Controler(Model m){
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
			activeControler = this;
		}
	}
	
	public Model getModel(){
		return model;
	}
	
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
