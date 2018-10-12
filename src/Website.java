import java.util.ArrayList;
import java.util.HashMap;

public class Website {
	public String image;
	public String description;
	public String customerNumber;
	public String salesNumber;
	public String email;
	public String frontOfficeHours;
	public String accessHours;
	public String street;
	public String city;
	public String state;
	public String zip;
	public HashMap<String, String> socialMedia;

	public Website() {
		socialMedia = new HashMap<String, String>();
	}
	
	public void print() {
		System.out.println("Image: " + image);
		System.out.println("Description: " + description);
		System.out.println("Customer Number: " + customerNumber);
		System.out.println("Sales Number: " + salesNumber);
		System.out.println("Email: " + email);
		System.out.println("Front Office Hours: " + frontOfficeHours);
		System.out.println("Access Hours: " + accessHours);
		System.out.println("Street: " + street);
		System.out.println("City: " + city);
		System.out.println("State: " + state);
		System.out.println("Zip: " + zip);
		for(String site : socialMedia.keySet()) {
			System.out.println(site + " " + socialMedia.get(site));
		}
	}
}
