package io.github.angel.raa.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable{
    private static final long serialVersionUID = -5357573548391384337L;

    public Response(String message, T data, int status, LocalDateTime timestamp, String error) {
        this.message = message;
        this.data = data;
        this.status = status;
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
        this.error = error;
    }
    private String message;
    private T data;
    private int status;
    private LocalDateTime timestamp;
    private String error;

    public Response() {
        this.timestamp = LocalDateTime.now();

    }

    public static <T> Response<T> success(String message,T data) {
        return Response.<T>builder()
                .status(HttpStatus.OK.value())
                .message(message)
                .data(data)
                .build();
    }

        public static <T> Response<T> error(HttpStatus status, String error) {
        return Response.<T>builder()
                .status(status.value())
                .error(error)
                .build();
    }

    public static <T> Response<T> error(HttpStatus status, String error, String message) {
        return Response.<T>builder()
                .status(status.value())
                .error(error)
                .message(message)
                .build();
    }
}
