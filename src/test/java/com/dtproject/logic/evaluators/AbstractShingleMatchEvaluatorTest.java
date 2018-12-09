package com.dtproject.logic.evaluators;


import org.junit.Assert;
import org.junit.Test;

public class AbstractShingleMatchEvaluatorTest {

    @Test
    public void canonize() throws Exception {
        String str = "asd, gdfg. gfdg, gsfg, ghh\n";
        StrictShingleMatchEvaluator evaluator = new StrictShingleMatchEvaluator();
        str = evaluator.canonize(str);
        String expectedStr = "asd gdfg gfdg gsfg ghh";
        Assert.assertEquals(expectedStr, str);
    }

    @Test
    public void evaluate() throws Exception {
        String original = "AsdDgSdG\nfsdfsdf\ngfg";
        String revised = "AsdDgSdG\nfsdfsdf\ngfg";
        StrictShingleMatchEvaluator evaluator = new StrictShingleMatchEvaluator();
        Double expectedPercentage = 100.00;
        Double actualPercentage = evaluator.evaluate(original, revised);
        Assert.assertEquals(expectedPercentage, actualPercentage);
    }

}