package com.example.payments.highnote;

import com.example.payments.commom.ActionName;
import com.example.payments.graphql.GraphQLResponse;
import com.example.payments.query.PingQuery;
import com.example.payments.query.PingRequestBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;


import java.io.IOException;

@RestController
@Data
@Slf4j
public class PingController {

    private final HighnoteProperties highnoteProperties;
    private final PingQuery pingQuery;
    private final RestTemplate restTemplate;

    @GetMapping("/ping")
    public String ping() {
        try {
            String url = highnoteProperties.getBaseUrl();
            String apikey = highnoteProperties.getApiKey();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBasicAuth(apikey, "");

            String query = pingQuery.getQuery();
            String body = new PingRequestBody(query).toJson();

            HttpEntity<String> request = new HttpEntity<>(body, headers);
            ResponseEntity<GraphQLResponse<PingResponse>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<GraphQLResponse<PingResponse>>() {
                    }
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                PingResponse pingResponse = response.getBody().getData();
                log.info("GraphQL response: {} - action={}", pingResponse, ActionName.HIGHNOTE_PING);
                return pingResponse.getPing();
            } else {
                throw new RuntimeException("GraphQL query failed with status code: " + response.getStatusCodeValue());
            }
        } catch (IOException e) {
            log.error("Error reading GraphQL query", e);
            return "Error reading GraphQL query";
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error executing GraphQL query", e);
            return "Error executing GraphQL query";
        } catch (ResourceAccessException e) {
            log.error("Error accessing GraphQL server", e);
            return "Error accessing GraphQL server";
        }
    }

}