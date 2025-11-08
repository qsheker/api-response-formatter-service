package org.qsheker.apiresponseformating.visitor;

public interface ApiResponse {
    String accept(ResponseFormatterVisitor visitor);
}