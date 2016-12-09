package enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum de Valeur pouvant être prise par les cartes de 1 à 21 et les têtes.
 * @author Thomas
 *
 */
public enum Values {
	ONE ("1",1),
	TWO ("2",2),
	THREE ("3",3),
	FOUR ("4",4),
	FIVE ("5",5),
	SIX ("6",6),
	SEVEN ("7",7),
	EIGHT ("8",8),
	NINE ("9",9),
	TEN ("10",10),
	ELEVEN("11",11),
	TWELVE("12",12),
	THIRTEEN("13",13),
	FORTEEN("14",14),
	FIFTEEN("15",15),
	SIXTEEN("16",16),
	SEVENTEEN("17",17),
	EIGHTEEN("18",18),
	NINETEEN("19",19),
	TWENTY("20",20),
	TWENTYONE("21",21),
	FOOL("fool",1000),
	JACK ("jack",11),
	KNIGHT ("commoner",12),
	QUEEN ("queen",13),
	KING ("king",14),
	ACE ("1",1);
	
	
	private String name;
	private int power;
	
	/**
	 * Constructeur de Values, affecte les deux attributs de l'enum.
	 * @param name
	 * Permet de définir la dénomination de cette valeur afin de lire les fichiers.
	 * @param power
	 */
	Values(String name, int power) {
		this.name = name;
		this.power = power;
	}
	Values(String name, String trumps) {
		
	}
	/**
	 * @return retourne l'attribut name correspondant a une partie du nom du fichier.
	 */
	public String toString() {
		return this.name;
	}
	/**
	 * @return retourne l'attribut power correspondant a la valeur de l'enum.
	 */
	public int getPower(){
		return power;
	}
	/**
	 * @param b, booleen qui permet de définir à l'appel si la fonction retourne les valeurs d'atouts ou de couleurs
	 * @return ArrayList<CardFX> contenant seulement les valeurs des atouts ou des couleurs.
	 */
	public static ArrayList<Values> getValues(boolean b) {
		List<Values> a = new ArrayList<Values>();
		if(b) {
			a.add(Values.ACE);
			a.add(Values.TWO);
			a.add(Values.THREE);
			a.add(Values.FOUR);
			a.add(Values.FIVE);
			a.add(Values.SIX);
			a.add(Values.SEVEN);
			a.add(Values.EIGHT);
			a.add(Values.NINE);
			a.add(Values.TEN);
			a.add(Values.JACK);
			a.add(Values.KNIGHT);
			a.add(Values.QUEEN);
			a.add(Values.KING);
			return (ArrayList<Values>) a;
		}
		else {
			a.add(Values.ONE);
			a.add(Values.TWO);
			a.add(Values.THREE);
			a.add(Values.FOUR);
			a.add(Values.FIVE);
			a.add(Values.SIX);
			a.add(Values.SEVEN);
			a.add(Values.EIGHT);
			a.add(Values.NINE);
			a.add(Values.TEN);
			a.add(Values.ELEVEN);
			a.add(Values.TWELVE);
			a.add(Values.THIRTEEN);
			a.add(Values.FORTEEN);
			a.add(Values.FIFTEEN);
			a.add(Values.SIXTEEN);
			a.add(Values.SEVENTEEN);
			a.add(Values.EIGHTEEN);
			a.add(Values.NINETEEN);
			a.add(Values.TWENTY);
			a.add(Values.TWENTYONE);
			a.add(Values.FOOL);
			return (ArrayList<Values>) a;
		}	
	}
}