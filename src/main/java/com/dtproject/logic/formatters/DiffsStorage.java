package com.dtproject.logic.formatters;

import java.util.ArrayList;
import java.util.List;

public class DiffsStorage {
    final List<String> originalResult = new ArrayList<>();
    final List<String> revisedResult = new ArrayList<>();

    void addAll(DiffsStorage diffsStorage) {
        this.originalResult.addAll(diffsStorage.originalResult);
        this.revisedResult.addAll(diffsStorage.revisedResult);
    }

    public List<String> getOriginalResult() {
        return originalResult;
    }

    public List<String> getRevisedResult() {
        return revisedResult;
    }
}
