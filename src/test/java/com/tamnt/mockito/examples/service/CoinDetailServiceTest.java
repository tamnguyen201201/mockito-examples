package com.tamnt.mockito.examples.service;

import com.tamnt.mockito.examples.service.dao.CoinDetailDAO;
import com.tamnt.mockito.examples.service.entity.CoinDetail;
import com.tamnt.mockito.examples.service.service.impl.CoinDetailServiceImpl;
import org.hibernate.StaleObjectStateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doAnswer;

/**
 * Created by tamnt on 6/13/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CoinDetailServiceTest {

    @InjectMocks
    private CoinDetailServiceImpl coinDetailService;

    @Mock
    private CoinDetailDAO coinDetailDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCoinDetail_returnsNewCoinDetail() {

        when(coinDetailDAO.create(any(CoinDetail.class))).thenReturn(new CoinDetail());

        CoinDetail customer = new CoinDetail();

        assertThat(coinDetailService.create(customer), is(notNullValue()));

    }

    @Test
    public void testAddCoinDetail_returnsNewCoinDetailWithId() {

        when(coinDetailDAO.create(any(CoinDetail.class))).thenAnswer(new Answer<CoinDetail>() {

            @Override
            public CoinDetail answer(InvocationOnMock invocationOnMock) throws Throwable {

                Object[] arguments = invocationOnMock.getArguments();
                if (arguments != null && arguments.length > 0 && arguments[0] != null){
                    CoinDetail coinDetail = (CoinDetail) arguments[0];
                    coinDetail.setCoinDetailId(1);
                    return coinDetail;
                }

                return null;
            }
        });

        CoinDetail coinDetail = new CoinDetail();

        coinDetailService.create(coinDetail);

        Assert.assertEquals(coinDetail.getCoinDetailId(), 1);
    }

    @Test(expected = RuntimeException.class)
    public void testAddCoinDetail_throwsException() {
        when(coinDetailDAO.create(any(CoinDetail.class))).thenThrow(RuntimeException.class);
        CoinDetail coinDetail = new CoinDetail();
        coinDetailService.create(coinDetail);
    }

    @Test
    public void testUpdate() {

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Object[] arguments = invocation.getArguments();
                if (arguments != null && arguments.length > 0 && arguments[0] != null){
                    CoinDetail coinDetail = (CoinDetail) arguments[0];
                    coinDetail.setCoinBalance(BigDecimal.TEN);
                }

                return null;
            }
        }).when(coinDetailDAO).updateCoinDetail(any(CoinDetail.class));

        // calling the method under test
        CoinDetail coinDetail = coinDetailService.updateCoinDetail(new CoinDetail(), BigDecimal.TEN);

        assertThat(coinDetail, is(notNullValue()));
        assertEquals(coinDetail.getCoinBalance(), BigDecimal.TEN);

    }

    @Test(expected = StaleObjectStateException.class)
    public void testUpdate_throwsException() {

        doThrow(StaleObjectStateException.class).when(coinDetailDAO).updateCoinDetail(any(CoinDetail.class));

        // calling the method under test
        CoinDetail coinDetail = coinDetailService.updateCoinDetail(new CoinDetail(), BigDecimal.ONE);

    }
}
