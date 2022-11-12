package com.authoriza.account.infrastructure.entrypoint.api.spring;

import com.authoriza.account.application.create.CreateAccountCommand;
import com.authoriza.shared.domain.bus.command.CommandHandlerExecutionError;
import com.authoriza.shared.infrastructure.entrypoint.api.ApiController;
import com.authoriza.shared.infrastructure.spring.validators.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@AccountsApiPrefix
public class AccountPutController extends ApiController {
    @PutMapping(value = "/accounts")
    public ResponseEntity<String> createAccount(@Valid @RequestBody Request request) throws CommandHandlerExecutionError {

        CreateAccountCommand command = new CreateAccountCommand(request.id(), request.email(), request.password());

        dispatch(command);

        return ResponseEntity.created(URI.create(String.format("/accounts/%s", command.id()))).body(null);
    }
}

final class Request {
    @Required(message = "Parameter <id> is required")
    private String id;

    @Required(message = "Parameter <email> is required")
    private String email;

    @Required(message = "Parameter <password> is required")
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
