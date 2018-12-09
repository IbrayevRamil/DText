package com.dtproject.logic.writer;

import com.dtproject.logic.formatters.FormattingType;
import com.dtproject.logic.comparator.Result;
import com.dtproject.logic.formatters.FormattedResult;
import com.dtproject.logic.formatters.ResultFormatter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TxtGenerator implements Generator {

    private final String path;

    @Override
    public void generateDocument(Result result, FormattingType formattingType) throws IOException {
        ResultFormatter resultFormatter = formattingType.getResultFormatter();
        FormattedResult formattedResult = resultFormatter.format(result);
        try (PrintStream writer = new PrintStream(Paths.get(path).toFile())) {
            writer.println("Match percentage: " + formattedResult.getMatchPercentage() + "%");
            writeBody(writer, formattedResult, formattingType);
        }

    }

    private static void writeBody(PrintStream writer, FormattedResult formattedResult, FormattingType formattingType) throws IOException {
        switch (formattingType) {
            case INLINE:
                for (String line : formattedResult.getDiffsStorage().getRevisedResult()) {
                    writer.println(line.trim());
                }
                break;
            case SIDE_BY_SIDE:
                for (int i = 0; i < formattedResult.getDiffsStorage().getOriginalResult().size(); i++) {
                    writer.printf("%-30.30s  %-30.30s\n", formattedResult.getDiffsStorage().getOriginalResult().get(i),
                            formattedResult.getDiffsStorage().getRevisedResult().get(i));
                }
                break;
            default:
                for (String line : formattedResult.getDiffsStorage().getRevisedResult()) {
                    writer.println(line.trim());
                }
                break;
        }
    }

    public TxtGenerator(String path) {
        this.path = path;
    }


}

