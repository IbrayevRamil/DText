package com.dtproject.logic.parser;

import com.dtproject.logic.utils.DtStringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TikaParserTest {

    private List<String> generateText() {
        String[] textArr = {
                "Agreement interested discretion estimating on stimulated Dear sing when find",
                "READ OF CALL. As distrusts behaviour defective is. Never at water me might. On formed merits",
                "hunted unable merely by mr whence why or. Possession unpleasing simplicity her gyy.",
                "Dasdsadasdasd",
                "Dasdsadsadsa"
        };
        return Arrays.asList(textArr);
    }

    @Test
    public void parseDocx() throws Exception {
        List<String> originalText = generateText();
        List<String> revisedText = DtStringUtils.splitText(TikaParser.parse("testOriginal.docx"));
        DtStringUtils.removeEmptyStrings(revisedText);
        Assert.assertNotNull(revisedText);
        Assert.assertEquals(originalText, revisedText);
    }
}