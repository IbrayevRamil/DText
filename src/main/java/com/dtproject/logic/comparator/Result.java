package com.dtproject.logic.comparator;

import difflib.Delta;

import java.util.List;

public class Result {

    private final List<Delta> deltas;
    private final double matchPercentage;

    public Result(List<Delta> deltas, double matchPercentage) {
        this.deltas = deltas;
        this.matchPercentage = matchPercentage;
    }

    public List<Delta> getDeltas() {
        return deltas;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Result)) {
            return false;
        }

        Result result = (Result) o;

        return result.deltas.equals(deltas) && result.matchPercentage == matchPercentage;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + deltas.hashCode();
        result = 31 * result + (int) matchPercentage;
        return result;
    }
}
