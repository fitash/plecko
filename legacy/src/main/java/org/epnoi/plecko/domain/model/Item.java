package org.epnoi.plecko.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by rgonza on 28/8/16.
 */
@Document(indexName = "item", type = "item")
public class Item {

    @Id
    private String uri;
    private String url;
    private String content;
    private String description;

    public Item() {
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!uri.equals(item.uri)) return false;
        if (!url.equals(item.url)) return false;
        if (!content.equals(item.content)) return false;
        return description.equals(item.description);

    }

    @Override
    public int hashCode() {
        int result = uri.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "uri='" + uri + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
