package org.testtc.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CallbackContext {

    private final List<Result> callbackResults;

    public CallbackContext() {
        callbackResults = new ArrayList<>();
    }

    public int resultsSize() {
        return callbackResults.size();
    }

    public <V> boolean addResult(String key, V value) {
        return callbackResults.add(new Result(key, value));
    }

    public List<Result> getResult(String key) {
        List<Result> results = callbackResults.stream().filter(result -> result.equals(key)).collect(Collectors.toList());
        return Collections.unmodifiableList(results);
    }

    public List<Result> getResults() {
        return Collections.unmodifiableList(callbackResults);
    }

    public static class Result {
        private final String key;
        private final Object value;

        public Result(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public <V> V getValue() {
            return (V) value;
        }
    }
}
