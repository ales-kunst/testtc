package org.testtc.handler;

import java.util.HashMap;
import java.util.Map;

public class CallbackContainer {

    private final Map<String, CallbackFunction> callbacks;

    private CallbackContainer(Map<String, CallbackFunction> callbacks) {
        this.callbacks = callbacks;
    }

    public static Builder builder() {
        return new Builder();
    }

    public CallbackFunction getCallback(String name) {
        return callbacks.get(name);
    }

    public static class Builder {

        private final Map<String, CallbackFunction> callbacks;

        public Builder() {
            callbacks = new HashMap<>();
        }

        public Builder add(String key, CallbackFunction callback) {
            callbacks.put(key, callback);
            return this;
        }

        public CallbackContainer build() {
            return new CallbackContainer(callbacks);
        }
    }
}
