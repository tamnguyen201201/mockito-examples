package com.tamnt.mockito.examples.service.dao;


import com.tamnt.mockito.examples.service.entity.CoinAccount;

/**
 * Created by tamnt on 07/08/2016.
 */
public interface CoinAccountDAO {

    CoinAccount addCoinAccount(CoinAccount coinAccount);

    void createCoinAccount(CoinAccount coinAccount);

    CoinAccount updateCoinAccount(CoinAccount coinAccount);

}
