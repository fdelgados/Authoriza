package com.authoriza.endtoend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RequestResult {
    private ResultActions result;

    private RequestResult(ResultActions result) {
        this.result = result;
    }

    public static RequestResult create(ResultActions result) {
        return new RequestResult(result);
    }

    public final void assertStatus(HttpStatus status) throws Exception {
        this.result.andExpect(status().is(status.value()));
    }

    public final void assertStatus(int status) throws Exception {
        assertStatus(HttpStatus.valueOf(status));
    }

    public final void assertNotFound() throws Exception {
        this.result.andExpect(status().isNotFound());
    }

    public final void assertOk() throws Exception {
        this.result.andExpect(status().isOk());
    }

    public final void assertNoContent() throws Exception {
        this.result.andExpect(status().isNoContent());
    }

    public final void assertCreated() throws  Exception {
        this.result.andExpect(status().isCreated());
    }

    public final void assertBadRequest() throws  Exception {
        this.result.andExpect(status().isBadRequest());
    }

    public final void assertLocation(String location) throws Exception {
        this.result.andExpect(header().string(HttpHeaders.LOCATION, location));
    }
}
