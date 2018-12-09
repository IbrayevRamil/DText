package com.dtproject.logic.formatters;

import com.dtproject.logic.comparator.ComparedStrings;
import com.dtproject.logic.comparator.Result;
import com.dtproject.logic.utils.DeltaUtils;
import difflib.Delta;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractResultFormatter implements ResultFormatter {

    @Override
    public FormattedResult format(Result result) {
        DiffsStorage diffsStorage = new DiffsStorage();
        result.getDeltas().forEach(delta -> diffsStorage.addAll(findDiffs(delta)));
        return new FormattedResult(diffsStorage, result.getMatchPercentage());
    }

    private DiffsStorage findDiffs(Delta delta) {
        DiffMatchPatch dmp = new DiffMatchPatch();
        int size = DeltaUtils.getDeltaSize(delta);
        DiffsStorageAccumulator diffsAccumulator = createDiffsStorageAccumulator();
        for (int currentDeltaIndex = 0; currentDeltaIndex < size; currentDeltaIndex++) {
            ComparedStrings deltaLines = DeltaUtils.setDeltaLines(delta, currentDeltaIndex);
            LinkedList<DiffMatchPatch.Diff> diffsList = dmp.diffMain(deltaLines.getOriginal(), deltaLines.getRevised());
            dmp.diffCleanupSemantic(diffsList);
            List<DiffMatchPatch.Diff> diffs = new ArrayList<>(diffsList);

            diffsAccumulator.startNewDiff();
            for (int currentDiffIndex = 0; currentDiffIndex < diffs.size(); currentDiffIndex++) {
                DiffMatchPatch.Diff currentDiff = diffs.get(currentDiffIndex);
                switch (currentDiff.operation) {
                    case INSERT:
                        diffsAccumulator.doInsert(currentDiff.text);
                        break;
                    case DELETE:
                        currentDiffIndex = diffsAccumulator.doDeleteAndGetNewIndex(diffs, currentDiffIndex);
                        break;
                    case EQUAL:
                        diffsAccumulator.doEqual(currentDiff.text);
                        break;
                }
            }
            diffsAccumulator.finalizeCurrentDiff();
        }
        return diffsAccumulator.storage;
    }

    abstract DiffsStorageAccumulator createDiffsStorageAccumulator();

    static String extractTailOfTheFirstWord(StringBuilder equalPart) {
        int wordEndIndex = equalPart.indexOf(" ");
        String wordTail;
        if (wordEndIndex >= 0) {
            wordTail = equalPart.substring(0, wordEndIndex);
            equalPart.replace(0, wordEndIndex, "");
        } else {
            wordTail = equalPart.toString();
            equalPart.setLength(0);
        }
        return wordTail;
    }

    abstract class DiffsStorageAccumulator {
        DiffsStorage storage = new DiffsStorage();

        void startNewDiff() {
        }

        abstract void doInsert(String currentText);

        abstract void doEqual(String currentText);

        abstract void doDelete(String currentText);

        abstract void doChange(String currentText, String nextText);

        abstract void doPartialChange(String originalText, String revisedText, String matchingText, String wordTail);

        void finalizeCurrentDiff() {
        }

        private int doDeleteAndGetNewIndex(List<DiffMatchPatch.Diff> diffs, int diffIndex) {
            DiffMatchPatch.Diff currentDiff = diffs.get(diffIndex);
            if (diffIndex + 1 < diffs.size()) {
                DiffMatchPatch.Diff nextDiff = diffs.get(diffIndex + 1);
                if (nextDiff.operation == DiffMatchPatch.Operation.INSERT) {
                    if (diffIndex + 2 < diffs.size()) {
                        DiffMatchPatch.Diff thirdDiff = diffs.get(diffIndex + 2);
                        if (thirdDiff.operation == DiffMatchPatch.Operation.EQUAL) {
                            if (thirdDiff.text.startsWith(" ")) {
                                doPartialChange(currentDiff.text, nextDiff.text, thirdDiff.text, "");
                            } else {
                                StringBuilder equalPart = new StringBuilder(thirdDiff.text);
                                String wordTail = extractTailOfTheFirstWord(equalPart);
                                doPartialChange(currentDiff.text, nextDiff.text, equalPart.toString(), wordTail);
                            }
                            return diffIndex + 2;
                        }
                    } else {
                        doChange(currentDiff.text, nextDiff.text);
                        return diffIndex + 1;
                    }
                }
            }
            doDelete(currentDiff.text);
            return diffIndex;
        }
    }
}
