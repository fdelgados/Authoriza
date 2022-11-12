package com.authoriza.shared.infrastructure.entrypoint.api;

import com.authoriza.shared.domain.DomainError;
import com.authoriza.shared.domain.Utils;
import com.authoriza.shared.domain.bus.command.CommandHandlerExecutionError;
import com.authoriza.shared.domain.bus.query.QueryHandlerExecutionError;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public final class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private ApiConfig apiConfig;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        Map<String, Map<String, Object>> errorAttributes = buildErrorAttributes(
                status.value(),
                ((ServletWebRequest) request).getRequest().getRequestURI(),
                "missing_or_invalid_parameters",
                "Missing or invalid parameters",
                errors
        );

        return ResponseEntity.badRequest()
                .body(errorAttributes);
    }

    @ExceptionHandler({CommandHandlerExecutionError.class, QueryHandlerExecutionError.class})
    public void handleCommandQueryExecutionErrors(Throwable exception, HttpServletRequest request, HttpServletResponse response) {
        try {
            ApiController controller = findController(request);
            HashMap<Class<? extends DomainError>, HttpStatus> errorMapping = controller.errorMapping();

            int statusCode = statusFor(errorMapping, exception.getCause());
            String errorCode = errorCodeFor(exception.getCause());

            response(statusCode, errorCode, exception.getCause().getMessage(), response, request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void response(int statusCode, String errorCode, String message, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Map<String, Map<String, Object>> errorAttributes = buildErrorAttributes(statusCode, request.getRequestURI(), errorCode, message, null);

        response.reset();
        response.setHeader("Content-Type", "application/json");
        response.setStatus(statusCode);
        PrintWriter writer = response.getWriter();
        JSONObject payload = new JSONObject(errorAttributes);
        writer.write(payload.toString());
        writer.close();
    }

    private Map<String, Map<String, Object>> buildErrorAttributes(int statusCode, String requestUri, String errorCode, String message, @Nullable List<String> errors) {
        Map<String, Map<String, Object>> errorPayload = new HashMap<>();
        Map<String, Object> errorAttributes = new HashMap<>();

        long millis = System.currentTimeMillis();
        errorAttributes.put("timestamp", new Date(millis).toString());
        errorAttributes.put("type", errorCode);
        errorAttributes.put("message", message);
        errorAttributes.put("status", statusCode);
        errorAttributes.put("instance", requestUri);
        if (errors != null) {
            errorAttributes.put("errors", errors);
        }

        errorPayload.put("error", errorAttributes);

        return errorPayload;
    }

    private ApiController findController(HttpServletRequest request) throws Exception {
        Object handler = (
                (HandlerMethod) Objects.requireNonNull(
                        apiConfig.requestMappingHandler().getHandler(request)).getHandler()
        ).getBean();

        return (ApiController) handler;
    }

    private String errorCodeFor(Throwable error) {
        if (error instanceof DomainError) {
            return ((DomainError) error).getErrorCode();
        }

        return Utils.toSnake(error.getClass().toString());
    }

    private int statusFor(HashMap<Class<? extends DomainError>, HttpStatus> errorMapping, Throwable error) {
        return errorMapping.getOrDefault(error.getClass(), HttpStatus.INTERNAL_SERVER_ERROR).value();
    }
}
