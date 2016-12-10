/* VAIN BLANC S3C */
package model.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Enums.Colors;
import Enums.Values;
import model.Card;
import model.Chien;
import model.NotInHandException;

public class ChienTest {

	@Test
	public void testAddCard() {
		Chien d = new Chien();
		
		int initSize = d.getMaw().size();
		
		d.addCard(new Card(Colors.TRUMPS, Values.ELEVEN));
		
		int finalSize = d.getMaw().size();
		
		if(initSize == finalSize){
			fail();
		}
	}

	@Test
	public void testGetCard() {
		Chien j = new Chien();
		
		j.addCard(new Card(Colors.CLUBS, Values.ACE));
		j.addCard(new Card(Colors.HEARTS, Values.THREE));
		j.addCard(new Card(Colors.TRUMPS, Values.NINETEEN));
		int initSize = j.getMaw().size();
		
		try {
			j.getCard(Colors.TRUMPS, Values.NINETEEN);
		} catch (NotInHandException e) {
			fail("Carte pas dans la main");
		}
		int finalSize = j.getMaw().size();
		
		if(finalSize>=initSize){
			fail("Nombre de carte incorrect après retrait");
		}
		
	}

	@Test
	public void testGetMaw() {
		Chien j = new Chien();
		
		j.addCard(new Card(Colors.CLUBS, Values.FIVE));
		
		List<Card> cards = j.getMaw();
		
		if(!cards.equals(j.getMaw())){
			fail();
		}
	}

}
