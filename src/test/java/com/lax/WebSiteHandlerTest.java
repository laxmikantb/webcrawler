package com.lax;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class WebSiteHandlerTest {

	@Mock
	private WebPageProcessor webPageProcessorMock;

	@InjectMocks
	private WebSiteHandler webSiteHandler;

	@Before
	public void setUp() throws Exception {
	      MockitoAnnotations.initMocks(this);
	}
	    
    @Test
    public void shouldReturnOneUrl() throws IOException {

        URL url = new URL("http://www.google.com");

        Mockito.when(webPageProcessorMock.retrievePageLinks(url)).thenReturn(new HashSet<>());
        webSiteHandler.setWebPageProcessor(webPageProcessorMock);

        webSiteHandler.depthSearch(url);

        Assert.assertEquals("Crawled Site : " + url.toString() + System.lineSeparator(), webSiteHandler.getResult());
    }

    @Test
    public void shouldReturnOneParentAndOneChild() throws IOException {

        URL parentUrl = new URL("http://www.google.com");
        URL childUrl = new URL("http://www.google.com/webhp");

        Mockito.when(webPageProcessorMock.retrievePageLinks(parentUrl)).thenReturn(new HashSet<>(Collections.singletonList(childUrl)));
        webSiteHandler.setWebPageProcessor(webPageProcessorMock);

        webSiteHandler.depthSearch(parentUrl);

        Assert.assertEquals("Crawled Site : " + parentUrl.toString() + System.lineSeparator() + "\t" + childUrl + System.lineSeparator() + "Crawled Site : " + childUrl.toString() + System.lineSeparator(), webSiteHandler.getResult());
    }
}
