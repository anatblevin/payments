package com.example.payments.query;

import lombok.Data;

@Data
public class PingRequestBody {
    private final String query;
    public static final String BODY_TEMPLATE = "{\"query\":\"%s\"}";


    public String toJson() {
        return String.format(BODY_TEMPLATE, query);
    }

    public PingRequestBody(String query) {
        this.query = query.replace("\n", "");
    }
}
