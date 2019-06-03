package org.testtc.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.Deque;
import java.util.LinkedList;

public class GenericXmlHandler extends org.xml.sax.helpers.DefaultHandler {
    private static final Logger LOG = LogManager.getLogger(GenericXmlHandler.class.getName());

    private final String encoding;
    private final Deque<XmlTag> xmlElementsStack;

    public GenericXmlHandler(String encoding) {
        this.encoding = encoding;
        this.xmlElementsStack = new LinkedList<>();
    }

    @Override
    public void startDocument() throws SAXException {

        LOG.info("Start document...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        LOG.info("Start element {}...", localName);
        XmlTag parent = null;
        if (!xmlElementsStack.isEmpty()) {
            parent = xmlElementsStack.peek();
        }
        XmlTag currentElement = new XmlTag(localName, parent);
        for (int i = 0; i < attributes.getLength(); i++) {
            currentElement.addAttribute(attributes.getLocalName(i), attributes.getValue(i));
        }
        xmlElementsStack.push(currentElement);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        LOG.info("End element {}...");
        if (!xmlElementsStack.isEmpty()) {
            xmlElementsStack.pop();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        LOG.info("Content {}", new String(ch, start, length));
        // TODO: Append to string because the content can be put in multiple runs
    }
}
