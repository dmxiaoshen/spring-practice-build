package com.xyz.service.demo;

import com.github.pagehelper.PageInfo;
import com.xyz.common.base.exception.LockException;
import com.xyz.common.utils.JacksonUtils;
import com.xyz.common.base.redis.RedisHelper;
import com.xyz.common.base.util.SpringContextUtil;
import com.xyz.service.demo.entity.Dict;
import com.xyz.service.demo.query.DictPagination;
import com.xyz.service.demo.query.TestTask;
import com.xyz.service.demo.service.DictService;
import com.xyz.service.demo.service.MongoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceDemoApplication.class)
public class ServiceDemoApplicationTests {

    @Autowired
    private MongoService mongoService;
    @Autowired
    private DictService dictService;

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

    @Test
    public void test(){
//        Book b = new Book();
//        b.setName("javaC");
//        b.setPrice(new BigDecimal("12.30"));
//        b.setId(IdGenUtils.uuid32());
//        mongoService.add(b);
//        System.out.println(b.getId());
        System.out.println(JacksonUtils.toJson(mongoService.get("25e2459d3fd34778a9e89afadfced9b4")));

    }

    @Test
    public void testRedisLock(){
        Dict dict = dictService.get("8ac621c4b1284c908ad68c65bf77fc96");
//        for(int i=0;i<100;i++){
//            dict.setDescription("redis lock "+i);
//            dictService.update(dict);
//        }

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                dict.setDescription("redis a");
                dictService.update(dict);
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                dict.setDescription("redis b");
                dictService.update(dict);
            }
        });
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                dict.setDescription("redis c");
                dictService.update(dict);
            }
        });
        Thread d = new Thread(new Runnable() {
            @Override
            public void run() {
                dict.setDescription("redis d");
                dictService.update(dict);
            }
        });
        Thread e = new Thread(new Runnable() {
            @Override
            public void run() {
                dict.setDescription("redis e");
                dictService.update(dict);
            }
        });
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
