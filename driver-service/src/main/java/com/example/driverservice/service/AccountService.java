package com.example.driverservice.service;

import com.example.driverservice.exception.BadArgumentException;
import com.example.driverservice.exception.ResourceNotFoundException;
import com.example.driverservice.model.Account;
import com.example.driverservice.model.dto.AccountDto;
import com.example.driverservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account showBalance(Long id) {
        return accountCheck(id);
    }

    public void addRedDollar(Long id, AccountDto accountDto) {
        Account accountFromMemory = accountCheck(id);

        if (accountDto.getRedDollarBalance() < 0) {
            throw new BadArgumentException("It's a negative number");
        }

        BigDecimal redDollar = BigDecimal.valueOf(accountDto.getRedDollarBalance());

        accountFromMemory.setRedDollarBalance(accountFromMemory.getRedDollarBalance().add(redDollar));
        accountFromMemory.setGreenDollarBalance(accountFromMemory.getRedDollarBalance()
                .multiply(BigDecimal.valueOf(2.5)));
        accountFromMemory.setBlueDollarBalance(accountFromMemory.getRedDollarBalance()
                .multiply(BigDecimal.valueOf(1.5)));

        accountRepository.save(accountFromMemory);
    }

    public void subRedDollar(Long id, AccountDto accountDto) {
        Account accountFromMemory = accountCheck(id);

        if (accountDto.getRedDollarBalance() < 0) {
            throw new BadArgumentException("It's a negative number");
        }

        BigDecimal redDollar = BigDecimal.valueOf(accountDto.getRedDollarBalance());

        if (redDollar.compareTo(accountFromMemory.getRedDollarBalance()) > 0) {
            throw new BadArgumentException("It's a bigger number");
        }

        BigDecimal subtract = accountFromMemory.getRedDollarBalance().subtract(redDollar);

        accountFromMemory.setRedDollarBalance(subtract);
        accountFromMemory.setGreenDollarBalance(accountFromMemory.getRedDollarBalance()
                .multiply(BigDecimal.valueOf(2.5)));
        accountFromMemory.setBlueDollarBalance(accountFromMemory.getRedDollarBalance()
                .multiply(BigDecimal.valueOf(1.5)));

        accountRepository.save(accountFromMemory);
    }

    public void addGreenDollar(Long id, AccountDto accountDto) {
        Account accountFromMemory = accountCheck(id);

        if (accountDto.getGreenDollarBalance() < 0) {
            throw new BadArgumentException("It's a negative number");
        }

        BigDecimal greenDollar = BigDecimal.valueOf(accountDto.getGreenDollarBalance());

        accountFromMemory.setGreenDollarBalance(accountFromMemory.getBlueDollarBalance().add(greenDollar));
        accountFromMemory.setRedDollarBalance(accountFromMemory.getBlueDollarBalance()
                .multiply(BigDecimal.valueOf(0.4)));
        accountFromMemory.setBlueDollarBalance(accountFromMemory.getBlueDollarBalance()
                .multiply(BigDecimal.valueOf(0.6)));

        accountRepository.save(accountFromMemory);
    }

    public void subGreenDollar(Long id, AccountDto accountDto) {
        Account accountFromMemory = accountCheck(id);

        if (accountDto.getGreenDollarBalance() < 0) {
            throw new BadArgumentException("It's a negative number");
        }

        BigDecimal greenDollar = BigDecimal.valueOf(accountDto.getGreenDollarBalance());

        if (greenDollar.compareTo(accountFromMemory.getGreenDollarBalance()) > 0) {
            throw new BadArgumentException("It's a bigger number");
        }

        BigDecimal subtract = accountFromMemory.getGreenDollarBalance().subtract(greenDollar);

        accountFromMemory.setGreenDollarBalance(subtract);
        accountFromMemory.setRedDollarBalance(accountFromMemory.getBlueDollarBalance()
                .multiply(BigDecimal.valueOf(0.4)));
        accountFromMemory.setBlueDollarBalance(accountFromMemory.getBlueDollarBalance()
                .multiply(BigDecimal.valueOf(0.6)));

        accountRepository.save(accountFromMemory);
    }

    public void addBlueDollar(Long id, AccountDto accountDto) {
        Account accountFromMemory = accountCheck(id);

        if (accountDto.getBlueDollarBalance() < 0) {
            throw new BadArgumentException("It's a negative number");
        }

        BigDecimal blueDollar = BigDecimal.valueOf(accountDto.getBlueDollarBalance());

        accountFromMemory.setBlueDollarBalance(accountFromMemory.getBlueDollarBalance().add(blueDollar));
        accountFromMemory.setRedDollarBalance(accountFromMemory.getBlueDollarBalance()
                .multiply(BigDecimal.valueOf(0.666)));
        accountFromMemory.setGreenDollarBalance(accountFromMemory.getBlueDollarBalance()
                .multiply(BigDecimal.valueOf(1.666)));

        accountRepository.save(accountFromMemory);
    }

    public void subBlueDollar(Long id, AccountDto accountDto) {
        Account accountFromMemory = accountCheck(id);

        if (accountDto.getBlueDollarBalance() < 0) {
            throw new BadArgumentException("It's a negative number");
        }

        BigDecimal blueDollar = BigDecimal.valueOf(accountDto.getBlueDollarBalance());

        if (blueDollar.compareTo(accountFromMemory.getBlueDollarBalance()) > 0) {
            throw new BadArgumentException("It's a bigger number");
        }

        BigDecimal subtract = accountFromMemory.getBlueDollarBalance().subtract(blueDollar);

        accountFromMemory.setBlueDollarBalance(subtract);
        accountFromMemory.setRedDollarBalance(accountFromMemory.getBlueDollarBalance()
                .multiply(BigDecimal.valueOf(0.666)));
        accountFromMemory.setGreenDollarBalance(accountFromMemory.getBlueDollarBalance()
                .multiply(BigDecimal.valueOf(1.666)));

        accountRepository.save(accountFromMemory);
    }

    private Account accountCheck(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isEmpty()) {
            throw new ResourceNotFoundException("Account with this id does not exist");
        }

        return accountOptional.get();
    }
}
