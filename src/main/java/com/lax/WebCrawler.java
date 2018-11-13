package com.lax;

import static java.lang.System.exit;

import java.net.MalformedURLException;
import java.net.URL;

public class WebCrawler {
	
	   private static WebSiteHandler webSiteHandler = new WebSiteHandler();
	   
	    private static URL validateArguments(String[] args) {
	        if (args.length != 1) {
	            System.out.println("Usage: WebCrawler URL");
	            exit(1);
	        }

	        URL url = null;
	        try {
	            url = new URL(args[0]);
	        }
	        catch (MalformedURLException malformedURLException ) {
	            System.out.println("Malformed URL " + args[0]);
	            exit(1);
	        }
	        return url;
	    }
	    
	    public static void main(String[] args) {
	        URL url = validateArguments(args);

	        try {
	        	webSiteHandler.setWebPageProcessor(new WebPageProcessor());
	        	webSiteHandler.depthSearch(url);
	        }
	        catch (Exception exception) {
	            System.out.println("Error while searching.");
	            exit(1);
	        }

	        System.out.println(webSiteHandler.getResult());
	        exit(0);
	    }
}
