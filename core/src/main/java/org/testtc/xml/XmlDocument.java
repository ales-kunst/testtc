package org.testtc.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XmlDocument {

    private final List<XmlElement> xmlElements;

    public XmlDocument() {
        this.xmlElements = new ArrayList<>();
    }

    public boolean addXmlElement(XmlElement xmlElement) {
        return xmlElements.add(xmlElement);
    }

    public int getXmlElementsSize() { return xmlElements.size(); }

    public List<XmlElement> getXmlElements() {
        return Collections.unmodifiableList(xmlElements);
    }

    public XmlElement getXmlElement(int index) { return xmlElements.get(index); }
}
