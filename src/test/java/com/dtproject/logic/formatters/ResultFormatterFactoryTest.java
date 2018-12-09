package com.dtproject.logic.formatters;

import org.junit.Assert;
import org.junit.Test;

public class ResultFormatterFactoryTest {
    @Test
    public void getSideResultFormatter() throws Exception {
        String format = "side";
        Assert.assertTrue(ResultFormatterFactory.getResultFormatter(format) instanceof SideBySideResultFormatter);
    }

    @Test
    public void getInlineResultFormatter() throws Exception {
        Assert.assertTrue(ResultFormatterFactory.getResultFormatter("") instanceof InLineResultFormatter);
    }
}