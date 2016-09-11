package org.epnoi.plecko.domain;

/**
 * Created by rgonza on 28/8/16.
 */
public class Item {

    private String uri;
    private String url;
    private String content;
    private String description;

    public Item(String uri, String url, String content, String description) {
        this.uri = uri;
        this.url = url;
        this.content = content;
        this.description = description;
    }

    public String getDescription() {
        return description;
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

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Item{" +
                "uri='" + uri + '\'' +
                ", extractContentFromHTML='" + content + '\'' +
                '}';
    }
}
