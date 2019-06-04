package org.testtc.xml;

public class XmlAttribute extends XmlNode<XmlElement> {

    public XmlAttribute(String name, String value, XmlElement parent) {
        super(name, parent);
        setValue(value);
    }
}
