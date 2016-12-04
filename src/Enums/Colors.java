package Enums;
public enum Colors {
	CLUBS ("rats",1),
	DIAMONDS ("swords",2),
	HEARTS("skulls",3),
	SPADES ("pistols",4),
	TRUMPS("Trumps",5);
	
	private String name;
	private int power;
	
	Colors(String name, int power) {
		this.name = name;
		this.power = power;
	}
	
	public int getPower(){
		return power;
	}

	public String toString() {
		return this.name;
	}
	
}
