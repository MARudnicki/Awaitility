package com.example.awaitility;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;

import org.awaitility.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RulesManagerTest {

    @InjectMocks
    RulesManager rulesManager = new RulesManager(new RulesService());

    @Test
    public void create() throws Exception {
        //Given
        int rule = 123;

        //When
        rulesManager.addRule(rule);

        //Then
        await()
                .atMost(new Duration(11, TimeUnit.SECONDS))
                .pollInterval(Duration.ONE_HUNDRED_MILLISECONDS)
                .untilAsserted(() -> assertThat(rulesManager.fetchRules()).contains(rule));
    }
}