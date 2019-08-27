package com.xyz.spring.boot.xxl.job.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xyz.spring.boot.xxl.job.strategy.IStrategy;
import com.xyz.spring.boot.xxl.job.strategy.StrategyFactory;
import com.xyz.spring.boot.xxl.job.util.DingTalkUtil;
import com.xyz.spring.boot.xxl.job.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@JobHandler(value="dingTalkJobHandler")
@Component
@Slf4j
public class DingTalkJobHandler extends IJobHandler {

    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, DingTalk.");

        Map<String,Object> p = GsonUtil.toMap2(param);

        String url = (String) p.get("url");
        String name = (String) p.get("body");

        IStrategy strategy = StrategyFactory.build(name);

        if(!DingTalkUtil.send(url,strategy.buildBody())){
            return FAIL;
        }

        return SUCCESS;
    }
}
