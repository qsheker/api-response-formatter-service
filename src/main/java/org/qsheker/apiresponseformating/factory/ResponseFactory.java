package org.qsheker.apiresponseformating.factory;

import org.qsheker.apiresponseformating.response.ErrorResponse;
import org.qsheker.apiresponseformating.response.PaginatedResponse;
import org.qsheker.apiresponseformating.response.SuccessResponse;
import org.qsheker.apiresponseformating.response.ValidationErrorResponse;
import org.qsheker.apiresponseformating.visitor.ApiResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Component
public class ResponseFactory implements ParentFactory{

    @Override
    public ApiResponse of(ResponseType type) {
        return switch (type){
            case ERROR_RESPONSE -> createErrorResponse();
            case SUCCESS_RESPONSE -> createSuccessResponse();
            case PAGINATED_RESPONSE -> createPaginatedResponse();
            case VALIDATION_ERROR_RESPONSE -> createValidationErrorResponse();
        };
    }
    private SuccessResponse createSuccessResponse() {
        return SuccessResponse.builder()
                .data(Map.of(
                        "id", 1,
                        "name", "John Doe",
                        "email", "john@example.com",
                        "createdAt", "2024-01-15T10:30:00Z"
                ))
                .message("Operation completed successfully")
                .timestamp(Instant.now().toString())
                .path("/api/users/1")
                .build();
    }

    private ErrorResponse createErrorResponse() {
        return ErrorResponse.builder()
                .error("NOT_FOUND")
                .message("Resource not found")
                .status(404)
                .timestamp(Instant.now().toString())
                .path("/api/users/999")
                .traceId(UUID.randomUUID().toString())
                .build();
    }

    private PaginatedResponse createPaginatedResponse() {
        return PaginatedResponse.builder()
                .content(List.of(
                        Map.of("id", 1, "name", "John", "email", "john@example.com"),
                        Map.of("id", 2, "name", "Jane", "email", "jane@example.com"),
                        Map.of("id", 3, "name", "Bob", "email", "bob@example.com")
                ))
                .page(0)
                .size(10)
                .totalElements(25L)
                .totalPages(3L)
                .isLast(false)
                .build();
    }

    private ValidationErrorResponse createValidationErrorResponse() {
        return ValidationErrorResponse.builder()
                .message("Validation failed")
                .errors(Map.of(
                        "email", "Must be a valid email address",
                        "password", "Password must be at least 8 characters",
                        "age", "Must be greater than 18"
                ))
                .timestamp(Instant.now().toString())
                .path("/api/users")
                .build();
    }
}
