package org.testtc.xml;

public abstract class XmlElement {

    private final String name;
    private String value;

    public XmlElement(String name) {
        this.name = name;
        this.value = null;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
