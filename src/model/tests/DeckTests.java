package model.tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

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
	public void testIsEmpty() {
		Deck d = new Deck();
		d.getCards().clear();
		
		if(d.isEmpty() == false)
		{
			fail();
		}
		
	}

}
