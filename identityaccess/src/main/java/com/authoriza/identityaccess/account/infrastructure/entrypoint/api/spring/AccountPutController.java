package com.authoriza.identityaccess.account.infrastructure.entrypoint.api.spring;

import com.authoriza.identityaccess.account.application.create.CreateAccountCommand;
import com.authoriza.shared.domain.DomainError;
import com.authoriza.shared.domain.bus.command.CommandHandlerExecutionError;
import com.authoriza.shared.infrastructure.entrypoint.api.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;

@RestController
@AccountsApiPrefix
public class AccountPutController extends ApiController {
    @PutMapping(value = "/accounts")
    public ResponseEntity<String> createAccount(@RequestBody Request request) throws CommandHandlerExecutionError {

        CreateAccountCommand command = new CreateAccountCommand(request.id(), request.email(), request.password());

        dispatch(command);

        return ResponseEntity.created(URI.create(String.format("/accounts/%s", command.id()))).body(null);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}

final class Request {
    private String id;
    private String email;
    private String password;

    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String email() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
