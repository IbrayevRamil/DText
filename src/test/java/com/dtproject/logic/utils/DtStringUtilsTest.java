package com.dtproject.logic.utils;


import com.dtproject.logic.parser.TikaParser;
import org.apache.tika.exception.TikaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

public class DtStringUtilsTest {

    private final static String ORIGINAL_FILE = "testOriginal.docx";
    private String parsedText;

    @Before
    public void setUp() throws TikaException, SAXException, IOException {
        parsedText = TikaParser.parse(ORIGINAL_FILE);
    }

    @Test
    public void checkForEmptyStrings() throws Exception {
        List<String> text = DtStringUtils.splitText(parsedText);
        DtStringUtils.removeEmptyStrings(text);
        int expectedAmountOfLines = 5;
        Assert.assertEquals(expectedAmountOfLines, text.size());
    }

    @Test
    public void splitText() throws Exception {
        int linesQuantity = 5;
        Assert.assertEquals(linesQuantity, DtStringUtils.splitText(parsedText).size());
    }

    @Test
    public void getWords() throws Exception {
        String str = "He She they want do go";
        int numberOfWords = 6;
        Assert.assertEquals(numberOfWords, DtStringUtils.getWords(str).size());
    }

}