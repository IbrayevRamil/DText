package com.dtproject.logic.utils;

import com.dtproject.logic.comparator.*;
import com.dtproject.logic.parser.TikaParser;
import difflib.Delta;
import org.apache.tika.exception.TikaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

public class DeltaUtilsTest {

    private final static String ORIGINAL_FILE = "testOriginal.docx";
    private final static String REVISED_FILE = "testRevised.docx";
    private Result result;

    @Before
    public void setUp() throws TikaException, SAXException, IOException {
        String original = TikaParser.parse(ORIGINAL_FILE);
        String revised = TikaParser.parse(REVISED_FILE);
        DocumentsComparator strictComparator = new StrictDocumentsComparator();
        result = strictComparator.compareDocuments(original, revised);
    }

    @Test
    public void getDeltaSize() throws Exception {
        Delta delta = result.getDeltas().get(0);
        Assert.assertEquals(Delta.TYPE.CHANGE, delta.getType());
        int expectedDeltaSize = 5;
        Assert.assertEquals(expectedDeltaSize, DeltaUtils.getDeltaSize(delta));
    }

    @Test
    public void setDeltaLines() throws Exception {
        Delta delta = result.getDeltas().get(0);
        Assert.assertEquals(Delta.TYPE.CHANGE, delta.getType());
        ComparedStrings expectedComparedStrings = new ComparedStrings(delta.getOriginal().getLines().get(0).toString(),
                delta.getRevised().getLines().get(0).toString());
        ComparedStrings actualComparedStrings = DeltaUtils.setDeltaLines(delta, 0);
        Assert.assertEquals(expectedComparedStrings.getOriginal(), actualComparedStrings.getOriginal());
        Assert.assertEquals(expectedComparedStrings.getRevised(), actualComparedStrings.getRevised());

    }

}