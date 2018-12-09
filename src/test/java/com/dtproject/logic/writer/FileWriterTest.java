package com.dtproject.logic.writer;

import com.dtproject.logic.comparator.DocumentsComparator;
import com.dtproject.logic.comparator.StrictDocumentsComparator;
import com.dtproject.logic.formatters.FormattingType;
import com.dtproject.logic.comparator.Result;

import com.dtproject.logic.parser.TikaParser;
import org.apache.tika.exception.TikaException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FileWriterTest {

    private final static String ORIGINAL_FILE = "testOriginal.docx";
    private final static String REVISED_FILE = "testRevised.docx";
    private final static String RESULT_FILE = "output";
    private Result result;

    @Before
    public void setUp() throws TikaException, SAXException, IOException {
        String original = TikaParser.parse(ORIGINAL_FILE);
        String revised = TikaParser.parse(REVISED_FILE);
        DocumentsComparator comparator = new StrictDocumentsComparator();
        result = comparator.compareDocuments(original, revised);
    }

    @After
    public void tearDown() throws IOException {
        Files.delete(Paths.get(RESULT_FILE + FileExtension.TXT.getExtension()));
    }

    @Test
    public void writeSideBySide() throws Exception {
        FileWriter fileWriter = new FileWriter(RESULT_FILE);
        fileWriter.write(result, FileExtension.TXT, FormattingType.SIDE_BY_SIDE);
        String[] textArr = {
                "Match percentage: 75.0%",
                "Agreement                       Compliment                    ",
                "discretion                      foundation                    ",
                "-                               oh.                           ",
                "READ OF CALL.                   read of call.                 ",
                "me                              po                            ",
                "-                               why                           ",
                "gyy.                            uncommonly.                   ",
                "Dasdsadasdasd                   -                             ",
                "Dasdsadsadsa                    -                             "
        };
        List<String> text = Arrays.asList(textArr);
        List<String> textFromFile = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(RESULT_FILE + FileExtension.TXT.getExtension()))) {
            stream.forEach(textFromFile::add);
        }
        Assert.assertEquals(text, textFromFile);
    }

    @Test
    public void writeInLine() throws Exception {
        FileWriter fileWriter = new FileWriter(RESULT_FILE);
        fileWriter.write(result, FileExtension.TXT, FormattingType.INLINE);
        String[] textArr = {
                "Match percentage: 75.0%",
                "[Agreement->Compliment] interested [discretion->foundation] estimating on stimulated[+oh.]  Dear sing when find",
                "[READ OF CALL.->read of call.] As distrusts behaviour defective is. Never at water [me->po] might. On [+why] formed merits",
                "hunted unable merely by mr whence why or. Possession unpleasing simplicity her [gyy.->uncommonly.]",
                "[-Dasdsadasdasd]",
                "[-Dasdsadsadsa]"
        };
        List<String> text = Arrays.asList(textArr);
        List<String> textFromFile = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(RESULT_FILE + FileExtension.TXT.getExtension()))) {
            stream.forEach(textFromFile::add);
        }
        Assert.assertEquals(text, textFromFile);
    }

    @Test
    public void write() throws Exception {
        FileWriter fileWriter = new FileWriter(RESULT_FILE + FileExtension.TXT.getExtension());
        fileWriter.write(result);
        String[] textArr = {
                "Match percentage: 75.0%",
                "[CHANGE] [Agreement interested discretion estimating on stimulated Dear sing when find] " +
                        "to [Compliment interested foundation estimating on stimulated oh. Dear sing when find] ",
                "[CHANGE] [READ OF CALL. As distrusts behaviour defective is. Never at water me might. On formed merits] " +
                        "to [read of call. As distrusts behaviour defective is. Never at water po might. On why formed merits] ",
                "[CHANGE] [hunted unable merely by mr whence why or. Possession unpleasing simplicity her gyy.] " +
                        "to [hunted unable merely by mr whence why or. Possession unpleasing simplicity her uncommonly.] ",
                "[CHANGE] [Dasdsadasdasd] to [] ",
                "[CHANGE] [Dasdsadsadsa] to [] "
        };
        List<String> text = Arrays.asList(textArr);
        List<String> textFromFile = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(RESULT_FILE + FileExtension.TXT.getExtension()))) {
            stream.forEach(textFromFile::add);
        }
        Assert.assertEquals(text, textFromFile);
    }

}