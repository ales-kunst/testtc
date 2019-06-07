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
                .add(TcSteps.TCTS03.val(), getTCTS03Function())
                .add(TcSteps.MZ10.val(), getMZ10Function())
                .build();
        emptyCallbackExecutor = new CallbackExecutor(emptyCallbackContainer, getDoNothingFunction());
        callbackExecutor = new CallbackExecutor(callbackContainer, getDoNothingFunction());
    }

    private CallbackFunction getBFTC01Function() {
        return (xmlElem, ctx) -> {
            ctx.addResult(TcSteps.BFTC01.val(), "BFTC01 result");
            return ReturnCode.OK;
        };
    }

    private CallbackFunction getTCINMPFunction() {
        return (xmlElem, ctx) -> {
            ctx.addResult(TcSteps.TCINMP.val(), "TCINMP result");
            return ReturnCode.OK;
        };
    }

    private CallbackFunction getZSVS01Function() {
        return (xmlElem, ctx) -> {
            ctx.addResult(TcSteps.ZSVS01.val(), "ZSVS01 result");
            return ReturnCode.OK;
        };
    }

    private CallbackFunction getTCTS03Function() {
        return (xmlElem, ctx) -> {
            ctx.addResult(TcSteps.TCTS03.val(), "TCTS03 result");
            return ReturnCode.OK;
        };
    }

    private CallbackFunction getMZ10Function() {
        return (xmlElem, ctx) -> {
            ctx.addResult(TcSteps.MZ10.val(), "MZ10 result");
            return ReturnCode.OK;
        };
    }

    private CallbackFunction getDoNothingFunction() {
        return (xmlElem, ctx) -> {
            ctx.addResult(TcSteps.DNO.val(), "Nothing processed");
            return ReturnCode.OK;
        };
    }

    @Test
    void execute() {
        checkEmptyExecutor();
        checkExecutor();
    }

    private void checkExecutor() {
        CallbackContext ctx = new CallbackContext();
        // Scenarios
        XmlElement scenarios = xmlDocument.getXmlElement(0);
        // Scenario
        XmlElement scenario = scenarios.getChild(0);
        for (XmlElement step : scenario.getChildren()) {
            callbackExecutor.execute(step, ctx);
        }
        Assertions.assertEquals(5, ctx.getResults().size());
        String resultVal = ctx.getResult(TcSteps.BFTC01.val()).get(0).getValue();
        Assertions.assertEquals("BFTC01 result", resultVal);
        resultVal = ctx.getResult(TcSteps.TCINMP.val()).get(0).getValue();
        Assertions.assertEquals("TCINMP result", resultVal);
        resultVal = ctx.getResult(TcSteps.ZSVS01.val()).get(0).getValue();
        Assertions.assertEquals("ZSVS01 result", resultVal);
        resultVal = ctx.getResult(TcSteps.TCTS03.val()).get(0).getValue();
        Assertions.assertEquals("TCTS03 result", resultVal);
        resultVal = ctx.getResult(TcSteps.MZ10.val()).get(0).getValue();
        Assertions.assertEquals("MZ10 result", resultVal);
    }

    private void checkEmptyExecutor() {
        CallbackContext ctx = new CallbackContext();
        emptyCallbackExecutor.execute(xmlDocument.getXmlElement(0), ctx);
        Assertions.assertEquals(1, ctx.getResults().size());
        String result = ctx.getResult(TcSteps.DNO.val()).get(0).getValue();
        Assertions.assertEquals(1, ctx.getResult(TcSteps.DNO.val()).size());
        Assertions.assertEquals("Nothing processed", result);

        ctx = new CallbackContext();
        callbackExecutor.execute(xmlDocument.getXmlElement(0), ctx);
    }
}

enum TcSteps {

    BFTC01("BFTC01"),
    TCINMP("TCINMP"),
    ZSVS01("ZSVS01"),
    TCTS03("TCTS03"),
    MZ10("MZ10"),
    DNO("DNO");

    private String value;

    TcSteps(String value) {
        this.value = value;
    }

    public String val() {
        return value;
    }
}