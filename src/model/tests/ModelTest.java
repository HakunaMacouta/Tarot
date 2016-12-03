package model.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Model;

public class ModelTest {

	@Test
	public void testDistribution_Chien() {
		Model m = new Model();

		m.distribution();

		if(m.getDog().getMaw().isEmpty()){
			fail("Aucune carte dans le chien");
		}

		if(m.getDog().getMaw().size() != 6){
			fail("Nombre de carte incorrecte dans le chien : " + m.getDog().getMaw().size());
		}
	}

	@Test
	public void testDistribution_Joueur() {
		Model m = new Model();

		m.distribution();

		for(int i=0;i<m.getPlayers().size();i++){
			if(m.getPlayer(i).getHand().isEmpty()){
				fail("Main du joueur " + i + " vide");
			}
			if(m.getPlayer(i).getHand().size() != 18){
				fail("Nombre de carte incorrecte Joueur " + i);
			}
		}
	}

}
