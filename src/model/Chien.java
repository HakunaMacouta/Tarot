package model;

import java.util.ArrayList;
import java.util.List;

import Enums.Colors;
import Enums.Values;

public class Chien {
	private List<Card> maw;
	
	public Chien(){
		maw = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		maw.add(card);
	}
	
	public Card getCard(Colors c, Values v) throws NotInHandException{
		Card card = new Card(c,v);
		boolean found =false;
		for(int i = 0; i<maw.size(); i++)
		{
			if(maw.get(i).getColor()==card.getColor() && maw.get(i).getValue()==card.getValue()){
				maw.remove(i);
				found = true;
			}	
		}
		if(!found)
			throw new NotInHandException();
		return card;
	}
	
	public List<Card> getMaw(){
		return maw;
	}

}
