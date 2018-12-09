package com.dtproject.logic.evaluators;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LcsAlgorithmTest {
    @Test
    public void findLcs() throws Exception {

        String[] str1 = new String[]{"Compliment", "interested", "discretion", "estimating", "on", "stimulated" };
        String[] str2 = new String[]{"interested", "dsf", "discretion", "estimating", "bb", "stimulated" };
        List<String> listStr1 = Arrays.asList(str1);
        List<String> listStr2 = Arrays.asList(str2);
        LcsAlgorithm.findLcs(listStr1, listStr2);
        int sizeOfSubseq1 = 0;
        int sizeOfSubseq2 = 0;
        for (String word : listStr1) {
            if (word.equals("")) {
                sizeOfSubseq1++;
            }
        }
        for (String word : listStr2) {
            if (word.equals("")) {
                sizeOfSubseq2++;
            }
        }
        Assert.assertEquals(sizeOfSubseq1, sizeOfSubseq2);
    }

}