/* VAIN BLANC S3C */
package model;

import java.util.Collections;
import java.util.List;

public class Deck {
 private List<Card> cards;
 
 public Deck(){
	 cards = Card.createDeck();
 }
 
 public void shuffle(){
	 Collections.shuffle(cards);
	 
 }
 
 public Card getCard(){
	 Card c = cards.get(cards.size()-1);
	 cards.remove(cards.size()-1);
	 return c;
 }
 
 public void resetDeck(){
	 cards.clear();
	 cards = Card.createDeck();
 }
 
 public List<Card> getCards(){
	 return cards;
 }

public boolean isEmpty() {
	return cards.isEmpty();
}
 
}
