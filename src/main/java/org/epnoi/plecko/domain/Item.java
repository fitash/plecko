package org.epnoi.plecko.domain;

/**
 * Created by rgonza on 28/8/16.
 */
public class Item {


    private String uri;

    public String getUrl() {
        return url;
    }

    private String url;
    private String content;

    public Item(String uri, String url, String content) {
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

    @Override
    public String toString() {
        return "Item{" +
                "uri='" + uri + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
