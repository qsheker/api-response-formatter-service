package org.qsheker.apiresponseformating.service;

import org.qsheker.apiresponseformating.visitor.ApiResponse;
import org.qsheker.apiresponseformating.visitor.ResponseFormatterVisitor;

public interface ResponseFormatService {
    String accept(ResponseFormatterVisitor visitor, ApiResponse apiResponse);
}
