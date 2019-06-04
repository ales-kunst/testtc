package org.testtc.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

class XmlParserTest {

    @Tag("XML")
    @Test
    void testParse() throws ParserConfigurationException, SAXException, IOException {
        URL url = this.getClass().getResource("/xml/Test_COC_BFTC01_Export.xml");
        XmlDocument xmlDocument = XmlParser.parse(new File(url.getFile()));
        Assertions.assertEquals(1, xmlDocument.getXmlElements().size());
    }
}