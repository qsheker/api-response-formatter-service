package org.qsheker.apiresponseformating.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.qsheker.apiresponseformating.visitor.ApiResponse;
import org.qsheker.apiresponseformating.visitor.ResponseFormatterVisitor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements ApiResponse {
    private String error;
    private String message;
    private int status;
    private String timestamp;
    private String path;
    private String traceId;

    @Override
    public String accept(ResponseFormatterVisitor visitor) {
        return visitor.visit(this);
    }
}
