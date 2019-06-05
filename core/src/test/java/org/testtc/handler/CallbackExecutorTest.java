package org.testtc.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testtc.xml.XmlDocument;
import org.testtc.xml.XmlParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class CallbackExecutorTest {

    private XmlDocument xmlDocument;
    private CallbackContainer emptyCallbackContainer;
    private CallbackContainer callbackContainer;
    private CallbackContainer testCallbackContainer;

    @BeforeEach
    void setUp() throws ParserConfigurationException, SAXException, IOException {
        URL url = this.getClass().getResource("/xml/tc_scenarios.xml");
        xmlDocument = XmlParser.parse(new File(url.getFile()));
        emptyCallbackContainer = CallbackContainer.builder().build();

        testCallbackContainer = CallbackContainer.builder()
                .add(TcSteps.TCINMP.val(), xml -> ReturnCode.WARNING)
                .add(TcSteps.ZSVS01.val(), xml -> ReturnCode.ERROR)
                .add(TcSteps.TCTS03.val(), xml -> ReturnCode.FATAL).build();
    }

    @Test
    void execute() {

    }
}

enum TcSteps {

    BFTC01("BFTC01"),
    TCINMP("TCINMP"),
    ZSVS01("ZSVS01"),
    TCTS03("TCTS03")
    ;

    private String value;

    private TcSteps(String value) {
        this.value = value;
    }

    public String val() {
        return value;
    }
}