package com.example.payments;

import com.example.payments.domain.bank.BankAccount;
import com.example.payments.domain.bank.Currency;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
public class BankAccountResolver implements GraphQLQueryResolver {

    public BankAccount bankAccount(UUID id)
    {
        log.info("Fetching bank account with id {}", id);
        return  BankAccount.builder().id(id).currency(Currency.USD).name("Bank of America").build();
    }
}
