package com.dtproject.logic.evaluators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StrictShingleMatchEvaluator extends AbstractShingleMatchEvaluator {
    @Override
    public List<Integer> generateShingle(String str) {
        List<String> wordsArray = Arrays.stream(str.toLowerCase().split("\n"))
                .map(this::canonize)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        int shinglesNumber = wordsArray.size() - SHINGLE_LEN;
        List<Integer> shingles = new ArrayList<>();
        //Create all shingles
        for (int i = 0; i <= shinglesNumber; i++) {
            StringBuilder shingle = new StringBuilder();

            //Create one shingle
            for (int j = 0; j < SHINGLE_LEN; j++) {
                shingle.append(wordsArray.get(i + j)).append(" ");
            }

            shingles.add(shingle.toString().hashCode());
        }
        return shingles;
    }
}
