package org.testtc.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.Deque;
import java.util.LinkedList;

public class GenericXmlHandler extends org.xml.sax.helpers.DefaultHandler {
    private static final Logger LOG = LogManager.getLogger(GenericXmlHandler.class.getName());

    private final XmlDocument xmlDocument;
    private final Deque<XmlElement> xmlElementsStack;
    private StringBuilder tmpContent;

    public GenericXmlHandler() {
        this.xmlDocument = new XmlDocument();
        this.xmlElementsStack = new LinkedList<>();
    }

    public XmlDocument getXmlDocument() {
        return xmlDocument;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        LOG.info("Start element {}...", localName);
        XmlElement parent = null;
        tmpContent = new StringBuilder();
        if (!xmlElementsStack.isEmpty()) {
            parent = xmlElementsStack.peek();
        }
        XmlElement currentElement = new XmlElement(qName, parent);
        for (int i = 0; i < attributes.getLength(); i++) {
            currentElement.addAttribute(attributes.getLocalName(i), attributes.getValue(i));
        }
        xmlElementsStack.push(currentElement);
        if (currentElement.isRoot()) {
            xmlDocument.addXmlElement(currentElement);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        LOG.info("End element {}...");
        if (!xmlElementsStack.isEmpty()) {
            XmlElement xmlElement = xmlElementsStack.pop();
            xmlElement.setValue(tmpContent.toString());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        LOG.info("Content {}", new String(ch, start, length));
        tmpContent.append(new String(ch, start, length));
    }
}
