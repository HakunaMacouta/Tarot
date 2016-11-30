package vue.javafx;

public enum Colors {
	SPADES ("Spades"),
	CLUBS ("Clubs"),
	DIAMONDS ("Diamonds"),
	HEARTS("Hearts"),
	TRUMPS("Trumps");
	
	private String name;
	
	Colors(String name) {
		this.name= name;
	}

	public String toString() {
		return this.name;
	}
}
