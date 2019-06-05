package org.testtc.handler;

import org.testtc.xml.XmlElement;

import java.util.function.Function;
import java.util.logging.Logger;

public class DoNothingCallback implements CallbackFunction {

    private static final Logger LOG =Logger.getLogger(DoNothingCallback.class.getName());

    @Override
    public ReturnCode apply(XmlElement xmlElement, CallbackContext callbackContext) {
        LOG.info("Do nothing...");
        return ReturnCode.WARNING;
    }
}
