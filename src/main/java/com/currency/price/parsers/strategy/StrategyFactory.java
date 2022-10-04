package com.currency.price.parsers.strategy;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class StrategyFactory {

    private Map<String, StrategyParser> strategyParsersMap;

    public StrategyFactory(Set<StrategyParser> strategyParsersSet){
        createStrategy(strategyParsersSet);
    }

    private void createStrategy(Set<StrategyParser> strategyParsersSet){
        strategyParsersMap = new HashMap<>();
        strategyParsersSet.forEach(strategy -> strategyParsersMap.put(strategy.getStrategyName(), strategy));
    }

    public StrategyParser getStrategy(String name){
        return Optional.ofNullable(strategyParsersMap.get(name))
                .orElse(strategyParsersMap.get("default"));
    }

}
