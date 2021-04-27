package com.demo.webflux.adapter.in.exception;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.stereotype.Component;

@Component
public class BodyWrapper {

    private final DataBufferFactory bufferFactory;
    private final ObjectMapper objectMapper;

    public BodyWrapper(final DataBufferFactory bufferFactory,
                       final ObjectMapper objectMapper) {
        this.bufferFactory = bufferFactory;
        this.objectMapper = objectMapper;
    }

    public DataBuffer wrap(ErrorDto error) {
        try {
            return this.bufferFactory.wrap(objectMapper.writeValueAsBytes(error));
        } catch (JsonProcessingException e) {
            return bufferFactory.wrap("".getBytes());
        }
    }
}
