package com.dtproject.logic.writer;

import com.dtproject.logic.formatters.FormattingType;
import com.dtproject.logic.comparator.Result;

import java.io.IOException;

public interface Writer {

    void write(Result result) throws IOException;

    void write(Result result, FileExtension fileExtension, FormattingType formattingType) throws IOException;
}
