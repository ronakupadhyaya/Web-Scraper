import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scraper {

	public static void main(String[] args) {
		ArrayList<Facility> facilities = readFileByLine("prime_group_urls.csv");
		for(int i = 0; i < facilities.size(); i++) {
			Facility facility = facilities.get(i);
			System.out.println(i + ": " + facility.url);
			facility.parse();
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
