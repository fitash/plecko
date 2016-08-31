package org.epnoi.plecko.model;

/**
 * Created by rgonza on 28/8/16.
 */
public class Item {


    private String uri;
    private String content;

    public Item(String uri, String content) {
        this.uri = uri;
        this.content = content;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
