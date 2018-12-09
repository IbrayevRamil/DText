package com.dtproject.logic.formatters;

public enum FormattingType {

    INLINE(new InLineResultFormatter()),
    SIDE_BY_SIDE(new SideBySideResultFormatter());

    final ResultFormatter resultFormatter;

    FormattingType(ResultFormatter resultFormatter) {
        this.resultFormatter = resultFormatter;
    }

    public static FormattingType fromString(String type) {
        for (FormattingType formattingType : FormattingType.values()) {
            if (formattingType.toString().equalsIgnoreCase(type)) {
                return formattingType;
            }
        }
        return FormattingType.INLINE;
    }

    public ResultFormatter getResultFormatter() {
        return this.resultFormatter;
    }
}
