package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Enums.Colors;
import Enums.Values;

public class Joueur{
	private List<Card> hand;
	
	public Joueur(){
		hand = new ArrayList<Card>();
	}
	
	public Card getCard(Colors c, Values v) throws NotInHandException{
		//Create a new card
		Card card = new Card(c,v);
		boolean found =false;
		for(int i = 0; i<hand.size(); i++)
		{
			if(hand.get(i).getColor()==card.getColor() && hand.get(i).getValue()==card.getValue()){
				hand.remove(i);
				found = true;
			}
		}
		if(!found)
			throw new NotInHandException();
		return card;
	}
	
	public void addCard(Card c){
		hand.add(c);
	}
	
	public List<Card> getHand(){
		return hand;
	}
	
	public boolean isSmallDry(){
		boolean hasSmall=false, isDry=false, end=false;
		int i = 0;
		
		while(i<hand.size() && !end){
			if(hand.get(i).getColor()==Colors.TRUMPS && hand.get(i).getValue()==Values.ONE && !hasSmall){
				hasSmall = isDry = true;
			}
			else if(hand.get(i).getColor()==Colors.TRUMPS){
				isDry = false;
				end = true;
			}
			i++;
		}
		
		return isDry;
	}

	public void sortHand() {
		Collections.sort(hand);
	}
}