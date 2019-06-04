package org.testtc.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public XmlElement getParent() {
        return parent;
    }

    public List<XmlElement> getChildren() { return Collections.unmodifiableList(children); }

    public boolean addChild(XmlElement childTag) {
        return children.add(childTag);
    }

    public List<XmlAttribute> getAttributes() { return Collections.unmodifiableList(attributes); }

    public boolean addAttribute(String name, String value) {
        return attributes.add(new XmlAttribute(name, value, this));
    }
}
