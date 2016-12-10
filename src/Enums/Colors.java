/* VAIN BLANC S3C */
package Enums;
/**
 * 
 * Enum représentant les couleurs des cartes.
 * @author Thomas
 *
 */
public enum Colors {
	CLUBS ("rats",1),
	DIAMONDS ("swords",5),
	HEARTS("skulls",4),
	SPADES ("pistols",2),
	TRUMPS("Trumps",3);
	
	private String name;
	private int power;
	
	/**
	 * Constructeur de l'enum, il affecte les attributs de l'enum.
	 * @param name
	 * String permettant le chargement du fichier dans la classe @see CardFX
	 * @param power
	 * Int représentant la force de la carte dans le jeu de Tarot. Utile dans le tri, @see Model
	 */
	Colors(String name, int power) {
		this.name = name;
		this.power = power;
	}
	/**
	 * 
	 * @return retourne l'attribut power correspondant a la valeur de l'enum.
	 */
	public int getPower(){
		return power;
	}
	/**
	 * @return retourne l'attribut name correspondant a une partie du nom du fichier.
	 */
	public String toString() {
		return this.name;
	}
	
}
