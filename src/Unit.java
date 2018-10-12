import java.util.ArrayList;

public class Unit {
	public String dimensions;
	public String amenities;
	public String unitPrice;
	public String onlinePrice;
	public String specialOffer;
	public String description;
	
	public void print() {
		System.out.println(dimensions + " " + amenities + " " + unitPrice + " " + onlinePrice
				+ " " + specialOffer +  " " + description);
	}
}
