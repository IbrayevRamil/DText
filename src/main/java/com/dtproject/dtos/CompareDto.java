package com.dtproject.dtos;

public class CompareDto {

    private String source;
    private String target;
    private String mode;
    private String format;

    public CompareDto() {

    }

    public CompareDto(String source, String target, String mode, String format) {
        this.source = source;
        this.target = target;
        this.mode = mode;
        this.format = format;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
