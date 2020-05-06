package com.example.awaitility;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.SneakyThrows;

public class RulesService {

    private static final Random RANDOM = new Random();

    @Getter
    private Set<Integer> rules = ConcurrentHashMap.newKeySet();

    public void sendRule(int data) {

        /**
         * async super-complex computation
         */
        new Thread(() -> addRule(data)).start();
    }

    public void removeAllRules() {
        new Thread(this::removeAll).start();
    }

    @SneakyThrows
    private void addRule(int data) {
        TimeUnit.SECONDS.sleep(RANDOM.nextInt(10));
        rules.add(data);
        System.out.println("Rule added " + data);
    }

    @SneakyThrows
    private void removeAll() {
        TimeUnit.SECONDS.sleep(RANDOM.nextInt(5));
        rules.clear();
        System.out.println("Rules erased");
    }

}
