import java.util.Map;
import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ActorsSaxHandler extends DefaultHandler{
	private Map<String, String> stars2dobMap;
	private int dobCount = 0;
	private String readText = "";
	private String starName = "";
	private String starDob = "";

	@Override
	public void startElement(String uri, 
	String localName, String qName, Attributes attributes)
	throws SAXException {
		if(qName.equalsIgnoreCase("actors")){
			stars2dobMap = new HashMap<String, String>();
		}
		else if(qName.equalsIgnoreCase("dob")){
			dobCount++;
		}
	}

	@Override
	public void endElement(String uri, 
	String localName, String qName) throws SAXException {
		if(qName.equalsIgnoreCase("stagename")){
			starName = readText;
		}
		else if(qName.equalsIgnoreCase("dob")){
			//only take in one dob
			if(dobCount == 1){
				if(!readText.isEmpty()){
					readText = readText.replaceAll("[^0-9]", "");
					//truncate ending of any year longer than 4 digits
					if(readText.length() > 3){
						readText = readText.substring(0,4);
					}
					//if year field is shorter than 4 digits, replace with 1900
					else{
						readText = "1900";
					}
					readText = readText + "-01-01";
					starDob = readText;
				}
			}
		}
		else if(qName.equalsIgnoreCase("actor")){
			dobCount = 0;
			stars2dobMap.put(starName, starDob);
		}
	}

	@Override
	public void characters(char ch[], 
	int start, int length) throws SAXException {
		readText = new String(ch, start, length);
	}

	public Map<String, String> getMap(){
		return stars2dobMap;
	}
}