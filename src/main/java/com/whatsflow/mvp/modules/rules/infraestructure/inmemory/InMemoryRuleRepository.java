package com.whatsflow.mvp.modules.rules.infraestructure.inmemory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.whatsflow.mvp.modules.rules.Rule;
import com.whatsflow.mvp.modules.rules.application.ports.RuleRepository;

public class InMemoryRuleRepository
        implements RuleRepository {

    private final Map<UUID, Rule> rules = new HashMap<>();

    @Override
    public Rule save(
            Rule rule) {

        rules.put(
                rule.getId(),
                rule);

        return rule;
    }

    @Override
    public List<Rule> findActiveRules() {

        return rules.values()
                .stream()
                .filter(Rule::getActive)
                .toList();
    }

    @Override
    public void deleteById(
            UUID id) {

        rules.remove(id);
    }
}