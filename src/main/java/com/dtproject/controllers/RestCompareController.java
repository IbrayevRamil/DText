package com.dtproject.controllers;

import com.dtproject.dtos.CompareDto;
import com.dtproject.logic.comparator.DocumentsComparator;
import com.dtproject.logic.comparator.DocumentsComparatorFactory;
import com.dtproject.logic.formatters.FormattingType;
import com.dtproject.logic.comparator.Result;
import com.dtproject.logic.formatters.FormattedResult;
import com.dtproject.logic.formatters.ResultFormatter;
import com.dtproject.logic.formatters.ResultFormatterFactory;

import com.dtproject.logic.parser.TikaParser;
import com.dtproject.logic.writer.FileExtension;
import com.dtproject.logic.writer.FileWriter;
import org.apache.tika.exception.TikaException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/compare")
public class RestCompareController {

    private static final String UPLOAD_FOLDER = System.getProperty("java.io.tmpdir") + "/";

    @GetMapping
    public String compareGet(@RequestParam("source") String source,
                             @RequestParam("target") String target,
                             @RequestParam(value = "mode", required = false) String mode,
                             @RequestParam(value = "format", required = false) String format) throws TikaException, SAXException, IOException {

        String original = TikaParser.parse(source);
        String revised = TikaParser.parse(target);
        DocumentsComparator comparator = DocumentsComparatorFactory.getDocumentsComparator(mode);
        Result result = comparator.compareDocuments(original, revised);
        ResultFormatter resultFormatter = ResultFormatterFactory.getResultFormatter(format);
        FormattedResult fm = resultFormatter.format(result);
        return fm.toString();
    }

    @PostMapping(consumes = "application/json")
    public String comparePost(@RequestBody CompareDto compareDto) throws TikaException, SAXException, IOException {

        String sourcePath = compareDto.getSource();
        String targetPath = compareDto.getTarget();
        String original = TikaParser.parse(sourcePath);
        String revised = TikaParser.parse(targetPath);
        DocumentsComparator comparator = DocumentsComparatorFactory.getDocumentsComparator(compareDto.getMode());
        Result result = comparator.compareDocuments(original, revised);
        ResultFormatter resultFormatter = ResultFormatterFactory.getResultFormatter(compareDto.getFormat());
        FormattedResult fm = resultFormatter.format(result);
        return fm.toString();
    }


    @PostMapping(consumes = "multipart/form-data")
    public void comparePostFile(@RequestParam("source") MultipartFile source,
                                @RequestParam("target") MultipartFile target,
                                @RequestParam(value = "mode", required = false) String mode,
                                @RequestParam(value = "format", required = false) String format,
                                HttpServletResponse response) throws TikaException, SAXException, IOException {

        String pathToSourceFile = saveUploadedFile(source);
        String original = parseUploadedFile(pathToSourceFile);
        String pathToTargetFile = saveUploadedFile(target);
        String revised = parseUploadedFile(pathToTargetFile);
        DocumentsComparator comparator = DocumentsComparatorFactory.getDocumentsComparator(mode);
        Result result = comparator.compareDocuments(original, revised);
        String pathToResultFile = UPLOAD_FOLDER + "result";
        FileWriter fw = new FileWriter(pathToResultFile);
        fw.write(result, FileExtension.PDF, FormattingType.fromString(format));
        response.setHeader("Content-disposition", "inline; filename=" + pathToResultFile + FileExtension.PDF.getExtension());
        response.setContentType("application/pdf");
        response.getOutputStream().write(Files.readAllBytes(Paths.get(pathToResultFile + FileExtension.PDF.getExtension())));
        response.flushBuffer();
    }

    private static String parseUploadedFile(String path) throws TikaException, SAXException, IOException {
        return TikaParser.parse(path);
    }


    private static String saveUploadedFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }
        return UPLOAD_FOLDER + file.getOriginalFilename();
    }
}
