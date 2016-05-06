import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MainSaxHandler extends DefaultHandler{
	private String currentDirector = "";
	private String readText = "";
	private String movieID = "";
	private MovieInfo movie;
	private Map<String, MovieInfo> movieMap;
	private Set<MovieInfo> movieSet;

	@Override
	public void startElement(String uri, 
	String localName, String qName, Attributes attributes)
	throws SAXException {
		if(qName.equalsIgnoreCase("movies")){
			movieMap = new HashMap<String, MovieInfo>();
			movieSet = new HashSet<MovieInfo>();
		}
		else if(qName.equalsIgnoreCase("film")){
			movie = new MovieInfo();
		}

	}

	@Override
	public void endElement(String uri, 
	String localName, String qName) throws SAXException {
		//TODO: translate dirnames into full director names
		if(qName.equalsIgnoreCase("dirname")){
			currentDirector = readText;
		}
		else if(qName.equalsIgnoreCase("film")){
			movie.setDirector(currentDirector);
			//check if duplicate movie
			//(duplicates are movies with same title AND same director)
			if(movieSet.add(movie)){
				movieMap.put(movieID, movie);
				movie = null;
			}
		}
		else if(qName.equalsIgnoreCase("fid")){
			movieID = readText;
		}
		else if(qName.equalsIgnoreCase("t")){
			movie.setTitle(readText);
		}
		else if(qName.equalsIgnoreCase("year")){
			if(!readText.isEmpty()){
				readText = readText.replaceAll("[^0-9]", "");
				movie.setYear(Integer.parseInt(readText));
			}
		}
		else if(qName.equalsIgnoreCase("cat")){
			String translatedCat = translateCat(readText);
			if(!translatedCat.isEmpty())
				movie.addToGenreSet(translatedCat);
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

	//Translate abbreviated categories into actual genre names.
	//Bad entries will return an empty string and no genre will be added
	private String translateCat(String catAbbrv){
		catAbbrv = catAbbrv.replaceAll(" ", "");
		if(catAbbrv.equalsIgnoreCase("susp")){
			return "Thriller";
		} else if(catAbbrv.equalsIgnoreCase("cnr")){
			return "Cops and Robbers";
		} else if(catAbbrv.equalsIgnoreCase("dram")){
			return "Drama";
		} else if(catAbbrv.equalsIgnoreCase("west")){
			return "Western";
		} else if(catAbbrv.equalsIgnoreCase("myst")){
			return "Mystery";
		} else if(catAbbrv.equalsIgnoreCase("s.f.")
				  || catAbbrv.equalsIgnoreCase("scfi")
				  || catAbbrv.equalsIgnoreCase("scif")){
			return "Science Fiction";
		} else if(catAbbrv.equalsIgnoreCase("advt")){
			return "Adventure";
		} else if(catAbbrv.equalsIgnoreCase("horr")){
			return "Horror";
		} else if(catAbbrv.equalsIgnoreCase("romt")){
			return "Romantic";
		} else if(catAbbrv.equalsIgnoreCase("comd")){
			return "Comedy";
		} else if(catAbbrv.equalsIgnoreCase("musc")){
			return "Musical";
		} else if(catAbbrv.equalsIgnoreCase("docu")){
			return "Documentary";
		} else if(catAbbrv.equalsIgnoreCase("porn")){
			return "Pornography";
		} else if(catAbbrv.equalsIgnoreCase("Noir")){
			return "Black";
		} else if(catAbbrv.equalsIgnoreCase("biop")){
			return "Biographical Picture";
		} else if(catAbbrv.equalsIgnoreCase("tv")){
			return "TV Show";
		} else if(catAbbrv.equalsIgnoreCase("tvs")){
			return "TV Series";
		} else if(catAbbrv.equalsIgnoreCase("tvm")){
			return "TV miniseries";
		} else if(catAbbrv.equalsIgnoreCase("actn")){
			return "Action";
		} else {
			return ""; 
		}
	}

}