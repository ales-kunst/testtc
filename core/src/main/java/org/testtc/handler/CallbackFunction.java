package org.testtc.handler;

import org.testtc.xml.XmlElement;

import java.util.function.BiFunction;

public interface CallbackFunction extends BiFunction<XmlElement, CallbackContext, ReturnCode> {
}
