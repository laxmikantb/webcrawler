package com.lax;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class WebSiteHandler {

	private static final int MAX_PAGELINKS = 25000;
	private static int currentPageLinkCount = 0;
    private String siteRoot = "";
    private HashSet<URL> visitedURLS = new HashSet<URL>();
    private LinkedHashMap<URL, HashSet<URL>> siteTree = new LinkedHashMap<>();

    private WebPageProcessor webPageProcessor;
    
    public void setWebPageProcessor(WebPageProcessor webPageProcessor) {
		this.webPageProcessor = webPageProcessor;
	}

    public void depthSearch(URL url) {

        if (siteRoot.length() == 0) {
            siteRoot = url.toString();
        }
        visitedURLS.add(url);
        HashSet<URL> pageLinks;

        try {
            pageLinks = webPageProcessor.retrievePageLinks(url);
        }
        catch (IOException ioException) {
            pageLinks = new HashSet<URL>();
        }

        if (!siteTree.containsKey(url)) {
        	siteTree.put(url, pageLinks);
        	currentPageLinkCount+= pageLinks.size();
        }

        if (currentPageLinkCount > MAX_PAGELINKS) // added restriction to limit 
        	return;
        
        for (URL pageLink : pageLinks) {
            if (pageLink.toString().startsWith(siteRoot) && !visitedURLS.contains(pageLink)) {
            	depthSearch(pageLink);
            }
        }
    }

    public String getResult() {
        StringBuilder stringBuilder = new StringBuilder();
        for (URL site : siteTree.keySet()) {
        	stringBuilder.append("Crawled Site : ");
            stringBuilder.append(site.toString());
            stringBuilder.append(System.lineSeparator());
            for (URL siteLink : siteTree.get(site)) {
                stringBuilder.append('\t');
                stringBuilder.append(siteLink.toString());
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }

}

