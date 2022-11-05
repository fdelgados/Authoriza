package com.authoriza.identityaccess.account.application.create;

import com.authoriza.identityaccess.account.domain.model.AccountId;
import com.authoriza.identityaccess.account.domain.model.UserEmail;
import com.authoriza.shared.domain.bus.command.CommandHandler;
import com.authoriza.shared.domain.service.Service;

@Service
public class CreateAccountCommandHandler implements CommandHandler<CreateAccountCommand> {
    private final AccountCreator accountCreator;

    public CreateAccountCommandHandler(AccountCreator accountCreator) {
        this.accountCreator = accountCreator;
    }

    @Override
    public void handle(CreateAccountCommand command) {
        AccountId accountId = new AccountId(command.id());
        UserEmail adminEmail = new UserEmail(command.email());

        accountCreator.create(accountId, adminEmail, command.password());
    }
}
