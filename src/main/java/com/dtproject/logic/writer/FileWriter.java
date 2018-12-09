package com.dtproject.logic.writer;

import com.dtproject.logic.formatters.FormattingType;
import com.dtproject.logic.comparator.ComparedStrings;
import com.dtproject.logic.comparator.Result;
import com.dtproject.logic.utils.DeltaUtils;
import difflib.Delta;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriter implements Writer {

    private final String outputPath;

    public FileWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public void write(Result result) throws IOException {
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputPath))) {
                    writer.write("Match percentage: " + result.getMatchPercentage() + "%" + "\n");
                    for (Delta delta : result.getDeltas()) {
                        int size = DeltaUtils.getDeltaSize(delta);
                        for (int i = 0; i < size; i++) {
                            ComparedStrings deltaLines = DeltaUtils.setDeltaLines(delta, i);
                            DeltaUtils.setDeltaLines(delta, i);
                            writer.write(String.format("[%s] [%s] to [%s] \n", delta.getType(), deltaLines.getOriginal(), deltaLines.getRevised()));
                        }
                    }
                }
    }

    @Override
    public void write(Result result, FileExtension fileExtension, FormattingType formattingType) throws IOException {
        switch (fileExtension) {
            case TXT:
                Generator txtGenerator = new TxtGenerator(outputPath + fileExtension.getExtension());
                txtGenerator.generateDocument(result, formattingType);
                break;
            case PDF:
            default:
                PdfGenerator pdfGenerator = new PdfGenerator(outputPath + fileExtension.getExtension());
                pdfGenerator.generateDocument(result, formattingType);
                break;
        }
    }
}
