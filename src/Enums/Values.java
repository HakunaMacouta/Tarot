package Enums;

import java.util.ArrayList;
import java.util.List;

public enum Values {
	ONE ("1",1),
	TWO ("2",2),
	THREE ("3",3),
	FOUR ("4",4),
	FIVE ("5",5),
	SIX ("6",6),
	SEVEN ("7",7),
	EIGHT ("8",8),
	NINE ("9",9),
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
	FOOL("fool",1000),
	JACK ("jack",11),
	KNIGHT ("commoner",12),
	QUEEN ("queen",13),
	KING ("king",14),
	ACE ("1",1);
	
	
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