package vue.javafx;

import java.util.ArrayList;
import java.util.List;

public enum Values {
	ONE ("01",1),
	TWO ("02",2),
	THREE ("03",3),
	FOUR ("04",4),
	FIVE ("05",5),
	SIX ("06",6),
	SEVEN ("07",7),
	EIGHT ("08",8),
	NINE ("09",9),
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
	FOOL("The_Fool",1000),
	JACK ("Jack",11),
	KNIGHT ("Knight",12),
	QUEEN ("Queen",13),
	KING ("King",14),
	ACE ("Ace",1);
	
	
	private String name;
	private int power;
	
	Values(String name, int power) {
		this.name = name;
		this.power = power;
	}
	Values(String name, String trumps) {
		
	}
	
	public String toString() {
		return this.name;
	}
	
	public int getPower(){
		return power;
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