package com.pltfhs.car.common;

import com.fasterxml.jackson.annotation.JsonInclude;

public class GeneralResponse<T> {

    private String statusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long totalCount;
    private T result;

    public GeneralResponse() {
    }

    public GeneralResponse(String statusCode) {
        this.statusCode = statusCode;
    }

    public GeneralResponse(String statusCode, T result) {
        this.statusCode = statusCode;
        this.result = result;
    }

    public GeneralResponse(String statusCode, T result, Long size) {
        this.statusCode = statusCode;
        this.totalCount = size;
        this.result = result;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

}
