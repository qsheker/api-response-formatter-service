package org.qsheker.apiresponseformating.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.qsheker.apiresponseformating.visitor.ApiResponse;
import org.qsheker.apiresponseformating.visitor.ResponseFormatterVisitor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse implements ApiResponse {
    private String message;
    private Map<String, String> errors;
    private String timestamp;
    private String path;

    @Override
    public String accept(ResponseFormatterVisitor visitor) {
        return visitor.visit(this);
    }
}
