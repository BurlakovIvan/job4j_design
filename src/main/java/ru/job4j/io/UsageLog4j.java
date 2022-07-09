package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte age = 25;
        short amount = 20000;
        int count = 1254789;
        long distance = 808049706L;
        char gender = 'М';
        float height = 1.8F;
        double distanceSpace = 3.41E+27;
        boolean correct = true;
        LOG.debug("byte : {}, short : {}, int : {}, long : {}, "
                + "char : {}, float : {}, double : {}, boolean : {}",
                age, amount, count, distance, gender,  height, distanceSpace, correct);
        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}