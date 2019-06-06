package org.testtc.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testtc.xml.XmlDocument;
import org.testtc.xml.XmlElement;
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
    private CallbackExecutor emptyCallbackExecutor;
    private CallbackExecutor callbackExecutor;

    @BeforeEach
    void setUp() throws ParserConfigurationException, SAXException, IOException {
        URL url = this.getClass().getResource("/xml/tc_scenarios.xml");
        xmlDocument = XmlParser.parse(new File(url.getFile()));
        emptyCallbackContainer = CallbackContainer.builder().build();

        callbackContainer = CallbackContainer.builder()
                .add(TcSteps.BFTC01.val(), getBFTC01Function())
                .add(TcSteps.TCINMP.val(), getTCINMPFunction())
                .add(TcSteps.ZSVS01.val(), getZSVS01Function())
                .add(TcSteps.TCTS03.val(), getTCTS03Function()).build();
        emptyCallbackExecutor = new CallbackExecutor(emptyCallbackContainer, getDoNothingFunction());
        callbackExecutor = new CallbackExecutor(callbackContainer, getDoNothingFunction());
    }

    private CallbackFunction getBFTC01Function() {
        return (xmlElem, ctx) -> {
            ctx.addResult("BFTC01", "BFTC01 value");
            return ReturnCode.OK;
        };
    }

    private CallbackFunction getTCINMPFunction() {
        return (xmlElem, ctx) -> {
            ctx.addResult("TCINMP", "TCINMP value");
            return ReturnCode.OK;
        };
    }

    private CallbackFunction getZSVS01Function() {
        return (xmlElem, ctx) -> {
            ctx.addResult("ZSVS01", "ZSVS01 value");
            return ReturnCode.OK;
        };
    }

    private CallbackFunction getTCTS03Function() {
        return (xmlElem, ctx) -> {
            ctx.addResult("TCTS03", "TCTS03 value");
            return ReturnCode.OK;
        };
    }

    private CallbackFunction getDoNothingFunction() {
        return (xmlElem, ctx) -> {
            ctx.addResult("Nothing processed", "Nothing processed");
            return ReturnCode.OK;
        };
    }

    @Test
    void execute() {
        CallbackContext ctx = new CallbackContext();
        emptyCallbackExecutor.execute(xmlDocument.getXmlElement(0), ctx);
        Assertions.assertEquals(1, ctx.getResults().size());
        String msg = ctx.getResult("Nothing processed").get(0).getValue();
        Assertions.assertEquals(1, ctx.getResult("Nothing processed").size());
        Assertions.assertEquals("Nothing processed", msg);

        ctx = new CallbackContext();
        callbackExecutor.execute(xmlDocument.getXmlElement(0), ctx);
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