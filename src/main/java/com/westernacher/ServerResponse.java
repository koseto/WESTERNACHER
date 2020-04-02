package com.westernacher;

public class ServerResponse<T> {

    private Boolean success;
    private String error;
    private T result;

    public ServerResponse(Boolean success, String error, T result) {

        this.success = success;
        this.error = error;
        this.result = result;
    }

    public ServerResponse(Boolean success, String error) {

        this.success = success;
        this.error = error;
    }

    public ServerResponse(Boolean success, T result) {

        this.success = success;
        this.result = result;
    }

    public ServerResponse(Boolean success) {

        this.success = success;
    }

    public Boolean getSuccess() {

        return this.success;
    }

    public String getError() {

        return this.error;
    }

    public T getResult() {

        return this.result;
    }

    public void setSuccess(Boolean success) {

        this.success = success;
    }

    public void setError(String error) {

        this.error = error;
    }

    public void setResult(T result) {

        this.result = result;
    }
}
