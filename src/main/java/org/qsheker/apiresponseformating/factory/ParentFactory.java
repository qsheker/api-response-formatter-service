package org.qsheker.apiresponseformating.factory;

import org.qsheker.apiresponseformating.visitor.ApiResponse;

public interface ParentFactory {
     ApiResponse of(ResponseType type);
}
