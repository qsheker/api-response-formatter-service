package org.qsheker.apiresponseformating.service;

import org.qsheker.apiresponseformating.visitor.ApiResponse;
import org.qsheker.apiresponseformating.visitor.ResponseFormatterVisitor;
import org.springframework.stereotype.Service;

@Service
public class ResponseFormatServiceImpl implements ResponseFormatService{

    @Override
    public String accept(ResponseFormatterVisitor visitor, ApiResponse apiResponse) {
        return apiResponse.accept(visitor);
    }
}
