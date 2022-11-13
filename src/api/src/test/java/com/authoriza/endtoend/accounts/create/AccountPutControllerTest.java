package com.authoriza.endtoend.accounts.create;

import com.authoriza.account.infrastructure.entrypoint.api.spring.create.AccountPutController;
import com.authoriza.endtoend.ApiTestCase;
import com.authoriza.endtoend.RequestResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;


@WebMvcTest(AccountPutController.class)
public class AccountPutControllerTest extends ApiTestCase {
    private static final String URI = "/api/v0/accounts";

    @Test
    public void it_should_create_a_new_account() throws Exception {
        PutRequestBuilder parameters = PutRequestBuilder.complete();

        RequestResult result = put(URI, parameters.build());

        result.assertCreated()
                .assertLocationContains(String.format("%s/%s", URI, parameters.getId()));
    }

    @Test
    public void it_should_fail_creating_a_new_account_if_id_is_not_provided() throws Exception {
        PutRequestBuilder parameters = new PutRequestBuilder().withEmail().withPassword();

        RequestResult result = put(URI, parameters.build());

        result.assertBadRequest()
                .assertErrorResponse(HttpStatus.BAD_REQUEST)
                .assertJsonPath("error.type", "missing_or_invalid_parameters");
    }

    @Test
    public void it_should_fail_creating_a_new_account_if_an_invalid_email_is_provided() throws Exception {

        PutRequestBuilder parameters = PutRequestBuilder
                .complete()
                .withEmail(faker.letterify("???????????????????"));

        RequestResult result = put(URI, parameters.build());

        result.assertBadRequest()
                .assertErrorResponse(HttpStatus.BAD_REQUEST)
                .assertErrorResponseMessage(String.format("Invalid user_email <%s>", parameters.getEmail()))
                .assertErrorResponseType("invalid_value");
    }
}
