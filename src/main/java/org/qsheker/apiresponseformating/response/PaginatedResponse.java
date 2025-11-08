package org.qsheker.apiresponseformating.response;

import lombok.*;
import org.qsheker.apiresponseformating.visitor.ApiResponse;
import org.qsheker.apiresponseformating.visitor.ResponseFormatterVisitor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse implements ApiResponse {
    private Object content;
    private int page;
    private int size;
    private Long totalElements;
    private Long totalPages;
    private boolean isLast;

    @Override
    public String accept(ResponseFormatterVisitor visitor) {
        return visitor.visit(this);
    }
}
