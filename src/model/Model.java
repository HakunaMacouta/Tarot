package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Model {

	private ArrayList<Joueur> players;
	public static final int INDEX_OF_PLAYER = 0;
	private Deck deck;
	private Chien dog;

	public Model(){
		players = new ArrayList<Joueur>();

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
		int i = 0, k=0, cpt=0;

		// On choisi un tirage aléatoire pour le chien
		Set<Integer> tChien = randomTurnDog();
		// On realise la distribution
		while(!deck.isEmpty()){
			// Si c'est le tour du chien on lui donne une cartes
			if(tChien.contains(cpt)){
				dog.addCard(deck.getCard());
			}
			else 
			{
				// On donne au joueur
				players.get(i).addCard(deck.getCard());
				k++;
				// Changement de joueur toutes les trois cartes
				if(k%3==0){
					i = (i+1)%4;
				}
			}
			//carte suivante
			cpt++;
		}
	}

	/**
	 * @return
	 */
	private Set<Integer> randomTurnDog() {
		int k;
		Random r = new Random();
		Set<Integer> tChien= new HashSet<Integer>();
		for(k=0;k<6;k++){
			while(!tChien.add(r.nextInt(70)+1));
		}
		return tChien;
	}

	public boolean isSmalDry(){
		int i=0;
		boolean stop=false;
		while(i<players.size() && !stop){
			stop = players.get(i).isSmallDry();
			i++;
		}
		return stop;
		
	}
}
