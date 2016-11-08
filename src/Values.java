
public enum Values {
	Two ("02"),
	Three ("03"),
	Four ("04"),
	Five ("05"),
	Six ("06"),
	Seven ("07"),
	Eight ("08"),
	Nine ("09"),
	Ten ("10"),
	Jack ("Jack"),
	Knight ("Knight"),
	Queen ("Queen"),
	King ("King"),
	Ace ("Ace");
	
	private String name; 
	
	Values(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
}
