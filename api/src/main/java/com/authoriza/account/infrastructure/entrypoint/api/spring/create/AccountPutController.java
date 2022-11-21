package com.authoriza.account.infrastructure.entrypoint.api.spring.create;

import com.authoriza.account.application.create.CreateAccountCommand;
import com.authoriza.account.domain.model.AccountAlreadyExistsException;
import com.authoriza.account.domain.model.UserAlreadyExistsException;
import com.authoriza.account.infrastructure.entrypoint.api.spring.AccountsApiPrefix;
import com.authoriza.shared.domain.DomainError;
import com.authoriza.shared.infrastructure.entrypoint.api.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;

@RestController
@AccountsApiPrefix
public class AccountPutController extends ApiController {
    @PutMapping
    public ResponseEntity<String> createAccount(@Valid @RequestBody Request request) throws Exception {
        CreateAccountCommand command = new CreateAccountCommand(request.id(), request.email(), request.password());

        dispatch(command);

        return ResponseEntity.created(baseUrl(command.id())).body(null);
    }

    public URI baseUrl(String id) {
        AccountsApiPrefix prefixAnnotation = this.getClass().getAnnotation(AccountsApiPrefix.class);
        String prefix = prefixAnnotation.prefix();

        return URI.create(String.format("%s/%s/%s", getBaseUrl(), prefix, id));
    }

    @Override
    protected HashMap<Class<? extends DomainError>, HttpStatus> customErrorMapping() {
        return new HashMap<>() {{
            put(AccountAlreadyExistsException.class, HttpStatus.CONFLICT);
            put(UserAlreadyExistsException.class, HttpStatus.CONFLICT);
        }};
    }
}
