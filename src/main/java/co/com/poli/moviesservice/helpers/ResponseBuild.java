package co.com.poli.moviesservice.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuild {

    public Response successCreated(Object data) {
        return new Response.Builder()
                .code(HttpStatus.CREATED.value())
                .data(data)
                .build();
    }

    public Response success(Object data) {
        return new Response.Builder()
                .code(HttpStatus.OK.value())
                .data(data)
                .build();
    }

    public Response failed(Object data) {
        return new Response.Builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .data(data)
                .build();
    }

    public Response failedNotFound(Object data) {
        return new Response.Builder()
                .code(HttpStatus.NOT_FOUND.value())
                .data(data)
                .build();
    }

    public Response failedServer(Object data) {
        return new Response.Builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .data(data)
                .build();
    }

    public Response failedServerUnavailable(Object data) {
        return new Response.Builder()
                .code(HttpStatus.SERVICE_UNAVAILABLE.value())
                .data(data)
                .build();
    }
}
