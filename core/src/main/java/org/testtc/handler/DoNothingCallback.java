package org.testtc.handler;

import org.testtc.xml.XmlElement;

public class DoNothingCallback implements CallbackFunction {

    @Override
    public ReturnCode apply(XmlElement xmlElement, CallbackContext callbackContext) {
        return ReturnCode.WARNING;
    }
}
