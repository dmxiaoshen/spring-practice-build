package com.xyz.spring.boot.standalone.activiti.config;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

/**
 * 用于工作流ID的重写，保证唯一性，参照 config/riskcontrol-activiti.xml
 */
public class UUIDGenerator implements IdGenerator {

	@Override
	public String getNextId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
