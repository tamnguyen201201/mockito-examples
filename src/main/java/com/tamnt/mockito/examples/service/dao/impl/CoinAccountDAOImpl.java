package com.tamnt.mockito.examples.service.dao.impl;

import com.tamnt.mockito.examples.service.dao.AbstractDAO;
import com.tamnt.mockito.examples.service.dao.CoinAccountDAO;
import com.tamnt.mockito.examples.service.entity.CoinAccount;
import org.springframework.stereotype.Repository;

/**
 * Created by FRAMGIA\nguyen.hong.son on 07/08/2016.
 */
@Repository
public class CoinAccountDAOImpl extends AbstractDAO implements CoinAccountDAO {

    @Override
    public CoinAccount addCoinAccount(CoinAccount coinAccount) {
        return coinAccount;
    }

    @Override
    public void createCoinAccount(CoinAccount coinAccount) {
        super.create(coinAccount);
    }

    @Override
    public CoinAccount updateCoinAccount(CoinAccount coinAccount) {
        return super.update(coinAccount);
    }

}
