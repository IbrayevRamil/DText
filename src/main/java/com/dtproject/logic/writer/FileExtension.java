package com.dtproject.logic.writer;

public enum FileExtension {

    PDF(".pdf"),
    TXT(".txt");

    private final String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
