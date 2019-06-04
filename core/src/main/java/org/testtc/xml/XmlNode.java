package org.testtc.xml;

public abstract class XmlNode<P> {

    private final P parent;
    private final String name;
    private String value;

    public XmlNode(String name, P parent) {
        this(name, null, parent);
    }

    public XmlNode(String name, String value, P parent) {
        this.parent = parent;
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value.toString();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public P getParent() {
        return parent;
    }
}
