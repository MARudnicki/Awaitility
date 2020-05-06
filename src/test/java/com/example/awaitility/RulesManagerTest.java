package com.example.awaitility;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.concurrent.TimeUnit;

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
        TimeUnit.SECONDS.sleep(11);

        //Then
        assertThat(rulesManager.fetchRules()).containsExactly(rule);

    }
}