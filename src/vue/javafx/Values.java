package vue.javafx;

public enum Values {
	ONE ("Ace"),
	TWO ("02"),
	THREE ("03"),
	FOUR ("04"),
	FIVE ("05"),
	SIX ("06"),
	SEVEN ("07"),
	EIGHT ("08"),
	NINE ("09"),
	TEN ("10"),
	ELEVEN("11"),
	TWELVE("12"),
	THIRTEEN("13"),
	FORTEEN("14"),
	FIFTEEN("15"),
	SIXTEEN("16"),
	SEVENTEEN("17"),
	EIGHTEEN("18"),
	NINETEEN("19"),
	TWENTY("20"),
	TWENTYONE("21"),
	FOOL("Excuse"),
	JACK ("Jack"),
	KNIGHT ("Knight"),
	QUEEN ("Queen"),
	KING ("King");
	
	
	private String name; 
	private String trumps;
	
	Values(String name) {
		this.name = name;
	}
	Values(String name, String trumps) {
		
	}
	
	public String toString() {
		return this.name;
	}
}
