package com.dtproject.logic.comparator;

import java.util.Arrays;

public class DocumentsComparatorFactory {

    private DocumentsComparatorFactory() {
    }

    public static DocumentsComparator getDocumentsComparator(String[] args) {
        return Arrays.asList(args).contains("-strict") ?
                new StrictDocumentsComparator() :
                new DefaultDocumentsComparator();
    }

    public static DocumentsComparator getDocumentsComparator(String mode) {
        return "strict".equals(mode) ?
                new StrictDocumentsComparator() :
                new DefaultDocumentsComparator();
    }

}
