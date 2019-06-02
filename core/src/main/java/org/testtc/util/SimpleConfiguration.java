package org.testtc.util;

public class SimpleConfiguration implements Configuration {
    String xsdSchemaLocation;

    @Override
    public String getXsdSchemaLocation() {
        return null;
    }

    public static class Builder {

        private String xsdSchemaLocation;

        public Builder() {
            // Empty because it does not need to do anything...
        }

        public Builder xsdSchemaLocation(String xsdSchemaLocation) {
            this.xsdSchemaLocation = xsdSchemaLocation;
            return this;
        }

        public SimpleConfiguration build() {
            SimpleConfiguration sc = new SimpleConfiguration();
            sc.xsdSchemaLocation = this.xsdSchemaLocation;
            return sc;
        }
    }
}
