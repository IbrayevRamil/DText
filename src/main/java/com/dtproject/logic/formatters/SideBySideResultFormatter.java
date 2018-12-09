package com.dtproject.logic.formatters;

class SideBySideResultFormatter extends AbstractResultFormatter {

    @Override
    DiffsStorageAccumulator createDiffsStorageAccumulator() {
        return new DiffsStorageAccumulator() {
            @Override
            void doInsert(String currentText) {
                doChange("-", currentText);
            }

            @Override
            void doPartialChange(String originalText, String revisedText, String matchingText, String wordTail) {
                doChange(originalText + wordTail, revisedText + wordTail);
            }

            @Override
            void doChange(String currentText, String nextText) {
                storage.originalResult.add(currentText.trim());
                storage.revisedResult.add(nextText.trim());
            }

            @Override
            void doDelete(String currentText) {
                doChange(currentText, "-");
            }

            @Override
            void doEqual(String currentText) {
                // do nothing
            }
        };
    }
}
