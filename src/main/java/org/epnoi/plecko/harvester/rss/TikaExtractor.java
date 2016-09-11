package org.epnoi.plecko.harvester.rss;

import org.apache.http.HttpEntity;
import org.apache.tika.metadata.Metadata;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.ContentHandler;

/**
 * Created by fitash on 6/09/16.
 */
public class TikaExtractor implements EntityExtractor<String> {
    public TikaExtractor() {
    }

    public String extract(HttpEntity entity) {
        Metadata metadata = new Metadata();
        InputStream is = null;
        ContentHandler handler = null;
        try {
            is = entity.getContent();

            Parser parser = new AutoDetectParser();
            handler = new BodyContentHandler(-1);

            ParseContext context = new ParseContext();
            context.set(Parser.class, parser);

            parser.parse(is, handler, metadata, new ParseContext());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        String content = handler.toString();
        return content;
    }
}
