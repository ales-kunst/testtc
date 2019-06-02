package org.testtc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Calc {
    static final Logger logger = LogManager.getLogger(Calc.class.getName());

    public static long add(long x, long y){
        logger.info("Add function called with params x: {}, y: {}", x, y);
        return x + y;
    }

    public static double div(long num, long denom) {
        if (denom == 0) {
            logger.error("Denomerator should not be 0");
        }
        return (double) num/denom;
    }

    private Calc() {}
}
