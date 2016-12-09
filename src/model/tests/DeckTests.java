package model.tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import Enums.Colors;
import Enums.Values;
import model.Card;
import model.Deck;

public class DeckTests {

	@Test
	public void testShuffle() {
		
		Random r = new Random();
		final int CARD_ONE = r.nextInt(77), CARD_TWO= r.nextInt(77),CARD_THREE= r.nextInt(77);
		
		Deck k = new Deck();
		
		Card c1 = k.getCards().get(CARD_ONE);
		Card c2 = k.getCards().get(CARD_TWO);
		Card c3 = k.getCards().get(CARD_THREE);
		
		k.shuffle();
		
		if(c1 == k.getCards().get(CARD_ONE))
			if(c2 == k.getCards().get(CARD_TWO))
				if (c3 == k.getCards().get(CARD_THREE))
					fail();
	}

	@Test
	public void testGetCardSuppression() {
		
		Deck d = new Deck();
		int initSize = d.getCards().size();
		
		d.getCard();
		
		int finalSize = d.getCards().size();
		
		if(initSize != finalSize+1){
			fail();
		}
		
	}
	
	@Test
	public void testGetCardObtention() {
		
		Deck d = new Deck();
		
		Card c = d.getCard();
		
		if(c == null){
			fail();
		}
		
		
	}

	@Test
	public void testResetDeck() {
		Random r = new Random();
		final int CARD_ONE = r.nextInt(77), CARD_TWO= r.nextInt(77),CARD_THREE= r.nextInt(77);
		
		Deck k = new Deck();
		
		Card c1 = k.getCards().get(CARD_ONE);
		Card c2 = k.getCards().get(CARD_TWO);
		Card c3 = k.getCards().get(CARD_THREE);
		
		k.resetDeck();
		
		if(c1 == k.getCards().get(CARD_ONE))
			if(c2 == k.getCards().get(CARD_TWO))
				if (c3 == k.getCards().get(CARD_THREE))
					fail();
	}
	
	@Test
	public void testDeckUnicity(){
		Deck k = new Deck();
		Card c = new Card(Colors.TRUMPS,Values.FOOL);
		
		for(int i=0;i<k.getCards().size();i++){
			for(int j=0;j<k.getCards().size();j++){
				if(i!=j){
					if(c.compare(k.getCards().get(i), k.getCards().get(j))==0){
						fail("Carte existe en double");
					}
				}
			}
		}
	}

	@Test
	public void testIsEmpty() {
		Deck d = new Deck();
		d.getCards().clear();
		
		if(d.isEmpty() == false)
		{
			fail();
		}
		
	}

}
