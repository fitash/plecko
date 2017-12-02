package org.epnoi.plecko.infrastructure.harvester.rss;

/**
 * Created by fitash on 10/09/16.
 */
public class ContentCleaner {
    public static String clean(String content){
        String cleanedContent= content.replace("(\\s){3,}"," ");
        return cleanedContent.replace("(\\n){2,}", "");
    }
}
