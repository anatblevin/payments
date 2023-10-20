package com.example.payments.highnote;

import com.example.payments.graphql.GraphQLResponse;
import com.example.payments.query.PingQuery;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class PingControllerTest {

    private PingController pingController;

    @Mock
    private HighnoteProperties highnoteProperties;
    @Mock
    private PingQuery pingQuery;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private Appender mockedAppender;
    @Captor
    private ArgumentCaptor<LoggingEvent> loggingEventCaptor;


    @Before
    public  void setUp() {
        Logger root = (Logger) LoggerFactory.getLogger(PingController.class);
        root.addAppender(mockedAppender);
        root.setLevel(Level.ALL);
        MockitoAnnotations.openMocks(this);
        pingController = new PingController(highnoteProperties, pingQuery, restTemplate);
    }

    @Test
    public void testPingController_succsses() throws IOException {

        String baseUrl = "https://api.us.test.highnote.com/graphql";
        String apiKey = "sk_test_9ty98inC7pG1b5AZDfRb1jwNNto9SfH61VFQtpaaQGqpL9RQUgx6CZG3Wd7y3wkwRhRAPHwhocNRFX8Wrc";
        String query = "query {\n" + "    ping\n" + "}";

        PingResponse pingResponse = new PingResponse();
        pingResponse.setPing("pong");
        GraphQLResponse<PingResponse> graphQLResponse = new GraphQLResponse<>();
        graphQLResponse.setData(pingResponse);
        ResponseEntity<GraphQLResponse<PingResponse>> response = new ResponseEntity<>(graphQLResponse, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class))).thenReturn(response);
        when(pingQuery.getQuery()).thenReturn(query);
        when(highnoteProperties.getBaseUrl()).thenReturn(baseUrl);
        when(highnoteProperties.getApiKey()).thenReturn(apiKey);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(response);

        pingController = new PingController(highnoteProperties, pingQuery, restTemplate);
        String ping = pingController.ping();

        System.out.println("Logging events: " + mockedAppender.getName());
        System.out.println("Logging events count: " + mockedAppender.getCopyOfAttachedFiltersList().size());


        verify(mockedAppender, times(1)).doAppend(loggingEventCaptor.capture());
        String loggingEvent = loggingEventCaptor.getAllValues().get(0).toString();
        Assert.assertTrue((loggingEvent).contains("[ERROR] Ident="));
    }

}