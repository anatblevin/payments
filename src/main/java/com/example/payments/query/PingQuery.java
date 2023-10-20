package com.example.payments.query;

import lombok.Data;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Data
public class PingQuery {

    private final ResourceLoader resourceLoader;

    public String getQuery() throws IOException {
        return StreamUtils.copyToString(
                resourceLoader.getResource("classpath:graphql/highnote/pingQuery.graphql").getInputStream(),
                StandardCharsets.UTF_8
        );
    }
}
