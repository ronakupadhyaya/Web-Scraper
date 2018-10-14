import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Facility {
	public String id;
	public String name;
	public String url;
	public Website website;
	public ArrayList<String> features;
	
	
	public Facility(String id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.features = new ArrayList<String>(); 
	}
	
	public void parse() {
		this.website = parseWebsite(url);
		this.features = parseFeatures(url + "/features");
		
	}
	
	public static ArrayList<String> parseFeatures(String url) {
		ArrayList<String> features = new ArrayList<String>();
		Document page;
		try {
			page = Jsoup.connect(url).get();
			if(page.select("div.html-content") != null && 
					page.select("div.html-content").size() > 2 &&
					page.select("div.html-content").get(2).select("ul") != null &&
					page.select("div.html-content").get(2).select("ul").first() != null) {
				Elements ul = page.select("div.html-content").get(2).select("ul").first().select("li");
				for(int i = 0; i < ul.size(); i++) {
					Element li = ul.select("li").get(i);
					features.add(li.text());
				}	
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
		
		if(element.select("span.sss-unit-size") != null) {
			unit.dimensions = element.select("span.sss-unit-size").first().text();
		}

		if(element.select("span.sss-unit-amenities ul") != null) {
			unit.amenities = element.select("span.sss-unit-amenities ul").first().text();
		}
		
		if(element.select("p.unit-price") != null) {
			unit.unitPrice = element.select("p.unit-price").first().text();
		}

		if(element.select("p.online-price") != null) {
			unit.onlinePrice = element.select("p.online-price").first().text();
		}

		if(element.select("span.unit-special-offer") != null) {
			unit.specialOffer = element.select("span.unit-special-offer").first().text();
		}

		if(element.select("div.sss-unit-description") != null) {
			unit.description = element.select("div.sss-unit-description").first().text();
		}
		
		return unit;
	}
	
	public static Website parseWebsite(String url) {
		Website website = new Website();
		Document homepage;
		Document hours;
		try {
			homepage = Jsoup.connect(url).get();
			hours = Jsoup.connect(url + "/map-hours").get();
			
			if(homepage.select("img.u-photo") != null && 
					homepage.select("img.u-photo").first() != null) {
				website.image = homepage.select("img.u-photo").first().attr("abs:src");
			}
			
			if(homepage.select("div.html-content p") != null && 
					homepage.select("div.html-content p").get(1) != null &&
					homepage.select("div.html-content p").get(2) != null) {
				website.description = homepage.select("div.html-content p").get(1).text() + homepage.select("div.html-content p").get(2).text();
			}
			
			if(homepage.select("p:contains(Current Customer)") != null && 
					homepage.select("p:contains(Current Customer)").first() != null) {
				website.customerNumber = homepage.select("p:contains(Current Customer)").first().text(); 
			}	
			
			if(hours.select("span.p-tel") != null && 
					hours.select("span.p-tel").first() != null) {
				website.salesNumber = hours.select("span.p-tel").first().text();
			}
			
			if(hours.select("p.u-email a") != null && 
					hours.select("p.u-email a").first() != null) {
				website.email = hours.select("p.u-email a").first().attr("abs:href");
			}
			
			if(hours.select("div.office-hours-condensed") != null &&
					hours.select("div.office-hours-condensed").first() != null) {
				website.frontOfficeHours = hours.select("div.office-hours-condensed").first().text();
			}
			
			if(hours.select("div.hours-wrapper div p") != null &&
					hours.select("div.hours-wrapper div p").first() != null) {
				website.accessHours = hours.select("div.hours-wrapper div p").first().text();
			}
			
			if(hours.select("p.h-adr a span") != null) {
				if(hours.select("p.h-adr a span").first() != null) {
					website.street = hours.select("p.h-adr a span").first().text();
				}
				
				if(hours.select("p.h-adr a span").get(2) != null) {
					website.city = hours.select("p.h-adr a span").get(2).text();
				}
				
				if(hours.select("p.h-adr a span").get(4) != null) {
					website.state = hours.select("p.h-adr a span").get(4).text();
				}
				
				if(hours.select("p.h-adr a span").get(5) != null) {
					website.zip = hours.select("p.h-adr a span").get(5).text();
				}
			}
			
			Elements links = hours.select("div.social-links a");
			if(hours.select("div.social-links a") != null) {
				for(int i = 0; i < links.size(); i++) {
					website.socialMedia.put(links.get(i).attr("title"), links.get(i).attr("abs:href"));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return website;
	}
}
