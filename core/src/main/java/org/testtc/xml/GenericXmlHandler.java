package org.testtc.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.Deque;
import java.util.LinkedList;

public class GenericXmlHandler extends org.xml.sax.helpers.DefaultHandler {
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
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
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
        if (!xmlElementsStack.isEmpty()) {
            XmlElement xmlElement = xmlElementsStack.pop();
            xmlElement.setValue(tmpContent.toString());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        boolean shouldAppend = !content.trim().isEmpty();
        if (shouldAppend) {
            tmpContent.append(new String(ch, start, length));
        }
    }
}
