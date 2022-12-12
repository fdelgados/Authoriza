package com.authoriza.api.endtoend;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

@AutoConfigureMockMvc
@SqlGroup({
        @Sql(scripts = {"/database/schema.sql", "/database/data.sql"}, config = @SqlConfig(dataSource = "authoriza-api-data_source", transactionManager = "authoriza-api-transaction_manager"), executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "/database/activity/schema.sql", config = @SqlConfig(dataSource = "authoriza-activity-data_source", transactionManager = "authoriza-activity-transaction_manager"), executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
})
public abstract class ApiTestCase {
    @Autowired
    protected MockMvc mockMvc;

    protected Faker faker;

    private MockHttpServletRequestBuilder request;

    public ApiTestCase() {
        faker = new Faker();
    }

    protected RequestResult post(String uri, HashMap<String, Object> parameters) {
        try {
            this.request = MockMvcRequestBuilders.request(HttpMethod.POST, new URI(uri))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(buildPayload(parameters));

            return RequestResult.create(this.mockMvc.perform(this.request));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected RequestResult put(String uri, HashMap<String, Object> parameters) {
        try {
            this.request = MockMvcRequestBuilders.request(HttpMethod.PUT, new URI(uri))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(buildPayload(parameters));

            return RequestResult.create(this.mockMvc.perform(this.request));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String buildPayload(HashMap<String, Object> parameters) {
        JSONObject payload = new JSONObject(parameters);

        return payload.toString();
    }

    protected RequestResult get(String uri, HashMap<String, Object> filters) {
        try {
            String uriTemplate = buildUriQueryString(uri, filters);

            this.request = MockMvcRequestBuilders.request(HttpMethod.GET, uriTemplate, filters.values().toArray())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON);

            return RequestResult.create(this.mockMvc.perform(this.request));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected RequestResult get(String uri) {
        try {
            this.request = MockMvcRequestBuilders.request(HttpMethod.GET, new URI(uri))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON);

            return RequestResult.create(this.mockMvc.perform(this.request));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String buildUriQueryString(String uri, HashMap<String, Object> parameters) {
        StringBuilder uriQueryString = new StringBuilder(uri);
        ArrayList<String> paramTemplate = new ArrayList<>();

        uriQueryString.append("?");
        for (String key: parameters.keySet()) {
            StringBuilder param = new StringBuilder();
            paramTemplate.add(param.append(key).append("=").append("{").append(key).append("}").toString());
        }
        uriQueryString.append(StringUtils.join(paramTemplate));

        return uriQueryString.toString();
    }
}
