package org.qsheker.apiresponseformating.visitor;

import org.qsheker.apiresponseformating.response.ErrorResponse;
import org.qsheker.apiresponseformating.response.PaginatedResponse;
import org.qsheker.apiresponseformating.response.SuccessResponse;
import org.qsheker.apiresponseformating.response.ValidationErrorResponse;

public interface ResponseFormatterVisitor {
    String visit(SuccessResponse response);
    String visit(ErrorResponse response);
    String visit(PaginatedResponse response);
    String visit(ValidationErrorResponse response);
}
