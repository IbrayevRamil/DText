package com.dtproject.logic.writer;

import com.dtproject.logic.formatters.FormattingType;
import com.dtproject.logic.comparator.Result;
import com.dtproject.logic.formatters.FormattedResult;
import com.dtproject.logic.formatters.ResultFormatter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PdfGenerator implements Generator {

    private final String path;

    public PdfGenerator(String path) {
        this.path = path;
    }

    @Override
    public void generateDocument(Result result, FormattingType formattingType) throws IOException {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDFont font = PDType1Font.TIMES_ROMAN;

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        ResultFormatter resultFormatter = formattingType.getResultFormatter();
        FormattedResult formattedResult = resultFormatter.format(result);

        contentStream.beginText();
        contentStream.setFont(font, 10);
        contentStream.setLeading(15f);
        contentStream.newLineAtOffset(20, 750);
        contentStream.showText("Match percentage: " + String.valueOf(formattedResult.getMatchPercentage()) + "%");
        contentStream.endText();
        writeBody(contentStream, formattedResult, formattingType);

        contentStream.close();

        document.save(path);
        document.close();
    }

    private static void writeBody(PDPageContentStream contentStream, FormattedResult formattedResult, FormattingType formattingType) throws IOException {
        switch (formattingType) {
            case INLINE:
                contentStream.beginText();
                contentStream.setLeading(15f);
                contentStream.newLineAtOffset(20, 735);
                contentStream.newLine();
                for (String line : formattedResult.getDiffsStorage().getRevisedResult()) {
                    contentStream.showText(line);
                    contentStream.newLine();
                }
                contentStream.endText();
                break;
            case SIDE_BY_SIDE:
                float yCursorPosition = 735;
                for (int i = 0; i < formattedResult.getDiffsStorage().getOriginalResult().size(); i++) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(20, yCursorPosition);
                    contentStream.showText(formattedResult.getDiffsStorage().getOriginalResult().get(i));
                    contentStream.endText();
                    contentStream.beginText();
                    contentStream.newLineAtOffset(120, yCursorPosition);
                    contentStream.showText(formattedResult.getDiffsStorage().getRevisedResult().get(i));
                    contentStream.endText();
                    yCursorPosition -= 15f;
                }
                break;
            default:
                contentStream.beginText();
                contentStream.setLeading(15f);
                contentStream.newLineAtOffset(20, 735);
                contentStream.newLine();
                for (String line : formattedResult.getDiffsStorage().getRevisedResult()) {
                    contentStream.showText(line);
                    contentStream.newLine();
                }
                contentStream.endText();
                break;
        }
    }
}
