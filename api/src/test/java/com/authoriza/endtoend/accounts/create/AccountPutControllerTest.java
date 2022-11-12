package com.authoriza.endtoend.accounts.create;

import com.authoriza.account.infrastructure.entrypoint.api.spring.AccountPutController;
import com.authoriza.endtoend.ApiTestCase;
import com.authoriza.endtoend.RequestResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


@WebMvcTest(AccountPutController.class)
public class AccountPutControllerTest extends ApiTestCase {
    private static String URI = "/api/v0/identity-access/accounts";
    @Test
    public void itShouldCreateANewAccount() throws Exception {
        PutRequestBuilder parameters = PutRequestBuilder.random();

        RequestResult result = put(URI, parameters.build());

        result.assertCreated();
        result.assertLocation(String.format("/accounts/%s", parameters.getId()));
    }

    @Test
    public void it_should_fail_creating_a_new_account_if_id_is_not_provided() throws Exception {
        PutRequestBuilder parameters = new PutRequestBuilder().withEmail().withPassword();

        RequestResult result = put(URI, parameters.build());

        result.assertBadRequest();
    }

}
