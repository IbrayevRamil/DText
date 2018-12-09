package com.dtproject.logic.formatters;

import com.dtproject.logic.comparator.DocumentsComparator;
import com.dtproject.logic.comparator.Result;
import com.dtproject.logic.comparator.StrictDocumentsComparator;
import com.dtproject.logic.parser.TikaParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class InLineResultFormatterTest {

    private final static String ORIGINAL_FILE = "testOriginal.docx";
    private final static String REVISED_FILE = "testRevised.docx";


    @Test
    public void format() throws Exception {
        String original = TikaParser.parse(ORIGINAL_FILE);
        String revised = TikaParser.parse(REVISED_FILE);
        DocumentsComparator comparator = new StrictDocumentsComparator();
        Result result = comparator.compareDocuments(original, revised);
        FormattedResult formattedResult = new InLineResultFormatter().format(result);
        String[] textArr = {
                "[Agreement->Compliment] interested [discretion->foundation] estimating on stimulated[+oh.]  Dear sing when find",
                "[READ OF CALL.->read of call.] As distrusts behaviour defective is. Never at water [me->po] might. On [+why] formed merits",
                "hunted unable merely by mr whence why or. Possession unpleasing simplicity her [gyy.->uncommonly.]",
                "[-Dasdsadasdasd] ",
                "[-Dasdsadsadsa] "
        };
        List<String> text = Arrays.asList(textArr);

        Assert.assertEquals(text, formattedResult.getDiffsStorage().getRevisedResult());

    }

}