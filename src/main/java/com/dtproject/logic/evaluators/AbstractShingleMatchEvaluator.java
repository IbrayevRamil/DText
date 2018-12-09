package com.dtproject.logic.evaluators;



import java.util.List;

public abstract class AbstractShingleMatchEvaluator implements MatchEvaluator {

    static final int SHINGLE_LEN = 2;

    abstract List<Integer> generateShingle(String str);

    public String canonize(String str) {
        return str.replaceAll("\n|[^a-zA-z0-9\\s]", "");
    }

    @Override
    public double evaluate(String original, String revised) {

        List<Integer> originalShingles = generateShingle(original.trim());
        List<Integer> revisedShingles = generateShingle(revised.trim());

        if (originalShingles.isEmpty() || revisedShingles.isEmpty()) {
            return 0.0;
        }

        int textShingles1Number = originalShingles.size();
        int textShingles2Number = revisedShingles.size();

        long similarShinglesNumber = originalShingles.stream().filter(revisedShingles::contains).count();

        return ((similarShinglesNumber / ((textShingles1Number + textShingles2Number) / 2.0)) * 100);
    }

}
