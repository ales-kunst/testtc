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

    public List<XmlElement> getXmlElements() {
        return Collections.unmodifiableList(xmlElements);
    }
}
