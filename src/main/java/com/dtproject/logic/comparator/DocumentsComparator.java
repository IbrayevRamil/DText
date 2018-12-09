package com.dtproject.logic.comparator;

import com.dtproject.logic.evaluators.MatchEvaluator;
import com.dtproject.logic.utils.DtStringUtils;
import difflib.Patch;

import java.util.List;

public interface DocumentsComparator {

    MatchEvaluator getMatchEvaluator();

    Patch findDifference(String original, String revised);

    default Result compareDocuments(String original, String revised) {
        Patch documentPatch = findDifference(original, revised);
        return new Result(documentPatch.getDeltas(), getMatchEvaluator().evaluate(original, revised));
    }

    //Compared texts must have the same size to avoid wrong result by DiffUtils.diff method
    default void alignLists(List<String> splittedOriginal, List<String> splittedRevised) {
        DtStringUtils.alignTexts(splittedOriginal, splittedRevised);
        splittedRevised.add("eof");
        splittedOriginal.add("eof");
    }

}
