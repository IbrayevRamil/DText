package com.dtproject.logic.comparator;

import com.dtproject.logic.evaluators.DefaultShingleMatchEvaluator;
import com.dtproject.logic.evaluators.MatchEvaluator;
import com.dtproject.logic.utils.DtStringUtils;
import difflib.DiffUtils;
import difflib.Patch;

import java.util.List;

public class DefaultDocumentsComparator implements DocumentsComparator {

    @Override
    public MatchEvaluator getMatchEvaluator() {
        return new DefaultShingleMatchEvaluator();
    }

    @Override
    public Patch findDifference(String original, String revised) {
        List<String> splittedOriginal = DtStringUtils.splitText(original);
        List<String> splittedRevised = DtStringUtils.splitText(revised);
        alignLists(splittedOriginal, splittedRevised);
        return DiffUtils.diff(splittedOriginal, splittedRevised);
    }
}
