package org.qsheker.apiresponseformating.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

@Configuration
public class ConverterConfiguration {
    @Value("${csv.file.name}")
    private String fileName;

    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public XmlMapper xmlMapper(){
        return new XmlMapper();
    }

    @Bean
    public CSVWriter csvWriter() throws IOException {
        return new CSVWriter(new FileWriter(fileName+".csv"));
    }

    @Bean
    public StringWriter stringWriter(){
        return new StringWriter();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
