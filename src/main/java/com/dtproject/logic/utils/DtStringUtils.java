package com.dtproject.logic.utils;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DtStringUtils {

    public static void removeEmptyStrings(List<String> text) {
        text.removeAll(Arrays.asList("", " "));
    }

    //Compared texts must have the same size to avoid wrong result by DiffUtils.diff method
    public static void alignTexts(List<String> originalText, List<String> revisedText) {
        if (originalText.size() > revisedText.size()) {
            for (int i = revisedText.size(); i < originalText.size(); i++) {
                revisedText.add("");
            }
        }
        else {
            for (int i = originalText.size(); i < revisedText.size(); i++) {
                originalText.add("");
            }
        }
    }

    public static List<String> splitText(String parsedText) {
        return Arrays.stream(parsedText.split("\n")).map(String::trim).collect(Collectors.toList());
    }

    public static List<String> getWords(String string) {
        return Arrays.asList(string.split(" "));
    }

}
