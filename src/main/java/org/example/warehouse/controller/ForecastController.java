package org.example.warehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class ForecastController {

    @GetMapping("/forecast")
    public String forecast() {

        RestTemplate rest = new RestTemplate();

        String prompt = """
        We have warehouse sales data.

        Product: Bio Orangensaft Sonne
        Average monthly sales: 120

        Product: Mineralwasser Alpen
        Average monthly sales: 300

        Predict the sales numbers for the next 3 months.
        """;

        Map<String, Object> body = Map.of(
                "model", "llama3",
                "prompt", prompt,
                "stream", false
        );

        return rest.postForObject(
                "http://localhost:11434/api/generate",
                body,
                String.class
        );
    }
}