package com.dtproject.logic.formatters;

public class ResultFormatterFactory {

    private ResultFormatterFactory() {
    }

    public static ResultFormatter getResultFormatter(String format) {
        if ("side".equals(format)) {
            return new SideBySideResultFormatter();
        }
        return new InLineResultFormatter();
    }
}
