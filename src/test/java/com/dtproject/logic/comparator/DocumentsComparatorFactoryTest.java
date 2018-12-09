package com.dtproject.logic.comparator;

import org.junit.Assert;
import org.junit.Test;

public class DocumentsComparatorFactoryTest {
    @Test
    public void getStrictMatchEvaluator() throws Exception {
        String mode = "strict";
        Assert.assertTrue(DocumentsComparatorFactory.getDocumentsComparator(mode) instanceof StrictDocumentsComparator);
    }

    @Test
    public void getDefaultMatchEvaluator() throws Exception {
        Assert.assertTrue(DocumentsComparatorFactory.getDocumentsComparator("") instanceof DefaultDocumentsComparator);
    }
}