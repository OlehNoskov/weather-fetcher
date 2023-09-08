package com.service.core.service.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.core.dto.response.ForecastResponse;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ForecastMessageBodyWriter implements MessageBodyWriter<ForecastResponse> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return ForecastResponse.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(ForecastResponse customType, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException, WebApplicationException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(entityStream, customType);
    }

    @Override
    public long getSize(ForecastResponse customType, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        return -1;
    }
}
