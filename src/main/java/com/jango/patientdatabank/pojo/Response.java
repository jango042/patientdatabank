package com.jango.patientdatabank.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    private String message;
    private int code;
    private T data;

    public Response(ResponseBuilder<T> tResponseBuilder) {
        this.message = tResponseBuilder.message;
        this.code = tResponseBuilder.code;
        this.data = tResponseBuilder.data;
    }

    public static class ResponseBuilder<T> {
        private String message;
        private int code;
        private T data;

        public ResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }
        public ResponseBuilder<T> code(int code) {
            this.code = code;
            return this;
        }
        public ResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }


        public Response<T> build() {
            return new Response<>(this);
        }
    }
}
