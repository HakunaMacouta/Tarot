package model.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import model.Card;
import model.Joueur;
import model.NotInHandException;
import vue.javafx.Colors;
import vue.javafx.Values;

public class JoueurTest {

	@Test
	public void testGetCard() {
		Joueur j = new Joueur();
		
		j.addCard(new Card(Colors.CLUBS, Values.ACE));
		j.addCard(new Card(Colors.HEARTS, Values.THREE));
		j.addCard(new Card(Colors.TRUMPS, Values.NINETEEN));
		int initSize = j.getHand().size();
		
		try {
			j.getCard(Colors.TRUMPS, Values.NINETEEN);
		} catch (NotInHandException e) {
			fail("Carte pas dans la main");
		}
		int finalSize = j.getHand().size();
		
		if(finalSize>=initSize){
			fail("Nombre de carte incorrect après retrait");
		}
		
	}

	@Test
	public void testAddCard() {
		Joueur j = new Joueur();
		
		int initSize = j.getHand().size();
		if(initSize != 0)
			fail();
		
		j.addCard(new Card(Colors.CLUBS, Values.ACE));
		int finalSize = j.getHand().size();
		
		if(initSize+1 != finalSize){
			fail();
		}
		
	}

	@Test
	public void testGetHand() {
		Joueur j = new Joueur();
		
		j.addCard(new Card(Colors.CLUBS, Values.FIVE));
		
		List<Card> cards = j.getHand();
		
		if(!cards.equals(j.getHand())){
			fail();
		}
	}

	@Test
	public void testIsSmallDry() {
		Joueur j = new Joueur();
		
		j.addCard(new Card(Colors.TRUMPS, Values.ELEVEN));
		j.addCard(new Card(Colors.TRUMPS, Values.ONE));
		j.addCard(new Card(Colors.TRUMPS, Values.SIX));
		j.addCard(new Card(Colors.DIAMONDS, Values.EIGHT));
		j.addCard(new Card(Colors.DIAMONDS, Values.THREE));
		
		if(j.isSmallDry())
			fail();
		
		j = new Joueur();
		
		j.addCard(new Card(Colors.HEARTS, Values.THREE));
		j.addCard(new Card(Colors.DIAMONDS, Values.FOUR));
		j.addCard(new Card(Colors.CLUBS, Values.KING));
		j.addCard(new Card(Colors.DIAMONDS, Values.NINE));
		j.addCard(new Card(Colors.TRUMPS, Values.ONE));
		
		if(!j.isSmallDry())
			fail();
		
	}

}
