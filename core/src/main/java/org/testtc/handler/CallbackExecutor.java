package org.testtc.handler;

import org.testtc.xml.XmlElement;

import java.util.function.Function;

public class CallbackExecutor {

    private final CallbackContainer callbackContainer;


    public CallbackExecutor(CallbackContainer callbackContainer) {
        this.callbackContainer = callbackContainer;
    }

    public ReturnCode execute(XmlElement xmlElement, CallbackContext ctx) {
        CallbackFunction callback = callbackContainer.getCallback(xmlElement.getName());
        return callback.apply(xmlElement, ctx);
    }
}
