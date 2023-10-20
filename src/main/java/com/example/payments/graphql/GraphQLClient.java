package com.example.payments.graphql;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class GraphQLClient<T> {

    private final GraphQL graphQL;

    public GraphQLClient(GraphQLSchema schema) {
        this.graphQL = GraphQL.newGraphQL(schema).build();
    }

    public T execute(String query, Class<T> responseType) {
        ExecutionResult result = graphQL.execute(query);
        List<GraphQLError> errors = result.getErrors();
        if (!errors.isEmpty()) {
            throw new RuntimeException("GraphQL query failed: " + errors);
        }
        requireNonNull(result.getExtensions(), "GraphQL response extensions must not be null");
        int statusCode = (int) result.getExtensions().get("status");
        if (statusCode != 200) {
            throw new RuntimeException("GraphQL query failed with status code: " + statusCode);
        }
        return result.getData();//.get(responseType.getSimpleName()).get(0).getValueAs(responseType);
    }
}
