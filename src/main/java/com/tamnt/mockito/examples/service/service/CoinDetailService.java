package com.tamnt.mockito.examples.service.service;


import com.tamnt.mockito.examples.service.entity.CoinDetail;

import java.math.BigDecimal;

public interface CoinDetailService {

    CoinDetail create(CoinDetail coinDetail);

    CoinDetail updateCoinDetail(CoinDetail coinDetail, BigDecimal newCoinBalance);

}
