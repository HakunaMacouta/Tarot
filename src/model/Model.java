package model;

import java.util.ArrayList;
import java.util.Random;

public class Model {
	
	private ArrayList<Joueur> players;
	private final int INDEX_OF_PLAYER = 0;
	private Deck deck;
	private Chien dog;
	
	public Model(){
		
		for(int i=0;i<4;i++)
			players.add(new Joueur());
		
		deck = new Deck();
		deck.shuffle();
		dog = new Chien();
	    
	}
	
	public void distribution(){
		int i = 0, cpt=0, cptChien=0;
		Random r = new Random();
		ArrayList<Integer> tChien= new ArrayList<Integer>();
		for(int k=0;k<6;k++){
			tChien.add(r.nextInt(70)+1);
		}
		while(!deck.isEmpty()){
			if(cpt==tChien.get(cptChien)){
				dog.addCard(deck.getCard());
			}
			players.get(i).addCard(deck.getCard());
			i = (i+1)%4;
			cpt++;
		}
	}

}
