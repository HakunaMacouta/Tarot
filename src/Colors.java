
public enum Colors {
	Spades ("Spades"),
	Clubs ("Clubs"),
	Diamonds ("Diamonds"),
	Hearts("Hearts"),
	Trumps ("Trumps");
	
	private String name;
	
	Colors(String name) {
		this.name= name;
	}

	public String toString() {
		return this.name;
	}
}
