package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import enums.Colors;
import enums.Values;


/**
 * This class is the default class for every card in the model.
 * Each card has a color and a value determining the card.
 * 
 * 
 * @author Florent Vain
 * @version 0.2a
 * @see Colors
 * @see Values
 *
 */
public class Card implements Comparator<Card>, Comparable<Card>{
	private Colors color;
	private Values value;

	public Card(Colors c, Values v) {
		this.color = c;
		this.value = v;
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Card arg0, Card arg1) {
		
		if(arg0==null && arg1==null){
			throw new NullPointerException();
		}
		int fComp = Integer.compare(arg0.getColor().getPower(), arg1.getColor().getPower());
		if(fComp==0)
		{
			int sComp = Integer.compare(arg0.getValue().getPower(), arg1.getValue().getPower());
			return sComp;
		}
		return fComp;
	}
	
	public int compareTo(Card arg0) {
		return compare(this, arg0);
	}

	public Colors getColor() {
		return color;
	}

	public void setColor(Colors color) {
		this.color = color;
	}

	public Values getValue() {
		return value;
	}

	public void setValue(Values value) {
		this.value = value;
	}

	/**
	 * 
	 * @return give a full deck of cards in a List
	 */
	public static List<Card> createDeck() {
		List<Card> list = new ArrayList<Card>();

		Colors[] pomme = { Colors.CLUBS, Colors.DIAMONDS, Colors.HEARTS, Colors.SPADES };

		for (int i = 0; i < 4; i++) {
			list.add(new Card(pomme[i], Values.ACE));
			list.add(new Card(pomme[i], Values.TWO));
			list.add(new Card(pomme[i], Values.THREE));
			list.add(new Card(pomme[i], Values.FOUR));
			list.add(new Card(pomme[i], Values.FIVE));
			list.add(new Card(pomme[i], Values.SIX));
			list.add(new Card(pomme[i], Values.SEVEN));
			list.add(new Card(pomme[i], Values.EIGHT));
			list.add(new Card(pomme[i], Values.NINE));
			list.add(new Card(pomme[i], Values.TEN));
			list.add(new Card(pomme[i], Values.KNIGHT));
			list.add(new Card(pomme[i], Values.JACK));
			list.add(new Card(pomme[i], Values.QUEEN));
			list.add(new Card(pomme[i], Values.KING));
		}

		list.add(new Card(Colors.TRUMPS, Values.ONE));
		list.add(new Card(Colors.TRUMPS, Values.TWO));
		list.add(new Card(Colors.TRUMPS, Values.THREE));
		list.add(new Card(Colors.TRUMPS, Values.FOUR));
		list.add(new Card(Colors.TRUMPS, Values.FIVE));
		list.add(new Card(Colors.TRUMPS, Values.SIX));
		list.add(new Card(Colors.TRUMPS, Values.SEVEN));
		list.add(new Card(Colors.TRUMPS, Values.EIGHT));
		list.add(new Card(Colors.TRUMPS, Values.NINE));
		list.add(new Card(Colors.TRUMPS, Values.TEN));
		list.add(new Card(Colors.TRUMPS, Values.ELEVEN));
		list.add(new Card(Colors.TRUMPS, Values.TWELVE));
		list.add(new Card(Colors.TRUMPS, Values.THIRTEEN));
		list.add(new Card(Colors.TRUMPS, Values.FORTEEN));
		list.add(new Card(Colors.TRUMPS, Values.FIFTEEN));
		list.add(new Card(Colors.TRUMPS, Values.SIXTEEN));
		list.add(new Card(Colors.TRUMPS, Values.SEVENTEEN));
		list.add(new Card(Colors.TRUMPS, Values.EIGHTEEN));
		list.add(new Card(Colors.TRUMPS, Values.NINETEEN));
		list.add(new Card(Colors.TRUMPS, Values.TWENTY));
		list.add(new Card(Colors.TRUMPS, Values.TWENTYONE));
		
		list.add(new Card(Colors.TRUMPS, Values.FOOL));
		
		return list;
	}
}