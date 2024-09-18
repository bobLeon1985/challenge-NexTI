package com.bce.cuentas.application.service;

import com.bce.cuentas.application.output.port.AccountRepositoryService;
import com.bce.cuentas.application.output.port.ClientService;
import com.bce.cuentas.domain.MockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {
        AccountServiceImpl.class
})
@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @MockBean
    private AccountRepositoryService accountRepositoryService;
    @MockBean
    private ClientService clientService;

    @Autowired
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void getAccountByIdTestSuccess() {
        when(accountRepositoryService.getAccountById(any()))
                .thenReturn(Mono.just(MockData.buildAccountDo()));
        StepVerifier.create(accountService.getAccountById(1L))
                .expectNextCount(1)
                .verifyComplete();
    }

}