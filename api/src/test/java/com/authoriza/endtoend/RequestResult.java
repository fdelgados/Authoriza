package com.authoriza.endtoend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RequestResult {
    private ResultActions result;

    private RequestResult(ResultActions result) {
        this.result = result;
    }

    public static RequestResult create(ResultActions result) {
        return new RequestResult(result);
    }

    public final RequestResult assertStatus(HttpStatus status) throws Exception {
        this.result.andExpect(status().is(status.value()));

        return this;
    }

    public final RequestResult assertStatus(int status) throws Exception {
        assertStatus(HttpStatus.valueOf(status));

        return this;
    }

    public final RequestResult assertNotFound() throws Exception {
        this.result.andExpect(status().isNotFound());

        return this;
    }

    public final RequestResult assertOk() throws Exception {
        this.result.andExpect(status().isOk());

        return this;
    }

    public final RequestResult assertNoContent() throws Exception {
        this.result.andExpect(status().isNoContent());

        return this;
    }

    public final RequestResult assertCreated() throws  Exception {
        this.result.andExpect(status().isCreated());

        return this;
    }

    public final RequestResult assertJsonPath(String path, Object expectedValue) throws Exception {
        String completePath = "$." + path;

        this.result.andExpect(jsonPath(completePath).value(is(expectedValue)));

        return this;
    }

    public final RequestResult assertBadRequest() throws  Exception {
        this.result.andExpect(status().isBadRequest());

        return this;
    }

    public final RequestResult assertConflict() throws Exception {
        this.result.andExpect(status().isConflict());

        return this;
    }

    public final RequestResult assertLocation(String expectedLocation) throws Exception {
        this.result.andExpect(header().string(HttpHeaders.LOCATION, is(expectedLocation)));

        return this;
    }

    public final RequestResult assertLocationContains(String expectedLocation) throws Exception {
        this.result.andExpect(header().string(HttpHeaders.LOCATION, endsWith(expectedLocation)));

        return this;
    }

    public final RequestResult assertErrorResponse(HttpStatus expectedStatus) throws Exception {
        this.result.andExpect(jsonPath("$.error").isNotEmpty())
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.error.status").value(expectedStatus.value()))
                .andExpect(jsonPath("$.error.timestamp").isNotEmpty());

        return this;
    }

    public final RequestResult assertErrorResponse() throws Exception {
        this.result.andExpect(jsonPath("$.error").isNotEmpty())
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.error.status").isNumber())
                .andExpect(jsonPath("$.error.timestamp").isNotEmpty());

        return this;
    }

    public final RequestResult assertErrorResponseMessage(String expectedMessage) throws Exception {
        this.assertJsonPath("error.message", expectedMessage);

        return this;
    }

    public final RequestResult assertErrorResponseType(String expectedType) throws Exception {
        this.assertJsonPath("error.type", expectedType);

        return this;
    }
}
