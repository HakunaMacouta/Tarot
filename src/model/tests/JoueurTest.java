/* VAIN BLANC S3C */
package model.tests;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import Enums.Colors;
import Enums.Values;
import model.Card;
import model.Joueur;
import model.NotInHandException;

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
			fail("Nombre de carte incorrect apr�s retrait");
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
	
	
	@Test
	public void testHandUnicity(){
		Joueur j = new Joueur();
		
		j.addCard(new Card(Colors.HEARTS, Values.THREE));
		j.addCard(new Card(Colors.HEARTS, Values.FOUR));
		j.addCard(new Card(Colors.SPADES, Values.ACE));
		j.addCard(new Card(Colors.SPADES, Values.KING));
		j.addCard(new Card(Colors.DIAMONDS, Values.KING));
		j.addCard(new Card(Colors.TRUMPS, Values.EIGHT));
		j.addCard(new Card(Colors.TRUMPS, Values.THREE));
		j.addCard(new Card(Colors.TRUMPS, Values.FIVE));
		j.addCard(new Card(Colors.TRUMPS, Values.FOOL));
		
		
		List<Card> k = j.getHand();
		Card c = new Card(Colors.TRUMPS, Values.ONE);
		
		for(int i=0;i<k.size();i++){
			for(int q=0;q<k.size();q++){
				if(i!=q){
					if(c.compare(k.get(i), k.get(q))==0){
						fail("Carte existe en double");
					}
				}
			}
		}
		
	}

}
