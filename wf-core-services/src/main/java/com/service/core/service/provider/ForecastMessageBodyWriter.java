package com.service.core.service.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.core.entity.Forecast;
import jakarta.ws.rs.Produces;
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
public class ForecastMessageBodyWriter implements MessageBodyWriter<Forecast> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Forecast.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Forecast customType, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Forecast customType, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(entityStream, customType);
    }
}
