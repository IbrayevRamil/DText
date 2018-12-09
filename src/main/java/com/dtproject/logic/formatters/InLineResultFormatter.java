package com.dtproject.logic.formatters;

class InLineResultFormatter extends AbstractResultFormatter {

    @Override
    DiffsStorageAccumulator createDiffsStorageAccumulator() {
        return new DiffsStorageAccumulator() {
            private final StringBuilder buffer = new StringBuilder();

            @Override
            void startNewDiff() {
                buffer.setLength(0);
            }

            @Override
            void doInsert(String currentText) {
                buffer.append("[+")
                        .append(currentText.trim())
                        .append("] ");
            }

            @Override
            void doPartialChange(String originalText, String revisedText, String matchingText, String wordTail) {
                doChange(originalText + wordTail, revisedText + wordTail);
                buffer.append(matchingText);
            }

            @Override
            void doChange(String currentText, String nextText) {
                buffer.append("[").append(currentText).append("->").append(nextText).append("]");
            }

            @Override
            void doDelete(String currentText) {
                buffer.append("[-").append(currentText.trim()).append("] ");
            }

            @Override
            void doEqual(String currentText) {
                buffer.append(currentText);
            }

            @Override
            void finalizeCurrentDiff() {
                storage.revisedResult.add(buffer.toString());
            }
        };
    }
}
