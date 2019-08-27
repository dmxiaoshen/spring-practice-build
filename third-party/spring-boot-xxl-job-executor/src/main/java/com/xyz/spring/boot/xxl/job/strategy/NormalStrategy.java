package com.xyz.spring.boot.xxl.job.strategy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class NormalStrategy extends AbstractStrategy{

    public NormalStrategy(String name) {
        super(name);
    }

    @Override
    public String buildBody() {
        return getName();
    }
}
