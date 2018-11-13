package com.lax;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.validator.UrlValidator;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebPageProcessor {

    private final HashMap<String, String> linkMap = new HashMap<String, String>() {
        {
            put("a", "href");
            put("link", "href");
            put("img", "src");
        }
    };

    private final UrlValidator urlValidator = new UrlValidator();

    public HashSet<URL> retrievePageLinks(URL url) throws IOException {
        HashSet<URL> pageSet = new HashSet<URL>();
        Document document = null;

        try {
            document = Jsoup
                    .connect(url.toString()) // create and returns connection of URL.
                    .ignoreContentType(true).get(); // get JSoup Document Object
        }
        catch (HttpStatusException httpStatusException) {
            return pageSet;
        }
        
        String linkType = null;

        for (String key :  linkMap.keySet()) {
            linkType = linkMap.get(key);
            for (Element link : document.select(key)) { // document child element selector
                String absoluteURLString = link.absUrl(linkType);
                if (urlValidator.isValid(absoluteURLString)) {
                    URL uri = new URL(absoluteURLString);
                  	pageSet.add(uri); // no duplicate
                }
            }
        }

        return pageSet;
    }

}

