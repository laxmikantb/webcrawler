package com.lax;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class WebCrawlerTest {

	 @Rule
	 public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	 @Test
	 public void validRunShouldExitWithNormalOutput() {
		 exit.expectSystemExitWithStatus(0);
	     String args[] = new String[] { "http://wiprodigital.com" };
	     WebCrawler.main(args);
     }

	 @Test
	 public void invalidURLTestShouldFailAndExitStatusOne() {
	        exit.expectSystemExitWithStatus(1);
	        String args[] = new String[] { "abc.com" };
	        WebCrawler.main(args);
	 }

	 @Test
	 public void noArgumentTestShouldExitWithStatusOne() {
	        exit.expectSystemExitWithStatus(1);
	        String args[] = new String[] { };
	        WebCrawler.main(args);
	 }

	 @Test
	 public void moreThanOneArgumentsShouldExitWithStatusOne() {
	        exit.expectSystemExitWithStatus(1);
	        String args[] = new String[] { "http://www.lax.com", "hello"};
	        WebCrawler.main(args);
     }
}
