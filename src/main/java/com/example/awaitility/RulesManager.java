package com.example.awaitility;

import java.util.Set;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RulesManager {

    private final RulesService rulesService;

    public void addRule(int data) {
        System.out.println("Create send data to nms : " + data);

        /*
         * sophisticated processing here
         */
        rulesService.sendRule(data);

    }

    public Set<Integer> fetchRules() {
        return rulesService.getRules();
    }

}
