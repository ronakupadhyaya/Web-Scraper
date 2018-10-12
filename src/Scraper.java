import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Scraper {

	public static void main(String[] args) {
		String url = "https://www.primestoragegroup.com/self-storage/ct/brookfield/ct09";
	}
	
	public static ArrayList<String> parseFeatures(String url) {
		ArrayList<String> features = new ArrayList<String>();
		Document page;
		try {
			page = Jsoup.connect(url).get();
			Elements ul = page.select("div.html-content").get(2).select("ul").first().select("li");
			for(int i = 0; i < ul.size(); i++) {
				Element li = ul.select("li").get(i);
				features.add(li.text());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return features;
	}
	
	public static ArrayList<Unit> parseUnits(String url) {
		ArrayList<Unit> units = new ArrayList<Unit>();
		
		System.setProperty("webdriver.chrome.driver", "/Users/digantupadhyaya/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		Document homepage = Jsoup.parse(driver.getPageSource());;
		Elements elements = homepage.select("div.unit-info");
		
		for(int i = 0; i < elements.size(); i++) {
			Unit unit = parseUnit(elements, i);
			units.add(unit);
		}
		
		return units;
	}
	
	public static Unit parseUnit(Elements units, int index) {
		Unit unit = new Unit();
		
		Element element = units.get(index);
		
		unit.dimensions = element.select("span.sss-unit-size").first().text();

		unit.amenities = element.select("span.sss-unit-amenities ul").first().text();

		unit.unitPrice = element.select("p.unit-price").first().text();

		unit.onlinePrice = element.select("p.online-price").first().text();

		unit.specialOffer = element.select("span.unit-special-offer").first().text();

		unit.description = element.select("div.sss-unit-description").first().text();
		
		return unit;
	}
	
	public static Website parseWebsite(String url) {
		Website website = new Website();
		Document homepage;
		Document hours;
		try {
			homepage = Jsoup.connect(url).get();
			hours = Jsoup.connect(url + "/map-hours").get();
			
			website.image = homepage.select("img.u-photo").first().attr("abs:src");
			
			website.description = homepage.select("div.html-content p").get(1).text() + homepage.select("div.html-content p").get(2).text();
			
			website.customerNumber = homepage.select("p:contains(Current Customer)").first().text(); 
						
			website.salesNumber = hours.select("span.p-tel").first().text();
			
			website.email = hours.select("p.u-email a").first().attr("abs:href");
			
			website.frontOfficeHours = hours.select("div.office-hours-condensed").first().text();
			
			website.accessHours = hours.select("div.hours-wrapper div p").first().text();
			
			website.street = hours.select("p.h-adr a span").first().text();
			
			website.city = hours.select("p.h-adr a span").get(2).text();
			
			website.state = hours.select("p.h-adr a span").get(4).text();
			
			website.zip = hours.select("p.h-adr a span").get(5).text();
			
			Elements links = hours.select("div.social-links a");
			for(int i = 0; i < links.size(); i++) {
				website.socialMedia.put(links.get(i).attr("title"), links.get(i).attr("abs:href"));
			}
			
			website.features = parseFeatures(url + "/features");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return website;
	}

}
