package org.qsheker.apiresponseformating.api;

import org.qsheker.apiresponseformating.factory.ResponseFactory;
import org.qsheker.apiresponseformating.factory.ResponseType;
import org.qsheker.apiresponseformating.service.ResponseFormatServiceImpl;
import org.qsheker.apiresponseformating.visitor.ApiResponse;
import org.qsheker.apiresponseformating.visitor.ResponseFormatterVisitor;
import org.qsheker.apiresponseformating.visitor.concrete.CsvFormatterVisitor;
import org.qsheker.apiresponseformating.visitor.concrete.FormatType;
import org.qsheker.apiresponseformating.visitor.concrete.JsonFormatterVisitor;
import org.qsheker.apiresponseformating.visitor.concrete.XmlFormatterVisitor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/format")
public class FormatAwareController {

    private final ResponseFormatServiceImpl responseFormatService;
    private final JsonFormatterVisitor jsonFormatterVisitor;
    private final CsvFormatterVisitor csvFormatterVisitor;
    private final XmlFormatterVisitor xmlFormatterVisitor;
    private final ResponseFactory responseFactory;

    public FormatAwareController(ResponseFormatServiceImpl responseFormatService, JsonFormatterVisitor jsonFormatterVisitor, CsvFormatterVisitor csvFormatterVisitor, XmlFormatterVisitor xmlFormatterVisitor, ResponseFactory responseFactory) {
        this.responseFormatService = responseFormatService;
        this.jsonFormatterVisitor = jsonFormatterVisitor;
        this.csvFormatterVisitor = csvFormatterVisitor;
        this.xmlFormatterVisitor = xmlFormatterVisitor;
        this.responseFactory = responseFactory;
    }

    @GetMapping
    public ResponseEntity<String> fetchFormat(
            @RequestParam(value = "format", defaultValue = "JSON")FormatType formatType,
            @RequestParam(value = "response", defaultValue = "SUCCESS_RESPONSE")ResponseType responseType)

    {
        var returnType = responseFactory.of(responseType);
        var typeFormat = resolve(formatType);
        String result = responseFormatService.accept(typeFormat, returnType);
        return ResponseEntity.ok()
                .header("Content-Type", getContentType(formatType))
                .body(result);
    }

    private ResponseFormatterVisitor resolve(FormatType formatType){
        return switch (formatType){
            case JSON -> jsonFormatterVisitor;
            case CSV -> csvFormatterVisitor;
            case XML -> xmlFormatterVisitor;
        };
    }
    private String getContentType(FormatType formatType) {
        return switch (formatType) {
            case JSON -> "application/json";
            case XML -> "application/xml";
            case CSV -> "text/csv";
        };
    }
}
