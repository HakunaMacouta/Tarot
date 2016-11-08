package vue.javafx;

import java.util.ArrayList;
import java.util.List;

public enum Values {
	ONE ("01"),
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
	KING ("King"),
	ACE ("Ace");
	
	
	private String name;
	
	Values(String name) {
		this.name = name;
	}
	Values(String name, String trumps) {
		
	}
	
	public String toString() {
		return this.name;
	}
	
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
