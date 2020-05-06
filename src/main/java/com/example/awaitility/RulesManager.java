package com.example.awaitility;

import java.util.Set;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RulesManager {

    private final RulesService rulesService;
    private final OtherService otherService;

    public void addRule(int data) {
        System.out.println("Create send data to nms : "+data);

        /*
         * sophisticated processing here
         */
        rulesService.sendRule(data);

        otherService.doSth();
    }

    public Set<Integer> fetchRules(){
        return rulesService.getRules();
    }

    public void cleanUp(){
        rulesService.removeAllRules();
    }

}
