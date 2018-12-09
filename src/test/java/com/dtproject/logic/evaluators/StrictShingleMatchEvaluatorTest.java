package com.dtproject.logic.evaluators;

import org.junit.Assert;
import org.junit.Test;

public class StrictShingleMatchEvaluatorTest {
    @Test
    public void generateShingle() throws Exception {
        String str = "Agreement interested\ndiscretion\n\n\n\n\n\non \nstimulated\n Dear\n sing when find\nfdgdfgdf";
        AbstractShingleMatchEvaluator evaluator = new StrictShingleMatchEvaluator();
        int expectedAmountOfShingles = 9;
        Assert.assertEquals(expectedAmountOfShingles, evaluator.generateShingle(str).size());
    }

}