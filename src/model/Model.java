package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Model {

	private ArrayList<Joueur> players;
	public static final int INDEX_OF_PLAYER = 0;
	private Deck deck;
	private Chien dog;
	
	private GameState state;
	private List<Integer> distribOrder;

	public Model(){
		state = GameState.NOT_INITIALIZED;
		players = new ArrayList<Joueur>();
		distribOrder = new ArrayList<Integer>();

		for(int i=0;i<4;i++)
			players.add(new Joueur());

		deck = new Deck();
		deck.shuffle();
		dog = new Chien();

	}

	public Joueur getPlayer(int id){
		return players.get(id);
	}

	public List<Joueur> getPlayers(){
		return players;
	}

	public Chien getDog(){
		return dog;
	}

	public Deck getDeck(){
		return deck;
	}

	public void distribution(){
		int i = 0, k=0, cpt=0, cptChien=0;
		boolean playerTurnDoing=true;
		Random r = new Random();
		// On choisi un tirage aléatoire pour le chien
		List<Integer> tChien = randomTurnDog();
		// On realise la distribution
		while(!deck.isEmpty()){
			// Si c'est le tour du chien on lui donne une cartes
			if(cptChien<tChien.size() && tChien.get(cptChien)<=cpt && !playerTurnDoing){
				int v = r.nextInt(2)+1;
				for(int c=0;c<v;c++){
					if(dog.getMaw().size()<6){
						dog.addCard(deck.getCard());
						distribOrder.add(-1);
						cptChien++;
						cpt++;
					}
				}
			}
			else 
			{
				playerTurnDoing=true;
				// On donne au joueur
				players.get(i).addCard(deck.getCard());
				distribOrder.add(i);
				k++;
				// Changement de joueur toutes les trois cartes
				if(k%3==0){
					i = (i+1)%4;
					playerTurnDoing=false;
				}

				//carte suivante
				cpt++;
			}
		}
	}

	public List<Integer> getDistributionOrder(){
		return distribOrder;
	}
	/**
	 * Une méthode
	 * @return Une liste de tour de chien
	 */
	private List<Integer> randomTurnDog() {
		int k;
		Random r = new Random();
		List<Integer> tChien= new ArrayList<Integer>();
		for(k=0;k<6;k++){
			while(!tChien.add(r.nextInt(70)+1));
		}
		Collections.sort(tChien);
		return tChien;
	}

	public boolean isSmallDry(){
		int i=0;
		boolean stop=false;
		while(i<players.size() && !stop){
			stop = players.get(i).isSmallDry();
			i++;
		}
		return stop;
		
	}
	
	public GameState getGameState(){
		return state;
	}
}
