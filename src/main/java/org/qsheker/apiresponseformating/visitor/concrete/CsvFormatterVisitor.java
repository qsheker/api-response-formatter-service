package org.qsheker.apiresponseformating.visitor.concrete;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import org.qsheker.apiresponseformating.response.ErrorResponse;
import org.qsheker.apiresponseformating.response.PaginatedResponse;
import org.qsheker.apiresponseformating.response.SuccessResponse;
import org.qsheker.apiresponseformating.response.ValidationErrorResponse;
import org.qsheker.apiresponseformating.visitor.ResponseFormatterVisitor;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.Objects;

@Component
public class CsvFormatterVisitor implements ResponseFormatterVisitor {
    private final CSVWriter csvWriter;
    private final StringWriter stringWriter;
    private final Gson gson;

    public CsvFormatterVisitor(CSVWriter csvWriter, StringWriter stringWriter, Gson gson) {
        this.csvWriter = csvWriter;
        this.stringWriter = stringWriter;
        this.gson = gson;
    }

    @Override
    public String visit(SuccessResponse response){
        String[] header = {"data", "message", "timestamp", "path"};
        String[] values = {
                Objects.toString(response.getData(), ""),
                Objects.toString(response.getMessage(), ""),
                Objects.toString(response.getTimestamp(), ""),
                Objects.toString(response.getPath(), "")
        };

        csvWriter.writeNext(header);
        csvWriter.writeNext(values);

        try {
            csvWriter.close();
        } catch (Exception ignored) {}

        return stringWriter.toString();
    }

    @Override
    public String visit(ErrorResponse response) {
        String[] header = {"error","message","status", "timestamp", "path", "traceId"};
        String[] values = {
                Objects.toString(response.getError(), ""),
                Objects.toString(response.getMessage(), ""),
                Objects.toString(response.getStatus(), ""),
                Objects.toString(response.getTimestamp(), ""),
                Objects.toString(response.getPath(), ""),
                Objects.toString(response.getTraceId(), "")
        };

        csvWriter.writeNext(header);
        csvWriter.writeNext(values);

        try {
            csvWriter.close();
        } catch (Exception ignored) {}

        return stringWriter.toString();
    }

    @Override
    public String visit(PaginatedResponse response) {
        String[] header = {"content", "page", "size", "totalElements", "totalPages", "isLast"};
        String contentString = Objects.toString(response.getContent(), "");
        String[] values = {
                contentString,
                String.valueOf(response.getPage()),
                String.valueOf(response.getSize()),
                String.valueOf(response.getTotalElements()),
                String.valueOf(response.getTotalPages()),
                String.valueOf(response.isLast())
        };
        csvWriter.writeNext(header);
        csvWriter.writeNext(values);

        try {
            csvWriter.close();
        } catch (Exception ignored) {}

        return stringWriter.toString();
    }

    @Override
    public String visit(ValidationErrorResponse response) {
        String[] header = {"message", "errors", "timestamp", "path"};
        String errorsString;
        errorsString = gson.toJson(response.getErrors());
        String[] values = {
                Objects.toString(response.getMessage(), ""),
                errorsString,
                Objects.toString(response.getTimestamp(), ""),
                Objects.toString(response.getPath(), "")
        };

        csvWriter.writeNext(header);
        csvWriter.writeNext(values);

        try {
            csvWriter.close();
        } catch (Exception ignored) {}

        return stringWriter.toString();
    }
}
