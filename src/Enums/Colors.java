package Enums;
public enum Colors {
	CLUBS ("rats",1),
	DIAMONDS ("swords",4),
	HEARTS("skulls",2),
	SPADES ("pistols",5),
	TRUMPS("Trumps",3);
	
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
