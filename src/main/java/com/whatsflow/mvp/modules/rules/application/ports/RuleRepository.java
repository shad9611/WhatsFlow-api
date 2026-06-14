package com.whatsflow.mvp.modules.rules.application.ports;

import java.util.List;
import java.util.UUID;

import com.whatsflow.mvp.modules.rules.Rule;

public interface RuleRepository {
    Rule save(Rule rule);

    List<Rule> findActiveRules();

    void deleteById(UUID id);
}
