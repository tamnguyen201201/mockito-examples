package com.tamnt.mockito.examples.service;

import com.tamnt.mockito.examples.service.dao.impl.CoinAccountDAOImpl;
import com.tamnt.mockito.examples.service.entity.CoinAccount;
import com.tamnt.mockito.examples.service.service.impl.CoinAccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by tamnt on 6/13/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CoinAccountServiceV2Test {

    @InjectMocks
    private CoinAccountServiceImpl coinAccountService;

    @Spy
    private CoinAccountDAOImpl coinAccountDAOSpy;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCoinAccountBySpy() {

        coinAccountService.addCoinAccount(new CoinAccount());

        //verify that the createCoinAccount method has been invoked
        verify(coinAccountDAOSpy).addCoinAccount(any(CoinAccount.class));
        //the above is similar to : verify(coinAccountDAOSpy, times(1)).addCoinAccount(any(CoinAccount.class));
        verify(coinAccountDAOSpy, times(1)).addCoinAccount(any(CoinAccount.class));

        //verify that the updateCoinAccount method has never been  invoked
        verify(coinAccountDAOSpy, never()).updateCoinAccount(any(CoinAccount.class));
    }

}
