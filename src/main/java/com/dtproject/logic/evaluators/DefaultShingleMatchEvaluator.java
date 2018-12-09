package com.dtproject.logic.evaluators;

import java.util.ArrayList;
import java.util.List;

public class DefaultShingleMatchEvaluator extends AbstractShingleMatchEvaluator {
    @Override
    public List<Integer> generateShingle(String str) {
        String canonizedStr = canonize(str.toLowerCase());
        String[] words = canonizedStr.split(" ");
        int shinglesNumber = words.length - SHINGLE_LEN;
        List<Integer> shingles = new ArrayList<>();
        //Create all shingles
        for (int i = 0; i <= shinglesNumber; i++) {
            StringBuilder shingle = new StringBuilder();
            //Create one shingle
            for (int j = 0; j < SHINGLE_LEN; j++) {
                shingle.append(words[i + j]).append(" ");
            }
            shingles.add(shingle.toString().hashCode());
        }
        return shingles;
    }
}
