package org.qsheker.apiresponseformating.visitor.concrete;

import com.google.gson.Gson;
import org.qsheker.apiresponseformating.response.ErrorResponse;
import org.qsheker.apiresponseformating.response.PaginatedResponse;
import org.qsheker.apiresponseformating.response.SuccessResponse;
import org.qsheker.apiresponseformating.response.ValidationErrorResponse;
import org.qsheker.apiresponseformating.visitor.ResponseFormatterVisitor;
import org.springframework.stereotype.Component;

@Component
public class JsonFormatterVisitor implements ResponseFormatterVisitor {
    private final Gson gson;

    public JsonFormatterVisitor(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String visit(SuccessResponse response) {
        String json = gson.toJson(response);
        return json;
    }

    @Override
    public String visit(ErrorResponse response) {
        String json = gson.toJson(response);
        return json;
    }

    @Override
    public String visit(PaginatedResponse response) {
        String json = gson.toJson(response);
        return json;
    }

    @Override
    public String visit(ValidationErrorResponse response) {
        String json = gson.toJson(response);
        return json;
    }
}
