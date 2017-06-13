package com.tamnt.mockito.examples.service.service;


import com.tamnt.mockito.examples.service.entity.CoinAccount;

public interface CoinAccountService {

    CoinAccount addCoinAccount(CoinAccount coinAccount);

    void createCoinAccount(CoinAccount coinAccount);

    CoinAccount updateCoinAccount(CoinAccount coinAccount);
}
