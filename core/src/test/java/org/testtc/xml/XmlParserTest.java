package org.testtc.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

class XmlParserTest {

    public static final String STEP_NUMBER = "stepNumber";
    public static final String RETURN_CODE = "returnCode";
    public static final String CHECK_LOGS = "checkLogs";
    public static final String CHECK_DB = "checkDB";
    public static final String IMPORT_FILE_NAME = "importFileName";
    public static final String FALSE = "false";
    public static final String TRUE = "true";
    public static final String CHECK_EXPORT_FILE = "checkExportFile";
    public static final String MANDANT = "mandant";

    @Test
    void testParse() throws ParserConfigurationException, SAXException, IOException {
        URL url = this.getClass().getResource("/xml/tc_scenarios.xml");
        XmlDocument xmlDocument = XmlParser.parse(new File(url.getFile()));

        Assertions.assertEquals(1, xmlDocument.getXmlElementsSize());
        checkRootElements(xmlDocument.getXmlElements());

    }

    private void checkRootElements(List<XmlElement> xmlElements) {
        XmlElement scenarios = xmlElements.get(0);
        Assertions.assertEquals(8, scenarios.getChildrenSize());
        checkScenario_01(scenarios.getChild(0));

    }

    private void checkScenario_01(XmlElement scenario) {
        Assertions.assertEquals("Scenario", scenario.getName());
        Assertions.assertEquals("", scenario.getValue());
        checkAttribute("scenarioName", "COC_BFTC01_PKV_Diffrent_Partners_Scenarios1", scenario.getAttribute(0));
        checkAttribute("initDB", TRUE, scenario.getAttribute(1));
        checkAttribute("logLevel", "DEBUG", scenario.getAttribute(2));
        checkAttribute("description", "BFTC01 import with different VN, VP and BA partners. This scenario locked VN partner.", scenario.getAttribute(3));
        checkStepsForScenario_01(scenario.getChildren());
    }

    private void checkStepsForScenario_01(List<XmlElement> steps) {
        XmlElement stepBFTC01 = steps.get(0);
        XmlElement stepTCINMP = steps.get(1);
        XmlElement stepZSVS01 = steps.get(2);
        XmlElement stepTCTS03 = steps.get(3);
        XmlElement stepMZ10 = steps.get(4);

        Assertions.assertEquals("BFTC01", stepBFTC01.getName());
        Assertions.assertEquals("", stepBFTC01.getValue());
        checkAttribute(STEP_NUMBER, "1", stepBFTC01.getAttribute(0));
        checkAttribute(RETURN_CODE,"0", stepBFTC01.getAttribute(1));
        checkAttribute(CHECK_LOGS, FALSE, stepBFTC01.getAttribute(2));
        checkAttribute(CHECK_DB, FALSE, stepBFTC01.getAttribute(3));
        checkAttribute(IMPORT_FILE_NAME,"G0x_KV.xml", stepBFTC01.getAttribute(4));

        Assertions.assertEquals("TCINMP", stepTCINMP.getName());
        Assertions.assertEquals("", stepTCINMP.getValue());
        checkAttribute(STEP_NUMBER, "2", stepTCINMP.getAttribute(0));
        checkAttribute(RETURN_CODE,"0", stepTCINMP.getAttribute(1));
        checkAttribute(CHECK_LOGS, FALSE, stepTCINMP.getAttribute(2));
        checkAttribute(CHECK_DB, FALSE, stepTCINMP.getAttribute(3));
        checkAttribute("waitPeriod","30", stepTCINMP.getAttribute(4));

        Assertions.assertEquals("ZSVS01", stepZSVS01.getName());
        Assertions.assertEquals("", stepZSVS01.getValue());
        checkAttribute(STEP_NUMBER, "103", stepZSVS01.getAttribute(0));
        checkAttribute(RETURN_CODE,"0", stepZSVS01.getAttribute(1));
        checkAttribute(CHECK_LOGS, TRUE, stepZSVS01.getAttribute(2));
        checkAttribute(CHECK_DB, TRUE, stepZSVS01.getAttribute(3));
        checkAttribute(IMPORT_FILE_NAME,"COC_VN.xml", stepZSVS01.getAttribute(4));
        checkAttribute("description","", stepZSVS01.getAttribute(5));

        Assertions.assertEquals("TCTS03", stepTCTS03.getName());
        Assertions.assertEquals("", stepTCTS03.getValue());
        checkAttribute(STEP_NUMBER, "104", stepTCTS03.getAttribute(0));
        checkAttribute(RETURN_CODE,"0", stepTCTS03.getAttribute(1));
        checkAttribute(CHECK_LOGS, TRUE, stepTCTS03.getAttribute(2));
        checkAttribute(CHECK_DB, TRUE, stepTCTS03.getAttribute(3));
        checkAttribute(CHECK_EXPORT_FILE, FALSE, stepTCTS03.getAttribute(4));
        checkAttribute(MANDANT,"(1)", stepTCTS03.getAttribute(5));

        Assertions.assertEquals("MZ10", stepMZ10.getName());
        Assertions.assertEquals("", stepMZ10.getValue());
        checkAttribute(STEP_NUMBER, "105", stepMZ10.getAttribute(0));
        checkAttribute(RETURN_CODE,"0", stepMZ10.getAttribute(1));
        checkAttribute(CHECK_LOGS, TRUE, stepMZ10.getAttribute(2));
        checkAttribute(CHECK_DB, TRUE, stepMZ10.getAttribute(3));
        checkAttribute(CHECK_EXPORT_FILE, FALSE, stepMZ10.getAttribute(4));
        checkAttribute(MANDANT,"(1)", stepMZ10.getAttribute(5));
    }

    private void checkAttribute(String name, String value, XmlAttribute attribute) {
        Assertions.assertEquals(name, attribute.getName());
        Assertions.assertEquals(value, attribute.getValue());
    }


}