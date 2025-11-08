package org.qsheker.apiresponseformating.factory;

import org.qsheker.apiresponseformating.response.ErrorResponse;
import org.qsheker.apiresponseformating.response.PaginatedResponse;
import org.qsheker.apiresponseformating.response.SuccessResponse;
import org.qsheker.apiresponseformating.response.ValidationErrorResponse;
import org.qsheker.apiresponseformating.visitor.ApiResponse;

public class ResponseFactory implements ParentFactory{

    @Override
    public ApiResponse of(ResponseType type) {
        return switch (type){
            case ERROR_RESPONSE -> new ErrorResponse();
            case SUCCESS_RESPONSE -> new SuccessResponse();
            case PAGINATED_RESPONSE -> new PaginatedResponse();
            case VALIDATION_ERROR_RESPONSE -> new ValidationErrorResponse();
        };
    }
}
