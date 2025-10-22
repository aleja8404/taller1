package com.parcial.dos.parcialdos.account.service;

import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;
import com.parcial.dos.parcialdos.account.entity.Account;
import com.parcial.dos.parcialdos.account.repository.AccountRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository repository;

    private AccountResponseDTO toResponse(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setId(account.getId());
        dto.setNumeroCuenta(account.getAccountNumber());
        dto.setDueno(account.getOwnerName());
        dto.setBalanceActual(account.getBalance());
        dto.setActive(account.getActive());
        return dto;
    }

    @Override
    public AccountResponseDTO create(AccountRequestDTO dto) {
        Account account = new Account();
        account.setAccountNumber(dto.getNumeroCuenta());
        account.setOwnerName(dto.getDueno());
        account.setBalance(dto.getBalanceActual());
        account.setActive(true);
        repository.save(account);
        return toResponse(account);
    }

    @Override
    public List<AccountResponseDTO> findAll() {
        return repository
            .findAll()
            .stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO findById(Long id) {
        return repository.findById(id).map(this::toResponse).orElse(null);
    }

    @Override
    public String updateBalance(Long id, AccountRequestDTO dto) {
        Optional<Account> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return "Cuenta no encontrada";
        }
        Account account = optional.get();
        Double oldBalance = account.getBalance();
        account.setBalance(dto.getBalanceActual());
        repository.save(account);
        return (
            "La cuenta " +
            account.getAccountNumber() +
            " fue actualizada: balanceAnterior=" +
            oldBalance +
            ", balanceActual=" +
            dto.getBalanceActual()
        );
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AccountOwnerBalanceDTO findByAccountNumber(String numeroCuenta) {
        return repository
            .findByAccountNumber(numeroCuenta)
            .map(acc -> {
                AccountOwnerBalanceDTO dto = new AccountOwnerBalanceDTO();
                dto.setDueno(acc.getOwnerName());
                dto.setBalanceActual(acc.getBalance());
                return dto;
            })
            .orElse(null);
    }
}
