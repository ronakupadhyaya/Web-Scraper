import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Scraper {

	public static void main(String[] args) {
		ArrayList<Facility> facilities = readFileByLine("for_ronak_updated_prime_urls.csv");
		for(int i = 0; i < 10; i++) {
			Facility facility = facilities.get(i);
			facility.parse();
			System.out.println(i + ": " + facility.url);
		}
		writeWebsiteFileByLine("website.csv", facilities);
		writeFeaturesFileByLine("features.csv", facilities);
//		writeUnitsFileByLine("units.csv", facilities);
	}
	
	public static void writeUnitsFileByLine(String filename, ArrayList<Facility> facilities) {
		try {
			File file = new File(filename);
			PrintWriter pw = new PrintWriter(file);
			StringBuilder sb = new StringBuilder();
			
			sb.append("Name");
			sb.append(",");
			sb.append("URL");
			sb.append(",");
			sb.append("Dimensions");
			sb.append(",");
			sb.append("Amenities");
			sb.append(",");
			sb.append("Unit_Price");
			sb.append(",");
			sb.append("Online_Price");
			sb.append(",");
			sb.append("Special_Offer");
			sb.append(",");
			sb.append("Description");
			sb.append("\n");
	
			for(int i = 0; i < 10; i++) {
				Facility facility = facilities.get(i);
				sb.append(facility.name);
				sb.append(",");
				String url = facility.url;
				sb.append(url);
				sb.append("\n");
				ArrayList<Unit> units = facility.units;
				if(units != null && units.size() > 0) {
					for(int j = 0; j < units.size(); j++) {
						Unit unit = units.get(j);
						sb.append(facility.name);
						sb.append(",");
						sb.append(url);
						sb.append(",");
						
						String dimensions = unit.dimensions;
						if(dimensions.contains(",")) {
							sb.append("\""+ dimensions + "\"");
						}
						else {
							sb.append(dimensions);
						}
						sb.append(",");
						
						String amenities = unit.amenities;
						if(amenities.contains(",")) {
							sb.append("\""+ amenities + "\"");
						}
						else {
							sb.append(amenities);
						}
						sb.append(",");
						
						String unitPrice = unit.unitPrice;
						if(unitPrice.contains(",")) {
							sb.append("\""+ unitPrice + "\"");
						}
						else {
							sb.append(unitPrice);
						}
						sb.append(",");
						
						String onlinePrice = unit.onlinePrice;
						if(onlinePrice.contains(",")) {
							sb.append("\""+ onlinePrice + "\"");
						}
						else {
							sb.append(onlinePrice);
						}
						sb.append(",");
						
						String specialOffer = unit.specialOffer;
						if(specialOffer.contains(",")) {
							sb.append("\""+ specialOffer + "\"");
						}
						else {
							sb.append(specialOffer);
						}
						sb.append(",");
						
						String description = unit.description;
						if(description.contains(",")) {
							sb.append("\""+ description + "\"");
						}
						else {
							sb.append(description);
						}
						sb.append("\n");
					}
				}
			}
			
			pw.write(sb.toString());
	        pw.close();
	        System.out.println("Done");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeWebsiteFileByLine(String filename, ArrayList<Facility> facilities) {
		try {
			File file = new File(filename);
			PrintWriter pw = new PrintWriter(file);
	        StringBuilder sb = new StringBuilder();
	        
			sb.append("Name");
			sb.append(",");
			sb.append("UID");
			sb.append(",");
			sb.append("URL");
			sb.append(",");
			sb.append("Image");
			sb.append(",");
			sb.append("Description");
			sb.append(",");
			sb.append("Customer_Number");
			sb.append(",");
			sb.append("Sales_Number");
			sb.append(",");
			sb.append("Email");
			sb.append(",");
			sb.append("Front_Office_Hours");
			sb.append(",");
			sb.append("Access_Hours");
			sb.append(",");
			sb.append("Street");
			sb.append(",");
			sb.append("City");
			sb.append(",");
			sb.append("State");
			sb.append(",");
			sb.append("Zip");
			sb.append("\n");
			
			for(int i = 0; i < 10; i++) {
				Facility facility = facilities.get(i);
				sb.append(facility.name);
				sb.append(",");
				
				String url = facility.url;
				String[] arr = url.split("/");
				sb.append(arr[arr.length - 1]);
				sb.append(",");
				sb.append(url);
				
				Website website = facility.website;
				if(website == null) {
					sb.append("\n");	
				}
				else {
					sb.append(",");
				}
				
				String image = website.image;
				if(image != null && image.contains(",")) {
					sb.append("\""+ image + "\"");
				}
				else {
					sb.append(image);
				}
				sb.append(",");
				
				String description = website.description;
				if(description != null && description.contains(",")) {
					sb.append("\""+ description + "\"");
				}
				else {
					sb.append(description);
				}
				sb.append(",");
				
				String customerNumber = website.customerNumber;
				if(customerNumber != null && customerNumber.contains(",")) {
					sb.append("\""+ customerNumber.substring(33, 47) + "\"");
				}
				else {
					if(customerNumber != null) {
						sb.append(customerNumber.substring(33, 47));
					}
				}
				sb.append(",");
				
				String salesNumber = website.salesNumber;
				if(salesNumber != null && salesNumber.contains(",")) {
					sb.append("\""+ description + "\"");
				}
				else {
					sb.append(salesNumber);
				}
				sb.append(",");
				
				String email = website.email;
				if(email != null && email.contains(",")) {
					sb.append("\""+ email + "\"");
				}
				else {
					sb.append(email);
				}
				sb.append(",");
				
				String frontOfficeHours = website.frontOfficeHours;
				if(frontOfficeHours != null && frontOfficeHours.contains(",")) {
					sb.append("\""+ frontOfficeHours + "\"");
				}
				else {
					sb.append(frontOfficeHours);
				}
				sb.append(",");
				
				String accessHours = website.accessHours;
				if(accessHours != null && accessHours.contains(",")) {
					sb.append("\""+ accessHours + "\"");
				}
				else {
					sb.append(accessHours);
				}
				sb.append(",");
				
				String street = website.street;
				if(street != null && street.contains(",")) {
					sb.append("\""+ street + "\"");
				}
				else {
					sb.append(street);
				}
				sb.append(",");
				
				String city = website.city;
				if(city != null && city.contains(",")) {
					sb.append("\""+ city + "\"");
				}
				else {
					sb.append(city);
				}
				sb.append(",");
				
				String state = website.state;
				if(state != null && state.contains(",")) {
					sb.append("\""+ state + "\"");
				}
				else {
					sb.append(state);
				}
				sb.append(",");
				
				String zip = website.zip;
				if(zip != null && zip.contains(",")) {
					sb.append("\""+ zip + "\"");
				}
				else {
					sb.append(zip);
				}
				sb.append("\n");
			}
			
			pw.write(sb.toString());
	        pw.close();
	        System.out.println("Done");
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFeaturesFileByLine(String filename, ArrayList<Facility> facilities) {
		try {
			File file = new File(filename);
			PrintWriter pw = new PrintWriter(file);
	        StringBuilder sb = new StringBuilder();
			sb.append("Name");
			sb.append(",");
			sb.append("URL");
			sb.append(",");
			sb.append("Features");
			sb.append("\n");			
			for(int i = 0; i < 10; i++) {
				Facility facility = facilities.get(i);
				sb.append(facility.name);
				sb.append(",");
				
				String url = facility.url;
				sb.append(url);
				
				ArrayList<String> features = facility.features;
				if(features == null || features.size() == 0) {
					sb.append("\n");	
				}
				else {
					sb.append(",");
				}
				for(int j = 0; j < features.size(); j++) {
					String feature = features.get(j);
					if(feature.contains(",")) {
						sb.append("\""+ feature + "\"");
					}
					else {
						sb.append(feature);
					}
					if(j == features.size() - 1) {
						sb.append("\n");
					}
					else {
						sb.append(" / ");
					}
				}
			}
			
			pw.write(sb.toString());
	        pw.close();
	        System.out.println("Done");
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Facility> readFileByLine(String fileName) {
		ArrayList<Facility> facilities = new ArrayList<Facility>();
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			scanner.useDelimiter("\n");
			scanner.next();
			while (scanner.hasNext()) {
				String[] array = scanner.next().split(",");
				String id = array[0];
				String name = array[1];
				String url = array[2];
				Facility facility;
				if(url.contains("#/units?")) {
					int index = url.indexOf("#/units?");
					facility = new Facility(id, name, url.substring(0, index));
				}
				else {
					facility = new Facility(id, name, url);
				}
				facilities.add(facility);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			   e.printStackTrace();
		} 
		return facilities;
	}
}
