package com.dtproject.logic.formatters;

import com.dtproject.logic.comparator.DocumentsComparator;
import com.dtproject.logic.comparator.Result;
import com.dtproject.logic.comparator.StrictDocumentsComparator;
import com.dtproject.logic.parser.TikaParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SideBySideResultFormatterTest {

    private final static String ORIGINAL_FILE = "testOriginal.docx";
    private final static String REVISED_FILE = "testRevised.docx";

    @Test
    public void format() throws Exception {

        String original = TikaParser.parse(ORIGINAL_FILE);
        String revised = TikaParser.parse(REVISED_FILE);
        DocumentsComparator comparator = new StrictDocumentsComparator();
        Result result = comparator.compareDocuments(original, revised);
        FormattedResult formattedResult = new SideBySideResultFormatter().format(result);
        String[] originalTextArr = {
                "Agreement",
                "discretion",
                "-",
                "READ OF CALL.",
                "me",
                "-",
                "gyy.",
                "Dasdsadasdasd",
                "Dasdsadsadsa",
        };
        List<String> textOriginal = Arrays.asList(originalTextArr);
        String[] revisedTextArr = {
                "Compliment",
                "foundation",
                "oh.",
                "read of call.",
                "po",
                "why",
                "uncommonly.",
                "-",
                "-",
        };
        List<String> textRevised = Arrays.asList(revisedTextArr);
        Assert.assertEquals(textOriginal, formattedResult.getDiffsStorage().getOriginalResult());
        Assert.assertEquals(textRevised, formattedResult.getDiffsStorage().getRevisedResult());

    }

}