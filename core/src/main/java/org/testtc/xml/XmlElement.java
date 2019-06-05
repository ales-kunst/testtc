package org.testtc.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XmlElement extends XmlNode<XmlElement> {

    private final XmlElement parent;
    private final List<XmlElement> children;
    private final List<XmlAttribute> attributes;

    public XmlElement(String name, XmlElement parent) {
        super(name, parent);
        this.parent = parent;
        this.children = new ArrayList<>();
        this.attributes = new ArrayList<>();
        if (parent != null) {
            parent.addChild(this);
        }
    }

    public boolean isRoot() {
        return parent == null;
    }

    public int getChildrenSize() { return children.size();}

    public List<XmlElement> getChildren() { return Collections.unmodifiableList(children); }

    public XmlElement getChild(int index) { return children.get(index); }

    public boolean addChild(XmlElement childTag) {
        return children.add(childTag);
    }

    public int getAttributesSize() { return attributes.size(); }

    public List<XmlAttribute> getAttributes() { return Collections.unmodifiableList(attributes); }

    public XmlAttribute getAttribute(int index) { return attributes.get(index); }

    public boolean addAttribute(String name, String value) {
        return attributes.add(new XmlAttribute(name, value, this));
    }
}
