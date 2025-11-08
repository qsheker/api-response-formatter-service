package org.qsheker.apiresponseformating.visitor.concrete;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.qsheker.apiresponseformating.response.ErrorResponse;
import org.qsheker.apiresponseformating.response.PaginatedResponse;
import org.qsheker.apiresponseformating.response.SuccessResponse;
import org.qsheker.apiresponseformating.response.ValidationErrorResponse;
import org.qsheker.apiresponseformating.visitor.ResponseFormatterVisitor;
import org.springframework.stereotype.Component;

import static org.apache.catalina.manager.JspHelper.escapeXml;

@Component
public class XmlFormatterVisitor implements ResponseFormatterVisitor {
    private final XmlMapper xmlMapper;

    public XmlFormatterVisitor(XmlMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
    }

    @Override
    public String visit(SuccessResponse response) {
        try {
            return xmlMapper.writeValueAsString(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert SuccessResponse to XML", e);
        }
    }

    @Override
    public String visit(ErrorResponse response) {
        try {
            return xmlMapper.writeValueAsString(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert ErrorResponse to XML", e);
        }
    }

    @Override
    public String visit(PaginatedResponse response) {
        try {
            return xmlMapper.writeValueAsString(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert PaginatedResponse to XML", e);
        }
    }

    @Override
    public String visit(ValidationErrorResponse response) {
        try {
            return xmlMapper.writeValueAsString(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert ValidationErrorResponse to XML", e);
        }
    }
}
