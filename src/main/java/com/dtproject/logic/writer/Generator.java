package com.dtproject.logic.writer;

import com.dtproject.logic.formatters.FormattingType;
import com.dtproject.logic.comparator.Result;

import java.io.IOException;

public interface Generator {

   void generateDocument(Result result, FormattingType formattingType) throws IOException;

}
