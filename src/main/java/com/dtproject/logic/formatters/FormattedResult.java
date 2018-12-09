package com.dtproject.logic.formatters;

public class FormattedResult {

    private final DiffsStorage diffsStorage;
    private final double matchPercentage;

    public FormattedResult(DiffsStorage diffsStorage, double matchPercentage) {
        this.diffsStorage = diffsStorage;
        this.matchPercentage = matchPercentage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!diffsStorage.originalResult.isEmpty()) {
            sb.append("Match percentage: ").append(matchPercentage).append("%").append("\n");
            for (int i = 0; i < diffsStorage.originalResult.size(); i++) {
                sb.append(String.format("%-30.30s  %-30.30s\n", diffsStorage.originalResult.get(i), diffsStorage.revisedResult.get(i)));
            }
        } else {
            sb.append("Match percentage: ").append(matchPercentage).append("%").append("\n");
            diffsStorage.revisedResult.forEach(string->sb.append(string).append("\n"));
        }
        return sb.toString();
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public DiffsStorage getDiffsStorage() {
        return diffsStorage;
    }

}
