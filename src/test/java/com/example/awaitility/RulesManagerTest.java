package com.example.awaitility;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;

import org.awaitility.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RulesManagerTest {

    @Mock
    OtherService otherService;

    @InjectMocks
    RulesManager rulesManager = new RulesManager(new RulesService(), otherService);

    @Test
    public void create() throws Exception {
        //Given
        int rule = 123;

        //When
        rulesManager.addRule(rule);

        //Then
        await()
                .atMost(new Duration(10, TimeUnit.SECONDS))
                .pollInterval(Duration.ONE_HUNDRED_MILLISECONDS)
                .untilAsserted(() -> assertThat(rulesManager.fetchRules()).contains(rule));
    }

    @Test
    public void verify_otherServiceCall() throws Exception {
        //Given
        int rule = 123;

        //When
        rulesManager.addRule(rule);

        //Then
        await().untilAsserted(()
                -> Mockito.verify(otherService).doSth());
    }

    @Test
    public void clean() throws Exception {
        //Given
        rulesManager.addRule(1);
        rulesManager.addRule(2);
        rulesManager.addRule(3);

        await()
                .atMost(new Duration(11, TimeUnit.SECONDS))
                .untilAsserted(() -> assertThat(rulesManager.fetchRules()).hasSize(3));

        //When
        rulesManager.cleanUp();

        //Then
        await().until(() -> rulesManager.fetchRules().size() == 0);
    }

}