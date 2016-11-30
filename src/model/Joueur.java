package model;

import java.util.ArrayList;
import java.util.List;

import vue.javafx.Colors;
import vue.javafx.Values;

public class Joueur{
	private List<Card> hand;
	
	public Joueur(){
		hand = new ArrayList<Card>();
	}
	
	public Card getCard(Values v, Colors c) throws NotInHandException{
		//Create a new card
		Card card = new Card(c,v);
		if(!hand.remove(card))
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
}