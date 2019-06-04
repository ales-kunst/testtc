package org.testtc.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XmlParser {

    public static XmlDocument parse(File file) throws IOException, ParserConfigurationException, SAXException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        GenericXmlHandler handler = new GenericXmlHandler();
        saxParser.parse(file, handler);
        return handler.getXmlDocument();
    }

    private XmlParser() {}
}
