package com.dtproject.logic.evaluators;

import java.util.List;

/**
 * Longest Common Subsequence (LCS)
 */
public class LcsAlgorithm {

    private LcsAlgorithm() {
    }

    // Returns length of LCS for X[0..m-1], Y[0..n-1]
    public static void findLcs(List<String> original, List<String> revised) {

        int m = original.size();
        int n = revised.size();

        int[][] L = new int[m + 1][n + 1];

        // Following steps build L[m+1][n+1] in bottom up fashion. Note
        // that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (original.get(i - 1).equals(revised.get(j - 1)))
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
            }
        }

        // Following code is used to print LCS
        int index = L[m][n];
        int temp = index;

        // Start from the right-most-bottom-most corner and
        // one by one store characters in lcs[]
        int i = m, j = n;
        while (i > 0 && j > 0) {
            // If current character in X[] and Y are same, then
            // current character is part of LCS
            if (original.get(i - 1).equals(revised.get(j - 1))) {
                // Put current character in result
                original.set(i - 1, "");
                revised.set(j - 1, "");
                // reduce values of i, j and index
                i--;
                j--;
                index--;
            }
            // If not same, then find the larger of two and
            // go in the direction of larger value
            else if (L[i - 1][j] > L[i][j - 1])
                i--;
            else
                j--;
        }
    }

}
