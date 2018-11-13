WebCrawler

This application is a simple webcrawler that takes as an input a well formed URL and visits all of the pages within
the given domain.  It will gather all of the internal links to other pages, links to external URL's and links to static
content.  It's output will be a simple structured map containing all of the links found.


Requirements:
1) Java 1.8
2) Maven 3.5.2

This project is a maven project and supports the following standard commands to do the following:

build, run tests -- maven clean package

To run on the command line
within the project\target directory

java -jar webcrawler-1.0-SNAPSHOT-jar-with-dependencies.jar [url]

The output is in the form of:

Crawled Site : Parent Page
    Children

Thoughts 

1) Used Jsoup for parsing the webpages.  The library is been used for years, stable and well tested.

2) Used URL validator class in apache commons.

3) Used in-memory (HashMap) vs. database.  This was done since this was a test project. added maximum limit on number of pages (in depth search).

4) If this crawler was meant to work on multiple sites, use of thread pool might be better.


