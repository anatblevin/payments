package com.example.payments.graphql;

import graphql.GraphQLError;

import java.util.List;

public class GraphQLResponse<T> {

    private T data;
    private List<GraphQLError> errors;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<GraphQLError> getErrors() {
        return errors;
    }

    public void setErrors(List<GraphQLError> errors) {
        this.errors = errors;
    }
}
