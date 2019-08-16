package com.xyz.spring.boot.standalone;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootStandaloneApplication.class)
public class SpringBootStandaloneApplicationTests {

    @Resource
    private ProcessEngine processEngine;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private ProcessEngineConfiguration processEngineConfiguration;

    @Test
    public void contextLoads() {
        repositoryService.createDeployment().addClasspathResource("diagrams/leave.bpmn20.xml.bpmn").name("请假流程").category("请假流程").deploy();
    }

}
