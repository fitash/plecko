package org.epnoi.plecko.infrastructure.harvester.rss;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fitash on 11/09/16.
 */
public class TikaHelper {
    public static String extractContentFromHTML(InputStream source) throws TikaException, SAXException, IOException {
        ContentHandler handler = null;
        try {
            Metadata metadata = new Metadata();

            Parser parser = new HtmlParser();
            handler = new BodyContentHandler(-1);

            ParseContext context = new ParseContext();
            context.set(Parser.class, parser);

            parser.parse(source,handler, metadata, new ParseContext());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                source.close();
            } catch (IOException e) {
                throw e;
            }
        }

        String content = handler.toString();
        return content;
    }

    public static String extractContentFromHTML(String source)throws TikaException, SAXException, IOException {
        InputStream is = new ByteArrayInputStream(source.getBytes("UTF-8"));
        return extractContentFromHTML(is);
    }

}
