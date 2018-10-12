import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;;

public class Scraper {

	public static void main(String[] args) {
		String url = "https://www.primestoragegroup.com/self-storage/ct/brookfield/ct09";
		parse(url).print();
	}
	
	public static Website parse(String url) {
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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return website;
	}

}
