package com.voteloadbalancer.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@Getter
public class VoteLoadBalancingResponse<T> {
    private final Boolean result;
    private final HttpStatus httpStatus;
    private final String message;
    private final T data;

    public VoteLoadBalancingResponse(T data) {
        this(Boolean.TRUE, HttpStatus.OK, "success", data);
    }

    public VoteLoadBalancingResponse(HttpStatus httpStatus, T data) {
        this(Boolean.TRUE, httpStatus, "success", data);
    }

}
