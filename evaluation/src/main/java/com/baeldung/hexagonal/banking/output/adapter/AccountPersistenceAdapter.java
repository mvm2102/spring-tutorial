package com.baeldung.hexagonal.banking.output.adapter;

import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Component;

import com.baeldung.hexagonal.banking.domain.Account;
import com.baeldung.hexagonal.banking.output.port.LoadAccountPort;
import com.baeldung.hexagonal.banking.output.port.UpdateAccountStatePort;

@Component
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {

    private final AccountRepository accountRepository;
    private final Supplier<AccountMapperFactory> mapperFactory;

    public AccountPersistenceAdapter(AccountRepository accountRepository) {
        super();
        this.accountRepository = accountRepository;
        mapperFactory = AccountMapperFactory::new;
    }

    @Override
    public Optional<Account> loadAccount(Long accountNumber) {
        AccountJpaEntity account = accountRepository.findById(accountNumber)
            .orElseThrow(EntityNotFoundException::new);

        return Optional.of(mapperFactory.get()
            .getMapper(account.getAccountType())
            .mapToDomainEntity(account));
    }

    @Override
    public Account createOrUpdateAccount(Account account) {
        
        accountRepository.save(mapperFactory.get()
            .getMapper(account.getAccountHolder().getHolderType())
            .mapToJpaEntity(account));
        
        return account;
    }

}
