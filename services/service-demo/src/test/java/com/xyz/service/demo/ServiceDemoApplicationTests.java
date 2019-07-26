package com.xyz.service.demo;

import com.github.pagehelper.PageInfo;
import com.xyz.common.base.redis.RedisHelper;
import com.xyz.common.base.util.SpringContextUtil;
import com.xyz.service.demo.entity.Dict;
import com.xyz.service.demo.query.DictPagination;
import com.xyz.service.demo.query.TestTask;
import com.xyz.service.demo.service.DictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceDemoApplication.class)
public class ServiceDemoApplicationTests {

    @Test
    public void contextLoads() {
        RedisHelper.set("keyty","777");
        DictService dictService = SpringContextUtil.getBean(DictService.class);
        PageInfo<Dict> page = dictService.page(new DictPagination());
        System.out.println(RedisHelper.get("keyty"));
        System.out.println(page.getTotal());


            Thread a = new Thread(new TestTask());
            Thread b = new Thread(new TestTask());
            a.start();
            b.start();
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }

}
