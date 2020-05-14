package ldansorean.s5guessthecelebrity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataExtractionService {

    private final static String CELEBRITIES_SECTION_START = "<div class=\"lister-list\">";
    private final static String CELEBRITIES_SECTION_END = "<div class=\"footer filmosearch\">";
    private final static String INFO_EXTRACTION_PATTERN = "<img alt=\"(.*?)\"([\\S\\s]*?)src=\"(.*?)\"";

    public ArrayList<Celebrity> getCelebrities(String webpage) {
        ArrayList<Celebrity> celebrities = new ArrayList<>();

        if (webpage != null) {
            String celebritiesSection = webpage.substring(webpage.indexOf(CELEBRITIES_SECTION_START), webpage.indexOf(CELEBRITIES_SECTION_END));
            Pattern celebrityPattern = Pattern.compile(INFO_EXTRACTION_PATTERN);
            Matcher matcher = celebrityPattern.matcher(celebritiesSection);
            while (matcher.find()) {
                celebrities.add(new Celebrity(matcher.group(1), matcher.group(3)));
            }
        }

        return celebrities;
    }

}
