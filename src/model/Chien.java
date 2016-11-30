package model;

import java.util.ArrayList;
import java.util.List;

import vue.javafx.Colors;
import vue.javafx.Values;

public class Chien {
	private List<Card> maw;
	
	public Chien(){
		maw = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		maw.add(card);
	}
	
	public Card getCard(Colors c, Values v) throws NotInHandException{
		Card card = new Card(c, v);
		if(maw.remove(card))
			return card;
		else
			throw new NotInHandException();
	}
	
	public List<Card> getMaw(){
		return maw;
	}

}
