package com.dtproject.logic.comparator;

import com.dtproject.logic.evaluators.DefaultShingleMatchEvaluator;
import difflib.Delta;
import difflib.Patch;
import org.junit.Assert;
import org.junit.Test;

public class DefaultDocumentsComparatorTest {

    @Test
    public void getMatchEvaluator() throws Exception {
        DocumentsComparator comparator = new DefaultDocumentsComparator();
        Assert.assertTrue(comparator.getMatchEvaluator() instanceof DefaultShingleMatchEvaluator);
    }

    @Test
    public void findDifference() throws Exception {
        String original = "Agreement interested\ndiscretion\non \nstimulated\n Dear\n sing when find\nfdgdfgdf";
        String revised = "Compliment interested\nfoundation\nestimating on stimulated oh. Dear sing when find";
        DocumentsComparator comparator = new DefaultDocumentsComparator();
        Patch patch = comparator.findDifference(original, revised);
        int expectedAmountOfChanges = 7;
        Delta delta = patch.getDeltas().get(0);
        Assert.assertEquals(delta.getOriginal().size(), delta.getRevised().size());
        Assert.assertEquals(expectedAmountOfChanges, delta.getOriginal().size());
    }

}