package com.tamnt.mockito.examples.service;

import com.tamnt.mockito.examples.service.dao.CoinAccountDAO;
import com.tamnt.mockito.examples.service.entity.CoinAccount;
import com.tamnt.mockito.examples.service.service.impl.CoinAccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by tamnt on 6/13/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CoinAccountServiceTest {

    @InjectMocks
    private CoinAccountServiceImpl coinAccountService;

    @Mock
    private CoinAccountDAO coinAccountDAO;

    @Captor
    private ArgumentCaptor<CoinAccount> coinAccountArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCoinAccountByVerify() {

        CoinAccount coinAccount = new CoinAccount();
        coinAccountService.createCoinAccount(coinAccount);

        //verify that the createCoinAccount method has been invoked
        verify(coinAccountDAO).createCoinAccount(any(CoinAccount.class));
        //the above is similar to : verify(coinAccountDAO, times(1)).createCoinAccount(any(CoinAccount.class));
        verify(coinAccountDAO, times(1)).createCoinAccount(any(CoinAccount.class));

        //verify that the updateCoinAccount method has never been  invoked
        verify(coinAccountDAO, never()).updateCoinAccount(any(CoinAccount.class));
    }

    @Test
    public void testCreateCoinAccountByArgumentCaptor() {

        // Requirement: we want to register a new CoinAccount. Every new CoinAccount
        // should be set createdDate before saving in the database.
        coinAccountService.createCoinAccount(new CoinAccount());

        // captures the argument which was passed in to save method.
        verify(coinAccountDAO).createCoinAccount(coinAccountArgumentCaptor.capture());

        // make sure createdDate is assigned by the createCoinAccount method before saving.
        assertThat(coinAccountArgumentCaptor.getValue().getCreatedDate(), is(notNullValue()));

    }

}
