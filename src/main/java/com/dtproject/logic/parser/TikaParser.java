package com.dtproject.logic.parser;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;

import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

public class TikaParser {

    private static BodyContentHandler parseDocument(String documentPath) throws IOException, SAXException, TikaException {

        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();

        try (FileInputStream inputStream = new FileInputStream(new File(documentPath))) {
            parser.parse(inputStream, handler, metadata, context);
        }
        return handler;
    }

    public static String parse(String documentPath) throws IOException, SAXException, TikaException {
        return parseDocument(documentPath).toString().trim();
    }



}
