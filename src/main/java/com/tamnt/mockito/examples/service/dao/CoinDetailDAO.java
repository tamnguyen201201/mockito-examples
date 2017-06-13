package com.tamnt.mockito.examples.service.dao;


import com.tamnt.mockito.examples.service.entity.CoinDetail;

public interface CoinDetailDAO {

    CoinDetail create(CoinDetail coinDetail);

    void updateCoinDetail(CoinDetail coinDetail);

}
