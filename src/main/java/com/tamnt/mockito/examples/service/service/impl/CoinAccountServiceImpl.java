package com.tamnt.mockito.examples.service.service.impl;

import com.tamnt.mockito.examples.service.dao.CoinAccountDAO;
import com.tamnt.mockito.examples.service.entity.CoinAccount;
import com.tamnt.mockito.examples.service.service.CoinAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by tamnt on 05/09/2016.
 */
@Component
@Transactional
public class CoinAccountServiceImpl implements CoinAccountService {

    @Autowired
    private CoinAccountDAO coinAccountDAO;

    @Override
    public CoinAccount addCoinAccount(CoinAccount coinAccount) {
        return coinAccountDAO.addCoinAccount(coinAccount);
    }

    @Override
    public void createCoinAccount(CoinAccount coinAccount) {
        coinAccount.setCreatedDate(new Date());
        coinAccountDAO.createCoinAccount(coinAccount);
    }

    @Override
    public CoinAccount updateCoinAccount(CoinAccount coinAccount) {
        return coinAccountDAO.updateCoinAccount(coinAccount);
    }
}
