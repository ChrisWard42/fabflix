import java.util.Map;
import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CastsSaxHandler extends DefaultHandler{
	private Map<String, MovieInfo> movieMap;
	private String readText = "";
	private String movieID = "";
	private String starName = "";

	public CastsSaxHandler(Map<String, MovieInfo> movieMap){
		this.movieMap = new HashMap<String, MovieInfo>(movieMap);
	}

	@Override
	public void startElement(String uri, 
	String localName, String qName, Attributes attributes)
	throws SAXException {
	}

	@Override
	public void endElement(String uri, 
	String localName, String qName) throws SAXException {
		if(qName.equalsIgnoreCase("f")){
			movieID = readText;
		}
		else if(qName.equalsIgnoreCase("a")){
			starName = readText;
		}
		else if(qName.equalsIgnoreCase("m")){
			if(movieMap.containsKey(movieID)){
				movieMap.get(movieID).addToStarSet(starName);
			}
		}
	}

	@Override
	public void characters(char ch[], 
	int start, int length) throws SAXException {
		readText = new String(ch, start, length);
	}

	public Map<String, MovieInfo> getMap(){
		return movieMap;
	}

}