package com.dtproject.logic.formatters;

import org.junit.Assert;
import org.junit.Test;

public class FormattingTypeTest {
    @Test
    public void fromString() throws Exception {
        Assert.assertEquals(FormattingType.INLINE, FormattingType.fromString("inline"));
        Assert.assertEquals(FormattingType.SIDE_BY_SIDE, FormattingType.fromString("side_by_side"));
    }

    @Test
    public void getResultFormatter() throws Exception {
        Assert.assertTrue(FormattingType.INLINE.getResultFormatter() instanceof InLineResultFormatter);
        Assert.assertTrue(FormattingType.SIDE_BY_SIDE.getResultFormatter() instanceof SideBySideResultFormatter);
    }

}