package com.tamnt.mockito.examples.service.service.impl;

import com.tamnt.mockito.examples.service.dao.CoinDetailDAO;
import com.tamnt.mockito.examples.service.entity.CoinDetail;
import com.tamnt.mockito.examples.service.service.CoinDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by tamnt on 05/09/2016.
 */
@Component
@Transactional
public class CoinDetailServiceImpl implements CoinDetailService {

    @Autowired
    private CoinDetailDAO coinDetailDAO;

    @Override
    public CoinDetail create(CoinDetail coinDetail) {
        return coinDetailDAO.create(coinDetail);
    }


    @Override
    public CoinDetail updateCoinDetail(CoinDetail coinDetail, BigDecimal newCoinBalance) {
        coinDetail.setCoinBalance(newCoinBalance);
        coinDetailDAO.updateCoinDetail(coinDetail);

        return coinDetail;
    }

}
