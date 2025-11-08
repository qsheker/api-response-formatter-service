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
public class SuccessResponse implements ApiResponse {

    private Object data;
    private String message;
    private String timestamp;
    private String path;

    @Override
    public String accept(ResponseFormatterVisitor visitor) {
        return visitor.visit(this);
    }

}
