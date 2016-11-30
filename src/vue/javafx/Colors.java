package vue.javafx;

public enum Colors {
	SPADES ("pistols"),
	CLUBS ("rats"),
	DIAMONDS ("swords"),
	HEARTS("skulls"),
	TRUMPS("Trumps");
	
	private String name;
	
	Colors(String name) {
		this.name= name;
	}

	public String toString() {
		return this.name;
	}
}
