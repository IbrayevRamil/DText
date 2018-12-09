package com.dtproject.logic.comparator;

import com.dtproject.logic.evaluators.MatchEvaluator;
import com.dtproject.logic.evaluators.StrictShingleMatchEvaluator;
import com.dtproject.logic.utils.DtStringUtils;
import difflib.DiffUtils;
import difflib.Patch;

import java.util.List;

public class StrictDocumentsComparator implements DocumentsComparator {


    @Override
    public MatchEvaluator getMatchEvaluator() {
        return new StrictShingleMatchEvaluator();
    }

    @Override
    public Patch findDifference(String original, String revised) {
        List<String> splittedOriginal = DtStringUtils.splitText(original);
        List<String> splittedRevised = DtStringUtils.splitText(revised);
        DtStringUtils.removeEmptyStrings(splittedOriginal);
        DtStringUtils.removeEmptyStrings(splittedRevised);
        alignLists(splittedOriginal, splittedRevised);
        return DiffUtils.diff(splittedOriginal, splittedRevised);
    }
}
