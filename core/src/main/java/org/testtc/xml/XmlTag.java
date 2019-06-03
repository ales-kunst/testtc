package org.testtc.xml;

import java.util.ArrayList;
import java.util.List;

public class XmlTag extends XmlElement {

    private final XmlTag parent;
    private final List<XmlTag> children;
    private final List<XmlAttribute> attributes;

    public XmlTag(String name, XmlTag parent) {
        super(name);
        this.parent = parent;
        this.children = new ArrayList<>();
        this.attributes = new ArrayList<>();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public XmlTag getParent() {
        return parent;
    }

    public XmlTag[] getChildren() {
        return children.toArray(new XmlTag[children.size()]);
    }

    public boolean addChild(XmlTag childTag) {
        return children.add(childTag);
    }

    public XmlAttribute[] getAttributes() {
        return attributes.toArray(new XmlAttribute[attributes.size()]);
    }

    public boolean addAttribute(String name, String value) {
        return attributes.add(new XmlAttribute(name, value));
    }

}
