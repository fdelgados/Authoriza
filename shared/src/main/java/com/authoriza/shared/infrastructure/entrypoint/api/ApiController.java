package com.authoriza.shared.infrastructure.entrypoint.api;

import com.authoriza.shared.domain.DomainError;
import com.authoriza.shared.domain.bus.command.Command;
import com.authoriza.shared.domain.bus.command.CommandBus;
import com.authoriza.shared.domain.bus.command.CommandHandlerExecutionError;
import com.authoriza.shared.domain.bus.query.Query;
import com.authoriza.shared.domain.bus.query.QueryBus;
import com.authoriza.shared.domain.bus.query.QueryHandlerExecutionError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

public abstract class ApiController {
    @Autowired
    private QueryBus queryBus;

    @Autowired
    private CommandBus commandBus;

    protected void dispatch(Command command) throws CommandHandlerExecutionError {
        commandBus.dispatch(command);
    }

    protected <R> R ask(Query query) throws QueryHandlerExecutionError {
        return queryBus.ask(query);
    }

    abstract public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping();
}
