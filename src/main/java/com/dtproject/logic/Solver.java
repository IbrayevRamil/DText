package com.dtproject.logic;

import com.dtproject.logic.comparator.DocumentsComparator;
import com.dtproject.logic.comparator.DocumentsComparatorFactory;
import com.dtproject.logic.comparator.Result;
import com.dtproject.logic.formatters.FormattingType;
import com.dtproject.logic.parser.TikaParser;
import com.dtproject.logic.writer.FileExtension;
import com.dtproject.logic.writer.FileWriter;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;

public class Solver {
    public static void main(String[] args) throws IOException, SAXException, TikaException {

        String original = TikaParser.parse(args[0]);
        String revised = TikaParser.parse(args[1]);


        DocumentsComparator documentsComparator = DocumentsComparatorFactory.getDocumentsComparator(args);

        Result result = documentsComparator.compareDocuments(original, revised);

        FileWriter fileWriter = new FileWriter(args[2]);
        fileWriter.write(result, FileExtension.TXT, FormattingType.INLINE);
    }
}
