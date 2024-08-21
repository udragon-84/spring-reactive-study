package com.weverse.voteprocessor.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@Getter
public class VoteProcessorResponse<T> {
    private final Boolean result;
    private final HttpStatus httpStatus;
    private final String message;
    private final T data;

    public VoteProcessorResponse(T data) {
        this(Boolean.TRUE, HttpStatus.OK, "success", data);
    }

    public VoteProcessorResponse(HttpStatus httpStatus, T data) {
        this(Boolean.TRUE, httpStatus, "success", data);
    }

}
