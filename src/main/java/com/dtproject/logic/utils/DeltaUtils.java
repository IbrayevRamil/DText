package com.dtproject.logic.utils;

import com.dtproject.logic.comparator.ComparedStrings;
import difflib.Delta;

import java.util.List;

public class DeltaUtils {

    private DeltaUtils() {
    }

    public static int getDeltaSize(Delta delta) {
        if (delta.getType() == Delta.TYPE.INSERT) {
            return delta.getRevised().size();
        }
        return delta.getOriginal().size();
    }

    public static ComparedStrings setDeltaLines(Delta delta, int index) {
        List<?> originalDeltas = delta.getOriginal().getLines();
        List<?> revisedDeltas = delta.getRevised().getLines();
        switch (delta.getType()) {
            case INSERT:
                return new ComparedStrings("", revisedDeltas.get(index).toString());
            case DELETE:
                return new ComparedStrings(originalDeltas.get(index).toString(), "");
            case CHANGE:
                return new ComparedStrings(originalDeltas.get(index).toString(), revisedDeltas.get(index).toString());
        }
        return new ComparedStrings("", "");
    }

}
