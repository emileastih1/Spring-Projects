package com.ea.bugtracking.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class ApplicationNotFoundException extends RuntimeException implements GraphQLError {

	
	private Map<String, Object> extensions = new HashMap<>();
	
    public ApplicationNotFoundException(String exception) {
        super(exception);
    }
    
    public ApplicationNotFoundException(String message, Integer invalidApplicationId) {
        super(message);
        extensions.put("invalidApplicationId", invalidApplicationId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
