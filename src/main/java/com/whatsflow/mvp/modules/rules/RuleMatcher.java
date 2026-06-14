package com.whatsflow.mvp.modules.rules;

import java.util.List;
import java.util.Optional;

public class RuleMatcher {
    public Optional<Rule> findMatch(
            String message,
            List<Rule> rules) {

        return rules.stream()
                .filter(Rule::getActive)
                .sorted((a, b) -> Integer.compare(
                        a.getPriority(),
                        b.getPriority()))
                .filter(rule -> message.toLowerCase()
                        .contains(
                                rule.getPattern()
                                        .toLowerCase()))
                .findFirst();
    }

}
