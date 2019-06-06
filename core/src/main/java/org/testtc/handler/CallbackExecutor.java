package org.testtc.handler;

import org.testtc.xml.XmlElement;

public class CallbackExecutor {

    private final CallbackContainer callbackContainer;
    private final CallbackFunction doNothingCallback;


    public CallbackExecutor(CallbackContainer callbackContainer) {
        this.callbackContainer = callbackContainer;
        this.doNothingCallback = new DoNothingCallback();
    }

    public CallbackExecutor(CallbackContainer callbackContainer, CallbackFunction doNothingCallback) {
        this.callbackContainer = callbackContainer;
        this.doNothingCallback = doNothingCallback;
    }

    public ReturnCode execute(XmlElement xmlElement, CallbackContext ctx) {
        CallbackFunction callback = callbackContainer.getCallback(xmlElement.getName());
        ReturnCode result = ReturnCode.NO_PROCESSING;
        if (callback != null) {
            result = callback.apply(xmlElement, ctx);
        } else if (doNothingCallback != null) {
            result = doNothingCallback.apply(xmlElement, ctx);
        }
        return result;
    }
}
