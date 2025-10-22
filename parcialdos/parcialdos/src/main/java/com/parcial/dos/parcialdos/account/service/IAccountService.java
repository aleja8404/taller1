package com.parcial.dos.parcialdos.account.service;

import java.util.List;

import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;

public interface IAccountService {
    AccountResponseDTO create(AccountRequestDTO dto);
    List<AccountResponseDTO> findAll();
    AccountResponseDTO findById(Long id);
    String updateBalance(Long id, AccountRequestDTO dto);
    void delete(Long id);
    AccountOwnerBalanceDTO findByAccountNumber(String numeroCuenta);
}
